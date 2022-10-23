package seedu.duke.command.exit;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;

//@@author bdthanh
/**
 * A representation of a command to exit program.
 */
public class ExitCommand extends Command {
    /**
     * Executes ExitCommand.
     *
     * @return true
     */
    public boolean executeCommand() {
        Ui.printExitMessage();
        return true;
    }
}
