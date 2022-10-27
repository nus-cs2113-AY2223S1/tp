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

        // set to semester 1
        state.setSemester(1);

        // add cs2113 to timetable. cs2113 has tutorial defaults to tut 1
        String[] input1 = {"add", "cs2113"};
        AddModuleCommand addModuleCommand = new AddModuleCommand(input1);
        addModuleCommand.execute(state, ui, storage);

        // change tutorial slot to tut 3
        String input = "select /module cs2113 /type tut /code 3";
        SelectSlotCommand selectSlotCommand = new SelectSlotCommand(input);
        selectSlotCommand.execute(state, ui, storage);

        String expectedOutput = state.getSelectedModulesList().get(0).getSelectedSlots().toString();

        // selected module list should have cs2113 with lecture 1, tutorial 3
        assertEquals("{LECTURE=1, TUTORIAL=3}", expectedOutput);
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

        String expectedOutput = state.getSelectedModulesList().get(0).getSelectedSlots().toString();

        // selected module list should have cs2113 with lecture 1, tutorial 3
        assertEquals("{LECTURE=2, TUTORIAL=01}", expectedOutput);
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