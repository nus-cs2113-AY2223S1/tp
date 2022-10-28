package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.model.LessonType;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.model.Module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SelectSlotCommandTest {

    @Test
    void selectSlotCommand_validInputsToChangeCS2113Tutorial_expectSuccessfulChangeInTutorial() throws YamomException {
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
    void selectSlotCommand_validInputsToChangeIE2141Lecture_expectSuccessfulChangeInLecture() throws YamomException {
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

        // selected module list should have cs2141 with lecture 2, tutorial 01
        assertEquals("{LECTURE=2, TUTORIAL=01}", expectedOutput);
    }

    @Test
    void selectSlotCommand_invalidInputsEmptyParams_exceptionThrown() {
        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();

        assertThrows(YamomException.class, () -> {
            String input = "select";
            SelectSlotCommand selectSlotCommand = new SelectSlotCommand(input);
            selectSlotCommand.execute(state, ui, storage);
        });
    }

    @Test
    void selectSlotCommand_invalidInputsEmptyParamsValue_exceptionThrown() {
        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();

        assertThrows(YamomException.class, () -> {
            String input = "select /module /type /code";
            SelectSlotCommand selectSlotCommand = new SelectSlotCommand(input);
            selectSlotCommand.execute(state, ui, storage);
        });
    }

    @Test
    void selectSlotCommand_invalidInputsIncompleteParams_exceptionThrown() {
        State state = new State();
        Ui ui = new Ui();
        Storage storage = new Storage();

        assertThrows(YamomException.class, () -> {
            String input = "select /module cs2113 /type";
            SelectSlotCommand selectSlotCommand = new SelectSlotCommand(input);
            selectSlotCommand.execute(state, ui, storage);
        });
    }

    @Test
    void validateLessonTypeAndClassNo_enteredCorrectLessonTypeAndClassNumber_expectTrue() {
        State state = new State();

        Module selectedModule = Module.get("cs1010s");

        // CS1010S has no lab lesson type
        LessonType lessonType = LessonType.TUTORIAL;

        // classNo 24C is a valid classNo for CS1010S tutorial
        String classNo = "24C";

        // set to semester 1
        state.setSemester(1);

        assertTrue(SelectSlotCommand.validateLessonTypeAndClassNo(selectedModule, lessonType, classNo, state));
    }

    @Test
    void validateLessonTypeAndClassNo_inputWrongClassNo_expectFalse() {
        State state = new State();

        Module selectedModule = Module.get("cs1010s");

        // CS1010S has tutorial lesson type
        LessonType lessonType = LessonType.TUTORIAL;

        // classNo 2 is an invalid classNo for CS1010S tutorial
        String classNo = "2";

        // set to semester 1
        state.setSemester(1);

        assertFalse(SelectSlotCommand.validateLessonTypeAndClassNo(selectedModule, lessonType, classNo, state));
    }

    @Test
    void validateLessonTypeAndClassNo_inputNonExistingLessonType_expectFalse() {
        State state = new State();

        Module selectedModule = Module.get("cs1010s");

        // CS1010S has no lab lesson type
        LessonType lessonType = LessonType.LABORATORY;

        // classNo 02A is a valid classNo for CS1010S tutorial
        String classNo = "02A";

        // set to semester 1
        state.setSemester(1);

        assertFalse(SelectSlotCommand.validateLessonTypeAndClassNo(selectedModule, lessonType, classNo, state));
    }

    @Test
    void validateLessonTypeAndClassNo_inputNonExistingLessonTypeAndClassNo_expectFalse() {
        State state = new State();

        Module selectedModule = Module.get("cs1010s");

        // CS1010S has no lab lesson type
        LessonType lessonType = LessonType.LABORATORY;

        // classNo 02A is a valid classNo for CS1010S tutorial
        String classNo = "17284";

        // set to semester 1
        state.setSemester(1);

        assertFalse(SelectSlotCommand.validateLessonTypeAndClassNo(selectedModule, lessonType, classNo, state));
    }

}