package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void viewTimetableCommand_execution_noErrors() throws YamomException {
        State state = new State();
        Ui ui = new Ui();
        new ViewTimetableCommand("view").execute(state, ui, null);
        new AddModuleCommand(new String[]{ "add", "cs2113" }).execute(state, ui, null);
        new ViewTimetableCommand("view").execute(state, ui, null);
        new ViewTimetableCommand("view /simple").execute(state, ui, null);
        assertFalse(new ViewTimetableCommand("view").isExit());
    }
}
