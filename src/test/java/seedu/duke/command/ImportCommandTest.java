package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.YamomException;
import seedu.duke.model.LessonType;
import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Ui;

public class ImportCommandTest {
    @Test
    @Disabled
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
        assertEquals(selectedModule.getModule().moduleCode, "CS1010");
        assertEquals(selectedModule.getSelectedSlots().size(), 3);
        assertEquals(selectedModule.getSelectedSlots().get(LessonType.LABORATORY), "B03");
        assertEquals(selectedModule.getSelectedSlots().get(LessonType.SECTIONAL_TEACHING), "1");
        assertEquals(selectedModule.getSelectedSlots().get(LessonType.TUTORIAL), "01");
    }

    @Test
    public void importCommand_withoutUrl_throwsException() {
        assertThrows(YamomException.class, () -> new ImportCommand(new String[]{ "import" }));
    }
}
