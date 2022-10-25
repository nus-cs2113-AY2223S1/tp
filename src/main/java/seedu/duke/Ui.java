package seedu.duke;

import seedu.duke.records.Record;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.CardioExercise;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.StrengthExercise;
import seedu.duke.records.food.Food;
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

        Integer[] columnSpacingArraySubHeader = { 10, 6, 14, 8, 8, 10, 10, 4, 4, 4, 15};
        getAllRecordsColumnsSpacing(weightAndFatArrayList,
                foodArrayList, exerciseArrayList, columnSpacingArraySubHeader);

        String subHeader = generateAllDataTableSubHeader(columnSpacingArraySubHeader);

        Integer[] columnSpacingArrayHeader = { 10, 23, 55};
        getAllRecordsHeaderSpacing(columnSpacingArrayHeader, subHeader);
        generateAllDataTableHeader(columnSpacingArrayHeader);
        String separatorLine = "-".repeat(subHeader.length());
        output(separatorLine, subHeader, separatorLine);
        printAllRecords(recordArrayList, columnSpacingArraySubHeader, subHeader);
        printEmptyLine();
    }

    private void getExerciseColumnsSpacing(ArrayList<Exercise> exerciseArrayList, Integer[] columnSpacingArray) {
        columnSpacingArray[0] = Math.max(columnSpacingArray[0], String.valueOf(exerciseArrayList.size()).length());
        for (Exercise exercise : exerciseArrayList) {
            if (exercise instanceof StrengthExercise) {
                columnSpacingArray[1] = Math.max(columnSpacingArray[1], String.valueOf(exercise.getWeight()).length());
                columnSpacingArray[2] = Math.max(columnSpacingArray[2], String.valueOf(exercise.getSet()).length());
            } else if (exercise instanceof CardioExercise) {
                columnSpacingArray[5] = Math.max(columnSpacingArray[5],
                        String.valueOf((int) exercise.getDistance()).length());
            }
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], exercise.getExerciseName().length());
            columnSpacingArray[4] = Math.max(columnSpacingArray[4],
                    String.valueOf(exercise.getRepetition()).length());
            columnSpacingArray[6] = Math.max(columnSpacingArray[6],
                    String.valueOf(exercise.getCaloriesBurnt()).length());
        }
    }

    private void getFoodColumnsSpacing(ArrayList<Food> foodArrayList, Integer[] columnSpacingArray) {
        columnSpacingArray[0] = Math.max(columnSpacingArray[0], String.valueOf(foodArrayList.size()).length());
        for (Food food : foodArrayList) {
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], food.getFoodDescription().length());
            columnSpacingArray[2] = Math.max(columnSpacingArray[2], String.valueOf(food.getCalories()).length());
            columnSpacingArray[3] = Math.max(columnSpacingArray[3], food.getDate().length());
        }
    }

    private void getWeightColumnsSpacing(ArrayList<WeightAndFat> weightAndFatArrayList, Integer[] columnSpacingArray) {
        columnSpacingArray[0] = Math.max(columnSpacingArray[0], weightAndFatArrayList.size() % 10 + 1);
        for (WeightAndFat weightAndFat : weightAndFatArrayList) {
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], String.valueOf(weightAndFat.getWeight()).length());
            columnSpacingArray[2] = Math.max(columnSpacingArray[2], String.valueOf(weightAndFat.getFat()).length());
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
                columnSpacingArray[6] = Math.max(columnSpacingArray[6], String.valueOf(exercise.getWeight()).length());
                columnSpacingArray[7] = Math.max(columnSpacingArray[7], String.valueOf(exercise.getSet()).length());
            } else if (exercise instanceof CardioExercise) {
                columnSpacingArray[9] = Math.max(columnSpacingArray[9],
                        String.valueOf((int) exercise.getDistance()).length());
            }
            columnSpacingArray[5] = Math.max(columnSpacingArray[5], exercise.getExerciseName().length());
            columnSpacingArray[8] = Math.max(columnSpacingArray[8],
                    String.valueOf(exercise.getRepetition()).length());
            columnSpacingArray[10] = Math.max(columnSpacingArray[10],
                    String.valueOf(exercise.getCaloriesBurnt()).length());
        }
    }

    private void getAllRecordsHeaderSpacing(Integer[] columnSpacingArray, String subHeader) {
        columnSpacingArray[0] = subHeader.substring(0, subHeader.indexOf("| Weight")).length() - 1;
        columnSpacingArray[1] = subHeader.substring(subHeader.indexOf("| Weight"),
                subHeader.indexOf("| Description")).length() - 3;
        columnSpacingArray[2] = subHeader.substring(subHeader.indexOf("| Description"),
                subHeader.indexOf("| Exercise")).length() - 3;
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

    private void printAllRecords(ArrayList<Record> recordArrayList, Integer[] columnSpacingArray, String header) {
        for (int i = 0; i < recordArrayList.size(); i++) {
            if (recordArrayList.get(i) instanceof WeightAndFat) {
                String date = addRightPadding(recordArrayList.get(i).getDate(), columnSpacingArray[0]) + " | ";
                WeightAndFat weightAndFat = (WeightAndFat) recordArrayList.get(i);
                String weight = addRightPadding(Integer.toString(weightAndFat.getWeight()),
                        columnSpacingArray[1]) + " | ";
                String fat = addRightPadding(Integer.toString(weightAndFat.getFat()), columnSpacingArray[2]) + " | ";
                printInSameLine(date, weight, fat);
            }
            if (recordArrayList.get(i) instanceof Food) {
                String date = addRightPadding(recordArrayList.get(i).getDate(),
                        header.substring(0, header.indexOf("| Description")).length() - 1) + " | ";
                Food food = (Food) recordArrayList.get(i);
                String foodName = addRightPadding(food.getFoodDescription(), columnSpacingArray[3]) + " | ";
                String calories = addRightPadding(Integer.toString(food.getCalories()),
                        columnSpacingArray[4]) + " | ";
                printInSameLine(date, foodName, calories);
            }
            if (recordArrayList.get(i) instanceof Exercise) {
                String date = addRightPadding(recordArrayList.get(i).getDate(),
                        header.substring(0, header.indexOf("| Exercise")).length() - 1) + " | ";
                Exercise exercise = (Exercise) recordArrayList.get(i);
                String exerciseName = addRightPadding(exercise.getExerciseName(), columnSpacingArray[5]) + " | ";

                String weight = getWeightForPrint(exercise, columnSpacingArray[6]) + " | ";
                String sets = addRightPadding(Integer.toString(exercise.getSet()),
                        columnSpacingArray[7]) + " | ";
                String repetitions = addRightPadding(Integer.toString(exercise.getRepetition()),
                        columnSpacingArray[8]) + " | ";
                String distance = getDistanceForPrint(exercise, columnSpacingArray[9]) + " | ";
                String calories = getCaloriesForPrint(exercise, columnSpacingArray[10]) + " | ";
                String status = exercise.getTaskStatus();
                printInSameLine(date, exerciseName, weight, sets, repetitions, distance, calories, status);
            }
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
        String paddedBlank = addRightPadding("", columnSpacingArray[0]) + " | ";
        String paddedWeightFat = addRightPadding("Weight&Fat", columnSpacingArray[1]) + " | ";
        String paddedFood = addRightPadding("Food", columnSpacingArray[2]) + " | ";
        String paddedExercise = "Exercise";
        String header = paddedBlank + paddedWeightFat + paddedFood + paddedExercise;
        String separatorLine = "-".repeat(header.length());
        output(separatorLine, header);
    }

    private String generateAllDataTableSubHeader(Integer[] columnSpacingArray) {
        String paddedDate = addRightPadding("Date", columnSpacingArray[0]) + " | ";

        String paddedWeight = addRightPadding("Weight", columnSpacingArray[1]) + " | ";
        String paddedFat = addRightPadding("Fat Percentage", columnSpacingArray[2]) + " | ";

        String paddedDescription = addRightPadding("Description", columnSpacingArray[3]) + " | ";
        String paddedCalories = addRightPadding("Calories", columnSpacingArray[4]) + " | ";

        String paddedExercise = addRightPadding("Exercise", columnSpacingArray[5]) + " | ";
        String paddedWeights = addRightPadding("Weights", columnSpacingArray[6]) + " | ";
        String paddedSets = addRightPadding("Sets", columnSpacingArray[7]) + " | ";
        String paddedRep = addRightPadding("Reps", columnSpacingArray[8]) + " | ";
        String paddedDist = addRightPadding("Dist", columnSpacingArray[9]) + " | ";
        String paddedCaloriesBurnt = addRightPadding("Calories Burnt", columnSpacingArray[10]) + " | ";
        String paddedStatus = "Status";

        String line = paddedDate + paddedWeight + paddedFat + paddedDescription + paddedCalories + paddedExercise
                + paddedWeights + paddedSets + paddedRep + paddedDist + paddedCaloriesBurnt + paddedStatus;
        return line;
    }

    private static String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string);
    }

    private static String addLeftPadding(String string, int numberOfSpace) {
        return String.format("%" + numberOfSpace + "s", string);
    }
}
