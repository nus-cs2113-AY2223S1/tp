package seedu.duke;

import seedu.duke.exercise.CardioExercise;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.StrengthExercise;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {


    private static Scanner in = new Scanner(System.in);


    public void line() {
        System.out.println("-------------------------------------------------------------");
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
        Integer[] columnSpacingArray = {5, 8, 4, 4, 4, 8};
        getColumnsSpacing(exerciseArrayList, columnSpacingArray);
        generateExerciseTableHeader(columnSpacingArray);
        printExerciseList(exerciseArrayList, columnSpacingArray);
        printEmptyLine();
    }

    private void getColumnsSpacing(ArrayList<Exercise> exerciseArrayList, Integer[] columnSpacingArray) {
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
            String status = exercise.getTaskStatus();
            printInSameLine(index, exerciseName, sets, time, repetitions, calories, status);
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
        String paddedStatus = "Status";
        String line = paddedIndex + paddedExercise + paddedSets + paddedTime + paddedRep
                + paddedCalories + paddedStatus;
        String separatorLine = "-".repeat(line.length());
        output(separatorLine, line, separatorLine);
    }

    private static String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string);
    }
}
