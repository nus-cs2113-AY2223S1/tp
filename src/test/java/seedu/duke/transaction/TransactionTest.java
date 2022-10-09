package seedu.duke.transaction;

import org.junit.jupiter.api.Test;
import seedu.duke.parser.DateParser;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TransactionTest {

    @Test
    void setAsFinished_expectTrue() {
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, "2022-10-03");
        transaction.setAsFinished();
        assertTrue(transaction.isFinished());
    }

    @Test
    void setAsNotFinished_expectFalse() {
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, "2022-10-03");
        transaction.setAsNotFinished();
        assertFalse(transaction.isFinished());
    }

    @Test
    void getReturnDate_fiveDaysDuration_expectReturnDate5DaysFromCreatedDate() {
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, "2022-01-03");
        assertEquals(LocalDate.parse("2022-01-08"), transaction.getReturnDate());
    }

    @Test
    void isOverdue_isNotFinished_expectTrue() {
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, "2022-10-03");
        assertTrue(transaction.isOverdue());
    }

    @Test
    void isOverdue_isFinished_expectFalse() {
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, "2022-10-03");
        transaction.setAsFinished();
        assertFalse(transaction.isOverdue());
    }

}