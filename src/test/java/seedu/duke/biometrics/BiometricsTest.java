package seedu.duke.biometrics;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.IllegalValueException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiometricsTest {

    Biometrics biometrics = new Biometrics();

    @Test
    void saveBiometrics_biometricsSet_biometricsString() throws IllegalValueException {
        biometrics.setBiometrics(10, "female", 87, 72, 64);
        assertEquals("/10 /female /87 /72 /64", biometrics.saveBiometrics());
    }
}
