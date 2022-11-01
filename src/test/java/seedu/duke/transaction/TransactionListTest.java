package seedu.duke.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidTransactionException;
import seedu.duke.exception.TransactionNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

// @@author bdthanh
class TransactionListTest {
    TransactionList transactionList;
    Transaction transaction;

    @BeforeEach
    void initializeTest() {
        transactionList = new TransactionList();
        transaction = new Transaction("pen", "28sd37h2", "bui", "jw", 5,
                LocalDate.parse("2022-10-03"), 3.2);
    }

    @Test
    void constructorTest() {
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        transactionArrayList.add(transaction);
        TransactionList transactionListNew = new TransactionList(transactionArrayList);
        assertEquals(1, transactionListNew.getSize());
    }

    @Test
    void add_addOneTransaction_expectSizeOne() {
        transactionList.addTransaction(transaction);
        assertEquals(1, transactionList.getSize());
    }

    @Test
    void getTransaction_getTheFirstTransaction_expectTheFirstTransaction()
            throws TransactionNotFoundException {
        transactionList.addTransaction(transaction);
        assertEquals(transaction, transactionList.getTransactionById(transaction.getTxId()));
    }

    @Test
    void getTransaction_getTheSecondTransaction_expectOutOfBoundException() {
        transactionList.addTransaction(transaction);
        assertThrows(TransactionNotFoundException.class,
            () -> transactionList.getTransactionById("6650266w82"));

    }

    @Test
    void markFinished_finishedTx_expectTrue() throws TransactionNotFoundException {
        transactionList.addTransaction(transaction);
        assertTrue(transactionList.getTransactionById(transaction.getTxId()).isFinished());
    }

    @Test
    void unmarkFinished_notFinishedTx_expectFalse() throws TransactionNotFoundException {
        Transaction unfinishedTransaction = new Transaction("pen", "28sd37h2", "bui", "jw", 300,
                LocalDate.parse("2022-10-03"), 3.2);
        transactionList.addTransaction(unfinishedTransaction);
        assertFalse(
                transactionList.getTransactionById(unfinishedTransaction.getTxId()).isFinished());
    }

    @Test
    void delete_deleteTheFirstTransaction_expectListSizeZero() throws TransactionNotFoundException {
        transactionList.addTransaction(transaction);
        transactionList.deleteTransaction(transaction.getTxId());
        assertEquals(0, transactionList.getSize());
    }

    @Test
    void updateTransactionDuration_txCanBeFound_durationIsUpdated()
            throws TransactionNotFoundException, InvalidTransactionException {
        transactionList.addTransaction(transaction);
        transactionList.updateTransaction(transaction.getTxId(), 300, 192);
        assertEquals(300, transactionList.getTransactionById(transaction.getTxId()).getDuration());
    }

    @Test
    void updateTransactionDuration_txCannotBeFound_exceptionIsThrown() {
        transactionList.addTransaction(transaction);
        assertThrows(TransactionNotFoundException.class,
            () -> transactionList.updateTransaction("982h28hw", 300, 192));
    }

    @Test
    void hasThisBorrower_hasBorrower_returnTrue() {
        Transaction unfinishedTransaction = new Transaction("pen", "28sd37h2", "bui", "jw", 300,
                LocalDate.parse("2022-10-03"), 3.2);
        transactionList.addTransaction(unfinishedTransaction);
        assertTrue(transactionList.hasThisBorrower("bui"));
    }

    @Test
    void hasThisBorrower_notHaveBorrower_returnFalse() {
        transactionList.addTransaction(transaction);
        assertFalse(transactionList.hasThisBorrower("buithanh"));
    }

    @Test
    void hasThisItemBeingBorrowed_hasItem_returnTrue() {
        Transaction unfinishedTransaction = new Transaction("pen", "28sd37h2", "bui", "jw", 300,
                LocalDate.parse("2022-10-03"), 3.2);
        transactionList.addTransaction(unfinishedTransaction);
        assertTrue(transactionList.hasThisItemBeingBorrowed("28sd37h2"));
    }

    @Test
    void hasThisItemBeingBorrowed_notHasItem_returnFalse() {
        transactionList.addTransaction(transaction);
        assertFalse(transactionList.hasThisItemBeingBorrowed("pencil"));
    }

    @Test
    void convertTransactionListToFileFormat() {
        String transactionId = transaction.getTxId();
        transactionList.addTransaction(transaction);
        assertEquals("28sd37h2 | bui | 5 | 2022-10-03 | jw | pen | 3.2 | " + transactionId + "\n",
                transactionList.convertTransactionListToFileFormat());
    }

    @Test
    void getTransactionListTest() {
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        transactionArrayList.add(transaction);
        transactionList.addTransaction(transaction);
        assertEquals(transactionArrayList, transactionList.getTransactionList());
    }

    @Test
    void checkIfListHasTransactionOfThisItemThatOverlapWithNewTransaction() {
        transactionList.addTransaction(transaction);
        Transaction newTransaction = new Transaction("pen", "28sd37h2", "bui", "jw", 6,
                LocalDate.parse("2022-10-03"), 3.2);
        assertThrows(InvalidTransactionException.class,
            () -> transactionList.checkOldTransactionsOverlapWithNew(newTransaction));
    }

    @Test
    void checkIfListHasTransactionOfThisItemThatOverlapWithUpdatedTransaction() {
        transactionList.addTransaction(transaction);
        Transaction newTransaction = new Transaction("pen", "28sd37h2", "bui", "jw", 6,
                LocalDate.parse("2022-10-09"), 3.2);
        transactionList.addTransaction(newTransaction);
        assertThrows(InvalidTransactionException.class, () -> transactionList
                .checkOldTransactionsOverlapWithNew(transaction.update(10, 6.4)));
    }
}
