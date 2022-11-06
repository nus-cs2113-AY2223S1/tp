package seedu.duke.ui;

import seedu.duke.records.Record;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.food.Food;

import java.util.ArrayList;

public class AllRecordsTable extends TableFrame {

    private ArrayList<String> allRecordsTable;

    private String caption;
    private final int [] relevant_indices = {
            DATE_INDEX, WEIGHT_INDEX, FAT_INDEX,
            FOOD_DESCRIPTION_INDEX, FOOD_CALORIES_INDEX,
            EXERCISE_DESCRIPTION_INDEX, EXERCISE_WEIGHT_INDEX, EXERCISE_SET_INDEX,
            EXERCISE_REPETITION_INDEX, EXERCISE_DISTANCE_INDEX, EXERCISE_CALORIES_INDEX,
            STATUS_INDEX};


    private final String[] mainheadings = {"Weight & Fat", "Food", "Exercises"};

    private final int MAXIMUM_WEIGHT_AND_FAT_SPACING = 12;

    private int[] maincolumnSpacingArray = {0, 0, 0};

    public AllRecordsTable(ArrayList<Food> foodArrayList,
                          ArrayList<WeightAndFat> weightAndFatArrayList,
                          ArrayList<Exercise> exerciseArrayList,
                          ArrayList<Record> recordArrayList,
                          String caption) {
        super(foodArrayList, weightAndFatArrayList, exerciseArrayList, recordArrayList);
        this.caption = caption;
        allRecordsTable = new ArrayList<>();
    }

    public ArrayList<String> getAllRecordsTable() {
        setColumnsSpacingForAll();
        addCaption();
        String mainHeading = addMainHeadingRow(allRecordsTable);
        addHeadingRow(allRecordsTable, relevant_indices);
        fillAllRecordsTable(this.recordArrayList, allRecordsTable, mainHeading);
        return allRecordsTable;
    }

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
                String extra_padding = addRightPadding("", mainHeading.substring(
                        0, mainHeading.indexOf("| Food")).length() - date.length() - 1);
                Food food = (Food) recordArrayList.get(i);
                String foodName = addRightPadding(food.getFoodDescription(),
                        columnSpacingArray[FOOD_DESCRIPTION_INDEX]);
                String calories = addRightPadding(Integer.toString(food.getCalories()),
                        columnSpacingArray[FOOD_CALORIES_INDEX]);
                String row = date + extra_padding + foodName + calories;
                tableFrame.add(row);
            }


            if (recordArrayList.get(i) instanceof Exercise) {
                String date = addRightPadding(recordArrayList.get(i).getDateString(), columnSpacingArray[DATE_INDEX]);
                String extra_padding = addRightPadding("", mainHeading.substring(
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
                String row = date + extra_padding + exerciseName + weight + sets + repetitions + distance
                        + calories + status;
                tableFrame.add(row);
            }
        }

    }

    public String addMainHeadingRow(ArrayList<String> tableFrame) {
        setMainHeaderSpacing();
        StringBuilder headingRow = new StringBuilder();
        headingRow.append(addRightPadding("", maincolumnSpacingArray[0]));
        for (int i = 1; i < mainheadings.length; i++) {
            headingRow.append(addRightPadding(mainheadings[i-1], maincolumnSpacingArray[i]));
        }
        headingRow.append(mainheadings[2]);
        tableFrame.add(ROW_SEPARATOR.repeat(headingRow.length()));
        tableFrame.add(headingRow.toString());
        return headingRow.toString();
    }

    public void setMainHeaderSpacing() {
        maincolumnSpacingArray[0] = MAXIMUM_DATE_COLUMN_SPACING;
        maincolumnSpacingArray[1] = MAXIMUM_WEIGHT_AND_FAT_SPACING;
        maincolumnSpacingArray[2] = columnSpacingArray[FOOD_DESCRIPTION_INDEX] +
                columnSpacingArray[FOOD_CALORIES_INDEX] + 3;
    }

    private void addCaption() {
        allRecordsTable.add(caption);
    }

}
