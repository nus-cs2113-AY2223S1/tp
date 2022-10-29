package seedu.duke.biometrics;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.biometrics.WeightAndFat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeightAndFatTest {
    private WeightAndFat weightAndFat;
    private final LocalDate date = LocalDate.parse("20-01-1999", DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    @Test
    void weightAndFat_invalidInput_exceptionThrown() {
        try {
            weightAndFat = new WeightAndFat(54, 121, date);
        } catch (IllegalValueException e) {
            assertEquals("Invalid fat percentage", e.getMessage());
        }
    }

    @Test
    void saveWeightAndFat_validWeightAndFat_weightAndFatString() throws IllegalValueException {
        weightAndFat = new WeightAndFat(80, 22, date);
        assertEquals("/80 /22 /20-01-1999", weightAndFat.saveWeightAndFat());
    }

}
