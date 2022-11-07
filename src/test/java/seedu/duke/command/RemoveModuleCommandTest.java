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
import static org.junit.jupiter.api.Assertions.assertThrows;

class RemoveModuleCommandTest {

    @Test
    public void testDeleteCommand_withoutModuleCode_throwsException() {
        assertThrows(YamomException.class, () -> new RemoveModuleCommand(new String[]{"delete"}));
    }

    @Test
    void testExecute_validModuleDeleted_StateUpdatedWithModuleDeleted() throws YamomException {
        Module module = Module.get("CS1010S");
        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();
        int semester = 1;
        state.setSemester(semester);

        SelectedModule selectedModule = new SelectedModule(module, semester);
        assertFalse(state.getSelectedModulesList().contains(selectedModule));

        String[] testInputToAdd = {"add", "cs1010s"};
        AddModuleCommand addModuleCommand = new AddModuleCommand(testInputToAdd);
        addModuleCommand.execute(state, ui, storage);
        assertTrue(state.getSelectedModulesList().contains(selectedModule));

        String[] testInputToDelete = {"delete", "cs1010s"};
        RemoveModuleCommand deleteModuleCommand = new RemoveModuleCommand(testInputToDelete);
        deleteModuleCommand.execute(state, ui, storage);
        assertFalse(state.getSelectedModulesList().contains(selectedModule));
    }

    @Test
    void testIsExit_false() throws YamomException {
        String[] testInput = {"remove", "cs2113"};
        assertFalse(new RemoveModuleCommand(testInput).isExit());
    }

}