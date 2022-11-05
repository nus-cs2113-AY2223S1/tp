package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.Parser;
import seedu.duke.ui.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SetCommandTest {

    private final FoodList foodList = new FoodList();
    private final RecordList recordList = new RecordList();
    private final Storage storage = new Storage();

    @Test
    void execute_validSetArguments_updatedBiometrics() throws IllegalValueException {
        Biometrics biometrics = new Biometrics();
        Ui ui = new Ui();
        ExerciseList exerciseList = new ExerciseList();
        String command = "set biometrics";
        int age = 22;
        String gender = "male";
        int height = 172;
        int activitylevel = 2;
        String fullCommand = String.format("%s /%d /%s /%d /%d",
                command, age, gender, height, activitylevel);

        Command c = Parser.parse(fullCommand);
        c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        c.execute();
        assertEquals(age, biometrics.getAge());
        assertEquals(gender, biometrics.getGender());
        assertEquals(height, biometrics.getHeight());
    }

    @Test
    void execute_negativeInt_exceptionThrown() {
        Biometrics biometrics = new Biometrics();
        Ui ui = new Ui();
        ExerciseList exerciseList = new ExerciseList();
        String command = "set biometrics";
        int age = -11;
        String gender = "male";
        int height = 172;
        int activitylevel = 2;
        String fullCommand = String.format("%s /%d /%s /%d /%d",
                command, age, gender, height, activitylevel);

        Command c = Parser.parse(fullCommand);
        c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        try {
            c.execute();
            fail();
        } catch (IllegalValueException e) {
            assertEquals("That age ain't possible", e.getMessage());
        }

    }

    @Test
    void execute_invalidGender_unchangedBiometrics() {
        Biometrics biometrics = new Biometrics();
        Ui ui = new Ui();
        ExerciseList exerciseList = new ExerciseList();
        String command = "set biometrics";
        int age = 22;
        String gender = "oher";
        int height = 172;
        int activitylevel = 2;
        String fullCommand = String.format("%s /%d /%s /%d /%d",
                command, age, gender, height, activitylevel);

        Command c = Parser.parse(fullCommand);
        c.setData(ui, storage, biometrics, exerciseList, foodList, recordList);
        try {
            c.execute();
            fail();
        } catch (IllegalValueException e) {
            assertEquals("Hi, I only recognise others, female and male genders", e.getMessage());
        }
    }
}