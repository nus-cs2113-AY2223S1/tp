package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.records.Record;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


class MarkCommandTest {

    private final Ui ui = new Ui();
    private final Biometrics biometrics = new Biometrics();
    private final FoodList foodList = new FoodList();
    private final RecordList recordList = new RecordList();
    private final Storage storage = new Storage();

    @Test
    void execute_validMarkDoneCommand_markAsDone() throws IllegalValueException {
        ExerciseList exerciseList = new ExerciseList();
        addExercise(exerciseList);
        int currentExerciseListSize = getCurrentExerciseListSize(exerciseList);
        int completedExerciseListSize = getCompletedExerciseListSize(exerciseList);
        Exercise exercise = markExerciseAsDone(exerciseList, 1);
        assertValidMarkAsDone(exerciseList, exercise, currentExerciseListSize, completedExerciseListSize);
    }

    @Test
    void execute_validMarkUnDoneCommand_markAsUnDone() throws IllegalValueException {
        ExerciseList exerciseList = new ExerciseList();
        addExercise(exerciseList);
        markExerciseAsDone(exerciseList, 1);
        int currentExerciseListSize = getCurrentExerciseListSize(exerciseList);
        int completedExerciseListSize = getCompletedExerciseListSize(exerciseList);
        Exercise exercise = markExerciseAsUndone(exerciseList, 1);
        assertValidMarkAsUnDone(exerciseList, exercise, currentExerciseListSize, completedExerciseListSize);
    }

    @Test
    void execute_MarkWithNoParameter_exceptionThrown() {
        ExerciseList exerciseList = new ExerciseList();
        try {
            addExercise(exerciseList);
        } catch (IllegalValueException e) {
            fail();
        }
        String input = "mark";
        assertInvalidMarkCommand(input, "Invalid mark command", exerciseList);
    }

    @Test
    void execute_MarkWithOneParameter_exceptionThrown() {
        ExerciseList exerciseList = new ExerciseList();
        try {
            addExercise(exerciseList);
        } catch (IllegalValueException e) {
            fail();
        }

        String[] testInputList = {"mark x", "mark done", "mark undone"};
        for (String input : testInputList) {
            assertInvalidMarkCommand(input, "Invalid mark command", exerciseList);
        }
    }

    @Test
    void execute_MarkWithTwoParametersWithNonIntegerIndex_exceptionThrown() {
        ExerciseList exerciseList = new ExerciseList();
        try {
            addExercise(exerciseList);
        } catch (IllegalValueException e) {
            fail();
        }
        String[] testInputList = {"mark done /0x", "mark undone /0a"};
        for (String input : testInputList) {
            assertInvalidMarkCommand(input, "Index must be an integer", exerciseList);
        }
    }

    @Test
    void execute_MarkUndoneWithTwoParametersWithOutOfBoundIndex_exceptionThrown() {
        ExerciseList exerciseList = new ExerciseList();
        try {
            addExercise(exerciseList);
            markExerciseAsDone(exerciseList, 1);
        } catch (IllegalValueException e) {
            fail();
        }
        int currentExerciseListSize = exerciseList.getCurrentExerciseListSize();
        int completedExerciseListSize = exerciseList.getCompletedExerciseListSize();
        ArrayList<String> testInputList = new ArrayList<>();
        testInputList.add("mark undone /-1");
        testInputList.add("mark undone /" + completedExerciseListSize + 1);
        for (String input : testInputList) {
            assertInvalidMarkCommand(input, "Exercise not found", exerciseList);
        }
    }

    @Test
    void execute_MarkUnDoneWithMoreThanTwoParameters() {
        ExerciseList exerciseList = new ExerciseList();
        try {
            addExercise(exerciseList);
            markExerciseAsDone(exerciseList, 1);
        } catch (IllegalValueException e) {
            fail();
        }
        ArrayList<String> testInputList = new ArrayList<>();
        testInputList.add("mark undone /0 /xx");
        testInputList.add("mark undone /2 /2");
        for (String input : testInputList) {
            assertInvalidMarkCommand(input, "Invalid mark undone command", exerciseList);
        }
    }

    private void assertInvalidMarkCommand(String input, String expectedMessage, ExerciseList exerciseList) {
        Command command = Parser.parse(input);
        command.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        try {
            command.execute();
            fail();
        } catch (Exception e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    private Exercise markExerciseAsUndone(ExerciseList exerciseList, int index) throws IllegalValueException {
        Exercise exercise = null;
        try {
            exercise = exerciseList.getCompletedExercise(index - 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String validMarkAsUndoneInput = "mark undone /" + index;
        Command command = Parser.parse(validMarkAsUndoneInput);
        command.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        command.execute();
        return exercise;
    }

    private Exercise markExerciseAsDone(ExerciseList exerciseList, int index) throws IllegalValueException {
        Exercise exercise = null;
        try {
            exercise = exerciseList.getCurrentExercise(index - 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String input = "mark done /" + index + " /20 /5";
        Command command = Parser.parse(input);
        command.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        command.execute();
        return exercise;
    }

    private static void assertValidMarkAsUnDone(ExerciseList exerciseList, Exercise exercise,
                                                int currentExerciseListSize, int completedExerciseListSize) {
        assertFalse(exercise.getDone());
        assertEquals(currentExerciseListSize + 1, exerciseList.getCurrentExerciseListSize());
        assertEquals(completedExerciseListSize - 1, exerciseList.getCompletedExerciseListSize());
    }

    private static void assertValidMarkAsDone(ExerciseList exerciseList, Exercise exercise,
                                              int currentExerciseListSize, int completedExerciseListSize) {
        assertTrue(exercise.getDone());
        assertEquals(currentExerciseListSize - 1, exerciseList.getCurrentExerciseListSize());
        assertEquals(completedExerciseListSize + 1, exerciseList.getCompletedExerciseListSize());
    }

    private static int getCompletedExerciseListSize(ExerciseList exerciseList) {
        return exerciseList.getCompletedExerciseListSize();
    }

    private static int getCurrentExerciseListSize(ExerciseList exerciseList) {
        return exerciseList.getCurrentExerciseListSize();
    }

    private void addExercise(ExerciseList exerciseList) throws IllegalValueException {
        ArrayList<String> commandList = new ArrayList<>();
        commandList.add("add strength /bench /30 /10 /180");
        commandList.add("add strength /press /40 /8 /58");
        commandList.add("add strength /deadlift /20 /6 /120");
        biometrics.setBiometrics(20, "male", 166, 76, 20,2);

        for (String input : commandList) {
            Command c = Parser.parse(input);
            c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
            c.execute();
        }
    }

}