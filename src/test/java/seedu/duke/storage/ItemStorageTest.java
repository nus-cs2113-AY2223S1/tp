package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.item.Item;
import seedu.duke.transaction.TransactionList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @@author bdthanh
class ItemStorageTest {

    @Test
    void handleItemLine_givenItemLine_returnItemObject() throws InvalidCategoryException {
        Item item = new Item("pen", 2, 0.1, "bui");
        String itemLine = item.getItemId() + " | pen | 0.1 | bui | 2";
        String[] splitItemLine = itemLine.split(" \\| ");
        assertEquals(item.toString(new TransactionList()),
                ItemStorage.handleItemLine(splitItemLine).toString(new TransactionList()));
    }
}