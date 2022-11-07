package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.YamomException;
import seedu.duke.model.LessonType;
import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Ui;

public class ImportCommandTest {
    @Test
    public void importCommand_invalidUrl_throwException() throws YamomException {
        State state = new State();
        Ui ui = new Ui();
        new ImportCommand(new String[]{
            "import",
            "https://nusmods.com/timetable/sem-1/share?CS101010=LAB:B03,SEC:1,TUT:01"
        }).execute(state, ui, null);
        assertEquals(0, state.getSelectedModulesList().size());
    }

    @Test
    public void importCommand_importUrl_importsCorrectly() throws YamomException {
        State state = new State();
        Ui ui = new Ui();
        ImportCommand command = new ImportCommand(new String[]{
            "import", 
            "https://nusmods.com/timetable/sem-1/share?CS1010=LAB:B03,SEC:1,TUT:01"
        });
        command.execute(state, ui, null);
        assertEquals(state.getSemester(), 1);
        assertEquals(state.getSelectedModulesList().size(), 1);
        SelectedModule selectedModule = state.getSelectedModulesList().get(0);
        assertEquals("CS1010", selectedModule.getModule().moduleCode);
        assertEquals(3, selectedModule.getSelectedSlots().size());
        assertEquals("B03", selectedModule.getSelectedSlots().get(LessonType.LABORATORY));
        assertEquals("1", selectedModule.getSelectedSlots().get(LessonType.SECTIONAL_TEACHING));
        assertEquals("01", selectedModule.getSelectedSlots().get(LessonType.TUTORIAL));
    }

    @Test
    public void importCommand_withoutUrl_throwsException() {
        assertThrows(YamomException.class, () -> new ImportCommand(new String[]{"import"}));
    }
}
