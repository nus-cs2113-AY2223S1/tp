package seedu.duke.transaction;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;

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
    void getTransaction_getTheFirstTransaction_expectTheFirstTransaction() throws DukeException {
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
        assertThrows(DukeException.class, () -> transactionList.getTransactionById("66502660sd"));
    }

    @Test
    void markFinished_markTheFirstTransaction_expectTrue() throws DukeException {
        TransactionList transactionList = new TransactionList();
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, LocalDate.parse("2022-10-03"));
        transactionList.add(transaction);
        transactionList.markFinished(transaction.getTxId());
        assertTrue(transactionList.getTransactionById(transaction.getTxId()).isFinished());
    }

    @Test
    void unmarkFinished_unmarkTheFirstTransaction_expectFalse() throws DukeException {
        TransactionList transactionList = new TransactionList();
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, LocalDate.parse("2022-10-03"));
        transactionList.add(transaction);
        transactionList.unmarkFinished(transaction.getTxId());
        assertFalse(transactionList.getTransactionById(transaction.getTxId()).isFinished());
    }

    @Test
    void delete_deleteTheFirstTransaction_expectListSizeZero() throws DukeException {
        TransactionList transactionList = new TransactionList();
        Transaction transaction = new Transaction("6650266082", "6650266082", 5, LocalDate.parse("2022-10-03"));
        transactionList.add(transaction);
        transactionList.deleteTransaction(transaction.getTxId());
        assertEquals(0, transactionList.getSize());
    }
}