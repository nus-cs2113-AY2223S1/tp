package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.model.Module;
import seedu.duke.utils.Ui;


public class InfoCommandTest {
    @Test
    void infoCommand_validModuleCodeCS1010X_expectModuleDetailsOutput() throws YamomException, IOException {
        Ui ui = new Ui();
        State state = new State();

        state.setSemester(1);

        String[] input = {"info", "CS1010X"};
        InfoCommand infoCommand = new InfoCommand(input);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        infoCommand.execute(state, ui, null);
        InputStream stream = InfoCommandTest.class.getClassLoader()
                .getResourceAsStream("moduleDetailsCS1010X.txt");
        String expectedOutput = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        stream.close();
        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    void infoCommand_validModuleCodeCS2113_expectCorrectModuleDetailsOutput() throws YamomException, IOException {
        Ui ui = new Ui();
        State state = new State();

        state.setSemester(1);
        String[] input = {"info", "CS2113"};

        InfoCommand infoCommand = new InfoCommand(input);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        infoCommand.execute(state, ui, null);
        InputStream stream = InfoCommandTest.class.getClassLoader().getResourceAsStream("moduleDetailsCS2113.txt");
        String expectedOutput = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        stream.close();
        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    void getModuleCommand_invalidOrNotPresentModuleCodeX1010SC_exceptionThrown() {
        Ui ui = new Ui();
        State state = new State();

        state.setSemester(1);

        try {
            String[] input = {"info", "X1010SC"};
            InfoCommand getModuleCommand = new InfoCommand(input);
            getModuleCommand.execute(state, ui, null);
            fail();
        } catch (YamomException e) {
            assertEquals("Error! \tModule not found! Please enter a valid module code! Try searching "
                    + "if you do not remember the exact module code.", e.getMessage());
        }
    }

    @Test
    void infoCommand_emptyModuleCode_exceptionThrown() {
        Ui ui = new Ui();
        State state = new State();
        state.setSemester(1);
        try {
            String[] input = {"info"};
            InfoCommand infoCommand = new InfoCommand(input);
            infoCommand.execute(state, ui, null);
            fail();
        } catch (YamomException e) {
            assertEquals("Error! \tPlease enter a module code!", e.getMessage());
        }
    }

    @Test
    void isModuleExist_moduleCodeCS2113Exist_expectTrue() {
        String moduleCode = "CS2113";
        List<Module> moduleList = Module.getAll();
        boolean moduleExistsInModuleList = false;
        for (Module module : moduleList) {
            if (moduleCode.equals(module.moduleCode)) {
                moduleExistsInModuleList = true;
                break;
            }
        }
        assertTrue(moduleExistsInModuleList);
    }

    @Test
    void isModuleExist_moduleCodeCS2113FDoNotExist_expectFalse() {
        String moduleCode = "CS2113F";
        List<Module> moduleList = Module.getAll();
        boolean moduleExistsInModuleList = false;
        for (Module module : moduleList) {
            if (moduleCode.equals(module.moduleCode)) {
                moduleExistsInModuleList = true;
                break;
            }
        }
        assertFalse(moduleExistsInModuleList);
    }

    @Test
    void infoCommand_weekendClass_noErrors() throws YamomException {
        Ui ui = new Ui();
        State state = new State();
        new InfoCommand(new String[]{"info", "bpm1701"}).execute(state, ui, null);
        new InfoCommand(new String[]{"info", "eg3301r"}).execute(state, ui, null);
    }
}
