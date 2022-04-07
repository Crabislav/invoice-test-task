package domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceName {
    SMS("sms"),
    MINUTES("minutes");

    private final String unitName;
}
