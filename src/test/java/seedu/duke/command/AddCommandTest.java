package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.exception.MaximumTransactionCountException;
import seedu.duke.exception.MoolahException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.common.Constants.MAX_AMOUNT_VALUE;
import static seedu.duke.common.Constants.MAX_TRANSACTIONS_COUNT;

public class AddCommandTest {
    //@@author wcwy
    @Test
    public void checkTransactionCapacity_transactionListHasSpace_expectNoException()
            throws MaximumTransactionCountException {
        TransactionList transactions = new TransactionList();
        MAX_TRANSACTIONS_COUNT = 2;
        transactions.addExpense("a", 1, "a", LocalDate.of(2022, 1, 1));
        AddCommand.checkTransactionCapacity(transactions);

        MAX_TRANSACTIONS_COUNT = 1000000;
    }

    @Test
    public void checkTransactionCapacity_transactionListFullSpace_exceptionThrown()
            throws MaximumTransactionCountException {
        TransactionList transactions = new TransactionList();
        MAX_TRANSACTIONS_COUNT = 2;
        transactions.addExpense("a", 1, "a", LocalDate.of(2022, 1, 1));
        transactions.addExpense("a", 1, "a", LocalDate.of(2022, 1, 1));

        assertThrows(
            MaximumTransactionCountException.class,
            () -> AddCommand.checkTransactionCapacity(transactions)
        );

        // Reset static variable back
        MAX_TRANSACTIONS_COUNT = 1000000;

    }

    @Test
    public void execute_addValidExpense_expectedSuccessfulExpenseAddition() throws MoolahException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TransactionList transactions = new TransactionList(storage.initializeFile());
        transactions.purgeTransactions();
        AddCommand addCommand = new AddCommand("expense", "a", 1, "a", LocalDate.of(2022, 1, 1));
        addCommand.execute(transactions, ui, storage);

        Expense expense = new Expense("a", 1, "a", LocalDate.of(2022, 1, 1));

        assertEquals(transactions.getEntry(0).toString(), expense.toString());
    }

    @Test
    public void execute_addValidIncome_expectedSuccessfulIncomeAddition() throws MoolahException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TransactionList transactions = new TransactionList(storage.initializeFile());
        transactions.purgeTransactions();
        AddCommand addCommand = new AddCommand("income", "a", 1, "a", LocalDate.of(2022, 1, 1));
        addCommand.execute(transactions, ui, storage);

        Income income = new Income("a", 1, "a", LocalDate.of(2022, 1, 1));

        assertEquals(transactions.getEntry(0).toString(), income.toString());
    }

}
