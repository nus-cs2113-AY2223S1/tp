package service;

public class Service {
    public static int idCounter = 5000;
    private final int serviceId;
    private String serviceDescription;

    public Service(String serviceDescription) {
        this.serviceId = ++idCounter;
        this.serviceDescription = serviceDescription;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }
}
