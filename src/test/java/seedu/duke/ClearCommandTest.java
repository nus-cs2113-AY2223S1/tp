package seedu.duke;

import commands.ClearCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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