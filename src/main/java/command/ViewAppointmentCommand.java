package command;

import appointment.AppointmentList;

public class ViewAppointmentCommand extends Command{

    public final String COMMAND_WORD = "view";

    public ViewAppointmentCommand(){
    }
    @Override
    public void execute(AppointmentList appointmentList) {
        AppointmentList.listAppointment();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
