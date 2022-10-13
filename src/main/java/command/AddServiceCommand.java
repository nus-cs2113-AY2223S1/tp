package command;

import appointment.AppointmentList;
import employee.EmployeeList;
import service.Service;
import service.ServiceList;

public class AddServiceCommand extends Command{

    private final Service service;

    public AddServiceCommand(String serviceDescription) {
        this.service = new Service(serviceDescription);
    }

    @Override
    public void execute(AppointmentList appointmentList, EmployeeList employeeList, ServiceList serviceList) {
        ServiceList.addService(service);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
