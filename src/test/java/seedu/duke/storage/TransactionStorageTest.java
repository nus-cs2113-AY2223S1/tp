package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.transaction.Transaction;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @@author bdthanh
class TransactionStorageTest {

    @Test
    void handleTransactionLine() {
        Transaction transaction =
                new Transaction("pen", "8934hd8a", "bui", 100, LocalDate.parse("2022-10-20"), 3.2);
        String transactionLine = transaction.getTxId() + " | pen | 8934hd8a | bui | 100 | 2022-10-20 | 3.2";
        String[] splitTransactionLine = transactionLine.split(" \\| ");
        assertEquals(transaction.toString(),
                TransactionStorage.handleTransactionLine(splitTransactionLine).toString());
    }
}