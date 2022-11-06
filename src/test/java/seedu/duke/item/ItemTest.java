package seedu.duke.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidCategoryException;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @@author jorellesee
public class ItemTest {

    Item item;

    @BeforeEach
    void initializeTest() throws InvalidCategoryException {
        item = new Item("pen", 2, 0.75, "jingwei");
    }

    @Test
    void getOwnerId_expect_jingwei() {
        assertEquals("jingwei", item.getOwnerId());
    }

    @Test
    void getName_expect_pen() {
        assertEquals("pen", item.getName());
    }

    @Test
    void getPricePerDay_expect_zeroPointSevenFive() {
        assert (item.getPricePerDay() == 0.75);
    }

    @Test
    void getCategory_expect_two() {
        assertEquals(2, Category.setCategory(item.getCategory()));
    }

    @Test
    void updatePriceTest() throws InvalidCategoryException {
        item = item.updatePrice(0.50);
        assertEquals(0.50, item.getPricePerDay());
    }

    @Test
    void convertItemToFileFormatTest() {
        String itemId = item.getItemId();
        assertEquals("pen | 2 | 0.75 | jingwei | " + itemId,
                item.convertItemToFileFormat());
    }
}
