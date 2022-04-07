package handler;

import domain.InvoiceTotal;
import domain.Package;
import domain.PriceList;
import domain.Usage;
import list.ServiceList;
import lombok.NonNull;

import java.util.List;

import static domain.constant.ServiceName.MINUTES;
import static domain.constant.ServiceName.SMS;

public class InvoiceHandler {

    //probably it need an extra input like client id
    public InvoiceTotal calculateInvoiceTotal(@NonNull List<Usage> usage,
                                              @NonNull PriceList priceList,
                                              @NonNull Package aPackage) {
        //sms
        long smsServiceId = ServiceList.services.get(SMS).getId();
        long totalSmsUsedAmount = getTotalAmountUsed(usage, smsServiceId);
        long extraSms = getExtraUnitUsage(totalSmsUsedAmount, aPackage.getAvailableSms());

        double priceForSms = getUsedUnitsPrice(extraSms, priceList.getServicePrices().get(SMS));

        //minutes
        long minutesServiceId = ServiceList.services.get(MINUTES).getId();
        long totalMinutesUsedAmount = getTotalAmountUsed(usage, minutesServiceId);
        long extraMinutes = getExtraUnitUsage(totalMinutesUsedAmount, aPackage.getAvailableMinutes());

        double priceForMinutes = getUsedUnitsPrice(extraMinutes, priceList.getServicePrices().get(MINUTES));

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

    private long getTotalAmountUsed(@NonNull List<Usage> usage, long minutesServiceId) {
        return usage.stream()
                .filter(aUsage -> minutesServiceId == aUsage.getServiceId())
                .map(Usage::getUsedAmount)
                .mapToLong(Long::longValue)
                .sum();
    }

    private long getExtraUnitUsage(long totalUsedAmount, long availableAmount) {
        return totalUsedAmount - availableAmount;
    }

    private double getTotalPrice(double packagePrice, double smsPrice, double minutesPrice) {
        return packagePrice + minutesPrice + smsPrice;
    }

    private double getUsedUnitsPrice(long extraUsageAmount, double unitPrice) {
        return extraUsageAmount < 0
                ? 0.0d
                : extraUsageAmount * unitPrice;
    }

}
