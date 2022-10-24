package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.duke.command.GetModuleCommand.isModuleOfferedInCurrentSem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.model.Module;
import seedu.duke.utils.Ui;


public class GetModuleCommandTest {
    @Test
    void getModuleCommand_validModuleCodeCS1010X_expectModuleDetailsOutput() throws YamomException {
        Ui ui = new Ui();
        State state = new State();

        State.setSemester(1);
        String[] input = {"get", "CS1010X"};
        GetModuleCommand getModuleCommand = new GetModuleCommand(input);


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        getModuleCommand.execute(state, ui, null);
        String s = System.lineSeparator();
        String expectedOutput =
                "--------------------------------------" + s
                        + "Module               : CS1010X" + s
                        + "Module Name          : Programming Methodology" + s
                        + "Module Description   : This module introduces the fundamental concepts of problem solving "
                        + "by computing and programming using an imperative programming language. It is the first and "
                        + "foremost introductory course to computing and is equivalent to CS1010, CS1010S and CS1010E "
                        + "Programming Methodology. The module will be taught using the Python programming language "
                        + "and topics covered include problem solving by computing, writing pseudo-codes, basic "
                        + "problem formulation and problem solving, program development, coding, testing and "
                        + "debugging, fundamental programming constructs (variables, types, expressions, assignments, "
                        + "functions, control structures, etc.), fundamental data structures: arrays, strings and "
                        + "structures, simple file processing, and basic recursion." + s
                        + "Module Credit        : 4" + s
                        + "Department           : Computer Science" + s
                        + "Faculty              : Computing" + s
                        + "Workload             : [2, 1, 1, 3, 3]" + s
                        + "Semester offering    : [2, 3]" + s
                        + "Prerequisite         : Nil" + s
                        + "Preclusion           : CS1010 or its equivalent, CS1010FC" + s
                        + "Corequisite          : Nil" + s
                        + "Module CS1010X is not offered in this semester, hence no timetable information is "
                        + "available due to unforseen circumstances" + s;

        assertEquals(expectedOutput, outContent.toString());
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
        } catch (YamomException e) {
            assertEquals("Error! \tWrong format, should be: get [EXACT_MODULE_CODE]", e.getMessage());
        }
    }

    @Test
    void getModuleCommand_validModuleCodeCS1010E_expectCorrectModuleDetailsOutput() throws YamomException {

        Ui ui = new Ui();
        State state = new State();

        State.setSemester(3);
        String[] input = {"get", "CS1010e"};
        GetModuleCommand getModuleCommand = new GetModuleCommand(input);


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        getModuleCommand.execute(state, ui, null);
        String s = System.lineSeparator();
        String expectedOutput =
                "--------------------------------------" + s
                        + "Module               : CS1010E" + s
                        + "Module Name          : Programming Methodology" + s
                        + "Module Description   : This module introduces the fundamental concepts of problem solving "
                        + "by computing and programming using an imperative programming language. It is the first "
                        + "and foremost introductory course to computing.  Topics covered include computational "
                        + "thinking and computational problem solving, designing and specifying an algorithm, "
                        + "basic problem formulation and problem solving approaches, program development, coding, "
                        + "testing and debugging, fundamental programming constructs (variables, types, expressions, "
                        + "assignments, functions, control structures, etc.), fundamental data structures (arrays, "
                        + "strings, composite data types), basic sorting, and recursion." + s
                        + "Module Credit        : 4" + s
                        + "Department           : Computer Science" + s
                        + "Faculty              : Computing" + s
                        + "Workload             : [2, 1, 1, 3, 3]" + s
                        + "Semester offering    : [1, 2, 4]" + s
                        + "Prerequisite         : Nil" + s
                        + "Preclusion           : CS1010, CS1010J, CS1010S, CS1010X, CS1010XCP, CS1101S" + s
                        + "Corequisite          : Nil" + s
                        + "Module CS1010E is not offered in this semester, hence no timetable information is available due to unforseen circumstances" + s;


        assertEquals(expectedOutput, outContent.toString());
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
