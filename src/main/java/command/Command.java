package command;

import appointment.AppointmentList;
import employee.EmployeeList;

public abstract class Command {

    Command() {
    }
    public abstract void execute(AppointmentList appointmentList, EmployeeList employeeList);
    public abstract boolean isExit();
}
