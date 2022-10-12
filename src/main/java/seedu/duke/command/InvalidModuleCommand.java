package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class InvalidModuleCommand extends Command {
    public InvalidModuleCommand(String[] input) {
        super(input);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return "Please enter a valid module code. Each module of study has a unique module code consisting of a "
                + "two- or three-letter prefix that generally denotes the discipline, and four digits, the first "
                + "of which indicates the level of the module (e.g., 1000 indicates a Level 1 module and 2000, a "
                + "Level 2 module).";
    }
}
