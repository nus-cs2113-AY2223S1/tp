package service;

public class Service {
    public static int id = 0;
    private final int serviceId;
    private String serviceDescription;

    public Service(String serviceDescription) {
        this.serviceId = ++id;
        this.serviceDescription = serviceDescription;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }
}
