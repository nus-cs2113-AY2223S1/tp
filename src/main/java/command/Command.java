package command;

import appointment.AppointmentList;
import employee.EmployeeList;
import service.ServiceList;

public abstract class Command {

    Command() {
    }
    public abstract void execute(AppointmentList appointmentList, EmployeeList employeeList, ServiceList serviceList);
    public abstract boolean isExit();
}
