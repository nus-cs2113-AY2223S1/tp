package seedu.duke.ui;

import seedu.duke.records.Calories;
import seedu.duke.records.Record;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.food.Food;

import java.util.ArrayList;

public class CaloriesTable extends TableFrame {
    public static final int DATE_INDEX = 0;
    public static final int CALORIES_CONSUMED_INDEX = 1;
    public static final int CALORIES_BURNT_INDEX = 2;
    public static final int NET_CALORIES_INDEX = 3;
    public static final int STATUS_INDEX = 4;
    public static final int MAXIMUM_DATE_COLUMN_SPACING = 10;
    public static final String ROW_SEPARATOR = "-";
    private final ArrayList<Calories> caloriesArrayList;
    private final String[] headings = new String[5];
    private final String caption;
    private int[] columnSpacingArray = new int[5];
    private ArrayList<String> caloriesTable;

    public CaloriesTable(ArrayList<Food> foodArrayList,
                         ArrayList<WeightAndFat> weightAndFatArrayList,
                         ArrayList<Exercise> exerciseArrayList,
                         ArrayList<Record> recordArrayList,
                         ArrayList<Calories> caloriesArrayList,
                         String caption) {
        super(foodArrayList, weightAndFatArrayList, exerciseArrayList, recordArrayList);
        this.caption = caption;
        this.caloriesArrayList = caloriesArrayList;
        caloriesTable = new ArrayList<>();
        addHeadings();
    }




    private void addHeadings() {

        headings[DATE_INDEX] = "Date";
        headings[CALORIES_CONSUMED_INDEX] = "Calories Consumed";
        headings[CALORIES_BURNT_INDEX] = "Calories Burnt";
        headings[NET_CALORIES_INDEX] = "Net Calories";
        headings[STATUS_INDEX] = "Status";
    }

    public ArrayList<String> getCaloriesTable() {
        setCaloriesColumnsSpacing();
        addCaption();
        addHeadingRow();
        fillTable();
        return caloriesTable;
    }

    private void fillTable() {
        for (int i = 0; i < caloriesArrayList.size(); i++) {
            Calories calories = caloriesArrayList.get(i);
            String date = addRightPadding(calories.getDate(),
                    columnSpacingArray[DATE_INDEX]);
            String caloriesConsumed = addRightPadding(Integer.toString(calories.getCaloriesConsumed()),
                    columnSpacingArray[CALORIES_CONSUMED_INDEX]);
            String caloriesBurnt = addRightPadding(Integer.toString(calories.getCaloriesBurnt()),
                    columnSpacingArray[CALORIES_BURNT_INDEX]);
            String netCalories = addRightPadding(Integer.toString(calories.getNetCalories()),
                    columnSpacingArray[NET_CALORIES_INDEX]);
            String status = addRightPadding(calories.getMessage(),
                    columnSpacingArray[STATUS_INDEX]);
            String row = date + caloriesConsumed + caloriesBurnt + netCalories + status;
            caloriesTable.add(row);
        }
    }



    private void addCaption() {
        caloriesTable.add(caption);
    }

    private void addHeadingRow() {
        StringBuilder headingRow = new StringBuilder();
        for (int i = 0; i < headings.length - 1; i++) {
            headingRow.append(addRightPadding(headings[i], columnSpacingArray[i]));
        }
        headingRow.append(headings[STATUS_INDEX]);
        caloriesTable.add(ROW_SEPARATOR.repeat(headingRow.length()));
        caloriesTable.add(headingRow.toString());
        caloriesTable.add(ROW_SEPARATOR.repeat(headingRow.length()));

    }


    private void setCaloriesColumnsSpacing() {
        setColumnSpacingBaseOnHeaderLength();
        setFinalIndexColumnSpacing();
        setFinalDateColumnSpacing();
        setFinalSpacingForRemainingColumns();
    }

    private void setFinalDateColumnSpacing() {
        columnSpacingArray[DATE_INDEX] = MAXIMUM_DATE_COLUMN_SPACING;
    }

    private void setFinalSpacingForRemainingColumns() {
        for (Calories calories : caloriesArrayList) {
            columnSpacingArray[DATE_INDEX] = Math.max(columnSpacingArray[DATE_INDEX],
                    String.valueOf(calories.getDate()).length());
            columnSpacingArray[CALORIES_CONSUMED_INDEX] = Math.max(columnSpacingArray[CALORIES_CONSUMED_INDEX],
                    String.valueOf(calories.getCaloriesConsumed()).length());
            columnSpacingArray[CALORIES_BURNT_INDEX] = Math.max(columnSpacingArray[CALORIES_BURNT_INDEX],
                    String.valueOf(calories.getCaloriesBurnt()).length());
            columnSpacingArray[NET_CALORIES_INDEX] = Math.max(columnSpacingArray[NET_CALORIES_INDEX],
                    String.valueOf(calories.getNetCalories()).length());
            columnSpacingArray[STATUS_INDEX] = Math.max(columnSpacingArray[STATUS_INDEX],
                    String.valueOf(calories.getMessage()).length());
        }
    }

    private void setFinalIndexColumnSpacing() {
        columnSpacingArray[INDEX_POSITION] = Math.max(columnSpacingArray[INDEX_POSITION],
                String.valueOf(caloriesArrayList.size()).length());
    }

    private void setColumnSpacingBaseOnHeaderLength() {
        for (int i = 0; i < headings.length; i++) {
            columnSpacingArray[i] = headings[i].length();
        }
    }
}
