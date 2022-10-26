package command.appointmentcommand;

import appointment.AppointmentList;
import command.Command;

public class ViewAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public ViewAppointmentCommand() {
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
