package command.appointmentcommand;

import appointment.AppointmentList;
import command.Command;
import task.Task;
import task.TaskList;
import ui.Ui;

public class RemoveAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "remove";
    private final int appointmentId;

    public RemoveAppointmentCommand(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    
    public void execute() {
        while (AppointmentList.findAppointment(appointmentId).tasks.size() != 0) {
            Task currTask = AppointmentList.findAppointment(appointmentId).tasks.get(0);
            TaskList.removeTask(currTask.taskId);
            Ui.showLine();
        }
        AppointmentList.removeAppointment(appointmentId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
