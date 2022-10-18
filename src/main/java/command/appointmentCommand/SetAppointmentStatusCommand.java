package command.appointmentCommand;

import appointment.AppointmentList;
import command.Command;

public class SetAppointmentStatusCommand extends Command {
    public final static String COMMAND_WORD = "status";

    private final int appointmentId;
    private final int appointmentStatus;

    public SetAppointmentStatusCommand(int appointmentId, int appointmentStatus) {
        this.appointmentId = appointmentId;
        this.appointmentStatus = appointmentStatus;
    }
    @Override
    public void execute() {
        AppointmentList.setAppointmentStatus(appointmentId, appointmentStatus);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
