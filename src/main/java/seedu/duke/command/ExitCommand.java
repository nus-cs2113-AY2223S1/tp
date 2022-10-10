package seedu.duke.command;

import seedu.duke.Ui;

public class ExitCommand extends Command {
    public boolean executeCommand() {
        Ui.exit();
        return true;
    }
}
