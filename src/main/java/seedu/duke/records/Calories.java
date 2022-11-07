package seedu.duke.records;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calories {
    private LocalDate date;
    private int caloriesConsumed;
    private int caloriesBurnt;
    private int netCalories;
    private String message;


    public Calories(int caloriesConsumed, int caloriesBurnt, int netCalories, LocalDate date, String message) {
        this.caloriesConsumed = caloriesConsumed;
        this.caloriesBurnt = caloriesBurnt;
        this.netCalories = netCalories;
        this.date = date;
        this.message = message;
    }

    /**
     * To get the calories consumed.
     * @return the calories consumed
     */
    public int getCaloriesConsumed() {
        return this.caloriesConsumed;
    }

    /**
     * To get the calories burnt.
     * @return the calories burnt
     */
    public int getCaloriesBurnt() {
        return this.caloriesBurnt;
    }

    /**
     * To get the net calories.
     * @return the net calories
     */
    public int getNetCalories() {
        return this.netCalories;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String toString() {
        return "Data is recorded on: " + getDate();
    }
}
