package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Usage {
    private Long clientId;
    private Long serviceId;
    private Long usedAmount;
}
