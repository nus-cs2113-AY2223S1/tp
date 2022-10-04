package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_setBiometricsWithValidParameters_biometricsSet() {
        Parser parser = new Parser();
        Biometrics biometrics = new Biometrics();
        String command = "set biometrics";
        int age = 22;
        String gender = "male";
        int height = 172;
        int weight = 70;
        int fatPercentage = 22;
        String fullCommand = String.format("%s %d %s %d %d %d", command, age, gender, height, weight, fatPercentage);
        assertAll("biometrics", () -> assertEquals("biometrics set", parser.parse(fullCommand, biometrics)),
                () -> assertEquals(age, biometrics.getAge()),
                () -> assertEquals(gender, biometrics.getGender()),
                () -> assertEquals(height, biometrics.getHeight()),
                () -> assertEquals(weight, biometrics.getWeight()),
                () -> assertEquals(fatPercentage, biometrics.getFatPercentage()));
    }

    @Test
    void parse_setBiometricsWithInvalidParameters_expectException() {
        Parser parser = new Parser();
        Biometrics biometrics = new Biometrics();
        String command = "set biometrics";
        int age = 22;
        String gender = "ale";
        int height = 172;
        int weight = 0;
        int fatPercentage = 22;
        String fullCommand = String.format("%s %d %s %d %d %d", command, age, gender, height, weight, fatPercentage);
        assertEquals("Invalid biometrics", parser.parse(fullCommand, biometrics));
    }
}