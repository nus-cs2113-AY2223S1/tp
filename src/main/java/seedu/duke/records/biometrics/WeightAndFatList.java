package seedu.duke.records.biometrics;

import seedu.duke.logic.exception.IllegalValueException;

import java.util.ArrayList;

/**
 * Represents the collection of weight and fat records.
 */
public class WeightAndFatList {

    private final ArrayList<WeightAndFat> weightAndFatArrayList;

    public WeightAndFatList() {
        weightAndFatArrayList = new ArrayList<>();
    }

    public void addWeightAndFat(WeightAndFat weightAndFat) {
        weightAndFatArrayList.add(weightAndFat);
        weightAndFatArrayList.sort(new WeightAndFatComparator());
    }

    /**
     * Gets the most recent record of weight and fat stored in the array list.
     * Assumes that the arraylist is not empty.
     *
     * @return returns the most recent weight and fat record
     */
    public WeightAndFat getMostRecent() {
        assert (weightAndFatArrayList.size() > 0);
        return weightAndFatArrayList.get(0);
    }

    /**
     * Removes a record of weight and fat from the array list.
     *
     * @param index index of the record to be deleted
     * @return returns the deleted weight and fat record
     * @throws IllegalValueException if the index provided is out of bounds of array list
     */
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
