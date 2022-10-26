package command.taskcommand;

import command.Command;
import task.TaskList;

public class FinishTaskCommand extends Command {
    public static final String COMMAND_WORD = "finish";

    private final int taskId;

    public FinishTaskCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute() {
        TaskList.finishTask(taskId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
