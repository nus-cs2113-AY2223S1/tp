package seedu.duke.module;

import org.junit.jupiter.api.Test;
import seedu.duke.module.lessons.Lesson;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleTest {
    List<Lesson> list = new ArrayList<>();
    Module module = new Module("CS2113", "SE & OOP", list);

    @Test
    void getModuleName_noInputs_correctName() {
        assertEquals("SE & OOP", module.getModuleName());
    }

    @Test
    void getModuleCode_noInputs_correctCode() {
        assertEquals("CS2113", module.getModuleCode());
    }

    @Test
    void getModuleDetails_noInputs_correctDetails() {
        assertEquals("CS2113: SE & OOP\n", module.getModuleDetails());
    }
}