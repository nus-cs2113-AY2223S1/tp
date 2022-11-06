package seedu.duke.records;

import java.util.ArrayList;

import static seedu.duke.logic.command.DateCommand.sortDateForCalories;

public class CaloriesList {

    ArrayList<Calories> caloriesList;

    public CaloriesList() {
        this.caloriesList = new ArrayList<>();
    }

    public void addCalories(Calories caloriesInput) {
        caloriesList.add(caloriesInput);
    }

    public ArrayList<Calories> getCaloriesList() {
        sortDateForCalories(caloriesList);
        return caloriesList;
    }
}
