package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.TransactionList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurgeCommandTest {

    @Test
    public void purge_IfEmpty_ReturnTrue() {
        TransactionList transactions = new TransactionList();
        boolean testOutputWithoutEntries = PurgeCommand.isEmpty(transactions);
        assertTrue(testOutputWithoutEntries);
    }

    @Test
    public void purge_IfNotEmpty_ReturnFalse() {
        TransactionList transactions = new TransactionList();
        LocalDate date = LocalDate.of(2022, 1, 1);
        transactions.addExpense("Maggi", 10, "Food", date);
        boolean testOutputWithEntries = PurgeCommand.isEmpty(transactions);
        assertFalse(testOutputWithEntries);
    }
}
