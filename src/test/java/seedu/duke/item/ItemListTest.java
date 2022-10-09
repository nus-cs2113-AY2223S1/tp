package seedu.duke.item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemListTest {
    @Test
    void add_addOneItem_expectSizeOne() {
        ItemList itemList = new ItemList();
        Item item = new Item("Cup", 1, 1.0, "jorelle");
        itemList.addItem(item);
        assertEquals(1, itemList.getListSize());
    }
}
