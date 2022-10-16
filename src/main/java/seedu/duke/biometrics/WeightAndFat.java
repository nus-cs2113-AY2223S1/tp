package seedu.duke.biometrics;

import seedu.duke.exception.IllegalValueException;

import java.util.ArrayList;

public class WeightAndFat {

    private int weight;
    private int fat;

    public WeightAndFat(int weight, int fat) throws IllegalValueException {
        setWeight(weight);
        setFat(fat);
    }

    public void setWeight(int weight) throws IllegalValueException {
        checkWeight(weight);
        this.weight = weight;
    }

    public void setFat(int fat) throws IllegalValueException {
        checkFat(fat);
        this.fat = fat;
    }

    static void checkWeight(int weight) throws IllegalValueException {
        if (weight <= 0 || weight > 400) {
            throw new IllegalValueException("That weight can't be real!");
        }
    }

    static void checkFat(int fat) throws IllegalValueException {
        if (fat <= 0 || fat >= 100) {
            throw new IllegalValueException("Invalid fat percentage");
        }
    }

    public String saveWeightAndFat() {
        return String.format("/%d /%d", weight, fat);
    }

    public String listWeightAndFat(ArrayList<WeightAndFat> weightAndFatArrayList) {
        return String.format("%d | %d | %d", weightAndFatArrayList.indexOf(this) + 1, weight, fat);
    }

    @Override
    public String toString() {
        return "Weight: " + weight + "kg" + System.lineSeparator()
                + "Fat percentage: " + fat + "%";
    }
}
