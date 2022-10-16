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

    @Override
    public boolean checkExit() {
        return true;
    }
}
