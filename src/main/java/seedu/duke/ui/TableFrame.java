package seedu.duke.ui;

import seedu.duke.records.Record;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.CardioExercise;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.StrengthExercise;
import seedu.duke.records.food.Food;

import java.util.ArrayList;

public class TableFrame {
    public static final int INDEX_POSITION = 0;
    public static final int WEIGHT_INDEX = 1;
    public static final int FAT_INDEX = 2;
    public static final int FOOD_DESCRIPTION_INDEX = 3;
    public static final int FOOD_CALORIES_INDEX = 4;

    public static final int EXERCISE_DESCRIPTION_INDEX = 5;
    public static final int EXERCISE_WEIGHT_INDEX = 6;
    public static final int EXERCISE_SET_INDEX = 7;
    public static final int EXERCISE_REPETITION_INDEX = 8;
    public static final int EXERCISE_DISTANCE_INDEX = 9;
    public static final int EXERCISE_CALORIES_INDEX = 10;
    public static final int STATUS_INDEX = 11;
    public static final int DATE_INDEX = 12;

    public static final int MAXIMUM_DATE_COLUMN_SPACING = 10;
    public static final String COLUMN_SEPARATOR = " | ";
    public static final String ROW_SEPARATOR = "-";
    public final ArrayList<Food> foodArrayList;
    public final ArrayList<WeightAndFat> weightAndFatArrayList;
    public final ArrayList<Exercise> exerciseArrayList;

    public final ArrayList<Record> recordArrayList;

    public final String[] headings = new String[13];
    public static int[] columnSpacingArray = new int[13];

    public TableFrame(ArrayList<Food> foodArrayList,
                      ArrayList<WeightAndFat> weightAndFatArrayList,
                      ArrayList<Exercise> exerciseArrayList,
                      ArrayList<Record> recordArrayList) {
        this.foodArrayList = foodArrayList;
        this.weightAndFatArrayList = weightAndFatArrayList;
        this.exerciseArrayList = exerciseArrayList;
        this.recordArrayList = recordArrayList;
        addHeadings();
    }

    private void addHeadings() {

        headings[INDEX_POSITION] = "Index";
        headings[WEIGHT_INDEX] = "Weight";
        headings[FAT_INDEX] = "Fat";

        headings[FOOD_DESCRIPTION_INDEX] = "Food Description";
        headings[FOOD_CALORIES_INDEX] = "Calories";

        headings[EXERCISE_DESCRIPTION_INDEX] = "Exercise";
        headings[EXERCISE_WEIGHT_INDEX] = "Weight";
        headings[EXERCISE_SET_INDEX] = "Sets";
        headings[EXERCISE_REPETITION_INDEX] = "Reps";
        headings[EXERCISE_DISTANCE_INDEX] = "Dist";
        headings[EXERCISE_CALORIES_INDEX] = "Calories";
        headings[STATUS_INDEX] = "Status";
        headings[DATE_INDEX] = "Date";

    }

    public void addHeadingRow(ArrayList<String> tableFrame, int[] relevantIndices) {
        setColumnsSpacingForAll();
        StringBuilder headingRow = new StringBuilder();
        for (int i : relevantIndices) {
            headingRow.append(addRightPadding(headings[i], columnSpacingArray[i]));
        }
        tableFrame.add(ROW_SEPARATOR.repeat(headingRow.length()));
        tableFrame.add(headingRow.toString());
        tableFrame.add(ROW_SEPARATOR.repeat(headingRow.length()));
    }


    public void setColumnsSpacingForAll() {
        setColumnSpacingBaseOnHeaderLength();
        columnSpacingArray[INDEX_POSITION] = Math.max(columnSpacingArray[INDEX_POSITION],
                String.valueOf(recordArrayList.size()).length());
        columnSpacingArray[DATE_INDEX] = MAXIMUM_DATE_COLUMN_SPACING;

        for (WeightAndFat weightAndFat : weightAndFatArrayList) {
            columnSpacingArray[WEIGHT_INDEX] = Math.max(columnSpacingArray[WEIGHT_INDEX],
                    String.valueOf(weightAndFat.getWeight()).length());
            columnSpacingArray[FAT_INDEX] = Math.max(columnSpacingArray[FAT_INDEX],
                    String.valueOf(weightAndFat.getFat()).length());
        }
        for (Food food : foodArrayList) {
            columnSpacingArray[FOOD_DESCRIPTION_INDEX] = Math.max(columnSpacingArray[FOOD_DESCRIPTION_INDEX],
                    food.getFoodDescription().length());
            columnSpacingArray[FOOD_CALORIES_INDEX] = Math.max(columnSpacingArray[FOOD_CALORIES_INDEX],
                    String.valueOf(food.getCalories()).length());
        }

        setRemainingExerciseColumnsSpacing(exerciseArrayList, columnSpacingArray, WEIGHT_INDEX, EXERCISE_SET_INDEX,
                EXERCISE_DISTANCE_INDEX, EXERCISE_DESCRIPTION_INDEX, EXERCISE_REPETITION_INDEX,
                EXERCISE_CALORIES_INDEX);
    }

    public void setRemainingExerciseColumnsSpacing(ArrayList<Exercise> exerciseArrayList, int[] columnSpacingArray,
                                                   int weightIndex, int exerciseSetIndex, int exerciseDistanceIndex,
                                                   int exerciseDescriptionIndex, int exerciseRepetitionIndex,
                                                   int exerciseCaloriesIndex) {
        for (Exercise exercise : exerciseArrayList) {
            if (exercise instanceof StrengthExercise) {
                columnSpacingArray[weightIndex] = Math.max(columnSpacingArray[weightIndex],
                        String.valueOf(exercise.getWeight()).length());
                columnSpacingArray[exerciseSetIndex] = Math.max(columnSpacingArray[exerciseSetIndex],
                        String.valueOf(exercise.getSet()).length());
            } else if (exercise instanceof CardioExercise) {
                columnSpacingArray[exerciseDistanceIndex] = Math.max(columnSpacingArray[exerciseDistanceIndex],
                        String.valueOf(exercise.getDistance()).length());
            }
            columnSpacingArray[exerciseDescriptionIndex] = Math.max(columnSpacingArray[exerciseDescriptionIndex],
                    exercise.getExerciseName().length());
            columnSpacingArray[exerciseRepetitionIndex] = Math.max(columnSpacingArray[exerciseRepetitionIndex],
                    String.valueOf(exercise.getRepetition()).length());
            columnSpacingArray[exerciseCaloriesIndex] = Math.max(columnSpacingArray[exerciseCaloriesIndex],
                    String.valueOf(exercise.getCaloriesBurnt()).length());
        }
    }

    private void setColumnSpacingBaseOnHeaderLength() {
        for (int i = 0; i < headings.length; i++) {
            columnSpacingArray[i] = headings[i].length();
        }
    }

    protected static String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string) + COLUMN_SEPARATOR;
    }


}
