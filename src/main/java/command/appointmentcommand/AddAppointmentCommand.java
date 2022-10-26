package command.appointmentcommand;

import appointment.Appointment;
import appointment.AppointmentList;
import command.Command;

public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "add";

    private final Appointment appointment;

    public AddAppointmentCommand(String petName, String appointmentDate, String service) {
        appointment = new Appointment(petName, appointmentDate, service);
    }

    @Override
    public void execute() {
        AppointmentList.addAppointment(appointment);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}