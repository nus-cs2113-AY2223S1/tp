package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.Validator;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.Calories;
import seedu.duke.records.CaloriesList;
import seedu.duke.records.Record;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.biometrics.Calculator;
import seedu.duke.records.biometrics.WeightAndFat;
import seedu.duke.records.exercise.CardioExercise;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.exercise.StrengthExercise;
import seedu.duke.records.food.Food;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ViewCommand extends Command {

    public static final String INVALID_VIEW_EXERCISE_COMMAND = "Invalid view exercise command";
    public static final String DONE = "done";
    public static final int ARRAY_LENGTH_FOR_VIEW_DONE = 2;
    private Ui ui;
    private Biometrics biometrics;
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
        int slashesCount = Parser.getArgumentsCount(arguments);
        switch (viewType) {
        case ("biometrics"):
            viewBiometrics();
            break;
        case ("food"):
            viewFood(argumentList);
            break;
        case ("exercise"):
            viewExercise(argumentList, slashesCount);
            break;
        case ("weight"):
            viewWeight();
            break;
        case ("strength"):
            viewStrengthExercise(argumentList, slashesCount);
            break;
        case ("cardio"):
            viewCardioExercise(argumentList, slashesCount);
            break;
        case ("bmi"):
            viewBmi();
            break;
        case ("maintenance"):
            viewMaintenanceCalories();
            break;
        case ("calories"):
            viewCalories(argumentList);
            break;
        case ("all"):
            viewAll(argumentList);
            break;
        default:
            handleInvalidViewType();
        }
    }

    private void viewCalories(String[] argumentList) throws IllegalValueException {
        handleInvalidViewCaloriesCommand(argumentList);
        ArrayList<Food> foodArrayList = foodList.getFoodList();
        ArrayList<Integer> caloriesConsumed = new ArrayList<Integer>();
        ArrayList<Integer> caloriesBurnt = new ArrayList<Integer>();
        ArrayList<Integer> netCalories = new ArrayList<Integer>();
        ArrayList<String> datesConsumption = new ArrayList<String>();
        ArrayList<String> datesBurnt = new ArrayList<String>();
        ArrayList<String> datesNetCalories = new ArrayList<String>();
        int newCaloriesConsumedEntry;
        int newCaloriesBurntEntry;
        int inputCaloriesBurntEntry = 0;
        int inputCaloriesConsumedEntry = 0;
        int inputnetCaloriesEntry = 0;
        CaloriesList caloriesList = new CaloriesList();
        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        calculator.setHealthyCalorieDeficit();
        calculator.setHealthyCalorieSurplus();
        for (Food f : foodArrayList) {
            newCaloriesConsumedEntry = calculator.calculateTotalCaloriesConsumed(foodArrayList, f.getDate());
            if (!datesConsumption.contains(f.getDate())) {
                caloriesConsumed.add(newCaloriesConsumedEntry);
                datesConsumption.add(f.getDate());
            }
        }
        ArrayList<Exercise> completedExerciseArrayList = exerciseList.getCompletedExerciseList();
        for (Exercise e : completedExerciseArrayList) {
            newCaloriesBurntEntry = calculator.calculateTotalCaloriesBurnt(completedExerciseArrayList, e.getDate());
            if (!datesBurnt.contains(e.getDate())) {
                caloriesBurnt.add(newCaloriesBurntEntry);
                datesBurnt.add(e.getDate());
            }
        }
        for (String d : datesConsumption) {
            if (!datesNetCalories.contains(d)) {
                netCalories.add(calculator.calculateNetCalories(completedExerciseArrayList, foodArrayList, d));
                datesNetCalories.add(d);
            }
        }
        for (String d : datesBurnt) {
            if (!datesNetCalories.contains(d)) {
                netCalories.add(calculator.calculateNetCalories(completedExerciseArrayList, foodArrayList, d));
                datesNetCalories.add(d);
            }
        }
        for (String d : datesNetCalories) {
            if (datesConsumption.indexOf(d) != -1) {
                inputCaloriesConsumedEntry = caloriesConsumed.get(datesConsumption.indexOf(d));
            }
            if (datesBurnt.indexOf(d) != -1) {
                inputCaloriesBurntEntry = caloriesBurnt.get(datesBurnt.indexOf(d));
            }
            if (datesNetCalories.indexOf(d) != -1) {
                inputnetCaloriesEntry = netCalories.get(datesNetCalories.indexOf(d));
            }
            String message = null;
            if (inputnetCaloriesEntry < 0) {
                if (inputnetCaloriesEntry < calculator.getHealthyCalorieDeficit()) {
                    message = "Your calorie deficit is too high! ";
                } else {
                    message = "Your calorie deficit is acceptable! ";
                }
            } else {
                if (inputnetCaloriesEntry > calculator.getHealthyCalorieSurplus()) {
                    message = "Your calorie surplus is too much! ";
                } else {
                    message = "Your calorie deficit is acceptable! ";
                }
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localdate = LocalDate.parse(d, formatter);
            Calories caloriesinput = new Calories(inputCaloriesConsumedEntry,
                    inputCaloriesBurntEntry, inputnetCaloriesEntry, localdate, message);
            caloriesList.addCalories(caloriesinput);
        }
        ArrayList<Calories> clist = caloriesList.getCaloriesList();
        ui.outputCalories(clist);
    }

    private void viewMaintenanceCalories() {

        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        ui.output(calculator.getActivityStatus());
        ui.output("Thus your maintenance calories is " + calculator.getIdealMaintenanceCalories());
    }

    private void viewBmi() {
        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        calculator.setBmi(biometrics.getWeight(), biometrics.getHeight());
        ui.output("Your current BMI is : " + calculator.getBmi());
        ui.output(calculator.getBmiStatus());
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

    private static void handleInvalidViewCaloriesCommand(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 1) {
            throw new IllegalValueException("Invalid view calories command");
        }
    }

    private void viewStrengthExercise(String[] argumentList, int slashesCount) throws IllegalValueException {
        handleInvalidViewExerciseCommand(argumentList, slashesCount);
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

    private void viewCardioExercise(String[] argumentList, int slashesCount) throws IllegalValueException {
        handleInvalidViewExerciseCommand(argumentList, slashesCount);
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


    private void viewExercise(String[] argumentList, int slashesCount) throws IllegalValueException {
        handleInvalidViewExerciseCommand(argumentList, slashesCount);
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


    private void handleInvalidViewExerciseCommand(String[] argumentList,
                                                  int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, 0, 1, INVALID_VIEW_EXERCISE_COMMAND,
                arguments.charAt(arguments.length() - 1));
        validateViewDone(argumentList, INVALID_VIEW_EXERCISE_COMMAND);
    }

    private void validateViewDone(String[] argumentList, String message) throws IllegalValueException {
        if (argumentList.length == ARRAY_LENGTH_FOR_VIEW_DONE && !argumentList[1].equals(DONE)) {
            throw new IllegalValueException(message);
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
