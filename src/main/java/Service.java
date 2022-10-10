public class Service {
    private int serviceId;
    private String serviceDescription;

    public Service(String employeeName) {
        this.serviceDescription = employeeName;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }
}
