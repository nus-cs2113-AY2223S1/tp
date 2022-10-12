package seedu.duke.utils;

import org.junit.jupiter.api.Test;
import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void saveState_state_outputFile() throws IOException {
        State state = new State();
        int semester = 1;
        state.setSemester(semester);
        SelectedModule selectedModule = new SelectedModule(Module.get("CS1010"),semester);
        selectedModule.selectSlot(LessonType.Tutorial,"01");
        selectedModule.selectSlot(LessonType.SectionalTeaching,"1");
        state.addSelectedModule(selectedModule);
        Storage storage = new Storage();
        storage.saveState(state);

        File actualFile = new File("data/duke.txt");
        Scanner actualScanner = new Scanner(actualFile);
        StringBuilder actualString = new StringBuilder();
        while (actualScanner.hasNext()) {
            actualString.append(actualScanner.nextLine());
        }
        actualScanner.close();

        File expectedFile = new File("src/test/resources/saveCs1010.txt");
        Scanner expectedScanner = new Scanner(expectedFile);
        StringBuilder expectedString = new StringBuilder();
        while (expectedScanner.hasNext()) {
            expectedString.append(expectedScanner.nextLine());
        }
        expectedScanner.close();

        assertEquals( expectedString.toString(), actualString.toString());
    }

    @Test
    public void openPreviousState_file_state() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        State actualState = new State();
        storage.openPreviousState(actualState, ui);

        List<SelectedModule> selectedModules = actualState.getSelectedModulesList();
        SelectedModule selectedModule = selectedModules.get(0);
        Module module = selectedModule.getModule();
        Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();
        assertEquals(1, actualState.getSemester());
        assertEquals("CS1010", module.moduleCode);
        assertEquals("01", selectedSlots.get(LessonType.Tutorial));
        assertEquals("1",selectedSlots.get(LessonType.SectionalTeaching));
    }
}
