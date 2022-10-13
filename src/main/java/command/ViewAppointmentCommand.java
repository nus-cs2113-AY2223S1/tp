package command;

import appointment.AppointmentList;
import employee.EmployeeList;

public class ViewAppointmentCommand extends Command{

    public final String COMMAND_WORD = "view";

    public ViewAppointmentCommand(){
    }
    @Override
    public void execute(AppointmentList appointmentList, EmployeeList employeeList) {
        AppointmentList.listAppointment();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
