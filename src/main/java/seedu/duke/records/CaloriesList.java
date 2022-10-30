package seedu.duke.records;

import java.util.ArrayList;

import static seedu.duke.command.DateCommand.sortDateForCalories;

public class CaloriesList {

    ArrayList<Calories> caloriesList;

    public CaloriesList() {
        this.caloriesList = new ArrayList<>();
    }

    public void addCalories(Calories caloriesinput) {
        caloriesList.add(caloriesinput);
    }

    public ArrayList<Calories> getCaloriesList() {
        sortDateForCalories(caloriesList);
        return caloriesList;
    }
}
