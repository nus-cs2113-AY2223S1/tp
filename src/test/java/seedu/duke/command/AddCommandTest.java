package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.Food;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddCommandTest {

    Biometrics biometrics = new Biometrics();
    ExerciseList exerciseList = new ExerciseList();
    FoodList foodList = new FoodList();
    RecordList recordList = new RecordList();
    Ui ui = new Ui();
    Storage storage = new Storage();

    @Test
    void execute_validFood_successfullyAddToList() throws IllegalValueException {
        String input = "add food /nasi lemak /400 /01-04-2015";
        Command c = Parser.parse(input);
        c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        c.execute();
        Food food = foodList.getFoodList().get(0);
        assertEquals(food.getFoodDescription(), "nasi lemak");
        assertEquals(food.getCalories(), 400);
        assertEquals(food.getDateString(), "01-04-2015");
    }

    @Test
    public void addCommand_invalidFoodDescription_throwsException() {
        String command = "add food";
        int calories = 100;
        String fullCommand;
        final String[] invalidFoodNames = {"", " ", "[]\\[;]"};
        for (String foodName : invalidFoodNames) {
            fullCommand = String.format("%s /%s /%d",
                    command, foodName, calories);
            Command c = Parser.parse(fullCommand);
            try {
                c.execute();
                fail();
            } catch (IllegalValueException e) {
                assertEquals("Please provide valid food description inputs!", e.getMessage());
            }
        }
    }


    @Test
    void execute_negativeCalories_exceptionThrown() {
        String command = "add food";
        String foodName = "chicken rice";
        int calories = -112;
        String fullCommand = String.format("%s /%s /%d",
                command, foodName, calories);
        Command c = Parser.parse(fullCommand);
        try {
            c.execute();
            fail();
        } catch (IllegalValueException e) {
            assertEquals("Calories inputs need to be positive integer values!", e.getMessage());
        }

    }

    @Test
    void execute_validWeightAndFat_successfullyAddToList() throws IllegalValueException {
        String input = "add weight /74 /17 /11-08-2011";
        Command c = Parser.parse(input);
        c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        c.execute();
        WeightAndFat weightAndFat = biometrics.weightAndFatList.getWeightAndFatList().get(0);
        assertEquals(weightAndFat.getWeight(), 74);
        assertEquals(weightAndFat.getFat(), 17);
        assertEquals(weightAndFat.getDateString(), "11-08-2011");
    }

    @Test
    void execute_invalidDate_exceptionThrown() {
        String input = "add weight /74 /17 /11082011";
        Command c = Parser.parse(input);
        c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        try {
            c.execute();
        } catch (IllegalValueException e) {
            assertEquals(e.getMessage(), "Date is in the wrong format or invalid. Please follow the dd-MM-yyyy format");
        }
    }

    @Test
    void execute_negativeDistance_throwsException() {
        String command = "add cardio ";
        String exerciseName = "sprints";
        int distance = -5;
        int repetitions = 2;
        String fullCommand = String.format("%s /%s /%d /%d",
                command, exerciseName, distance, repetitions);
        Command c = Parser.parse(fullCommand);
        c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        try {
            c.execute();
        } catch (IllegalValueException e) {
            assertEquals("Invalid value for distance", e.getMessage());
        }
    }

    @Test
    void execute_invalidFormatRepetitions_throwsException() {
        String command = "add cardio ";
        String exerciseName = "sprints";
        double distance = 3.5;
        double repetitions = 2.5;
        String fullCommand = String.format("%s /%s /%f /%f",
                command, exerciseName, distance, repetitions);
        Command c = Parser.parse(fullCommand);
        c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        try {
            c.execute();
        } catch (IllegalValueException e) {
            assertEquals("Distance must be in numbers and repetitions must be integers", e.getMessage());
        }
    }

    @Test
    void execute_invalidRepetitions_throwsException() {
        String command = "add cardio ";
        String exerciseName = "sprints";
        double distance = 3.5;
        int repetitions = -5;
        String fullCommand = String.format("%s /%s /%f /%d",
                command, exerciseName, distance, repetitions);
        Command c = Parser.parse(fullCommand);
        c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        try {
            c.execute();
        } catch (IllegalValueException e) {
            assertEquals("Invalid value for repetitions", e.getMessage());
        }
    }

}