package seedu.duke.ui;

import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.records.Record;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.food.Food;

import java.util.ArrayList;

/**
 * Represents a display table for all records.
 */
public class AllRecordsTable extends TableFrame {

    private ArrayList<String> allRecordsTable;

    private String caption;
    private final int[] relevantIndices = {DATE_INDEX, WEIGHT_INDEX, FAT_INDEX, FOOD_DESCRIPTION_INDEX,
        FOOD_CALORIES_INDEX, EXERCISE_DESCRIPTION_INDEX, EXERCISE_WEIGHT_INDEX, EXERCISE_SET_INDEX,
        EXERCISE_REPETITION_INDEX, EXERCISE_DISTANCE_INDEX, EXERCISE_CALORIES_INDEX, STATUS_INDEX};

    private final String[] mainHeadings = {"Weight & Fat", "Food", "Exercises"};

    public static final int MAXIMUM_WEIGHT_COLUMN_SPACING = 12;

    private int[] mainColumnSpacingArray = {0, 0, 0};

    public AllRecordsTable(ArrayList<Food> foodArrayList,
                          ArrayList<WeightAndFat> weightAndFatArrayList,
                          ArrayList<Exercise> exerciseArrayList,
                          ArrayList<Record> recordArrayList,
                          String caption) {
        super(foodArrayList, weightAndFatArrayList, exerciseArrayList, recordArrayList);
        this.caption = caption;
        allRecordsTable = new ArrayList<>();
    }

    /**
     * Returns the table with filled data for all the records.
     */
    public ArrayList<String> getAllRecordsTable() {
        setColumnsSpacingForAll();
        addCaption();
        String mainHeading = addMainHeadingRow(allRecordsTable);
        addHeadingRow(allRecordsTable, relevantIndices);
        fillAllRecordsTable(this.recordArrayList, allRecordsTable, mainHeading);
        return allRecordsTable;
    }

    /**
     * Fills the table with all the data from the record list.
     *
     * @param recordArrayList an ArrayList storing all the records that user has inputted
     * @param tableFrame a empty table to be filled with the record data
     * @param mainHeading a string of header descriptions
     *
     */
    private void fillAllRecordsTable(ArrayList<Record> recordArrayList, ArrayList<String> tableFrame,
                                     String mainHeading) {
        for (int i = 0; i < recordArrayList.size(); i++) {
            if (recordArrayList.get(i) instanceof WeightAndFat) {
                String date = addRightPadding(recordArrayList.get(i).getDateString(), columnSpacingArray[DATE_INDEX]);
                WeightAndFat weightAndFat = (WeightAndFat) recordArrayList.get(i);
                String weight = addRightPadding(Integer.toString(weightAndFat.getWeight()),
                        columnSpacingArray[WEIGHT_INDEX]);
                String fat = addRightPadding(Integer.toString(weightAndFat.getFat()), columnSpacingArray[FAT_INDEX]);
                String row = date + weight + fat;
                tableFrame.add(row);
            }

            if (recordArrayList.get(i) instanceof Food) {
                String date = addRightPadding(recordArrayList.get(i).getDateString(), columnSpacingArray[DATE_INDEX]);
                String extraPadding = addRightPadding("", mainHeading.substring(
                        0, mainHeading.indexOf("| Food")).length() - date.length() - 1);
                Food food = (Food) recordArrayList.get(i);
                String foodName = addRightPadding(food.getFoodDescription(),
                        columnSpacingArray[FOOD_DESCRIPTION_INDEX]);
                String calories = addRightPadding(Integer.toString(food.getCalories()),
                        columnSpacingArray[FOOD_CALORIES_INDEX]);
                String row = date + extraPadding + foodName + calories;
                tableFrame.add(row);
            }

            if (recordArrayList.get(i) instanceof Exercise) {
                String date = addRightPadding(recordArrayList.get(i).getDateString(), columnSpacingArray[DATE_INDEX]);
                String extraPadding = addRightPadding("", mainHeading.substring(
                        0, mainHeading.indexOf("| Exercises")).length() - date.length() - 1);
                Exercise exercise = (Exercise) recordArrayList.get(i);
                String exerciseName = addRightPadding(exercise.getExerciseName(),
                        columnSpacingArray[EXERCISE_DESCRIPTION_INDEX]);
                String weight = ExerciseTable.getWeightForTable(exercise, columnSpacingArray[WEIGHT_INDEX]);
                String sets = addRightPadding(Integer.toString(exercise.getSet()),
                        columnSpacingArray[EXERCISE_SET_INDEX]);
                String repetitions = addRightPadding(Integer.toString(exercise.getRepetition()),
                        columnSpacingArray[EXERCISE_REPETITION_INDEX]);
                String distance = ExerciseTable.getDistanceForTable(
                        exercise, columnSpacingArray[EXERCISE_DISTANCE_INDEX]);
                String calories = ExerciseTable.getCaloriesForTable(exercise,
                        columnSpacingArray[EXERCISE_CALORIES_INDEX]);
                String status = exercise.getTaskStatus();
                String row = date + extraPadding + exerciseName + weight + sets + repetitions + distance
                        + calories + status;
                tableFrame.add(row);
            }
        }

    }


    /**
     * Fills the tableFrame with the main header descriptions.
     *
     * @param tableFrame a empty table to be filled with the record data
     *
     */
    public String addMainHeadingRow(ArrayList<String> tableFrame) {
        setMainHeaderSpacing();
        StringBuilder headingRow = new StringBuilder();
        headingRow.append(addRightPadding("", mainColumnSpacingArray[0]));
        for (int i = 1; i < mainHeadings.length; i++) {
            headingRow.append(addRightPadding(mainHeadings[i - 1], mainColumnSpacingArray[i]));
        }
        headingRow.append(mainHeadings[2]);
        tableFrame.add(ROW_SEPARATOR.repeat(headingRow.length()));
        tableFrame.add(headingRow.toString());
        return headingRow.toString();
    }

    /**
     * Determines the spacing for the main header descriptions.
     */
    public void setMainHeaderSpacing() {
        mainColumnSpacingArray[0] = MAXIMUM_DATE_COLUMN_SPACING;
        mainColumnSpacingArray[1] = MAXIMUM_WEIGHT_COLUMN_SPACING;
        mainColumnSpacingArray[2] = columnSpacingArray[FOOD_DESCRIPTION_INDEX]
                + columnSpacingArray[FOOD_CALORIES_INDEX] + 3;
    }

    /**
     * Adds a caption to the table.
     */
    private void addCaption() {
        allRecordsTable.add(caption);
    }

}
