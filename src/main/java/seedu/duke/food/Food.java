package seedu.duke.food;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Food {
    private String foodDescription;
    private int calories;

    private LocalDate date;

    public Food(String foodDescription, int calories, LocalDate date) {
        this.foodDescription = foodDescription;
        this.calories = calories;
        this.date = date;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public int getCalories() {
        return calories;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
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
