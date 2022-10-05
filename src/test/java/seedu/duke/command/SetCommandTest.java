package seedu.duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.Biometrics;
import seedu.duke.Parser;
import seedu.duke.Ui;

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
        Assertions.assertEquals(age, biometrics.getAge());
        Assertions.assertEquals(gender, biometrics.getGender());
        Assertions.assertEquals(height, biometrics.getHeight());
        Assertions.assertEquals(weight, biometrics.getWeight());
        Assertions.assertEquals(fatPercentage, biometrics.getFatPercentage());
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
        Assertions.assertEquals(0, biometrics.getAge());
        Assertions.assertEquals("-", biometrics.getGender());
        Assertions.assertEquals(0, biometrics.getHeight());
        Assertions.assertEquals(0, biometrics.getWeight());
        Assertions.assertEquals(0, biometrics.getFatPercentage());
    }
}