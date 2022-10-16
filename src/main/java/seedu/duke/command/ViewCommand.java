package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.biometrics.Biometrics;
import seedu.duke.biometrics.WeightAndFat;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.exercise.StrengthExercise;
import seedu.duke.food.FoodList;
import seedu.duke.storage.Storage;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ViewCommand extends Command {

    private Ui ui;
    private Biometrics biometrics;
    private String arguments;
    private ExerciseList exerciseList;

    private FoodList foodList;

    public ViewCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        String[] argumentList = Parser.getArgumentList(arguments);
        String viewType = argumentList[0];
        switch (viewType) {
        case ("biometrics"):
            viewBiometrics();
            break;
        case ("food"):
            viewFood();
            break;
        case ("exercise"):
            viewExercise(argumentList);
            break;
        case ("weight"):
            viewWeight();
            break;
        case ("strength"):
            viewStrengthExercise(argumentList);
            break;
        default:
            handleInvalidViewType();
        }
    }


    private void handleInvalidViewType() throws IllegalValueException {
        throw new IllegalValueException("Invalid view command");
    }

    private void viewBiometrics() {
        ui.output("Biometrics:\n" + biometrics.toString());
    }

    private void viewWeight() {
        ui.output(biometrics.weightAndFatList.getSize() + " records of weight and fat percentage:");
        ArrayList<WeightAndFat> weightAndFatList = biometrics.weightAndFatList.getWeightAndFatList();
        for (WeightAndFat weightAndFat : weightAndFatList) {
            ui.output(weightAndFat.listWeightAndFat(weightAndFatList));
        }
    }

    private void viewFood() throws IllegalValueException {
        for (int i = 0; i < foodList.getFoodListSize(); i++) {
            ui.output((i + 1) + " " + (foodList.getFood(i)));
        }
    }

    private void viewStrengthExercise(String[] argumentList) throws IllegalValueException {
        handleInvalidViewExerciseCommand(argumentList);
        ArrayList<Exercise> strengthExerciseArrayList = getStrengthExerciseArrayListByCommand(argumentList);
        showExerciseListCaption(strengthExerciseArrayList.size(), argumentList, "Strength exercises");
        handleViewExerciseList(strengthExerciseArrayList);
    }

    private ArrayList<Exercise> getStrengthExerciseArrayListByCommand(String[] argumentList) {
        if (argumentList.length == 1) {
            return (ArrayList<Exercise>) exerciseList.getCurrentExerciseList()
                    .stream().filter(StrengthExercise.class::isInstance).collect(Collectors.toList());
        }
        return (ArrayList<Exercise>) exerciseList.getCompletedExerciseList()
                .stream().filter(StrengthExercise.class::isInstance).collect(Collectors.toList());
    }


    private void viewExercise(String[] argumentList) throws IllegalValueException {
        handleInvalidViewExerciseCommand(argumentList);
        ArrayList<Exercise> exerciseArrayList = getExerciseArrayListByCommand(argumentList);
        showExerciseListCaption(exerciseArrayList.size(), argumentList, "Exercises");
        handleViewExerciseList(exerciseArrayList);
    }

    private void showExerciseListCaption(int arrayListSize, String[] argumentList, String caption) {
        ui.printEmptyLine();
        if (argumentList.length == 1) {
            ui.output(caption + " to be done: " + arrayListSize);
        } else {
            ui.output(caption + " completed: " + arrayListSize);
        }
    }


    private ArrayList<Exercise> getExerciseArrayListByCommand(String[] argumentList) {
        if (argumentList.length == 1) {
            return exerciseList.getCurrentExerciseList();
        }
        return exerciseList.getCompletedExerciseList();
    }


    private static void handleInvalidViewExerciseCommand(String[] argumentList) throws IllegalValueException {
        if (argumentList.length == 2 && !argumentList[1].equals("done") || argumentList.length > 2) {
            throw new IllegalValueException("Invalid view command");
        }
    }

    private void handleViewExerciseList(ArrayList<Exercise> exerciseArrayList) {
        Integer[] columnSpacingArray = {5, 8, 4, 4, 8};
        getColumnsSpacing(exerciseArrayList, columnSpacingArray);
        generateExerciseTableHeader(columnSpacingArray);
        printExerciseList(exerciseArrayList, columnSpacingArray);
        ui.printEmptyLine();
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
            ui.printInSameLine(index, exerciseName, sets, repetitions, calories, status);
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
        ui.output(separatorLine, line, separatorLine);
    }

    private static String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string);
    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList) {
        this.ui = ui;
        this.biometrics = biometrics;
        this.exerciseList = exerciseList;
        this.foodList = foodList;
    }
}
