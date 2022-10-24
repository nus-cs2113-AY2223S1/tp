package seedu.duke;

import seedu.duke.exercise.CardioExercise;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.StrengthExercise;
import seedu.duke.food.Food;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {


    private static Scanner in = new Scanner(System.in);


    public void line() {
        System.out.println("---------------------------------------------------------------------------------");
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
        Integer[] columnSpacingArray = {5, 8, 6, 4, 4, 4, 8, 10};
        getExerciseColumnsSpacing(exerciseArrayList, columnSpacingArray);
        generateExerciseTableHeader(columnSpacingArray);
        printExerciseList(exerciseArrayList, columnSpacingArray);
        printEmptyLine();
    }

    public void outputFoodList(ArrayList<Food> foodArrayList) {
        Integer[] columnSpacingArray = {5, 12, 8};
        getFoodColumnsSpacing(foodArrayList, columnSpacingArray);
        generateFoodTableHeader(columnSpacingArray);
        printFoodList(foodArrayList, columnSpacingArray);
        printEmptyLine();
    }

    private void getExerciseColumnsSpacing(ArrayList<Exercise> exerciseArrayList, Integer[] columnSpacingArray) {
        columnSpacingArray[0] = Math.max(columnSpacingArray[0], exerciseArrayList.size() % 10 + 1);
        for (Exercise exercise : exerciseArrayList) {
            if (exercise instanceof StrengthExercise) {
                columnSpacingArray[1] = Math.max(columnSpacingArray[1], exercise.getWeight() % 10 + 1);
                columnSpacingArray[2] = Math.max(columnSpacingArray[2], exercise.getSet() % 10 + 1);
            } else if (exercise instanceof CardioExercise) {
                columnSpacingArray[5] = Math.max(columnSpacingArray[5], (int) exercise.getDistance() % 10 + 1);
            }
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], exercise.getExerciseName().length());
            columnSpacingArray[4] = Math.max(columnSpacingArray[4], exercise.getRepetition() % 10 + 1);
            columnSpacingArray[6] = Math.max(columnSpacingArray[6], exercise.getCaloriesBurnt() % 10 + 1);
        }
    }

    private void getFoodColumnsSpacing(ArrayList<Food> foodArrayList, Integer[] columnSpacingArray) {
        columnSpacingArray[0] = Math.max(columnSpacingArray[0], foodArrayList.size() % 10 + 1);
        for (Food food : foodArrayList) {
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], food.getFoodDescription().length());
            columnSpacingArray[2] = Math.max(columnSpacingArray[2], food.getCalories() % 10 + 1);
        }
    }

    private void printExerciseList(ArrayList<Exercise> exerciseArrayListList, Integer[] columnSpacingArray) {
        for (int i = 0; i < exerciseArrayListList.size(); i++) {
            Exercise exercise = exerciseArrayListList.get(i);
            String index = addRightPadding(Integer.toString(i + 1), columnSpacingArray[0])
                    + " | ";
            String exerciseName = addRightPadding(exercise.getExerciseName(), columnSpacingArray[1])
                    + " | ";
            String weight = getWeightForPrint(exercise, columnSpacingArray[2]) + " | ";
            String sets = addRightPadding(Integer.toString(exercise.getSet()),
                    columnSpacingArray[3]) + " | ";
            String repetitions = addRightPadding(Integer.toString(exercise.getRepetition()),
                    columnSpacingArray[4]) + " | ";
            String distance = getDistanceForPrint(exercise, columnSpacingArray[5]) + " | ";
            String calories = getCaloriesForPrint(exercise, columnSpacingArray[6]) + " | ";
            String date = addRightPadding(exercise.getDate(), columnSpacingArray[7]) + " | ";
            String status = exercise.getTaskStatus();
            printInSameLine(index, exerciseName, weight, sets, repetitions, distance, calories, date, status);
        }
    }

    private static String getCaloriesForPrint(Exercise exercise, int numberOfSpace) {
        if (exercise.getCaloriesBurnt() == 0) {
            return addRightPadding("-", numberOfSpace);
        }
        return addRightPadding(Integer.toString(exercise.getCaloriesBurnt()),
                numberOfSpace);
    }

    private void printFoodList(ArrayList<Food> foodArrayListList, Integer[] columnSpacingArray) {
        for (int i = 0; i < foodArrayListList.size(); i++) {
            Food food = foodArrayListList.get(i);
            String index = addRightPadding(Integer.toString(i + 1), columnSpacingArray[0]) + " | ";
            String foodName = addRightPadding(food.getFoodDescription(), columnSpacingArray[1]) + " | ";
            String calories = addRightPadding(Integer.toString(food.getCalories()),
                    columnSpacingArray[2]) + " | ";
            printInSameLine(index, foodName, calories);
        }
    }

    private String getDistanceForPrint(Exercise exercise, Integer numberOfSpace) {
        if (exercise instanceof StrengthExercise) {
            return addRightPadding("-", numberOfSpace);
        }
        return addRightPadding(Double.toString(exercise.getDistance()), numberOfSpace);
    }

    private String getWeightForPrint(Exercise exercise, Integer numberOfSpace) {
        if (exercise instanceof CardioExercise) {
            return addRightPadding("-", numberOfSpace);
        }
        return addRightPadding(Integer.toString(exercise.getWeight()), numberOfSpace);
    }


    private void generateExerciseTableHeader(Integer[] columnSpacingArray) {
        String paddedIndex = addRightPadding("Index", columnSpacingArray[0]) + " | ";
        String paddedExercise = addRightPadding("Exercise", columnSpacingArray[1]) + " | ";
        String paddedWeight = addRightPadding("Weight", columnSpacingArray[2]) + " | ";
        String paddedSets = addRightPadding("Sets", columnSpacingArray[3]) + " | ";
        String paddedRep = addRightPadding("Reps", columnSpacingArray[4]) + " | ";
        String paddedDistance = addRightPadding("Dist", columnSpacingArray[5]) + " | ";
        String paddedCalories = addRightPadding("Calories", columnSpacingArray[6]) + " | ";
        String paddedDate = addRightPadding("Date", columnSpacingArray[7]) + " | ";
        String paddedStatus = "Status";
        String line = paddedIndex + paddedExercise + paddedWeight + paddedSets
                + paddedRep + paddedDistance + paddedCalories + paddedDate + paddedStatus;
        String separatorLine = "-".repeat(line.length());
        output(separatorLine, line, separatorLine);
    }

    private void generateFoodTableHeader(Integer[] columnSpacingArray) {
        String paddedIndex = addRightPadding("Index", columnSpacingArray[0]) + " | ";
        String paddedDescription = addRightPadding("Description", columnSpacingArray[1]) + " | ";
        String paddedCalories = addRightPadding("Calories", columnSpacingArray[2]) + " | ";
        String line = paddedIndex + paddedDescription + paddedCalories;
        String separatorLine = "-".repeat(line.length());
        output(separatorLine, line, separatorLine);
    }

    private static String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string);
    }
}
