package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ViewTimetableCommandTest {
    @Test
    public void viewTimetableCommandConstructor_incorrectViewTimetableInput_throwYamomException() {
        String expectedErrorMessage = "Error! \tUnknown command. Maybe you meant \"view\".";
        YamomException exception = assertThrows(YamomException.class, () -> new ViewTimetableCommand("view timetable"));
        assertEquals(expectedErrorMessage, exception.getMessage());

        exception = assertThrows(YamomException.class, () -> new ViewTimetableCommand("view fancy"));
        assertEquals("Error! \tUnknown command. Maybe you forgot a \"/\".", exception.getMessage());

        exception = assertThrows(YamomException.class, () -> new ViewTimetableCommand("view /fancy /simple"));
        assertEquals("Error! \tTimetable cannot be both simple and fancy!", exception.getMessage());
    }
}
