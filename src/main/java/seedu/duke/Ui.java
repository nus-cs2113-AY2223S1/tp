package seedu.duke;

import seedu.duke.records.Record;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.CardioExercise;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.StrengthExercise;
import seedu.duke.records.food.Food;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {


    private static Scanner in = new Scanner(System.in);


    public void line() {
        System.out.println("--------------------------------------------------------------------------");
    }


    public String input() {

        return in.nextLine();
    }

    /**
     * Prints output to terminal.
     *
     * @param output Varargs output for printing
     */
    public void output(String... output) {
        assert (output != null);
        for (String line : output) {
            System.out.println(line);
        }
    }

    public void printInSameLine(String... output) {
        for (String line : output) {
            System.out.print(line);
        }
        System.out.println();
    }

    public void showExerciseListCaption(int arrayListSize, String[] argumentList, String caption) {
        printEmptyLine();
        if (argumentList.length == 1) {
            output(caption + " to be done: " + arrayListSize);
        } else {
            output(caption + " completed: " + arrayListSize);
        }
    }

    public void printEmptyLine() {
        output("");
    }

    public void outputExerciseList(ArrayList<Exercise> exerciseArrayList) {
        Integer[] columnSpacingArray = {5, 8, 4, 4, 4, 8, 8};
        getExerciseColumnsSpacing(exerciseArrayList, columnSpacingArray);
        generateExerciseTableHeader(columnSpacingArray);
        printExerciseList(exerciseArrayList, columnSpacingArray);
        printEmptyLine();
    }

    public void outputFoodList(ArrayList<Food> foodArrayList) {
        Integer[] columnSpacingArray = {5, 12, 8, 4};
        getFoodColumnsSpacing(foodArrayList, columnSpacingArray);
        generateFoodTableHeader(columnSpacingArray);
        printFoodList(foodArrayList, columnSpacingArray);
        printEmptyLine();
    }

    public void outputWeightList(ArrayList<WeightAndFat> weightAndFatArrayList) {
        Integer[] columnSpacingArray = {5, 6, 14, 4};
        getWeightColumnsSpacing(weightAndFatArrayList, columnSpacingArray);
        generateWeightTableHeader(columnSpacingArray);
        printWeightList(weightAndFatArrayList, columnSpacingArray);
        printEmptyLine();
    }

    public void outputAllRecords(ArrayList<Record> recordArrayList,
                                 ArrayList<WeightAndFat> weightAndFatArrayList,
                                 ArrayList<Food> foodArrayList,
                                 ArrayList<Exercise> exerciseArrayList) {
        Integer[] columnSpacingArrayHeader = { 4, 20, 20, 28};
        Integer[] columnSpacingArraySubHeader = { 4, 6, 14, 12, 8, 8, 4, 4, 4, 8};
        getAllRecordsColumnsSpacing(weightAndFatArrayList,
                foodArrayList, exerciseArrayList, columnSpacingArraySubHeader);
        generateAllDataTableHeader(columnSpacingArrayHeader);
        generateAllDataTableSubHeader(columnSpacingArraySubHeader);
        printAllRecords(recordArrayList, columnSpacingArraySubHeader);
        printEmptyLine();
    }

    private void getExerciseColumnsSpacing(ArrayList<Exercise> exerciseArrayList, Integer[] columnSpacingArray) {
        columnSpacingArray[0] = Math.max(columnSpacingArray[0], exerciseArrayList.size() % 10 + 1);
        for (Exercise exercise : exerciseArrayList) {
            if (exercise instanceof StrengthExercise) {
                columnSpacingArray[2] = Math.max(columnSpacingArray[2], exercise.getSet() % 10 + 1);
            } else if (exercise instanceof CardioExercise) {
                columnSpacingArray[3] = Math.max(columnSpacingArray[3], exercise.getTime() % 10 + 1);
            }
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], exercise.getExerciseName().length());
            columnSpacingArray[4] = Math.max(columnSpacingArray[4], exercise.getRepetition() % 10 + 1);
            columnSpacingArray[5] = Math.max(columnSpacingArray[5], exercise.getCaloriesBurnt() % 10 + 1);
            columnSpacingArray[6] = Math.max(columnSpacingArray[6], exercise.getDate().length());
        }
    }

    private void getFoodColumnsSpacing(ArrayList<Food> foodArrayList, Integer[] columnSpacingArray) {
        columnSpacingArray[0] = Math.max(columnSpacingArray[0], foodArrayList.size() % 10 + 1);
        for (Food food : foodArrayList) {
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], food.getFoodDescription().length());
            columnSpacingArray[2] = Math.max(columnSpacingArray[2], food.getCalories() % 10 + 1);
            columnSpacingArray[3] = Math.max(columnSpacingArray[3], food.getDate().length());
        }
    }

    private void getWeightColumnsSpacing(ArrayList<WeightAndFat> weightAndFatArrayList, Integer[] columnSpacingArray) {
        columnSpacingArray[0] = Math.max(columnSpacingArray[0], weightAndFatArrayList.size() % 10 + 1);
        for (WeightAndFat weightAndFat : weightAndFatArrayList) {
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], weightAndFat.getWeight() % 10 + 1);
            columnSpacingArray[2] = Math.max(columnSpacingArray[2], weightAndFat.getFat() % 10 + 1);
            columnSpacingArray[3] = Math.max(columnSpacingArray[3], weightAndFat.getDate().length());
        }
    }

    private void getAllRecordsColumnsSpacing(ArrayList<WeightAndFat> weightAndFatArrayList,
                                             ArrayList<Food> foodArrayList,
                                             ArrayList<Exercise> exerciseArrayList,
                                             Integer[] columnSpacingArray) {
        for (WeightAndFat weightAndFat : weightAndFatArrayList) {
            columnSpacingArray[0] = Math.max(columnSpacingArray[0], weightAndFat.getDate().length());
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], weightAndFat.getWeight() % 10 + 1);
            columnSpacingArray[2] = Math.max(columnSpacingArray[2], weightAndFat.getFat() % 10 + 1);
        }
        for (Food food : foodArrayList) {
            columnSpacingArray[3] = Math.max(columnSpacingArray[3], food.getFoodDescription().length());
            columnSpacingArray[4] = Math.max(columnSpacingArray[4], food.getCalories() % 10 + 1);
        }
        for (Exercise exercise : exerciseArrayList) {
            if (exercise instanceof StrengthExercise) {
                columnSpacingArray[6] = Math.max(columnSpacingArray[6], exercise.getSet() % 10 + 1);
            } else if (exercise instanceof CardioExercise) {
                columnSpacingArray[7] = Math.max(columnSpacingArray[7], exercise.getTime() % 10 + 1);
            }
            columnSpacingArray[5] = Math.max(columnSpacingArray[5], exercise.getExerciseName().length());
            columnSpacingArray[8] = Math.max(columnSpacingArray[8], exercise.getRepetition() % 10 + 1);
            columnSpacingArray[9] = Math.max(columnSpacingArray[9], exercise.getCaloriesBurnt() % 10 + 1);
        }
    }

    private void printExerciseList(ArrayList<Exercise> exerciseArrayListList, Integer[] columnSpacingArray) {
        for (int i = 0; i < exerciseArrayListList.size(); i++) {
            Exercise exercise = exerciseArrayListList.get(i);
            String index = addRightPadding(Integer.toString(i + 1), columnSpacingArray[0]) + " | ";
            String exerciseName = addRightPadding(exercise.getExerciseName(), columnSpacingArray[1]) + " | ";
            String sets = addRightPadding(Integer.toString(exercise.getSet()),
                    columnSpacingArray[2]) + " | ";
            String time = getTimeForPrint(exercise, columnSpacingArray[3]) + " | ";
            String repetitions = addRightPadding(Integer.toString(exercise.getRepetition()),
                    columnSpacingArray[4]) + " | ";
            String calories = addRightPadding(Integer.toString(exercise.getCaloriesBurnt()),
                    columnSpacingArray[5]) + " | ";
            String date = addRightPadding(exercise.getDate(),columnSpacingArray[6]) + " | ";
            String status = exercise.getTaskStatus();
            printInSameLine(index, exerciseName, sets, time, repetitions, calories, date, status);
        }
    }

    private void printFoodList(ArrayList<Food> foodArrayList, Integer[] columnSpacingArray) {
        for (int i = 0; i < foodArrayList.size(); i++) {
            Food food = foodArrayList.get(i);
            String index = addRightPadding(Integer.toString(i + 1), columnSpacingArray[0]) + " | ";
            String foodName = addRightPadding(food.getFoodDescription(), columnSpacingArray[1]) + " | ";
            String calories = addRightPadding(Integer.toString(food.getCalories()),
                    columnSpacingArray[2]) + " | ";
            String date = addRightPadding(food.getDate(), columnSpacingArray[3]) + " | ";
            printInSameLine(index, foodName, calories, date);
        }
    }

    private void printWeightList(ArrayList<WeightAndFat> weightAndFatArrayList, Integer[] columnSpacingArray) {
        for (int i = 0; i < weightAndFatArrayList.size(); i++) {
            WeightAndFat weightAndFat = weightAndFatArrayList.get(i);
            String index = addRightPadding(Integer.toString(i + 1), columnSpacingArray[0]) + " | ";
            String weight = addRightPadding(Integer.toString(weightAndFat.getWeight()), columnSpacingArray[1]) + " | ";
            String fat = addRightPadding(Integer.toString(weightAndFat.getFat()), columnSpacingArray[2]) + " | ";
            String date = addRightPadding(weightAndFat.getDate(), columnSpacingArray[3]) + " | ";
            printInSameLine(index, weight, fat, date);
        }
    }

    private void printAllRecords(ArrayList<Record> recordArrayList, Integer[] columnSpacingArray) {
        for (int i = 0; i < recordArrayList.size(); i++) {
            String date = addRightPadding(recordArrayList.get(i).getDate(), columnSpacingArray[0]) + " | ";
            if (recordArrayList.get(i) instanceof WeightAndFat){
                WeightAndFat weightAndFat = (WeightAndFat) recordArrayList.get(i);
                String weight = addRightPadding(Integer.toString(weightAndFat.getWeight()),
                        columnSpacingArray[1]) + " | ";
                String fat = addRightPadding(Integer.toString(weightAndFat.getFat()), columnSpacingArray[2]) + " | ";
                printInSameLine(date, weight, fat);
            }
            if (recordArrayList.get(i) instanceof Food){
                Food food = (Food) recordArrayList.get(i);
                String foodName = addRightPadding(food.getFoodDescription(), columnSpacingArray[3]) + " | ";
                String calories = addRightPadding(Integer.toString(food.getCalories()),
                        columnSpacingArray[4]) + " | ";
                printInSameLine(date, foodName, calories);
            }
            if (recordArrayList.get(i) instanceof Exercise){
                Exercise exercise = (Exercise) recordArrayList.get(i);
                String exerciseName = addRightPadding(exercise.getExerciseName(), columnSpacingArray[1]) + " | ";
                String sets = addRightPadding(Integer.toString(exercise.getSet()),
                        columnSpacingArray[2]) + " | ";
                String time = getTimeForPrint(exercise, columnSpacingArray[3]) + " | ";
                String repetitions = addRightPadding(Integer.toString(exercise.getRepetition()),
                        columnSpacingArray[4]) + " | ";
                String calories = addRightPadding(Integer.toString(exercise.getCaloriesBurnt()),
                        columnSpacingArray[5]) + " | ";
                String status = exercise.getTaskStatus();
                printInSameLine(date, exerciseName, sets, time, repetitions, calories, status);
            }
        }
    }

    private String getTimeForPrint(Exercise exercise, Integer numberOfSpace) {
        if (exercise instanceof StrengthExercise) {
            return addRightPadding("-", numberOfSpace);
        }
        return addRightPadding(Integer.toString(exercise.getTime()), numberOfSpace);
    }

    private void generateExerciseTableHeader(Integer[] columnSpacingArray) {
        String paddedIndex = addRightPadding("Index", columnSpacingArray[0]) + " | ";
        String paddedExercise = addRightPadding("Exercise", columnSpacingArray[1]) + " | ";
        String paddedSets = addRightPadding("Sets", columnSpacingArray[2]) + " | ";
        String paddedTime = addRightPadding("Time", columnSpacingArray[3]) + " | ";
        String paddedRep = addRightPadding("Reps", columnSpacingArray[4]) + " | ";
        String paddedCalories = addRightPadding("Calories", columnSpacingArray[5]) + " | ";
        String paddedDate = addRightPadding("Date",columnSpacingArray[6]) + " | ";
        String paddedStatus = "Status";
        String line = paddedIndex + paddedExercise + paddedSets + paddedTime + paddedRep
                + paddedCalories + paddedDate + paddedStatus;
        String separatorLine = "-".repeat(line.length() + 4);
        output(separatorLine, line, separatorLine);
    }

    private void generateFoodTableHeader(Integer[] columnSpacingArray) {
        String paddedIndex = addRightPadding("Index", columnSpacingArray[0]) + " | ";
        String paddedDescription = addRightPadding("Description", columnSpacingArray[1]) + " | ";
        String paddedCalories = addRightPadding("Calories", columnSpacingArray[2]) + " | ";
        String paddedDate = addRightPadding("Date", columnSpacingArray[3]) + " | ";
        String line = paddedIndex + paddedDescription + paddedCalories + paddedDate;
        String separatorLine = "-".repeat(line.length());
        output(separatorLine, line, separatorLine);
    }

    private void generateWeightTableHeader(Integer[] columnSpacingArray) {
        String paddedIndex = addRightPadding("Index", columnSpacingArray[0]) + " | ";
        String paddedWeight = addRightPadding("Weight", columnSpacingArray[1]) + " | ";
        String paddedFat = addRightPadding("Fat Percentage", columnSpacingArray[2]) + " | ";
        String paddedDate = addRightPadding("Date", columnSpacingArray[3]) + " | ";
        String line = paddedIndex + paddedWeight + paddedFat + paddedDate;
        String separatorLine = "-".repeat(line.length());
        output(separatorLine, line, separatorLine);
    }

    private void generateAllDataTableHeader(Integer[] columnSpacingArray) {
        String paddedDate = addRightPadding("Date", columnSpacingArray[0]) + " | ";
        String paddedWeightFat = addRightPadding("Weight&Fat", columnSpacingArray[1]) + " | ";
        String paddedFood = addRightPadding("Food", columnSpacingArray[2]) + " | ";
        String paddedExercise = addRightPadding("Exercise", columnSpacingArray[3]) + " | ";
        String header = paddedDate + paddedWeightFat + paddedFood + paddedExercise;
        String separatorLine = "-".repeat(header.length());
        output(separatorLine, header, separatorLine);
    }
    private void generateAllDataTableSubHeader(Integer[] columnSpacingArray) {
        String paddedDate = addRightPadding("Date", columnSpacingArray[0]) + " | ";

        String paddedWeight = addRightPadding("Weight", columnSpacingArray[1]) + " | ";
        String paddedFat = addRightPadding("Fat Percentage", columnSpacingArray[2]) + " | ";

        String paddedDescription = addRightPadding("Description", columnSpacingArray[3]) + " | ";
        String paddedCalories = addRightPadding("Calories", columnSpacingArray[4]) + " | ";

        String paddedExercise = addRightPadding("Exercise", columnSpacingArray[5]) + " | ";
        String paddedSets = addRightPadding("Sets", columnSpacingArray[6]) + " | ";
        String paddedTime = addRightPadding("Time", columnSpacingArray[7]) + " | ";
        String paddedRep = addRightPadding("Reps", columnSpacingArray[8]) + " | ";
        String paddedStatus = "Status";

        String line = paddedDate + paddedWeight + paddedFat + paddedDescription + paddedCalories + paddedExercise
                + paddedSets + paddedTime + paddedRep + paddedStatus;
        String separatorLine = "-".repeat(line.length());
        output(separatorLine, line, separatorLine);
    }

    private static String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string);
    }
}
