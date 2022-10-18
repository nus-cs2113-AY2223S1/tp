package command.appointmentCommand;

import appointment.AppointmentList;
import command.Command;
import employee.EmployeeList;
import service.ServiceList;

public class RemoveAppointmentCommand extends Command {

    public final static String COMMAND_WORD = "remove";
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
