package command.taskCommand;

import command.Command;
import task.TaskList;

public class ViewTaskCommand extends Command {

    public final static String COMMAND_WORD = "view";

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
