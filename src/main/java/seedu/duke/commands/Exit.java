package seedu.duke.commands;

import seedu.duke.ModuleList;
import seedu.duke.UI;

public class Exit extends Command {
    public Exit() {

    }

    @Override
    public void execute(ModuleList modulelist) {
        UI.endMessage();
    }

    /**
     * Function to check if command entered by user is an exit command or not
     * @return true if the command is exit. Format: boolean
     */
    @Override
    public boolean checkExit() {
        return true;
    }
}
