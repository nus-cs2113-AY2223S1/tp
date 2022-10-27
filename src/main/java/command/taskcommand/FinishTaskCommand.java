package command.taskcommand;

import command.Command;
import exception.DukeException;
import task.TaskList;

public class FinishTaskCommand extends Command {
    public static final String COMMAND_WORD = "finish";

    private final int taskId;

    public FinishTaskCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute() {
        try {
            TaskList.finishTask(taskId);
        } catch (DukeException e) {
            System.out.println("Sorry, no corresponding appointment found related to the task.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
