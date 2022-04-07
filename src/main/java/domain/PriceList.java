package domain;

import domain.constant.PackageName;
import domain.constant.ServiceName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class PriceList {

    private final Map<ServiceName, Double> servicePrices;
    private final Map<PackageName, Double> packagePrices;

}
