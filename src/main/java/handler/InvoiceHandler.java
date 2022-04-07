package handler;

import availableData.AvailableServices;
import domain.InvoiceTotal;
import domain.Package;
import domain.PriceList;
import domain.Usage;
import lombok.NonNull;

import java.util.List;

import static domain.constant.ServiceName.MINUTES;
import static domain.constant.ServiceName.SMS;

public class InvoiceHandler {


    //about task: probably it needs an extra inputs like client id
    public InvoiceTotal calculateInvoiceTotal(@NonNull List<Usage> usage,
                                              @NonNull PriceList priceList,
                                              @NonNull Package aPackage) {
        //sms
        long extraSms = getExtraServiceUsage(
                usage,
                AvailableServices.services.get(SMS).getId(),
                aPackage.getAvailableSms()
        );
        double priceForSms = getServicePrice(extraSms, priceList.getServicePrices().get(SMS));

        //minutes
        long extraMinutes = getExtraServiceUsage(
                usage,
                AvailableServices.services.get(MINUTES).getId(),
                aPackage.getAvailableMinutes()
        );
        double priceForMinutes = getServicePrice(extraMinutes, priceList.getServicePrices().get(MINUTES));

        //total price
        double totalPrice = getTotalPrice(
                priceList.getPackagePrices().get(aPackage.getName()),
                priceForSms,
                priceForMinutes
        );

        InvoiceTotal invoiceTotal = new InvoiceTotal(aPackage.getName(), extraMinutes, extraSms, totalPrice);
        //better to use logger
        System.out.println(invoiceTotal);
        return invoiceTotal;
    }

    private long getExtraServiceUsage(@NonNull List<Usage> usage, long serviceId, long availableAmount) {
        long totalUsedAmount = usage.stream()
                .filter(aUsage -> serviceId == aUsage.getServiceId())
                .map(Usage::getUsedAmount)
                .mapToLong(Long::longValue)
                .sum();

        return totalUsedAmount - availableAmount;
    }

    private double getTotalPrice(double packagePrice, double smsPrice, double minutesPrice) {
        return packagePrice + minutesPrice + smsPrice;
    }

    private double getServicePrice(long extraUsageAmount, double unitPrice) {
        return extraUsageAmount < 0
                ? 0.0d
                : extraUsageAmount * unitPrice;
    }

}
