package seedu.duke.command;

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

class DeleteModuleCommandTest {

    @Test
    public void testDeleteCommand_withoutModuleCode_throwsException() {
        assertThrows(YamomException.class, () -> new DeleteModuleCommand(new String[]{ "delete" }));
    }

    @Test
    void testExecute_validModuleDeleted_StateUpdatedWithModuleDeleted() throws YamomException {

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

        String[] testInputToAdd = {"add","cs1010s"};
        AddModuleCommand addModuleCommand = new AddModuleCommand(testInputToAdd);
        addModuleCommand.execute(state,ui,storage);
        assertTrue(state.getSelectedModulesList().contains(selectedModule));

        String[] testInputToDelete = {"delete","cs1010s"};
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(testInputToDelete);
        deleteModuleCommand.execute(state,ui,storage);
        assertFalse(state.getSelectedModulesList().contains(selectedModule));
    }

    @Test
    void testIsExit_false() throws YamomException {
        String[] testInput = {"remove","cs2113"};
        assertFalse(new DeleteModuleCommand(testInput).isExit());
    }

    //@Test
    //void getExecutionMessage() {
    //}

    @Test
    void testGetCommandDescription_CorrectCommandDescription() throws YamomException {
        assertEquals("delete\t: remove a module from YAMOM timetable.", DeleteModuleCommand.getCommandDescription());
    }

    @Test
    void testGetUsage_CorrectUsageDescription() {
        assertEquals("delete [MODULE_CODE]", DeleteModuleCommand.getUsage());
    }

}