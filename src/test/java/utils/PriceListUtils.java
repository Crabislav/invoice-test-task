package utils;

import domain.constant.PackageName;
import domain.constant.ServiceName;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static domain.constant.PackageName.*;

public class PriceListUtils {

    private PriceListUtils() {
    }

    public static Map<ServiceName, Double> getServicePrices() {
        Map<ServiceName, Double> price = new HashMap<>();
        price.put(ServiceName.SMS, 0.3d);
        price.put(ServiceName.MINUTES, 0.2d);

        return price;
    }

    public static Map<PackageName, Double> getPackagePrices() {
        Map<PackageName, Double> packagePrices = new HashMap<>();
        packagePrices.put(PACKAGE_S, 5.0d);
        packagePrices.put(PACKAGE_M, 10.0d);
        packagePrices.put(PACKAGE_L, 20.0d);

        return packagePrices;
    }


}
