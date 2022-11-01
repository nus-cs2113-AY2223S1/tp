package seedu.duke.data.transaction;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionTest {
    //@@author chydarren

    @Test
    public void setDescription_setValidDescription_expectSuccessfulSetDescription() {
        Transaction transaction = new Income("Milked_cows_in_the_farm", 50,
                "Salary", LocalDate.of(2022, 1, 1));
        transaction.setDescription("Milked_cows_in_the_pantry");
        assertEquals("Milked_cows_in_the_pantry", transaction.getDescription());
    }

    @Test
    public void setAmount_setValidAmount_expectSuccessfulSetAmount() {
        Transaction transaction = new Income("Milked_cows_in_the_farm", 50,
                "Salary", LocalDate.of(2022, 1, 1));
        transaction.setAmount(500);
        assertEquals(500, transaction.getAmount());
    }

    @Test
    public void setCategory_setValidCategory_expectSuccessfulSetCategory() {
        Transaction transaction = new Income("Milked_cows_in_the_farm", 50,
                "Salary", LocalDate.of(2022, 1, 1));
        transaction.setCategory("Chore");
        assertEquals("Chore", transaction.getCategory());
    }

    @Test
    public void setDate_setValidDate_expectSuccessfulSetDate() {
        Transaction transaction = new Income("Milked_cows_in_the_farm", 50,
                "Salary", LocalDate.of(2022, 1, 1));
        transaction.setDate(LocalDate.of(2022, 10, 30));
        assertEquals(transaction.getDate(), LocalDate.of(2022, 10, 30));
    }

    @Test
    public void compareTo_inputTransactionWithEarlierDate_expectTransactionSortAsEarlier() {
        Transaction transaction = new Income("Milked_cows_in_the_farm", 50,
                "Salary", LocalDate.of(2022, 1, 1));
        Transaction earlierTransaction = new Expense("Bought_cow_food", 50,
                "Food", LocalDate.of(2021, 12, 25));
        assertTrue(transaction.compareTo(earlierTransaction) > 0);
    }

    @Test
    public void compareTo_inputTransactionWithLaterDate_expectTransactionSortAsLater() {
        Transaction transaction = new Income("Milked_cows_in_the_farm", 50,
                "Salary", LocalDate.of(2022, 1, 1));
        Transaction laterTransaction = new Expense("Destroy_the_farm", 1000,
                "Food", LocalDate.of(2022, 9, 20));
        assertTrue(transaction.compareTo(laterTransaction) < 0);
    }

    //@@author wcwy

    @Test
    public void printFormattedDate_validDateFormat_expectCorrectFormatDate() {
        Transaction transaction = new Income("Milked_cows_in_the_farm", 50,
                "Salary", LocalDate.of(2022, 1, 1));
        assertEquals(transaction.printFormattedDate(), "Jan 01 2022");
    }
}