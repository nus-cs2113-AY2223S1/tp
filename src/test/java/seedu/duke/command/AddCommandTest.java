package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.AddTransactionInvalidDateException;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    @Test
    public void execute_EmptyDate_ExpectedException() {
        TransactionList transactions = new TransactionList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddCommand addCommand = new AddCommand("t/income c/bonus a/-1 d/ i/thank_you_boss");

        assertThrows(
            AddTransactionInvalidDateException.class,
            () -> addCommand.execute(transactions, ui, storage)
        );
    }

    @Test
    public void execute_InvalidDateFormat_ExpectedException() {
        TransactionList transactions = new TransactionList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddCommand addCommand = new AddCommand("t/income c/bonus a/-1 d/2020-01-01 i/thank_you_boss");

        assertThrows(
            AddTransactionInvalidDateException.class,
            () -> addCommand.execute(transactions, ui, storage)
        );
    }

    @Test
    public void containNumeric_IfContainsNumeric_ReturnTrue() {
        boolean testOutputContainsNumber = AddCommand.containNumeric("Food1");
        assertTrue(testOutputContainsNumber);
    }

    @Test
    public void containNumeric_IfDoesNotContainNumeric_ReturnFalse() {
        boolean testOutputWithoutNumber = AddCommand.containNumeric("Food");
        assertFalse(testOutputWithoutNumber);
    }
}
