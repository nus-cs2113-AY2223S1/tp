package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.timetable.Timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandAddModuleTest {

    @BeforeEach

    @Test
    void runAddModuleProcedure_validModuleCode_addSuccess() {
        assertEquals("Successfully added new module: CS2113 : "
                        + "Software Engineering & Object-Oriented Programming\n",
                CommandAddModule.runAddModuleProcedure("1", "cs2113"));
        Timetable.deleteModule(1);
    }

    @Test
    void runAddModuleProcedure_invalidModuleCode_addFailure() {
        assertEquals("Module not found, please enter a valid module code next time!",
                CommandAddModule.runAddModuleProcedure("1", "ct2113"));
    }

    @Test
    void runAddModuleProcedure_invalidSem_addFailure() {
        assertEquals("Sorry, this module is not available "
                        + "in the current semester or this semester is invalid.",
                CommandAddModule.runAddModuleProcedure("0", "cs2113"));
    }

}