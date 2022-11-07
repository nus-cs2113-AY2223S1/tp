package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.Budget;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StorageWriteErrorException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetCommandTest {
    //@@author wcwy
    @Test
    public void execute_setValidBudget_expectedSuccessfulBudgetSet() throws MoolahException {
        try {
            TransactionList transactions = new TransactionList();
            Ui ui = new Ui();
            Storage storage = new Storage();

            BudgetCommand budgetCommand = new BudgetCommand();
            budgetCommand.execute(transactions, ui, storage);
            assertEquals(Budget.getBudget(), 1000);

            budgetCommand.setBudgetAmount(100000);
            budgetCommand.execute(transactions, ui, storage);
            assertEquals(Budget.getBudget(), 100000);

            budgetCommand.setBudgetAmount(Long.MAX_VALUE);
            budgetCommand.execute(transactions, ui, storage);
            assertEquals(Budget.getBudget(), Long.MAX_VALUE);

            // Reset budget back to default
            budgetCommand.setBudgetAmount(1000);
            budgetCommand.execute(transactions, ui, storage);
        } catch (StorageWriteErrorException ignore) {
            // Ignores exception to prevent printing of error message
        }
    }
}
