package utils;

import domain.constant.PackageName;
import domain.constant.ServiceName;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static domain.constant.PackageName.*;

public class PriceListUtils {

    private PriceListUtils() {
    }

    public static Map<ServiceName, BigDecimal> getServicePrices() {
        Map<ServiceName, BigDecimal> price = new HashMap<>();
        price.put(ServiceName.SMS, new BigDecimal("0.3"));
        price.put(ServiceName.MINUTES, new BigDecimal("0.2"));

        return price;
    }

    public static Map<PackageName, BigDecimal> getPackagePrices() {
        Map<PackageName, BigDecimal > packagePrices = new HashMap<>();
        packagePrices.put(PACKAGE_S, new BigDecimal("5"));
        packagePrices.put(PACKAGE_M, BigDecimal.TEN);
        packagePrices.put(PACKAGE_L, new BigDecimal("20"));

        return packagePrices;
    }


}
