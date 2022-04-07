package handler;

import availableData.AvailableServices;
import domain.InvoiceTotal;
import domain.Package;
import domain.PriceList;
import domain.Usage;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        BigDecimal priceForSms = getServicePrice(extraSms, priceList.getServicePrices().get(SMS));

        //minutes
        long extraMinutes = getExtraServiceUsage(
                usage,
                AvailableServices.services.get(MINUTES).getId(),
                aPackage.getAvailableMinutes()
        );
        BigDecimal priceForMinutes = getServicePrice(extraMinutes, priceList.getServicePrices().get(MINUTES));

        //total price
        BigDecimal totalPrice = getTotalPrice(
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

    private BigDecimal getTotalPrice(BigDecimal packagePrice, BigDecimal smsPrice, BigDecimal minutesPrice) {
        return packagePrice.add(smsPrice).add(minutesPrice);
    }

    private BigDecimal getServicePrice(long extraUsageAmount, BigDecimal servicePrice) {
        return extraUsageAmount < 0
                ? BigDecimal.ZERO
                : servicePrice.multiply(new BigDecimal(String.valueOf(extraUsageAmount))).setScale(1, RoundingMode.UP);
    }
}
