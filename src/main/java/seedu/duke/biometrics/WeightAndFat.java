package seedu.duke.biometrics;

import seedu.duke.exception.IllegalValueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WeightAndFat {

    private int weight;
    private int fat;
    private LocalDate date;

    public WeightAndFat(int weight, int fat, LocalDate date) throws IllegalValueException {
        setWeight(weight);
        setFat(fat);
        setDate(date);
    }

    public int getWeight() {
        return weight;
    }

    public int getFat() {
        return fat;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
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
        return String.format("/%d /%d /%s", weight, fat, getDate());
    }

    public String listWeightAndFat(ArrayList<WeightAndFat> weightAndFatArrayList) {
        return String.format("%d | %d | %d", weightAndFatArrayList.indexOf(this) + 1, weight, fat);
    }

    @Override
    public String toString() {
        return "Weight: " + weight + "kg" + System.lineSeparator()
                + "Fat percentage: " + fat + "%" + System.lineSeparator()
                + "Recorded on: " + getDate();
    }
}
