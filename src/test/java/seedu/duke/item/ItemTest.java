package seedu.duke.item;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.transaction.TransactionList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    @Test
    void testItemToString() throws InvalidCategoryException {
        Item item = new Item("Cup", 1, 1.0, "jorelle");
        TransactionList transactionList = new TransactionList();
        assertEquals("YES", item.getStatus(transactionList));
    }

    @Test
    void convertItemToFileFormatTest() throws InvalidCategoryException {
        Item item = new Item("Cup", 1, 1.0, "jorelle");
        String itemId = item.getItemId();
        assertEquals("Cup | 1 | 1.0 | jorelle | " + itemId,
                item.convertItemToFileFormat());
    }
}
