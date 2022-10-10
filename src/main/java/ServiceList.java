import java.util.ArrayList;

public class ServiceList {
    static ArrayList<Service> services = new ArrayList<>();

    public static void listService() {
        for (Service service : services) {
            System.out.print(service.getServiceId() + " ");
            System.out.print(service.getServiceDescription()+ " ");
        }
    }

    public static void addService(Service service) {
        services.add(service);
    }

    public static void removeEmployee(int employeeId) {
        for (Service service : services) {
            if (service.getServiceId() == employeeId) {
                services.remove(service);
                break;
            }
        }
    }
}
