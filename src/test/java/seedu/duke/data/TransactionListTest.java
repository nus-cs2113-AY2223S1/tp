package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.InputTransactionInvalidTypeException;
import seedu.duke.exception.StatsInvalidTypeException;
import seedu.duke.parser.ParameterParser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TransactionListTest {
    //@@author wcwy

    @Test
    public void addExpense_addValidExpense_expectedSuccessfulExpenseAddition() {
        TransactionList transactions = new TransactionList();
        transactions.addExpense("a", 1, "a", LocalDate.of(2022, 1, 1));
        Expense expense = new Expense("a", 1, "a", LocalDate.of(2022, 1, 1));
        assertEquals(transactions.getEntry(0).toString(), expense.toString());

        transactions.addExpense("b", 2, "b", LocalDate.of(2222, 2, 2));
        expense = new Expense("b", 2, "b", LocalDate.of(2222, 2, 2));
        assertEquals(transactions.getEntry(1).toString(), expense.toString());
    }

    @Test
    public void addIncome_addValidIncome_expectedSuccessfulIncomeAddition() {
        TransactionList transactions = new TransactionList();
        transactions.addIncome("a", 1, "a", LocalDate.of(2022, 1, 1));
        Income income = new Income("a", 1, "a", LocalDate.of(2022, 1, 1));
        assertEquals(transactions.getEntry(0).toString(), income.toString());

        transactions.addIncome("b", 2, "b", LocalDate.of(2222, 2, 2));
        income = new Income("b", 2, "b", LocalDate.of(2222, 2, 2));
        assertEquals(transactions.getEntry(1).toString(), income.toString());
    }

    @Test
    public void calculateMonthlyTotalExpense_emptyTransactionList_expectedZero() {
        TransactionList transactions = new TransactionList();
        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2022, 1, 1)), 0);
    }

    @Test
    public void calculateMonthlyTotalExpense_noExpenseOnGivenMonth_expectedZero() {
        TransactionList transactions = new TransactionList();
        transactions.addExpense("a", 1, "a", LocalDate.of(2022, 1, 1));
        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2022, 2, 1)), 0);
    }

    @Test
    public void calculateMonthlyTotalExpense_multipleDateDifferentMonth_expectedCorrectAmountFromSameMonth() {
        TransactionList transactions = new TransactionList();
        transactions.addExpense("a", 1, "a", LocalDate.of(2022, 1, 1));
        transactions.addExpense("a", 2, "a", LocalDate.of(2022, 2, 1));
        transactions.addExpense("a", 3, "a", LocalDate.of(2022, 3, 1));
        transactions.addExpense("a", 4, "a", LocalDate.of(2022, 4, 1));
        transactions.addExpense("a", 5, "a", LocalDate.of(2022, 5, 1));

        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2022, 1, 31)), 1);
        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2022, 2, 01)), 2);
        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2022, 3, 02)), 3);
        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2022, 4, 03)), 4);
        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2022, 5, 04)), 5);
    }

    @Test
    public void calculateMonthlyTotalExpense_multipleDateDifferentYear_expectedCorrectAmountFromSameYearAndMonth() {
        TransactionList transactions = new TransactionList();
        transactions.addExpense("a", 1, "a", LocalDate.of(2001, 1, 1));
        transactions.addExpense("a", 2, "a", LocalDate.of(2002, 1, 1));
        transactions.addExpense("a", 3, "a", LocalDate.of(2003, 1, 1));
        transactions.addExpense("a", 4, "a", LocalDate.of(2004, 1, 1));
        transactions.addExpense("a", 5, "a", LocalDate.of(2005, 1, 1));

        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2001, 1, 31)), 1);
        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2002, 1, 01)), 2);
        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2003, 1, 02)), 3);
        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2004, 1, 03)), 4);
        assertEquals(transactions.calculateMonthlyTotalExpense(LocalDate.of(2005, 1, 04)), 5);
    }

    //@@author chydarren

    @Test
    public void findTransactions_matchingTransaction_expectNonEmptyTransactionList() {
        TransactionList transactions = new TransactionList();
        transactions.addExpense("buy_an_apple", 1, "fruits", LocalDate.of(2001, 1, 1));
        transactions.addIncome("sell_a_pear", 2, "fruits", LocalDate.of(2002, 1, 1));

        String transactionsList = transactions.findTransactions("sell");
        assertEquals(transactionsList, "2: [+][fruits] $2 on Jan 01 2002 | Description: sell_a_pear"
                + System.lineSeparator());
    }

    @Test
    public void findTransactions_noMatchingTransaction_expectEmptyTransactionList() {
        TransactionList transactions = new TransactionList();
        transactions.addExpense("buy_an_apple", 1, "fruits",
                LocalDate.of(2001, 1, 1));
        transactions.addIncome("sell_a_pear", 2, "fruits",
                LocalDate.of(2002, 1, 1));

        String transactionsList = transactions.findTransactions("");
        assertEquals(transactionsList, "");
    }

    @Test
    public void getSpendingHabitComment_highSavingsRate_expectHighSavingsSpendingHabitComment() {
        TransactionList transactions = new TransactionList();
        assertEquals(transactions.getSpendingHabitComment(50, 40),
                "Wow, keep up the good work. You saved at least two-third of your income.");
    }

    @Test
    public void getSpendingHabitComment_divideByZeroIncome_expectVeryLowSavingsSpendingHabitComment() {
        TransactionList transactions = new TransactionList();
        assertEquals(transactions.getSpendingHabitComment(0, -100),
                "You spent way more than what you have earned for the current month. "
                        + "Please spend wisely based on your income.");
    }

    @Test
    public void isMatchListFilters_matchingTransactionDate_expectTrue() throws
            InputTransactionInvalidTypeException {
        TransactionList transactions = new TransactionList();
        Transaction transaction = new Income("sell_a_pear", 20, "fruits",
                LocalDate.of(2002, 1, 1));

        assertEquals(transactions.isMatchListFilters(transaction, "", "",
                LocalDate.of(2002, 1, 1)), true);

    }

    @Test
    public void isMatchListFilters_matchingTransactionType_expectTrue() throws
            InputTransactionInvalidTypeException {
        TransactionList transactions = new TransactionList();
        Transaction transaction = new Income("sell_a_pear", 20, "fruits",
                LocalDate.of(2002, 1, 1));

        assertEquals(transactions.isMatchListFilters(transaction, "seedu.duke.data.transaction.Income",
                "", null), true);
    }

    @Test
    public void isMatchListFilters_matchingTransactionCategoryAndType_expectTrue() throws
            InputTransactionInvalidTypeException {
        TransactionList transactions = new TransactionList();
        Transaction transaction = new Income("sell_a_pear", 20, "fruits",
                LocalDate.of(2002, 1, 1));

        assertEquals(transactions.isMatchListFilters(transaction, "seedu.duke.data.transaction.Income",
                "fruits", null), true);
    }

    @Test
    public void isMatchListFilters_NoMatchingTransactionDateAndCategory_expectFalse() throws
            InputTransactionInvalidTypeException {
        TransactionList transactions = new TransactionList();
        Transaction transaction = new Income("sell_a_pear", 20, "fruits",
                LocalDate.of(2002, 1, 1));

        assertEquals(transactions.isMatchListFilters(transaction, "", "veggies",
                LocalDate.of(2022, 1, 1)), false);
    }

    @Test
    public void isMatchListFilters_invalidTransactionType_exceptionThrown() {
        TransactionList transactions = new TransactionList();
        Transaction transaction = new Income("sell_a_pear", 20, "fruits",
                LocalDate.of(2002, 1, 1));

        assertThrows(
            InputTransactionInvalidTypeException.class,
            () -> transactions.isMatchListFilters(transaction, "income", "fruits", null)
        );
    }

    @Test
    public void isTransactionInstance_validTransactionInstanceAsExpense_expectTrue() throws
            ClassNotFoundException {
        TransactionList transactions = new TransactionList();
        Transaction transaction = new Expense("buy_a_bear", 80, "toys",
                LocalDate.of(2022, 10, 30));

        assertTrue(transactions.isTransactionInstance(transaction, "seedu.duke.data.transaction.Expense"));
    }

    @Test
    public void isTransactionInstance_invalidTransactionInstanceAsExpense_expectFalse() throws
            ClassNotFoundException {
        TransactionList transactions = new TransactionList();
        Transaction transaction = new Income("buy_a_bear", 80, "toys",
                LocalDate.of(2022, 10, 30));

        assertFalse(transactions.isTransactionInstance(transaction, "seedu.duke.data.transaction.Expense"));
    }
}
