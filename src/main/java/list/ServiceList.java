package list;

import domain.Service;
import domain.constant.ServiceName;

import java.util.HashMap;
import java.util.Map;

import static domain.constant.ServiceName.MINUTES;
import static domain.constant.ServiceName.SMS;

public class ServiceList {

    public static final Map<ServiceName, Service> services;

    static {
        services = new HashMap<>();
        services.put(SMS, new Service(1L, SMS));
        services.put(MINUTES, new Service(2L, MINUTES));
    }

}
