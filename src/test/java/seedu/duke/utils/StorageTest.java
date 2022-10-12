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
        Storage storage = new Storage();
        SelectedModule selectedModule = new SelectedModule(Module.get("CS1010"),semester);
        selectedModule.selectSlot(LessonType.Tutorial,"06");
        selectedModule.selectSlot(LessonType.SectionalTeaching,"1");
        state.addSelectedModule(selectedModule);
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

        assertEquals(actualString.toString(), expectedString.toString());
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

        assertEquals(actualState.getSemester(),1);
        assertEquals(module.moduleCode, "CS1010");
        assertEquals(selectedSlots.get(LessonType.Tutorial),"06");
        assertEquals(selectedSlots.get(LessonType.SectionalTeaching),"1");
    }
}
