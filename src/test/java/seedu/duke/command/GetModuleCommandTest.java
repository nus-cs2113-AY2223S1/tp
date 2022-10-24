package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;


public class GetModuleCommandTest {
    @Test
    void getModuleCommand_validModuleCodeWithoutTimetable_expectCorrectModuleDetailsOutput() throws YamomException {
        Ui ui = new Ui();
        State state = new State();

        state.setSemester(1);
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
                + "Module Description   : This module introduces the fundamental concepts of problem solving by computing and programming using an imperative programming language. It is the first and foremost introductory course to computing and is equivalent to CS1010, CS1010S and CS1010E Programming Methodology. The module will be taught using the Python programming language and topics covered include problem solving by computing, writing pseudo-codes, basic problem formulation and problem solving, program development, coding, testing and debugging, fundamental programming constructs (variables, types, expressions, assignments, functions, control structures, etc.), fundamental data structures: arrays, strings and structures, simple file processing, and basic recursion." + s
                + "Module Credit        : 4" + s
                + "Department           : Computer Science" + s
                + "Faculty              : Computing" + s
                + "Workload             : [2, 1, 1, 3, 3]" + s
                + "Semester offering    : [2, 3]" + s
                + "Prerequisite         : Nil" + s
                + "Preclusion           : CS1010 or its equivalent, CS1010FC" + s
                + "Corequisite          : Nil" + s
                + "Module CS1010X is not offered in this semester, hence no timetable information is available due to unforseen circumstances" + s;

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void getModuleCommand_validModuleCodeWithTimetable_expectCorrectModuleDetailsOutput() throws YamomException {
        Ui ui = new Ui();
        State state = new State();

        state.setSemester(1);
        String[] input = {"get", "CS2113"};
        GetModuleCommand getModuleCommand = new GetModuleCommand(input);


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        getModuleCommand.execute(state, ui, null);
        String s = System.lineSeparator();
        String expectedOutput =
                "--------------------------------------" + s
                        + "Module               : CS2113" + s
                        + "Module Name          : Software Engineering & Object-Oriented Programming" + s
                        + "Module Description   : This module introduces the necessary skills for systematic and rigorous development of software systems. It covers requirements, design, implementation, quality assurance, and project management aspects of small-to-medium size multi-person software projects. The module uses the Object Oriented Programming paradigm. Students of this module will receive hands-on practice of tools commonly used in the industry, such as test automation tools, build automation tools, and code revisioning tools will be covered." + s
                        + "Module Credit        : 4" + s
                        + "Department           : Computer Science" + s
                        + "Faculty              : Computing" + s
                        + "Workload             : [2, 1, 0, 3, 4]" + s
                        + "Semester offering    : [1, 2]" + s
                        + "Prerequisite         : CS2040C or ((CS2030 or its equivalent) and CS2040/S)" + s
                        + "Preclusion           : CS2103, CS2103T, (CS2113T for CS2113), (CS2113 for CS2113T)" + s
                        + "Corequisite          : CS2101 Effective Communication for Computing Professionals is co-requisite for CS2113T. Students exempted from CS2101 will take CS2113 which does not have CS2101 as co-req. Otherwise, CS2113 and CS2113T are identical." + s
                        + "\u001B[40m                                                                               \u001B[0m\n"
                        + "\u001B[40m          : Mon\u001B[0m\u001B[40m      : Tues\u001B[0m\u001B[40m     : Wed\u001B[0m\u001B[40m      : Thur\u001B[0m\u001B[40m                : Fri\u001B[0m\u001B[40m         \u001B[0m\n"
                        + "\u001B[40m===============================================================================\u001B[0m\n"
                        + "\u001B[40m   1100\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m                     +----------+  \u001B[0m\n"
                        + "\u001B[40m   1130\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m                     |\u001B[32mCS2113\u001B[0m\u001B[40m    |  \u001B[0m\n"
                        + "\u001B[40m   1200\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          +----------+                     +-\u001B[32mTUT[4]\u001B[0m\u001B[40m---+  \u001B[0m\n"
                        + "\u001B[40m   1230\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          |\u001B[32mCS2113\u001B[0m\u001B[40m    |                     :\u001B[0m\u001B[40m             \u001B[0m\n"
                        + "\u001B[40m   1300\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          +-\u001B[32mTUT[1]\u001B[0m\u001B[40m---+                     :\u001B[0m\u001B[40m             \u001B[0m\n"
                        + "\u001B[40m   1330\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          |\u001B[32mCS2113\u001B[0m\u001B[40m    |                     :\u001B[0m\u001B[40m             \u001B[0m\n"
                        + "\u001B[40m   1400\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          +-\u001B[32mTUT[2]\u001B[0m\u001B[40m---+                     :\u001B[0m\u001B[40m             \u001B[0m\n"
                        + "\u001B[40m   1430\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m                     :\u001B[0m\u001B[40m             \u001B[0m\n"
                        + "\u001B[40m   1500\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m                     :\u001B[0m\u001B[40m             \u001B[0m\n"
                        + "\u001B[40m   1530\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m                     :\u001B[0m\u001B[40m             \u001B[0m\n"
                        + "\u001B[40m   1600\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m                     +----------+  \u001B[0m\n"
                        + "\u001B[40m   1630\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m                     |\u001B[32mCS2113\u001B[0m\u001B[40m    |  \u001B[0m\n"
                        + "\u001B[40m   1700\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          +----------+----------+ \u001B[32mLEC[1]\u001B[0m\u001B[40m   |  \u001B[0m\n"
                        + "\u001B[40m   1730\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          |\u001B[32mCS2113\u001B[0m\u001B[40m    |\u001B[32mCS2113\u001B[0m\u001B[40m    |          |  \u001B[0m\n"
                        + "\u001B[40m   1800\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          +-\u001B[32mTUT[3]\u001B[0m\u001B[40m---+-\u001B[32mTUT[5]\u001B[0m\u001B[40m---+----------+  \u001B[0m\n"
                        + "\u001B[40m   1830\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m                     :\u001B[0m\u001B[40m             \u001B[0m\n"
                        + "\u001B[40m   1900\u001B[0m\u001B[40m   :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m          :\u001B[0m\u001B[40m                     :\u001B[0m\u001B[40m             \u001B[0m\n"
                        + "\u001B[40m                                                                               \u001B[0m\n"
                        + "\n";


        assertEquals(expectedOutput, outContent.toString());
    }
}
