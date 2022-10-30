package seedu.duke.command;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.Ui;

public class HelpCommandTest {
    @Test
    public void helpCommand_execution_noErrors() throws YamomException {
        Ui ui = new Ui();
        new HelpCommand(new String[]{"help"}).execute(null, ui, null);
    }
}
