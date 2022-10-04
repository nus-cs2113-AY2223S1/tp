package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Biometrics;
import seedu.duke.Parser;
import seedu.duke.Ui;

import static org.junit.jupiter.api.Assertions.*;

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
        c.setData(ui, biometrics);
        c.execute();
        assertAll("biometrics", () -> assertEquals(age, biometrics.getAge()),
                () -> assertEquals(gender, biometrics.getGender()),
                () -> assertEquals(height, biometrics.getHeight()),
                () -> assertEquals(weight, biometrics.getWeight()),
                () -> assertEquals(fatPercentage, biometrics.getFatPercentage()));
    }

    @Test
    void execute_InvalidSetArguments_UnchangedBiometrics() {
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
        c.setData(ui, biometrics);
        c.execute();
        assertAll("biometrics", () -> assertEquals(0, biometrics.getAge()),
                () -> assertEquals("-", biometrics.getGender()),
                () -> assertEquals(0, biometrics.getHeight()),
                () -> assertEquals(0, biometrics.getWeight()),
                () -> assertEquals(0, biometrics.getFatPercentage()));
    }
}