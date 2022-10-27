package service;

import command.petcommand.RemovePetCommand;
import command.servicecommand.AddServiceCommand;
import command.servicecommand.RemoveServiceCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceListTest {

    @Test
    void listService() {
    }

    @Test
    void addService() {
        int numOfServices = ServiceList.services.size();
        AddServiceCommand addServiceCommand = new AddServiceCommand("Trim");
        addServiceCommand.execute();
        int numOfServicesAfterAdd = ServiceList.services.size();
        assertEquals(1, numOfServicesAfterAdd - numOfServices);

    }

    @Test
    void removeService() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Trim");
        addServiceCommand.execute();
        int numOfServices = ServiceList.services.size();
        RemoveServiceCommand removeServiceCommand = new RemoveServiceCommand(Service.id);
        removeServiceCommand.execute();
        int numOfServicesAfterRemove = ServiceList.services.size();
        assertEquals(-1, numOfServicesAfterRemove - numOfServices);

    }
}