package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModuleTest {
    Module module = new Module("SE & OOP", "CS2113");

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
        assertEquals("CS2113: SE & OOP", module.getModuleDetails());
    }
}