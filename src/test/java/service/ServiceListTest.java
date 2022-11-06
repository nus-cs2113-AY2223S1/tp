package service;

import command.servicecommand.AddServiceCommand;
import command.servicecommand.RemoveServiceCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceListTest {

    @Test
    void addService() {
        int numOfServices = ServiceList.services.size();
        AddServiceCommand addServiceCommand = new AddServiceCommand("Wash");
        addServiceCommand.execute();
        int numOfServicesAfterAdd = ServiceList.services.size();
        assertEquals(1, numOfServicesAfterAdd - numOfServices);

    }

    @Test
    void removeService() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Disinfect");
        addServiceCommand.execute();
        int numOfServices = ServiceList.services.size();
        RemoveServiceCommand removeServiceCommand = new RemoveServiceCommand(Service.idCounter);
        removeServiceCommand.execute();
        int numOfServicesAfterRemove = ServiceList.services.size();
        assertEquals(-1, numOfServicesAfterRemove - numOfServices);

    }

    @Test
    void removeServiceNotExist() {
        int numOfServices = ServiceList.services.size();
        RemoveServiceCommand removeServiceCommand = new RemoveServiceCommand(5999);
        removeServiceCommand.execute();
        int numOfServicesAfterRemove = ServiceList.services.size();
        assertEquals(0, numOfServicesAfterRemove - numOfServices);

    }

    void removeServiceIndexOutOfBound() {
        int numOfServices = ServiceList.services.size();
        RemoveServiceCommand removeServiceCommand = new RemoveServiceCommand(-1);
        removeServiceCommand.execute();
        int numOfServicesAfterRemove = ServiceList.services.size();
        assertEquals(0, numOfServicesAfterRemove - numOfServices);
    }

    @Test
    void addDuplicateService() {
        int numOfServices = ServiceList.services.size();
        AddServiceCommand addServiceCommand = new AddServiceCommand("Wash");
        addServiceCommand.execute();
        int numOfServicesAfterAdd = ServiceList.services.size();
        assertEquals(0, numOfServicesAfterAdd - numOfServices);

    }

    @Test
    void findServiceExist() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Prepare fruits");
        addServiceCommand.execute();
        boolean serviceExist = false;
        if (ServiceList.findService("Prepare fruits") != null){
            serviceExist = true;
        }
        assertEquals(true, serviceExist);
    }

    @Test
    void findServiceNotExist() {
        boolean serviceExist = false;
        if (ServiceList.findService("no one will add this") != null){
            serviceExist = true;
        }
        assertEquals(false, serviceExist);
    }

    @Test
    void listService(){
        int beforeList = ServiceList.services.size();
        ServiceList.listService();
        int afterList = ServiceList.services.size();
        assertEquals(beforeList, afterList);
    }

}