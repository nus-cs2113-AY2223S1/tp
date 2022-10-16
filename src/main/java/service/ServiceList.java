package service;

import java.util.ArrayList;

public class ServiceList {
    static ArrayList<Service> services = new ArrayList<>();

    public static void listService() {
        for (Service service : services) {
            System.out.print(service.getServiceId() + " ");
            System.out.print(service.getServiceDescription()+ " " + "\n");
        }
    }

    public static void addService(Service service) {
        services.add(service);
    }

    public static void removeService(int serviceId) {
        for (Service service : services) {
            if (service.getServiceId() == serviceId) {
                services.remove(service);
                break;
            }
        }
    }
}
