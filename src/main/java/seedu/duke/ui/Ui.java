package seedu.duke.ui;

import seedu.duke.records.Calories;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.CardioExercise;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.StrengthExercise;

import java.util.ArrayList;
import java.util.Scanner;


public class Ui {


    private static Scanner in = new Scanner(System.in);


    public void line() {
        System.out.println("-------------------------------------------------------------------------------");
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

    public void printTable(ArrayList<String> table) {
        for (String row : table) {
            output(row);
        }
        printEmptyLine();
    }

    public void printInSameLine(String... output) {
        for (String line : output) {
            System.out.print(line);
        }
        System.out.println();
    }



    public void printEmptyLine() {
        output("");
    }


    public void outputWeightList(ArrayList<WeightAndFat> weightAndFatArrayList) {
        Integer[] columnSpacingArray = {5, 6, 14, 4};
        getWeightColumnsSpacing(weightAndFatArrayList, columnSpacingArray);
        generateWeightTableHeader(columnSpacingArray);
        printWeightList(weightAndFatArrayList, columnSpacingArray);
        printEmptyLine();
    }


    private void getWeightColumnsSpacing(ArrayList<WeightAndFat> weightAndFatArrayList, Integer[] columnSpacingArray) {
        columnSpacingArray[0] = Math.max(columnSpacingArray[0], weightAndFatArrayList.size() % 10 + 1);
        for (WeightAndFat weightAndFat : weightAndFatArrayList) {
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], String.valueOf(weightAndFat.getWeight()).length());
            columnSpacingArray[2] = Math.max(columnSpacingArray[2], String.valueOf(weightAndFat.getFat()).length());
            columnSpacingArray[3] = Math.max(columnSpacingArray[3], weightAndFat.getDateString().length());
        }
    }


    private void printWeightList(ArrayList<WeightAndFat> weightAndFatArrayList, Integer[] columnSpacingArray) {
        for (int i = 0; i < weightAndFatArrayList.size(); i++) {
            WeightAndFat weightAndFat = weightAndFatArrayList.get(i);
            String index = addRightPadding(Integer.toString(i + 1), columnSpacingArray[0]) + " | ";
            String weight = addRightPadding(Integer.toString(weightAndFat.getWeight()), columnSpacingArray[1]) + " | ";
            String fat = addRightPadding(Integer.toString(weightAndFat.getFat()), columnSpacingArray[2]) + " | ";
            String date = addRightPadding(weightAndFat.getDateString(), columnSpacingArray[3]) + " | ";
            printInSameLine(index, weight, fat, date);
        }
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

    private static String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string);
    }

}
