package seedu.duke.item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    @Test
    void testItemToString() {
        Item item = new Item("Cup", 1, 1.0, "jorelle");
        assertEquals("YES", item.getStatus());
    }
}
