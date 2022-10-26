package command.taskcommand;

import command.Command;
import exception.DukeException;
import task.Task;
import task.TaskList;

public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add";

    private final Task task;

    public AddTaskCommand(int appointmentId, int employeeId, String taskDescription) {
        this.task = new Task(appointmentId, employeeId, taskDescription);
    }

    @Override
    public void execute() {
        try {
            TaskList.addTask(task);
        } catch (DukeException e) {
            System.out.println("Sorry, no corresponding appointment/employee found to add the task.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
