package seedu.duke.command;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Ui;

public class ListCommandTest {
    @Test
    public void listCommand_execution_noErrors() throws YamomException {
        Ui ui = new Ui();
        State state = new State();
        new AddModuleCommand(new String[]{ "add", "cs2113" }).execute(state, ui, null);
        new ListCommand(new String[]{ "list" }).execute(state, ui, null);
    }
}
