package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.exercise.ExerciseList;
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
        assertEquals(weightAndFat.getDate(), "11-08-2011");
    }

    @Test
    void execute_invalidDate_exceptionThrown() {
        String input = "add weight /74 /17 /11082011";
        Command c = Parser.parse(input);
        c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        try {
            c.execute();
        } catch (IllegalValueException e) {
            assertEquals(e.getMessage(), "Date should be in the format dd-mm-yyyy");
        }
    }

}