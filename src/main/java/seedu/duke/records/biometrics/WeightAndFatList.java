package seedu.duke.records.biometrics;

import seedu.duke.logic.exception.IllegalValueException;

import java.util.ArrayList;

public class WeightAndFatList {

    private final ArrayList<WeightAndFat> weightAndFatArrayList;

    public WeightAndFatList() {
        weightAndFatArrayList = new ArrayList<>();
    }

    public void addWeightAndFat(WeightAndFat weightAndFat) {
        weightAndFatArrayList.add(weightAndFat);
        weightAndFatArrayList.sort(new WeightAndFatComparator());
    }

    public WeightAndFat getMostRecent() {
        assert (weightAndFatArrayList.size() > 0);
        return weightAndFatArrayList.get(0);
    }

    public WeightAndFat removeWeightAndFat(int index) throws IllegalValueException {
        if (index < 0 || index >= weightAndFatArrayList.size()) {
            throw new IllegalValueException("Weight and fat record does not exist");
        }
        return weightAndFatArrayList.remove(index);
    }

    public int getSize() {
        return weightAndFatArrayList.size();
    }

    public ArrayList<WeightAndFat> getWeightAndFatList() {
        return weightAndFatArrayList;
    }
}
