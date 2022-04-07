package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsedAmount {
    private Long usedSms;
    private Long usedMinutes;
}
