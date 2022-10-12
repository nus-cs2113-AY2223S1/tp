package seedu.duke.command.exit;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;

public class ExitCommand extends Command {
    public boolean executeCommand() {
        Ui.printExitMessage();
        return true;
    }
}
