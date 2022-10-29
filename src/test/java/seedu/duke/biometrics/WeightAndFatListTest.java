package seedu.duke.biometrics;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.biometrics.WeightAndFatList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightAndFatListTest {

    private WeightAndFatList weightAndFatList;
    private final LocalDate date = LocalDate.now();


    @Test
    void addWeightAndFat_validWeightAndFat_weightAndFatAdded() throws IllegalValueException {
        weightAndFatList = new WeightAndFatList();
        WeightAndFat weightAndFat = new WeightAndFat(70, 32, date);
        weightAndFatList.addWeightAndFat(weightAndFat);
        assertEquals(weightAndFat, weightAndFatList.getWeightAndFatList().get(0));
    }
}
