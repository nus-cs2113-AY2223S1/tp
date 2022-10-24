package command.taskCommand;

import command.Command;
import task.TaskList;

public class RemoveTaskCommand extends Command {

    public final static String COMMAND_WORD = "remove";
    private final int taskId;

    public RemoveTaskCommand(int taskId) {
        this.taskId = taskId;
    }
    @Override
    public void execute() {
        TaskList.removeTask(taskId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
