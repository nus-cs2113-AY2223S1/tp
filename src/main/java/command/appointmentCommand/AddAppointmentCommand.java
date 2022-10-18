package command.appointmentCommand;

import appointment.Appointment;
import appointment.AppointmentList;
import command.Command;
import employee.EmployeeList;
import service.Service;
import service.ServiceList;

import java.time.LocalDateTime;

public class AddAppointmentCommand extends Command {

    public final static String COMMAND_WORD = "add";

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