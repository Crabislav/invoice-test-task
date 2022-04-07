package domain;

import domain.constant.PackageName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class InvoiceTotal {

    private PackageName packageName;
    private Long extraMinutes;
    private Long extraSMS;
    private Double totalPrice;
}
