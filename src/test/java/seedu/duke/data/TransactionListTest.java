package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
