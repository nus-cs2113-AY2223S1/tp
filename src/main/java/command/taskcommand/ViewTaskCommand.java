package command.taskcommand;

import command.Command;
import task.TaskList;

public class ViewTaskCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public ViewTaskCommand(){
    }

    @Override
    public void execute() {
        TaskList.viewTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
