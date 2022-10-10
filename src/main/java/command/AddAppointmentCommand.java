package command;

import appointment.Appointment;
import appointment.AppointmentList;

import java.time.LocalDateTime;

public class AddAppointmentCommand extends Command {

    public final String COMMAND_WORD = "add";

    private final Appointment appointment;

    public AddAppointmentCommand(String petName, LocalDateTime appointmentDate, Service service){
        this.appointment = new Appointment(petName, appointmentDate, service);
    }

    @Override
    public void execute(AppointmentList appointmentList) {
        AppointmentList.addAppointment(this.appointment);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
