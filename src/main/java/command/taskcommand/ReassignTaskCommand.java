package command.taskcommand;

import command.Command;
import exception.DukeException;
import task.TaskList;

public class ReassignTaskCommand extends Command {

    public static final String COMMAND_WORD = "reassign";

    private final int taskId;

    private final int employeeId;

    public ReassignTaskCommand(int taskId, int employeeId) {
        this.taskId = taskId;
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        try {
            TaskList.reassignTask(taskId, employeeId);
        } catch (DukeException e) {
            System.out.println("Sorry, no corresponding task/employee found.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
