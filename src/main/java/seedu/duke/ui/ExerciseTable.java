package seedu.duke.ui;

import seedu.duke.records.exercise.CardioExercise;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.StrengthExercise;

import java.util.ArrayList;

public class ExerciseTable extends Table {
    public static final int INDEX_POSITION = 0;
    public static final int DESCRIPTION_INDEX = 1;
    public static final int WEIGHT_INDEX = 2;
    public static final int SET_INDEX = 3;
    public static final int REPETITION_INDEX = 4;
    public static final int DISTANCE_INDEX = 5;
    public static final int CALORIES_INDEX = 6;
    public static final int DATE_INDEX = 7;
    public static final int STATUS_INDEX = 8;
    public static final int MAXIMUM_DATE_COLUMN_SPACING = 10;
    public static final String UNAVAILABLE_SYMBOL = "-";
    public static final String COLUMN_SEPARATOR = " | ";
    public static final String ROW_SEPARATOR = "-";
    private final ArrayList<Exercise> exerciseArrayList;
    private final String[] headings = new String[9];
    private int[] columnSpacingArray = new int[9];
    private ArrayList<String> exerciseTable;

    public ExerciseTable(ArrayList<Exercise> exerciseArrayList, String caption) {
        super(caption);
        this.exerciseArrayList = exerciseArrayList;
        exerciseTable = new ArrayList<>();
        addHeadings();
    }

    private void addHeadings() {

        headings[INDEX_POSITION] = "Index";
        headings[DESCRIPTION_INDEX] = "Exercise";
        headings[WEIGHT_INDEX] = "Weight";
        headings[SET_INDEX] = "Sets";
        headings[REPETITION_INDEX] = "Reps";
        headings[DISTANCE_INDEX] = "Dist";
        headings[CALORIES_INDEX] = "Calories";
        headings[DATE_INDEX] = "Date";
        headings[STATUS_INDEX] = "Status";
    }

    public ArrayList<String> getExerciseTable() {
        setExerciseColumnsSpacing();
        addCaption();
        addHeadingRow();
        fillTable();
        return exerciseTable;
    }

    private void fillTable() {
        for (int i = 0; i < exerciseArrayList.size(); i++) {
            Exercise exercise = exerciseArrayList.get(i);
            String index = addRightPadding(Integer.toString(i + 1),
                    columnSpacingArray[INDEX_POSITION]);
            String exerciseName = addRightPadding(exercise.getExerciseName(),
                    columnSpacingArray[DESCRIPTION_INDEX]);
            String weight = getWeightForTable(exercise, columnSpacingArray[WEIGHT_INDEX]);
            String sets = addRightPadding(Integer.toString(exercise.getSet()),
                    columnSpacingArray[SET_INDEX]);
            String repetitions = addRightPadding(Integer.toString(exercise.getRepetition()),
                    columnSpacingArray[REPETITION_INDEX]);
            String distance = getDistanceForTable(exercise, columnSpacingArray[DISTANCE_INDEX]);
            String calories = getCaloriesForTable(exercise, columnSpacingArray[CALORIES_INDEX]);
            String date = addRightPadding(exercise.getDateString(), columnSpacingArray[DATE_INDEX]);
            String status = exercise.getTaskStatus();
            String row = index + exerciseName + weight + sets + repetitions + distance
                    + calories + date + status;
            exerciseTable.add(row);
        }
    }

    private String getCaloriesForTable(Exercise exercise, int numberOfSpace) {
        if (!exercise.getDone()) {
            return addRightPadding(UNAVAILABLE_SYMBOL, numberOfSpace);
        }
        return addRightPadding(Integer.toString(exercise.getCaloriesBurnt()),
                numberOfSpace);
    }

    private String getDistanceForTable(Exercise exercise, Integer numberOfSpace) {
        if (exercise instanceof StrengthExercise) {
            return addRightPadding(UNAVAILABLE_SYMBOL, numberOfSpace);
        }
        return addRightPadding(Double.toString(exercise.getDistance()), numberOfSpace);
    }


    private String getWeightForTable(Exercise exercise, Integer numberOfSpace) {
        if (exercise instanceof CardioExercise) {
            return addRightPadding(UNAVAILABLE_SYMBOL, numberOfSpace);
        }
        return addRightPadding(Integer.toString(exercise.getWeight()), numberOfSpace);
    }

    private void addCaption() {
        exerciseTable.add(caption);
    }

    private void addHeadingRow() {
        StringBuilder headingRow = new StringBuilder();
        for (int i = 0; i < headings.length - 1; i++) {
            headingRow.append(addRightPadding(headings[i], columnSpacingArray[i]));
        }
        headingRow.append(headings[STATUS_INDEX]);
        exerciseTable.add(ROW_SEPARATOR.repeat(headingRow.length()));
        exerciseTable.add(headingRow.toString());
        exerciseTable.add(ROW_SEPARATOR.repeat(headingRow.length()));

    }


    private void setExerciseColumnsSpacing() {
        setColumnSpacingBaseOnHeaderLength();
        setFinalIndexColumnSpacing();
        setFinalDateColumnSpacing();
        setFinalSpacingForRemainingColumns();
    }

    private void setFinalDateColumnSpacing() {
        columnSpacingArray[DATE_INDEX] = MAXIMUM_DATE_COLUMN_SPACING;
    }

    private void setFinalSpacingForRemainingColumns() {
        for (Exercise exercise : exerciseArrayList) {
            if (exercise instanceof StrengthExercise) {
                columnSpacingArray[WEIGHT_INDEX] = Math.max(columnSpacingArray[WEIGHT_INDEX],
                        String.valueOf(exercise.getWeight()).length());
                columnSpacingArray[SET_INDEX] = Math.max(columnSpacingArray[SET_INDEX],
                        String.valueOf(exercise.getSet()).length());
            } else if (exercise instanceof CardioExercise) {
                columnSpacingArray[DISTANCE_INDEX] = Math.max(columnSpacingArray[DISTANCE_INDEX],
                        String.valueOf(exercise.getDistance()).length());
            }
            columnSpacingArray[DESCRIPTION_INDEX] = Math.max(columnSpacingArray[DESCRIPTION_INDEX],
                    exercise.getExerciseName().length());
            columnSpacingArray[REPETITION_INDEX] = Math.max(columnSpacingArray[REPETITION_INDEX],
                    String.valueOf(exercise.getRepetition()).length());
            columnSpacingArray[CALORIES_INDEX] = Math.max(columnSpacingArray[CALORIES_INDEX],
                    String.valueOf(exercise.getCaloriesBurnt()).length());
        }
    }

    private void setFinalIndexColumnSpacing() {
        columnSpacingArray[INDEX_POSITION] = Math.max(columnSpacingArray[INDEX_POSITION],
                String.valueOf(exerciseArrayList.size()).length());
    }

    private void setColumnSpacingBaseOnHeaderLength() {
        for (int i = 0; i < headings.length; i++) {
            columnSpacingArray[i] = headings[i].length();
        }
    }

    @Override
    protected String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string) + COLUMN_SEPARATOR;
    }


}
