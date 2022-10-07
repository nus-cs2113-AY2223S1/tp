package seedu.duke.transaction;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.item.Item;
import seedu.duke.user.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TransactionListTest {
    @Test
    void add_addOneTransaction_expectSizeOne() {
        TransactionList transactionList = new TransactionList();
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        transactionList.add(transaction);
        assertEquals(1, transactionList.getSize());
    }

    @Test
    void getTransaction_getTheFirstTransaction_expectTheFirstTransaction() throws DukeException {
        TransactionList transactionList = new TransactionList();
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        transactionList.add(transaction);
        assertEquals(transaction, transactionList.getTransaction(1));
    }

    @Test
    void getTransaction_getTheSecondTransaction_expectOutOfBoundException() {
        TransactionList transactionList = new TransactionList();
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        transactionList.add(transaction);
        assertThrows(DukeException.class, () -> transactionList.getTransaction(2));
    }

    @Test
    void markFinished_markTheFirstTransaction_expectTrue() throws DukeException {
        TransactionList transactionList = new TransactionList();
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        transactionList.add(transaction);
        transactionList.markFinished(1);
        assertTrue(transactionList.getTransaction(1).isFinished());
    }

    @Test
    void unmarkFinished_unmarkTheFirstTransaction_expectFalse() throws DukeException {
        TransactionList transactionList = new TransactionList();
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        transactionList.add(transaction);
        transactionList.unmarkFinished(1);
        assertFalse(transactionList.getTransaction(1).isFinished());
    }

    @Test
    void delete_deleteTheFirstTransaction_expectListSizeZero() throws DukeException {
        TransactionList transactionList = new TransactionList();
        User lender = new User("John Doe", 45, "93746378");
        User borrower = new User("Doe John", 23, "12312378");
        Item item = new Item("Broom", 1, 0.5, lender);
        Transaction transaction = new Transaction(item, lender, borrower, 5, "2022-01-03");
        transactionList.add(transaction);
        transactionList.delete(1);
        assertEquals(0, transactionList.getSize());
    }
}
