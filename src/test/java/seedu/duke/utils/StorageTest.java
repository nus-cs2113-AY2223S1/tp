package seedu.duke.utils;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
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
    @RepeatedTest(value = 100, name = RepeatedTest.LONG_DISPLAY_NAME)
    public void openPreviousState_file_state() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        State actualState = new State();
        storage.openPreviousState(actualState, ui);
        assertEquals(1, actualState.getSemester());
        List<SelectedModule> selectedModules = actualState.getSelectedModulesList();
        //        if (selectedModules.isEmpty()) {
        //            return;
        //        }
        SelectedModule selectedModule1 = selectedModules.get(0);
        Module module1 = selectedModule1.getModule();
        assertEquals("CS1010", module1.moduleCode);
        Map<LessonType, String> selectedSlots1 = selectedModule1.getSelectedSlots();
        assertEquals("01", selectedSlots1.get(LessonType.TUTORIAL));
        assertEquals("1", selectedSlots1.get(LessonType.SECTIONAL_TEACHING));

        SelectedModule selectedModule2 = selectedModules.get(1);
        Module module2 = selectedModule2.getModule();
        assertEquals("CS2113", module2.moduleCode);
        Map<LessonType, String> selectedSlots2 = selectedModule2.getSelectedSlots();
        assertEquals("4", selectedSlots2.get(LessonType.TUTORIAL));
        assertEquals("1", selectedSlots2.get(LessonType.LECTURE));


    }

    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
    public void saveState_state_outputFile() throws IOException {
        //Save state into file
        State state = new State();
        int semester = 1;
        state.setSemester(semester);
        SelectedModule selectedModule1 = new SelectedModule(Module.get("CS1010"), semester);
        selectedModule1.selectSlot(LessonType.TUTORIAL, "01");
        selectedModule1.selectSlot(LessonType.SECTIONAL_TEACHING, "1");
        state.addSelectedModule(selectedModule1);
        SelectedModule selectedModule2 = new SelectedModule(Module.get("CS2113"), semester);
        selectedModule2.selectSlot(LessonType.LECTURE, "1");
        selectedModule2.selectSlot(LessonType.TUTORIAL, "4");
        state.addSelectedModule(selectedModule2);
        Storage storage = new Storage();
        Ui ui = new Ui();
        storage.saveState(state, ui);

        //Read saved state from saved file
        File actualFile = new File("data/duke.txt");
        Scanner actualScanner = new Scanner(actualFile);
        StringBuilder actualString = new StringBuilder();
        while (actualScanner.hasNext()) {
            actualString.append(actualScanner.nextLine());
        }
        actualScanner.close();

        //Read state from expected file
        File expectedFile = new File("src/test/resources/saveCs1010.txt");
        Scanner expectedScanner = new Scanner(expectedFile);
        StringBuilder expectedString = new StringBuilder();
        while (expectedScanner.hasNext()) {
            expectedString.append(expectedScanner.nextLine());
        }
        expectedScanner.close();

        assertEquals(expectedString.toString(), actualString.toString());
    }
}
