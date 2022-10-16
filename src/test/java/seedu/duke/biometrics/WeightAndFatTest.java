package seedu.duke.biometrics;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.IllegalValueException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeightAndFatTest {
    private WeightAndFat weightAndFat;

    @Test
    void weightAndFat_invalidInput_exceptionThrown() {
        try {
            weightAndFat = new WeightAndFat(54, 121);
        } catch (IllegalValueException e) {
            assertEquals("Invalid fat percentage", e.getMessage());
        }
    }

    @Test
    void saveWeightAndFat_validWeightAndFat_weightAndFatString() throws IllegalValueException {
        weightAndFat = new WeightAndFat(80, 22);
        assertEquals("/80 /22", weightAndFat.saveWeightAndFat());
    }

}
