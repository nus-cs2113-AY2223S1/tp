package service;

import exception.DukeException;

import java.util.ArrayList;
import java.util.Objects;

public class ServiceList {
    static ArrayList<Service> services = new ArrayList<>();

    public static void listService() {
        System.out.println("Here are the services in your service list:");
        int counter = 0;
        for (Service service : services) {
            counter += 1;
            System.out.print(counter + ".\tID:" + service.getServiceId() + " \t");
            System.out.println(service.getServiceDescription());
        }
    }

    public static void addService(Service service) throws DukeException {
        if (findService(service.getServiceDescription()) != null) {
            throw new DukeException();
        }

        services.add(service);
        System.out.println("Got it. I've added this service:");
        System.out.println(service.getServiceDescription());
        System.out.println("Now you have " + services.size() + " services in the service list.");
    }

    public static void removeService(int serviceId) throws DukeException {
        for (Service service : services) {
            if (service.getServiceId() == serviceId) {
                System.out.println("Noted. I've removed this service:");
                System.out.println(service.getServiceDescription());
                System.out.println("Now you have " + (services.size() - 1) + " services in the service list.");
                services.remove(service);
                return;
            }
        }
        throw new DukeException();
    }

    public static Service findService(String serviceDescription) {
        for (Service service : services) {
            if (Objects.equals(service.getServiceDescription(), serviceDescription)) {
                return service;
            }
        }
        return null;
    }
}
