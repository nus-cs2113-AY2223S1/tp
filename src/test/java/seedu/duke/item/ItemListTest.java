package seedu.duke.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.exception.InvalidItemException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.transaction.TransactionList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @@author jorellesee
public class ItemListTest {
    ItemList itemList;
    Item item;
    TransactionList transactionList;

    @BeforeEach
    void initializeTest() throws InvalidCategoryException {
        itemList = new ItemList();
        item = new Item("28sd37h2", "pen", 2, 0.50, "jingwei");
        itemList.addItem(item);
        transactionList = new TransactionList();
    }

    @Test
    void add_addOneItem_expectSizeTwo() throws InvalidCategoryException {
        Item item2 = new Item("30fd39h2", "ball", 2, 0.75, "jingwei");
        itemList.addItem(item2);
        assertEquals(2, itemList.getListSize());
    }

    @Test
    void delete_expectSizeOne() throws ItemNotFoundException, InvalidItemException {
        itemList.deleteItem(item.getItemId(), transactionList);
        assertEquals(0, itemList.getListSize());
    }

    @Test
    void getItemById_expect_pen() throws ItemNotFoundException {
        assertEquals("pen", itemList.getItemById("28sd37h2").getName());
    }

}
