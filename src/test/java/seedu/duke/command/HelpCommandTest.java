package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class HelpCommandTest {
    TransactionList transactions = new TransactionList();
    Ui ui = new Ui();
    Storage storage = new Storage();

    @Test
    public void execute_getBasicHelpForAddCommand_expectedNoErrorCaught() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setQueryCommand("Add");
        assertDoesNotThrow(
            () -> helpCommand.execute(transactions, ui, storage)
        );
    }

    @Test
    public void execute_getBasicHelpForAllCommands_expectedNoErrorCaught() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.isDetailed(true);
        assertDoesNotThrow(
            () -> helpCommand.execute(transactions, ui, storage)
        );
    }

    @Test
    public void execute_getDetailedHelpForAllCommands_expectedNoErrorCaught() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.isDetailed(false);
        helpCommand.setQueryCommand("Add");
        assertDoesNotThrow(
            () -> helpCommand.execute(transactions, ui, storage)
        );
    }
}
