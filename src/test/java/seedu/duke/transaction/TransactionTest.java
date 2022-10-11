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
    void getReturnDate_fiveDaysDuration_expectReturnDate5DaysFromCreatedDate() {
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, LocalDate.parse("2022-01-03"));
        assertEquals(LocalDate.parse("2022-01-08"), transaction.getReturnDate());
    }

    @Test
    void isFinished_finishedTx_expectTrue() {
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, LocalDate.parse("2022-10-03"));
        assertTrue(transaction.isFinished());
    }

    @Test
    void isFinished_notFinishedTx_expectFalse() {
        Transaction transaction = new Transaction("6650266082", "6650266082", 100, LocalDate.parse("2022-10-03"));
        assertFalse(transaction.isFinished());
    }

}