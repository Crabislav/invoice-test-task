package availableData;

import domain.Service;
import domain.constant.ServiceName;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static domain.constant.ServiceName.MINUTES;
import static domain.constant.ServiceName.SMS;

@Getter
public class AvailableServices {

    public static final Map<ServiceName, Service> services = new HashMap<>();

    static {
        services.put(SMS, new Service(1L, SMS));
        services.put(MINUTES, new Service(2L, MINUTES));
    }

}
