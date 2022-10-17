package seedu.duke;

import seedu.duke.exercise.Exercise;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {


    private static Scanner in = new Scanner(System.in);


    public void line() {
        System.out.println("--------------------------------------------------");
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
        Integer[] columnSpacingArray = {5, 8, 4, 4, 8};
        getColumnsSpacing(exerciseArrayList, columnSpacingArray);
        generateExerciseTableHeader(columnSpacingArray);
        printExerciseList(exerciseArrayList, columnSpacingArray);
        printEmptyLine();
    }

    private void getColumnsSpacing(ArrayList<Exercise> exerciseArrayList, Integer[] columnSpacingArray) {
        columnSpacingArray[0] = Math.max(columnSpacingArray[0], exerciseArrayList.size() % 10 + 1);
        for (Exercise exercise : exerciseArrayList) {
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], exercise.getExerciseName().length());
            columnSpacingArray[2] = Math.max(columnSpacingArray[2], exercise.getRepetitions() % 10 + 1);
            columnSpacingArray[3] = Math.max(columnSpacingArray[3], exercise.getRepetitions() % 10 + 1);
            columnSpacingArray[4] = Math.max(columnSpacingArray[4], exercise.getCaloriesBurnt() % 10 + 1);
        }
    }

    private void printExerciseList(ArrayList<Exercise> exerciseArrayListList, Integer[] columnSpacingArray) {
        for (int i = 0; i < exerciseArrayListList.size(); i++) {
            Exercise exercise = exerciseArrayListList.get(i);
            String index = addRightPadding(Integer.toString(i + 1), columnSpacingArray[0]) + " | ";
            String exerciseName = addRightPadding(exercise.getExerciseName(), columnSpacingArray[1]) + " | ";
            String sets = addRightPadding(Integer.toString(exercise.getSet()),
                    columnSpacingArray[3]) + " | ";
            String repetitions = addRightPadding(Integer.toString(exercise.getRepetitions()),
                    columnSpacingArray[3]) + " | ";
            String calories = addRightPadding(Integer.toString(exercise.getCaloriesBurnt()),
                    columnSpacingArray[4]) + " | ";
            String status = exercise.getTaskStatus();
            printInSameLine(index, exerciseName, sets, repetitions, calories, status);
        }
    }

    private void generateExerciseTableHeader(Integer[] columnSpacingArray) {
        String paddedIndex = addRightPadding("Index", columnSpacingArray[0]) + " | ";
        String paddedExercise = addRightPadding("Exercise", columnSpacingArray[1]) + " | ";
        String paddedSets = addRightPadding("Sets", columnSpacingArray[2]) + " | ";
        String paddedRep = addRightPadding("Reps", columnSpacingArray[3]) + " | ";
        String paddedCalories = addRightPadding("Calories", columnSpacingArray[4]) + " | ";
        String paddedStatus = "Status";
        String line = paddedIndex + paddedExercise + paddedSets + paddedRep + paddedCalories + paddedStatus;
        String separatorLine = "-".repeat(line.length());
        output(separatorLine, line, separatorLine);
    }

    private static String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string);
    }
}
