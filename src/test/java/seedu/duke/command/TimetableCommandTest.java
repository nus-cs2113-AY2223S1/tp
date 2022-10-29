package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimetableCommandTest {
    @Test
    public void timetableCommandConstructor_incorrectViewTimetableInput_throwYamomException() {
        String expectedErrorMessage = "Error! \tUnknown command. Maybe you meant \"view\".";
        YamomException exception = assertThrows(YamomException.class, () -> new TimetableCommand("view timetable"));
        assertEquals(expectedErrorMessage, exception.getMessage());
        exception = assertThrows(YamomException.class, () -> new TimetableCommand("view fancy"));
        assertEquals("Error! \tUnknown command. Maybe you forgot a \"/\".", exception.getMessage());
        exception = assertThrows(YamomException.class, () -> new TimetableCommand("view /fancy /simple"));
        assertEquals("Error! \tTimetable cannot be both simple and fancy!", exception.getMessage());
    }

    @Test
    public void timetableCommand_execution_noErrors() throws YamomException {
        State state = new State();
        Ui ui = new Ui();
        new TimetableCommand("view").execute(state, ui, null);
        new AddModuleCommand(new String[]{ "add", "cs2113" }).execute(state, ui, null);
        new TimetableCommand("view").execute(state, ui, null);
        new TimetableCommand("view /simple").execute(state, ui, null);
        assertFalse(new TimetableCommand("view").isExit());
    }
}
