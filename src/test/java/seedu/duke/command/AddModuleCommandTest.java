package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AddModuleCommandTest {

    @Test
    public void testAddCommand_withoutModuleCode_throwsException() {
        assertThrows(YamomException.class, () -> new AddModuleCommand(new String[]{"add"}));
    }

    @Test
    void testExecute_validModuleAdded_StateUpdatedWithNewModule() throws YamomException {
        Module module = Module.get("CS1010S");
        assertNotNull(module);
        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();
        int semester = 1;
        state.setSemester(semester);
        SelectedModule selectedModule = new SelectedModule(module, semester);
        assertFalse(state.getSelectedModulesList().contains(selectedModule));

        String[] testInput = {"add", "cs1010s"};
        AddModuleCommand addModuleCommand = new AddModuleCommand(testInput);
        addModuleCommand.execute(state, ui, storage);
        assertTrue(state.getSelectedModulesList().contains(selectedModule));
    }

    @Test
    void addCommand_testIsExit_correctOutput() throws YamomException {
        String[] testInput = {"add", "cs2113"};
        assertFalse(new AddModuleCommand(testInput).isExit());
    }

    @Test
    void testIsModuleExistWithInvalidInput_ExpectFalse() {
        String invalidModuleCode = "cs1111";
        assertThrows(YamomException.class,
            () -> new AddModuleCommand(new String[]{"add", invalidModuleCode}));
    }

}
