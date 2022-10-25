package command.taskCommand;

import command.Command;
import task.Task;
import task.TaskList;

public class AddTaskCommand extends Command {

    public final static String COMMAND_WORD = "add";

    private final Task task;

    public AddTaskCommand(int appointmentId, int employeeId, String taskDescription) {
        this.task = new Task(appointmentId, employeeId, taskDescription);
    }

    @Override
    public void execute() {
        TaskList.addTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
