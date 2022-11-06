package seedu.duke.ui;

import seedu.duke.records.Record;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.food.Food;

import java.util.ArrayList;

public class FoodTable extends TableFrame {

    private ArrayList<String> foodTable;

    private String caption;
    private final int [] relevant_indices = {INDEX_POSITION, FOOD_DESCRIPTION_INDEX, FOOD_CALORIES_INDEX, DATE_INDEX};

    public FoodTable(ArrayList<Food> foodArrayList,
                     ArrayList<WeightAndFat> weightAndFatArrayList,
                     ArrayList<Exercise> exerciseArrayList,
                     ArrayList<Record> recordArrayList,
                     String caption) {
        super(foodArrayList, weightAndFatArrayList, exerciseArrayList, recordArrayList);
        this.caption = caption;
        foodTable = new ArrayList<>();
    }

    public ArrayList<String> getFoodTable() {
        setColumnsSpacingForAll();
        addCaption();
        addHeadingRow(foodTable, relevant_indices);
        fillFoodTable(this.foodArrayList, foodTable);
        return foodTable;
    }

    private void fillFoodTable(ArrayList<Food> foodArrayList, ArrayList<String> tableFrame) {
        for (int i = 0; i < foodArrayList.size(); i++) {
            Food food = foodArrayList.get(i);
            String index = addRightPadding(Integer.toString(i + 1), columnSpacingArray[INDEX_POSITION]);
            String foodName = addRightPadding(food.getFoodDescription(), columnSpacingArray[FOOD_DESCRIPTION_INDEX]);
            String calories = addRightPadding(Integer.toString(food.getCalories()),
                    columnSpacingArray[FOOD_CALORIES_INDEX]);
            String date = addRightPadding(food.getDateString(), columnSpacingArray[DATE_INDEX]);
            String row = index + foodName + calories + date;
            tableFrame.add(row);
        }
    }

    private void addCaption() {
        foodTable.add(caption);
    }

}
