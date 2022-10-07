package seedu.duke.transaction;

import org.junit.jupiter.api.Test;
import seedu.duke.item.Item;
import seedu.duke.parser.DateParser;
import seedu.duke.user.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TransactionTest {

    @Test
    void setAsFinished_expectTrue() {
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        transaction.setAsFinished();
        assertTrue(transaction.isFinished());
    }

    @Test
    void setAsNotFinished_expectFalse() {
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        transaction.setAsNotFinished();
        assertFalse(transaction.isFinished());
    }

    @Test
    void getReturnDate_fiveDaysDuration_expectReturnDate5DaysFromCreatedDate() {
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        assertEquals(LocalDate.parse("2022-01-08"), transaction.getReturnDate());
    }

    @Test
    void isOverdue_isNotFinished_expectTrue() {
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        assertTrue(transaction.isOverdue());
    }

    @Test
    void isOverdue_isFinished_expectFalse() {
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        transaction.setAsFinished();
        assertFalse(transaction.isOverdue());
    }
}
