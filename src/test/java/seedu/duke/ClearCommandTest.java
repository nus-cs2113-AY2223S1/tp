package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClearCommandTest {
    static final String confirmClear = "Your list is now cleared.";
    ReviewList storedReviews = new ReviewList();

    @Test
    public void clearTest() {
        ClearCommand command = new ClearCommand(storedReviews);
        assertEquals(confirmClear, command.execute());
        assertEquals((new ReviewList()).inputs, storedReviews.inputs);
    }

}