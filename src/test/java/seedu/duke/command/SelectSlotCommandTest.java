package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SelectSlotCommandTest {

    @Test
    void addModuleCommand_validInputsToChangeCS2113Tutorial_expectCorrectChangeInTutorial() throws YamomException {
        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();

        // add cs2113 to timetable. cs2113 has tutorial defaults to tut 1
        String[] input1 = {"add", "cs2113"};
        AddModuleCommand addModuleCommand = new AddModuleCommand(input1);
        addModuleCommand.execute(state, ui, storage);

        // change tutorial slot to tut 3
        String input = "select /module cs2113 /type tut /code 3";
        SelectSlotCommand selectSlotCommand = new SelectSlotCommand(input);
        selectSlotCommand.execute(state, ui, storage);

        // view timetable to check if the tutorial slot has been changed to T03
        // capture the output of view timetable command as the actual output
        String input2 = "view";
        ViewTimetableCommand viewTimetableCommand = new ViewTimetableCommand(input2);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        viewTimetableCommand.execute(state, ui, storage);

        String expectedOutput =
                "                                                                    \n"
                        + "          : Mon      : Tues     : Wed      : Thur     : Fri         \n"
                        + "====================================================================\n"
                        + "   1600   :          :          :          :          +----------+  \n"
                        + "   1630   :          :          :          :          |CS2113    |  \n"
                        + "   1700   :          :          :          +----------+ LEC[1]   |  \n"
                        + "   1730   :          :          :          |CS2113    |          |  \n"
                        + "   1800   :          :          :          +-TUT[3]---+----------+  \n"
                        + "   1830   :          :          :          :          :             \n"
                        + "   1900   :          :          :          :          :             \n"
                        + "                                                                    \n";

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

    }

    @Test
    void addModuleCommand_validInputsToChangeIE2141Lecture_expectCorrectChangeInLecture() throws YamomException {
        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();

        // add ie2141 to timetable. ie2141 lecture slots default to lec 1
        String[] input1 = {"add", "ie2141"};
        AddModuleCommand addModuleCommand = new AddModuleCommand(input1);
        addModuleCommand.execute(state, ui, storage);

        // change lecture slot to lec 2
        String input = "select /module ie2141 /type lec /code 2";
        SelectSlotCommand selectSlotCommand = new SelectSlotCommand(input);
        selectSlotCommand.execute(state, ui, storage);

        // view timetable to check if the tutorial slot has been changed to T03
        // capture the output of the view timetable command as the actual output
        String input2 = "view";
        ViewTimetableCommand viewTimetableCommand = new ViewTimetableCommand(input2);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        viewTimetableCommand.execute(state, ui, storage);

        String expectedOutput =
                "                                                                    \n"
                        + "          : Mon      : Tues     : Wed      : Thur     : Fri         \n"
                        + "====================================================================\n"
                        + "   0900   :          +----------+          :          :             \n"
                        + "   0930   :          |IE2141    |          :          :             \n"
                        + "   1000   :          | TUT[01]  |          :          :             \n"
                        + "   1030   :          |          |          :          :             \n"
                        + "   1100   :          +----------+          :          :             \n"
                        + "   1130   :          :          :          :          :             \n"
                        + "   1200   :          :          :          :          :             \n"
                        + "   1230   :          :          :          :          :             \n"
                        + "   1300   :          :          :          :          :             \n"
                        + "   1330   :          :          :          :          :             \n"
                        + "   1400   :          :          :          :          :             \n"
                        + "   1430   :          :          :          :          :             \n"
                        + "   1500   :          :          :          :          :             \n"
                        + "   1530   :          :          :          :          :             \n"
                        + "   1600   +----------+          :          :          :             \n"
                        + "   1630   |IE2141    |          :          :          :             \n"
                        + "   1700   | LEC[2]   |          :          :          :             \n"
                        + "   1730   |          |          :          :          :             \n"
                        + "   1800   +----------+          :          :          :             \n"
                        + "   1830   :          :          :          :          :             \n"
                        + "   1900   :          :          :          :          :             \n"
                        + "                                                                    \n";

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    void addModuleCommand_invalidInputsEmptyParams_exceptionThrown() {
        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();
        String s = System.lineSeparator();
        try {
            String input = "select";
            SelectSlotCommand selectSlotCommand = new SelectSlotCommand(input);
            selectSlotCommand.execute(state, ui, storage);
            fail();
        } catch (YamomException e) {
            assertEquals("Error! \tWrong format given, should be " + s
                    + "\tselect [ /module [EXACT_MODULE_CODE] /type [LESSON_TYPE] /code [CLASS_NO] ]", e.getMessage());
        }
    }

    @Test
    void addModuleCommand_invalidInputsEmptyParamsValue_exceptionThrown() {
        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();
        String s = System.lineSeparator();
        try {
            String input = "select /module /type /code";
            SelectSlotCommand selectSlotCommand = new SelectSlotCommand(input);
            selectSlotCommand.execute(state, ui, storage);
            fail();
        } catch (YamomException e) {
            assertEquals("Error! \tWrong format given, should be " + s
                            + "\tselect [ /module [EXACT_MODULE_CODE] /type [LESSON_TYPE] /code [CLASS_NO] ]" + s
                            + s
                            + "You might have missed out the Module Code, Lesson Type or Class No.",
                    e.getMessage());
        }
    }

    @Test
    void addModuleCommand_invalidInputsIncompleteParams_exceptionThrown() {
        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();
        String s = System.lineSeparator();
        try {
            String input = "select /module cs2113 /type";
            SelectSlotCommand selectSlotCommand = new SelectSlotCommand(input);
            selectSlotCommand.execute(state, ui, storage);
            fail();
        } catch (YamomException e) {
            assertEquals("Error! \tWrong format given, should be " + s
                            + "\tselect [ /module [EXACT_MODULE_CODE] /type [LESSON_TYPE] /code [CLASS_NO] ]",
                    e.getMessage());
        }
    }
}