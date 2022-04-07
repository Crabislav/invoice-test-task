package domain;

import domain.constant.PackageName;
import domain.constant.ServiceName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@AllArgsConstructor
public class PriceList {

    private final Map<ServiceName, BigDecimal> servicePrices;
    private final Map<PackageName, BigDecimal> packagePrices;

}
