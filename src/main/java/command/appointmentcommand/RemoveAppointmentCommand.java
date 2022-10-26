package command.appointmentcommand;

import appointment.AppointmentList;
import command.Command;

public class RemoveAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "remove";
    private final int appointmentId;

    public RemoveAppointmentCommand(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public void execute() {
        AppointmentList.removeAppointment(appointmentId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
