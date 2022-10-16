package seedu.duke.biometrics;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.IllegalValueException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightAndFatListTest {

    private WeightAndFatList weightAndFatList;

    @Test
    void addWeightAndFat_validWeightAndFat_weightAndFatAdded() throws IllegalValueException {
        weightAndFatList = new WeightAndFatList();
        WeightAndFat weightAndFat = new WeightAndFat(70, 32);
        weightAndFatList.addWeightAndFat(weightAndFat);
        assertEquals(weightAndFat, weightAndFatList.getWeightAndFatList().get(0));
    }
}
