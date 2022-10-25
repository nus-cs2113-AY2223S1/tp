package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.duke.command.GetModuleCommand.isModuleOfferedInCurrentSem;

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


public class GetModuleCommandTest {
    @Test
    void getModuleCommand_validModuleCodeCS1010X_expectModuleDetailsOutput() throws YamomException, IOException {
        Ui ui = new Ui();
        State state = new State();
        State.setSemester(1);
        String[] input = {"get", "CS1010X"};
        GetModuleCommand getModuleCommand = new GetModuleCommand(input);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        getModuleCommand.execute(state, ui, null);
        InputStream stream = GetModuleCommandTest.class.getClassLoader()
                .getResourceAsStream("moduleDetailsCS1010X.txt");
        String expectedOutput = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        stream.close();
        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    void getModuleCommand_validModuleCodeCS2113_expectCorrectModuleDetailsOutput() throws YamomException, IOException {
        Ui ui = new Ui();
        State state = new State();
        State.setSemester(1);
        String[] input = {"get", "CS2113"};
        GetModuleCommand getModuleCommand = new GetModuleCommand(input);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        getModuleCommand.execute(state, ui, null);
        InputStream stream = GetModuleCommandTest.class.getClassLoader().getResourceAsStream("moduleDetailsCS2113.txt");
        String expectedOutput = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        stream.close();
        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    void getModuleCommand_invalidOrNotPresentModuleCodeX1010SC_exceptionThrown() {
        Ui ui = new Ui();
        State state = new State();
        State.setSemester(1);
        try {
            String[] input = {"get", "X1010SC"};
            GetModuleCommand getModuleCommand = new GetModuleCommand(input);
            getModuleCommand.execute(state, ui, null);
            fail();
        } catch (YamomException e) {
            assertEquals("Error! \tModule not found! Please enter a valid module code! Try searching "
                    + "if you do not remember the exact module code.", e.getMessage());
        }
    }

    @Test
    void getModuleCommand_emptyModuleCode_exceptionThrown() {
        Ui ui = new Ui();
        State state = new State();
        State.setSemester(1);
        try {
            String[] input = {"get"};
            GetModuleCommand getModuleCommand = new GetModuleCommand(input);
            getModuleCommand.execute(state, ui, null);
            fail();
        } catch (YamomException e) {
            assertEquals("Error! \tPlease enter a module code!", e.getMessage());
        }
    }

    @Test
    void isModuleOfferedInCurrentSem_moduleCS2113OfferedInCurrentSemester_expectsTrue() {
        // check if module is offered in current semester
        State state = new State();
        String moduleCode = "CS2113";
        Module testModule = Module.get(moduleCode);
        // setting current semester to 1
        State.setSemester(1);
        // check if module is offered in current semester
        boolean testResult = isModuleOfferedInCurrentSem(testModule, state);
        assertTrue(testResult);
    }

    @Test
    void isModuleOfferedInCurrentSem_moduleCS2113NotOfferedInCurrentSemester_expectsFalse() {
        // check if module is offered in current semester
        State state = new State();
        String moduleCode = "CS2113";
        Module testModule = Module.get(moduleCode);
        // setting current semester to 4
        State.setSemester(4);
        // check if module is offered in current semester
        boolean testResult = isModuleOfferedInCurrentSem(testModule, state);
        assertFalse(testResult);
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
}
