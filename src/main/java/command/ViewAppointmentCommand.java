package command;

import appointment.AppointmentList;
import employee.EmployeeList;
import service.ServiceList;

public class ViewAppointmentCommand extends Command{

    public final static String COMMAND_WORD = "view";

    public ViewAppointmentCommand(){
    }
    @Override
    public void execute() {
        AppointmentList.listAppointment();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
