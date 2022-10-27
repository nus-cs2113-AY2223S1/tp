package seedu.duke.command;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;



class AddModuleCommandTest {

    @Test
    public void testAddCommand_withoutModuleCode_throwsException() {
        assertThrows(YamomException.class, () -> new AddModuleCommand(new String[]{ "add" }));
    }

    @Test
    void testExecute_validModuleAdded_StateUpdatedWithNewModule() throws YamomException {

        // validate correct module details
        Module module = Module.get("CS1010S");
        assertNotNull(module);
        assertEquals("CS1010S", module.moduleCode);
        assertEquals("Programming Methodology", module.title);
        assertEquals(List.of(2, 1, 1, 3, 3), module.workload);
        assertEquals(2, module.semesterData.size());
        assertEquals("Computer Science", module.department);
        assertEquals("Computing", module.faculty);

        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();
        int semester = 1;
        state.setSemester(semester);

        SelectedModule selectedModule = new SelectedModule(module,semester);
        assertFalse(state.getSelectedModulesList().contains(selectedModule));

        String[] testInput = {"add","cs1010s"};
        AddModuleCommand addModuleCommand = new AddModuleCommand(testInput);
        addModuleCommand.execute(state,ui,storage);
        assertTrue(state.getSelectedModulesList().contains(selectedModule));
    }

    @Test
    void testExecute_InvalidModuleCodeInInput_CorrectErrorMessageShows() throws YamomException {
        //String[] testInput = {"add","cs1010s"};
        //AddModuleCommand addModuleCommand = new
    }

    @Test
    void testIsExit_false() throws YamomException {
        String[] testInput = {"add","cs2113"};
        assertFalse(new AddModuleCommand(testInput).isExit());
    }


    @Test
    void testGetCommandDescription_CorrectCommandDescription() throws YamomException {
        assertEquals("add\t: add a module into YAMOM timetable.", AddModuleCommand.getCommandDescription());
    }

    @Test
    void testGetUsage_CorrectUsageDescription() {
        assertEquals("add [MODULE_CODE]", AddModuleCommand.getUsage());
    }

    @Test
    void testIsModuleExistWithInvalidInput_ExpectFalse() {
        String invalidModuleCode = "cs1111";
        Module module = Module.get(invalidModuleCode);
        assertFalse(AddModuleCommand.isModuleExist(module));
    }

    @Test
    void testIsModuleOfferedInCurrentSemesterWithInvalidStateSemester_ExpectFalse() {
        State state = new State();
        int semester = 4;
        state.setSemester(semester);
        String moduleCode = "cs2113";
        Module module = Module.get(moduleCode);

        assertFalse(AddModuleCommand.isModuleOfferedInCurrentSemester(module, state));
    }
}