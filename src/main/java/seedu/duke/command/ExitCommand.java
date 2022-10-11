package seedu.duke.command;


import seedu.duke.operationlist.OperationList;

public class ExitCommand extends Command {
    @Override
    public void execute(OperationList operations, String lineInput) {
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
