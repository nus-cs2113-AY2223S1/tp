package seedu.duke.records.food;

import seedu.duke.records.Record;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Food extends Record {
    private String foodDescription;
    private int calories;

    private LocalDate date;

    public Food(String foodDescription, int calories, LocalDate date) {
        super(date);
        this.foodDescription = foodDescription;
        this.calories = calories;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Food Description: " + foodDescription + System.lineSeparator()
                + "calories: " + calories + System.lineSeparator()
                +  "Recorded on: " + getDate();
    }

    public String saveFood() {
        return String.format("/%s /%d", foodDescription, calories);
    }
}
