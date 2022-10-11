package seedu.duke.transaction;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.TransactionNotFoundException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TransactionListTest {
    @Test
    void add_addOneTransaction_expectSizeOne() {
        TransactionList transactionList = new TransactionList();
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, LocalDate.parse("2022-10-03"));
        transactionList.add(transaction);
        assertEquals(1, transactionList.getSize());
    }

    @Test
    void getTransaction_getTheFirstTransaction_expectTheFirstTransaction() throws TransactionNotFoundException {
        TransactionList transactionList = new TransactionList();
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, LocalDate.parse("2022-10-03"));
        transactionList.add(transaction);
        assertEquals(transaction, transactionList.getTransactionById(transaction.getTxId()));
    }

    @Test
    void getTransaction_getTheSecondTransaction_expectOutOfBoundException() {
        TransactionList transactionList = new TransactionList();
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, LocalDate.parse("2022-10-03"));
        transactionList.add(transaction);
        assertThrows(TransactionNotFoundException.class, () -> transactionList.getTransactionById("6650266w82"));

    }

    @Test
    void markFinished_finishedTx_expectTrue() throws TransactionNotFoundException {
        TransactionList transactionList = new TransactionList();
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, LocalDate.parse("2022-10-03"));
        transactionList.add(transaction);
        assertTrue(transactionList.getTransactionById(transaction.getTxId()).isFinished());
    }

    @Test
    void unmarkFinished_notFinishedTx_expectFalse() throws TransactionNotFoundException {
        TransactionList transactionList = new TransactionList();
        Transaction transaction = new Transaction("6650266082", "6650266082", 100, LocalDate.parse("2022-10-03"));
        transactionList.add(transaction);
        assertFalse(transactionList.getTransactionById(transaction.getTxId()).isFinished());
    }

    @Test
    void delete_deleteTheFirstTransaction_expectListSizeZero() throws TransactionNotFoundException {
        TransactionList transactionList = new TransactionList();
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, LocalDate.parse("2022-10-03"));
        transactionList.add(transaction);
        transactionList.deleteTransaction(transaction.getTxId());
        assertEquals(0, transactionList.getSize());
    }
}
