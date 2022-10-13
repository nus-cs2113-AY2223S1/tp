package command;

import appointment.Appointment;
import appointment.AppointmentList;
import employee.EmployeeList;
import service.Service;
import service.ServiceList;

import java.time.LocalDateTime;

public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "add";

    private final Appointment appointment;

    // public AddAppointmentCommand(String petName, String appointmentDate, String serviceName){
    public AddAppointmentCommand(String petName, String appointmentDate){
        // Service service = new Service(serviceName);
        this.appointment = new Appointment(petName, appointmentDate);
    }

    @Override
    public void execute() {
        AppointmentList.addAppointment(this.appointment);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}