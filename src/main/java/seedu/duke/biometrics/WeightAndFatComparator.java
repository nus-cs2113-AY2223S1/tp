package seedu.duke.biometrics;

import java.util.Comparator;

public class WeightAndFatComparator implements Comparator<WeightAndFat> {

    @Override
    public int compare(WeightAndFat weightAndFat1, WeightAndFat weightAndFat2) {
        return -1 * weightAndFat1.getDate().compareTo(weightAndFat2.getDate());
    }
}
