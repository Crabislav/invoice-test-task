package domain;

import domain.constant.ServiceName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Service {

    private Long id;
    private ServiceName name;
}
