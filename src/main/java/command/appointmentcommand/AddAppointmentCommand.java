package command.appointmentcommand;

import appointment.Appointment;
import appointment.AppointmentList;
import command.Command;
import exception.DukeException;

public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "add";

    private final Appointment appointment;

    public AddAppointmentCommand(int petId, String appointmentDateStr, String service) {
        if (Appointment.checkIsFormattedDate(appointmentDateStr)){

        }
        appointment = new Appointment(petId, appointmentDate, service);
    }

    @Override
    public void execute() {
        try {
            AppointmentList.addAppointment(appointment);
        } catch (DukeException e) {
            System.out.println("Sorry, no corresponding service/pet found to add the appointment.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }


}