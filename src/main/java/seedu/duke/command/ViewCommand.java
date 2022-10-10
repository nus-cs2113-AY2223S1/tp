package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;

import java.util.ArrayList;

import static seedu.duke.command.AddCommand.foodList;

public class ViewCommand extends Command {


    private Ui ui;
    private Biometrics biometrics;
    private String arguments;
    private ExerciseList exerciseList;

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
        default:
            handleInvalidViewType();
        }
    }

    private void handleInvalidViewType() throws IllegalValueException {
        throw new IllegalValueException("Invalid view command");
    }

    private void viewBiometrics() {
        ui.output(biometrics.toString());
    }

    private void viewFood() {
        for (int i = 0; i < foodList.size(); i++) {
            ui.output((i + 1) + " " + (foodList.get(i)).toString());
        }
    }

    private void viewExercise(String[] argumentList) throws IllegalValueException {
        if (argumentList.length == 2 && !argumentList[1].equals("done") || argumentList.length > 2) {
            throw new IllegalValueException("Invalid view command");
        }
        if (argumentList.length == 1) {
            viewCurrentExercise();
        } else if (argumentList.length == 2) {
            viewCompletedExercise();
        }
    }

    private void viewCompletedExercise() {
        ArrayList<Exercise> completedExerciseArrayListList = exerciseList.getCompletedExerciseList();
        Integer[] columnSpacingArray = {5, 8, 3, 8};
        ui.printEmptyLine();
        ui.showCompletedExerciseCaption(completedExerciseArrayListList.size());
        getColumnsSpacing(completedExerciseArrayListList, columnSpacingArray);
        generateTableHeader(columnSpacingArray);
        printExerciseList(completedExerciseArrayListList, columnSpacingArray);
        ui.printEmptyLine();
    }

    private void viewCurrentExercise() {
        ui.printEmptyLine();
        ArrayList<Exercise> exerciseArrayList = exerciseList.getCurrentExerciseList();
        Integer[] columnSpacingArray = {5, 8, 3, 8};
        ui.showCurrentExerciseCaption(exerciseArrayList.size());
        getColumnsSpacing(exerciseArrayList, columnSpacingArray);
        generateTableHeader(columnSpacingArray);
        printExerciseList(exerciseArrayList, columnSpacingArray);
        ui.printEmptyLine();
    }

    private void getColumnsSpacing(ArrayList<Exercise> exerciseArrayList, Integer[] columnSpacingArray) {
        for (Exercise exercise : exerciseArrayList) {
            columnSpacingArray[1] = Math.max(columnSpacingArray[1], exercise.getExerciseName().length());
            columnSpacingArray[2] = Math.max(columnSpacingArray[2], exercise.getRepetitions() % 10 + 1);
            columnSpacingArray[3] = Math.max(columnSpacingArray[3], exercise.getCaloriesBurnt() % 10 + 1);
        }
    }

    private void printExerciseList(ArrayList<Exercise> exerciseArrayListList, Integer[] columnSpacingArray) {
        for (int i = 0; i < exerciseArrayListList.size(); i++) {
            Exercise exercise = exerciseArrayListList.get(i);
            String index = addRightPadding(Integer.toString(i + 1), columnSpacingArray[0]) + " | ";
            String exerciseName = addRightPadding(exercise.getExerciseName(), columnSpacingArray[1]) + " | ";
            String repetitions = addRightPadding(Integer.toString(exercise.getRepetitions()),
                    columnSpacingArray[2]) + " | ";
            String calories = addRightPadding(Integer.toString(exercise.getCaloriesBurnt()),
                    columnSpacingArray[3]) + " | ";
            String status = exercise.getTaskStatus();
            ui.printInSameLine(index, exerciseName, repetitions, calories, status);
        }
    }

    private void generateTableHeader(Integer[] columnSpacingArray) {
        String paddedIndex = addRightPadding("index", columnSpacingArray[0]) + " | ";
        String paddedExercise = addRightPadding("Exercise", columnSpacingArray[1]) + " | ";
        String paddedRep = addRightPadding("Rep", columnSpacingArray[2]) + " | ";
        String paddedCalories = addRightPadding("Calories", columnSpacingArray[3]) + " | ";
        String paddedStatus = "Status";
        String line = paddedIndex + paddedExercise + paddedRep + paddedCalories + paddedStatus;
        String separatorLine = "-".repeat(line.length());
        ui.output(separatorLine, line, separatorLine);
    }

    private static String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string);
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics, ExerciseList exerciseList) {
        this.ui = ui;
        this.biometrics = biometrics;
        this.exerciseList = exerciseList;
    }
}
