package command;

import appointment.AppointmentList;
import employee.EmployeeList;
import service.ServiceList;

public abstract class Command {

    public Command() {
    }
    public abstract void execute();
    public abstract boolean isExit();
}
