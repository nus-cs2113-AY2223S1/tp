package seedu.duke.command;

import seedu.duke.records.biometrics.Calculator;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.records.Record;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.exercise.CardioExercise;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.exercise.StrengthExercise;
import seedu.duke.records.food.Food;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ViewCommand extends Command {

    private Ui ui;
    private Biometrics biometrics;
    private Calculator calculator;
    private String arguments;
    private ExerciseList exerciseList;

    private FoodList foodList;

    private RecordList recordList;

    public ViewCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        String[] argumentList = Parser.getArgumentList(arguments);
        String viewType = Parser.getClassType(argumentList);
        switch (viewType) {
        case ("biometrics"):
            viewBiometrics();
            break;
        case ("food"):
            viewFood(argumentList);
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
        case ("cardio"):
            viewCardioExercise(argumentList);
            break;
        case ("bmi"):
            viewBmi();
            break;
        case ("maintenance"):
            viewMaintenanceCalories();
            break;
        case ("all"):
            viewAll(argumentList);
            break;
        default:
            handleInvalidViewType();
        }
    }

    private void viewMaintenanceCalories() {
        ui.line();
        ui.output(calculator.getActivityStatus());
        ui.output("Thus your maintenance calories is " + calculator.getIdealMaintenanceCalories());
        ui.line();
    }

    private void viewBmi() {
        ui.line();
        ui.output("Your current BMI is :" + calculator.getBmi());
        ui.output(calculator.getBmiStatus());
        ui.line();
    }


    private void handleInvalidViewType() throws IllegalValueException {
        throw new IllegalValueException("Invalid view command");
    }

    private void viewAll(String[] argumentList) throws IllegalValueException {
        handleInvalidViewAllCommand(argumentList);
        ArrayList<WeightAndFat> weightAndFatList = biometrics.weightAndFatList.getWeightAndFatList();
        ArrayList<Food> foodArrayList = foodList.getFoodList();
        ArrayList<Exercise> exerciseArrayList = getExerciseArrayListByCommand(argumentList);
        ArrayList<Record> recordArrayList = recordList.getRecordList(weightAndFatList,
                foodArrayList, exerciseArrayList);
        ui.outputAllRecords(recordArrayList, weightAndFatList, foodArrayList, exerciseArrayList);
    }

    private void viewBiometrics() {
        ui.output("Biometrics:\n" + biometrics.toString());
    }

    private void viewWeight() {
        ui.output(biometrics.weightAndFatList.getSize() + " records of weight and fat percentage:");
        ArrayList<WeightAndFat> weightAndFatList = biometrics.weightAndFatList.getWeightAndFatList();
        ui.outputWeightList(weightAndFatList);
    }

    private void viewFood(String[] argumentList) throws IllegalValueException {
        handleInvalidViewFoodCommand(argumentList);
        ArrayList<Food> foodArrayList = foodList.getFoodList();
        ui.outputFoodList(foodArrayList);
    }

    private static void handleInvalidViewFoodCommand(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 1) {
            throw new IllegalValueException("Invalid view food command");
        }
    }

    private static void handleInvalidViewAllCommand(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 1) {
            throw new IllegalValueException("Invalid view all command");
        }
    }

    private void viewStrengthExercise(String[] argumentList) throws IllegalValueException {
        handleInvalidViewExerciseCommand(argumentList);
        ArrayList<Exercise> strengthExerciseArrayList = getStrengthExerciseArrayListByCommand(argumentList);
        ui.showExerciseListCaption(strengthExerciseArrayList.size(), argumentList, "Strength exercises");
        ui.outputExerciseList(strengthExerciseArrayList);
    }

    private ArrayList<Exercise> getStrengthExerciseArrayListByCommand(String[] argumentList) {
        if (argumentList.length == 1) {
            return (ArrayList<Exercise>) exerciseList.getCurrentExerciseList()
                    .stream().filter(StrengthExercise.class::isInstance).collect(Collectors.toList());
        }
        return (ArrayList<Exercise>) exerciseList.getCompletedExerciseList()
                .stream().filter(StrengthExercise.class::isInstance).collect(Collectors.toList());
    }

    private void viewCardioExercise(String[] argumentList) throws IllegalValueException {
        handleInvalidViewExerciseCommand(argumentList);
        ArrayList<Exercise> cardioExerciseArrayList = getCardioExerciseArrayListByCommand(argumentList);
        ui.showExerciseListCaption(cardioExerciseArrayList.size(), argumentList, "Cardio exercises");
        ui.outputExerciseList(cardioExerciseArrayList);
    }

    private ArrayList<Exercise> getCardioExerciseArrayListByCommand(String[] argumentList) {
        if (argumentList.length == 1) {
            return (ArrayList<Exercise>) exerciseList.getCurrentExerciseList()
                    .stream().filter(CardioExercise.class::isInstance).collect(Collectors.toList());
        }
        return (ArrayList<Exercise>) exerciseList.getCompletedExerciseList()
                .stream().filter(CardioExercise.class::isInstance).collect(Collectors.toList());
    }


    private void viewExercise(String[] argumentList) throws IllegalValueException {
        handleInvalidViewExerciseCommand(argumentList);
        ArrayList<Exercise> exerciseArrayList = getExerciseArrayListByCommand(argumentList);
        ui.showExerciseListCaption(exerciseArrayList.size(), argumentList, "Exercises");
        ui.outputExerciseList(exerciseArrayList);
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

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {
        this.ui = ui;
        this.biometrics = biometrics;
        this.exerciseList = exerciseList;
        this.foodList = foodList;
        this.recordList = recordList;
    }
}
