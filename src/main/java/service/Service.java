package service;

public class Service {
    private int serviceId;
    private String serviceDescription;

    public Service(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }
}
