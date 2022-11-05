package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
<<<<<<< HEAD
import seedu.duke.ui.Ui;
=======
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
>>>>>>> master
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.ExerciseList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

class RemoveCommandTest {

    private FoodList foodList = new FoodList();
    private Biometrics biometrics = new Biometrics();
    private Ui ui = new Ui();
    private ExerciseList exerciseList = new ExerciseList();
    private RecordList recordList = new RecordList();
    private final Storage storage = new Storage();

    @BeforeEach
    public void setUp() throws Exception {
        ArrayList<String> commandList = new ArrayList<>();
        commandList.add("add food /cola /100");
        commandList.add("add food /chicken rice /250");
        commandList.add("add food /ice cream /300");


        for (String input : commandList) {
            Command c = Parser.parse(input);
            c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
            c.execute();
        }
    }

    private void addExercise(ExerciseList exerciseList) throws IllegalValueException {
        ArrayList<String> commandList = new ArrayList<>();
        commandList.add("add strength /bench /30 /10 /40");
        commandList.add("add cardio /sprints /3.5 /5");
        commandList.add("add strength /squat /100 /5 /5");

        for (String input : commandList) {
            Command c = Parser.parse(input);
            c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
            c.execute();
        }
    }

    @Test
    void execute_RemoveWithNoParameter_exceptionThrown() {
        String command = "remove food";
        assertInvalidRemoveFoodCommand(command, "Invalid number input");
    }


    @Test
    void execute_RemoveWithNonIntegerIndex_exceptionThrown() {
        String command = "remove food /0x";
        assertInvalidRemoveFoodCommand(command, "Index should be an integer");
    }


    @Test
    void execute_RemoveNonIntegerIndex_exceptionThrown() {
        ExerciseList exerciseList = new ExerciseList();
        try {
            addExercise(exerciseList);
        } catch (IllegalValueException e) {
            fail();
        }
        ArrayList<String> testInputList = new ArrayList<>();
        testInputList.add("remove exercise /1.5");
        testInputList.add("remove exercise /johnny");
        for (String input : testInputList) {
            assertInvalidRemoveExerciseCommand(input, "Index should be an integer", exerciseList);
        }
    }

    private void assertInvalidRemoveExerciseCommand(String input, String expectedMessage, ExerciseList exerciseList) {
        Command command = Parser.parse(input);
        command.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        try {
            command.execute();
            fail();
        } catch (Exception e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    private void assertInvalidRemoveFoodCommand(String input, String expectedMessage) {
        Command command = Parser.parse(input);
        try {
            command.execute();
            fail();
        } catch (Exception e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

}