package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Biometrics;
import seedu.duke.Parser;
import seedu.duke.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetCommandTest {

    @Test
    void execute_ValidSetArguments_UpdatedBiometrics() {
        Biometrics biometrics = new Biometrics();
        Ui ui = new Ui();
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
    void execute_NegativeInt_UnchangedBiometrics() {
        Biometrics biometrics = new Biometrics();
        Ui ui = new Ui();
        String command = "set biometrics";
        int age = -11;
        String gender = "male";
        int height = 172;
        int weight = 70;
        int fatPercentage = 22;
        String fullCommand = String.format("%s %d %s %d %d %d", command, age, gender, height, weight, fatPercentage);

        Command c = Parser.parse(fullCommand);
        c.setData(ui, biometrics, exerciseList);
        c.execute();
        assertEquals(0, biometrics.getAge());
        assertEquals("-", biometrics.getGender());
        assertEquals(0, biometrics.getHeight());
        assertEquals(0, biometrics.getWeight());
        assertEquals(0, biometrics.getFatPercentage());
    }

    @Test
    void execute_InvalidGender_UnchangedBiometrics() {
        Biometrics biometrics = new Biometrics();
        Ui ui = new Ui();
        String command = "set biometrics";
        int age = 22;
        String gender = "oher";
        int height = 172;
        int weight = 70;
        int fatPercentage = 22;
        String fullCommand = String.format("%s %d %s %d %d %d", command, age, gender, height, weight, fatPercentage);

        Command c = Parser.parse(fullCommand);
        c.setData(ui, biometrics, exerciseList);
        c.execute();
        assertEquals(0, biometrics.getAge());
        assertEquals("-", biometrics.getGender());
        assertEquals(0, biometrics.getHeight());
        assertEquals(0, biometrics.getWeight());
        assertEquals(0, biometrics.getFatPercentage());
    }
}