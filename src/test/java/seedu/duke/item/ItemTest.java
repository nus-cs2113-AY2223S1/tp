package seedu.duke.item;

import org.junit.jupiter.api.Test;
import seedu.duke.transaction.TransactionList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    @Test
    void testItemToString() {
        Item item = new Item("Cup", 1, 1.0, "jorelle");
        TransactionList transactionList = new TransactionList();
        assertEquals("YES", item.getStatus(transactionList));
    }
}
