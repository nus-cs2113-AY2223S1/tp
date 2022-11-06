package seedu.duke.records.biometrics;

import seedu.duke.records.Record;
import seedu.duke.logic.exception.IllegalValueException;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeightAndFat extends Record {

    private static final int MAX_WEIGHT = 400;
    private static final int MAX_FAT = 99;
    private int weight;
    private int fat;
    private LocalDate date;

    public WeightAndFat(int weight, int fat, LocalDate date) throws IllegalValueException {
        super(date);
        setWeight(weight);
        setFat(fat);
    }

    public int getWeight() {
        return weight;
    }

    public int getFat() {
        return fat;
    }

    public void setWeight(int weight) throws IllegalValueException {
        checkWeight(weight);
        this.weight = weight;
    }

    public void setFat(int fat) throws IllegalValueException {
        checkFat(fat);
        this.fat = fat;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    static void checkWeight(int weight) throws IllegalValueException {
        if (weight <= 0 || weight > MAX_WEIGHT) {
            throw new IllegalValueException("That weight can't be real!");
        }
    }

    static void checkFat(int fat) throws IllegalValueException {
        if (fat <= 0 || fat > MAX_FAT) {
            throw new IllegalValueException("Invalid fat percentage");
        }
    }

    public String saveWeightAndFat() {
        return String.format("/%d /%d /%s", weight, fat, getDateString());
    }

    public String listWeightAndFat(ArrayList<WeightAndFat> weightAndFatArrayList) {
        return String.format("%d | %d | %d", weightAndFatArrayList.indexOf(this) + 1, weight, fat);
    }

    @Override
    public String toString() {
        return "Weight: " + weight + "kg" + System.lineSeparator()
                + "Fat percentage: " + fat + "%" + System.lineSeparator()
                + "Recorded on: " + getDateString();
    }
}
