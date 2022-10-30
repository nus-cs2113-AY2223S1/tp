package command.appointmentcommand;

import appointment.Appointment;
import appointment.AppointmentList;
import command.Command;
import exception.DukeException;
import java.util.Date;

public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "add";

    private final Appointment appointment;

    public AddAppointmentCommand(int petId, String appointmentDateStr, String service) {
        Date appointmentDate = Appointment.checkFormattedDate(appointmentDateStr);
        appointment = new Appointment(petId, appointmentDate, service);
    }

    @Override
    public void execute() {
        try {
            AppointmentList.addAppointment(appointment);
        } catch (DukeException e) {
            System.out.println("Add appointment failed.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }


}