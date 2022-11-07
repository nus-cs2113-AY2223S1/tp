package seedu.duke.exercise;

import org.junit.jupiter.api.Test;
import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.logic.Parser;
import seedu.duke.logic.command.Command;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ExerciseListTest {
    private final Ui ui = new Ui();
    private final Storage storage = new Storage();
    private final Biometrics biometrics = new Biometrics();

    private FoodList foodList = new FoodList();
    private RecordList recordList = new RecordList();

    @Test
    void getCompletedExercise_outOfBoundIndex_exceptionThrown() {
        ExerciseList exerciseList = new ExerciseList();
        try {
            addExercise(exerciseList);
        } catch (IllegalValueException e) {
            fail();
        }
        int completedExerciseListSize = exerciseList.getCompletedExerciseListSize();
        try {
            exerciseList.getCompletedExercise(completedExerciseListSize);
            fail();
        } catch (IllegalValueException e) {
            assertEquals("Exercise not found", e.getMessage());
        }
        try {
            exerciseList.getCompletedExercise(-1);
            fail();
        } catch (IllegalValueException e) {
            assertEquals("Exercise not found", e.getMessage());
        }
    }

    @Test
    void getCurrentExercise_outOfBoundIndex_exceptionThrown() {
        ExerciseList exerciseList = new ExerciseList();
        try {
            addExercise(exerciseList);
        } catch (IllegalValueException e) {
            fail();
        }
        int currentExerciseListSize = exerciseList.getCurrentExerciseListSize();
        try {
            exerciseList.getCurrentExercise(currentExerciseListSize);
            fail();
        } catch (IllegalValueException e) {
            assertEquals("Exercise not found", e.getMessage());
        }
        try {
            exerciseList.getCurrentExercise(-1);
            fail();
        } catch (IllegalValueException e) {
            assertEquals("Exercise not found", e.getMessage());
        }
    }

    private void addExercise(ExerciseList exerciseList) throws IllegalValueException {
        ArrayList<String> commandList = new ArrayList<>();
        commandList.add("add strength /bench /3 /10 /18");
        commandList.add("add strength /press /4 /8 /38");
        commandList.add("add strength /deadlift /2 /6 /20");

        for (String input : commandList) {
            Command c = Parser.parse(input);
            c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
            c.execute();
        }
    }

}