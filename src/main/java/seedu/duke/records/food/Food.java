package seedu.duke.records.food;

import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.records.Record;

import java.time.LocalDate;

/**
 * Represents a Food record in the food array list.
 */
public class Food extends Record {
    private String foodDescription;
    private int calories;

    private LocalDate date;

    public Food(String foodDescription, int calories, LocalDate date) {
        super(date);
        this.foodDescription = foodDescription;
        this.calories = calories;
    }

    /**
     * Returns the description of the food.
     */
    public String getFoodDescription() {
        return foodDescription;
    }

    /**
     * Returns the calories of the food.
     */
    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Food Description: " + foodDescription + System.lineSeparator()
                + "calories: " + calories + System.lineSeparator()
                +  "Recorded on: " + getDateString();
    }

    /**
     * Returns the food record in the form of a string to be saved in the data file.
     */
    public String saveFood() {
        return String.format("/%s /%d /%s", foodDescription, calories, getDateString());
    }
}
