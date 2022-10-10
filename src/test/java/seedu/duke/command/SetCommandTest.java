package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Biometrics;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.ExerciseList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SetCommandTest {

    @Test
    void execute_ValidSetArguments_UpdatedBiometrics() throws IllegalValueException {
        Biometrics biometrics = new Biometrics();
        Ui ui = new Ui();
        ExerciseList exerciseList = new ExerciseList();
        String command = "set biometrics";
        int age = 22;
        String gender = "male";
        int height = 172;
        int weight = 70;
        int fatPercentage = 22;
        String fullCommand = String.format("%s %d %s %d %d %d", command, age, gender, height, weight, fatPercentage);

        Command c = Parser.parse(fullCommand);
        c.setData(ui, biometrics, exerciseList);
        c.execute();
        assertEquals(age, biometrics.getAge());
        assertEquals(gender, biometrics.getGender());
        assertEquals(height, biometrics.getHeight());
        assertEquals(weight, biometrics.getWeight());
        assertEquals(fatPercentage, biometrics.getFatPercentage());
    }

    @Test
    void execute_NegativeInt_exceptionThrown() {
        Biometrics biometrics = new Biometrics();
        Ui ui = new Ui();
        ExerciseList exerciseList = new ExerciseList();
        String command = "set biometrics";
        int age = -11;
        String gender = "male";
        int height = 172;
        int weight = 70;
        int fatPercentage = 22;
        String fullCommand = String.format("%s %d %s %d %d %d", command, age, gender, height, weight, fatPercentage);

        Command c = Parser.parse(fullCommand);
        c.setData(ui, biometrics, exerciseList);
        try {
            c.execute();
            fail();
        } catch (IllegalValueException e) {
            assertEquals("Invalid biometrics", e.getMessage());
        }

    }

    @Test
    void execute_InvalidGender_UnchangedBiometrics() {
        Biometrics biometrics = new Biometrics();
        Ui ui = new Ui();
        ExerciseList exerciseList = new ExerciseList();
        String command = "set biometrics";
        int age = 22;
        String gender = "oher";
        int height = 172;
        int weight = 70;
        int fatPercentage = 22;
        String fullCommand = String.format("%s %d %s %d %d %d", command, age, gender, height, weight, fatPercentage);

        Command c = Parser.parse(fullCommand);
        c.setData(ui, biometrics, exerciseList);
        try {
            c.execute();
            fail();
        } catch (IllegalValueException e) {
            assertEquals("Invalid biometrics", e.getMessage());
        }
    }
}