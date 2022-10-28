package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Ui;

public class SelectSemesterCommandTest {
    @Test
    public void selectSemesterCommand_execution_noErrors() throws YamomException {
        Ui ui = new Ui();
        State state = new State();
        new SelectSemesterCommand(new String[]{ "semester", "1" }).execute(state, ui, null);
        assertEquals(state.getSemester(), 1);
        new SelectSemesterCommand(new String[]{ "semester", "2" }).execute(state, ui, null);
        assertEquals(state.getSemester(), 2);
        new SelectSemesterCommand(new String[]{ "semester", "st1" }).execute(state, ui, null);
        assertEquals(state.getSemester(), 3);
        new SelectSemesterCommand(new String[]{ "semester", "specialterm2" }).execute(state, ui, null);
        assertEquals(state.getSemester(), 4);
        new SelectSemesterCommand(new String[]{ "semester", "1" }).execute(state, ui, null);
        assertEquals(state.getSemester(), 1);
    }
}
