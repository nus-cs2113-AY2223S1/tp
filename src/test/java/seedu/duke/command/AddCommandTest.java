package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.AddTransactionInvalidAmountException;
import seedu.duke.exception.InputTransactionInvalidCategoryException;
import seedu.duke.exception.InputTransactionInvalidDateException;
import seedu.duke.exception.AddTransactionMissingParameterException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {


    @Test
    public void execute_InvalidCategory_ExpectedException() {
        TransactionList transactions = new TransactionList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddCommand addCommand = new AddCommand("t/expense c/f0od a/20 d/13092022 i/yummy");
        assertThrows(InputTransactionInvalidCategoryException.class, () -> addCommand.execute(transactions,
                ui, storage));
    }


    @Test
    public void execute_InvalidNegativeAmount_ExpectedException() {
        TransactionList transactions = new TransactionList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddCommand addCommand = new AddCommand("t/expense c/food a/-20 d/13092022 i/isThisAnIncome");
        assertThrows(AddTransactionInvalidAmountException.class, () -> addCommand.execute(transactions, ui, storage));
    }

    @Test
    public void execute_InvalidAmountInput_ExpectedException() {
        TransactionList transactions = new TransactionList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddCommand addCommand = new AddCommand("t/income c/bonus a/one_dollar d/13092022 i/thank_you_boss");
        assertThrows(AddTransactionInvalidAmountException.class, () -> addCommand.execute(transactions, ui, storage));
    }

    @Test
    public void execute_EmptyDate_ExpectedException() {
        TransactionList transactions = new TransactionList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddCommand addCommand = new AddCommand("t/income c/bonus a/1 d/ i/thank_you_boss");

        assertThrows(AddTransactionMissingParameterException.class, () -> addCommand.execute(transactions,
                ui, storage));
    }

    @Test
    public void execute_InvalidDateFormat_ExpectedException() {
        TransactionList transactions = new TransactionList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddCommand addCommand = new AddCommand("t/income c/bonus a/1 d/2020-01-01 i/thank_you_boss");

        assertThrows(InputTransactionInvalidDateException.class, () -> addCommand.execute(transactions, ui, storage));
    }
}
