package seedu.duke.logic.command;

import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.logic.Parser;
import seedu.duke.logic.Validator;
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
import seedu.duke.ui.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static seedu.duke.logic.command.FindCommand.CALORIES_NOT_FOUND;

public class ViewCommand extends Command {

    public static final String INVALID_VIEW_EXERCISE_COMMAND = "Invalid view exercise command";
    public static final String DONE = "done";
    public static final int ARRAY_LENGTH_FOR_VIEW_DONE = 2;
    public static final String BIOMETRICS = "biometrics";
    public static final String FOOD = "food";
    public static final String EXERCISE = "exercise";
    public static final String WEIGHT = "weight";
    public static final String STRENGTH = "strength";
    public static final String CARDIO = "cardio";
    public static final String BMI = "bmi";
    public static final String MAINTENANCE = "maintenance";
    public static final String CALORIES = "calories";
    public static final String ALL = "all";
    public static final String INVALID_VIEW_COMMAND_MESSAGE = "Invalid view command";
    public static final String BIOMETRICS_LABEL = "Biometrics:";
    public static final String STRENGTH_EXERCISES_CAPTION = "Strength exercises";
    public static final String FOOD_LIST_CAPTION = "All Food Records:";
    public static final String EXERCISE_LIST_CAPTION = "Exercises";
    public static final int MINIMUM_VIEW_EXERCISE_SLASH_COUNT = 0;
    public static final int MAXIMUM_VIEW_EXERCISE_SLASH_COUNT = 1;
    public static final int INDEX_FOR_DONE = 1;
    public static final int EMPTY_LIST = 0;
    public static final String TO_BE_DONE_MESSAGE = " to be done: ";
    public static final String EXERCISE_COMPLETED_MESSAGE = " completed: ";
    public static final String CALORIES_NOT_FOUND = "No matching calories entry found";
    public static final String CALORIES_FOUND = "Here are the matching calorie entries in your list:";
    private static final String BIOMETRICS_NOT_SET = "Your biometrics are not set yet!";
    private Ui ui;
    private Biometrics biometrics;
    private String arguments;
    private ExerciseList exerciseList;

    private FoodList foodList;
    private RecordList recordList;

    private ArrayList<WeightAndFat> weightAndFatList;
    private ArrayList<Food> foodArrayList;
    private ArrayList<Exercise> exerciseArrayList;
    private ArrayList<Record> recordArrayList;

    public ViewCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        String[] argumentList = Parser.getArgumentList(arguments);

        weightAndFatList = biometrics.weightAndFatList.getWeightAndFatList();
        foodArrayList = foodList.getFoodList();
        exerciseArrayList = getExerciseArrayListByCommand(argumentList);
        recordArrayList = recordList.getRecordList(weightAndFatList,
                foodArrayList, exerciseArrayList);

        String viewType = Parser.getClassType(argumentList);
        int slashesCount = Parser.getArgumentsCount(arguments);
        switch (viewType) {
        case BIOMETRICS:
            viewBiometrics();
            break;
        case FOOD:
            viewFood(argumentList);
            break;
        case EXERCISE:
            viewExercise(argumentList, slashesCount);
            break;
        case WEIGHT:
            viewWeight();
            break;
        case STRENGTH:
            viewStrengthExercise(argumentList, slashesCount);
            break;
        case CARDIO:
            viewCardioExercise(argumentList, slashesCount);
            break;
        case BMI:
            viewBmi();
            break;
        case MAINTENANCE:
            viewMaintenanceCalories();
            break;
        case CALORIES:
            viewCalories(argumentList);
            break;
        case ALL:
            viewAll(argumentList);
            break;
        default:
            handleInvalidViewType();
            break;
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
        int inputNetCaloriesEntry = 0;
        CaloriesList calList = new CaloriesList();
        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        calculator.setIdealMaintenanceCalories();
        calculator.setBmi(biometrics.getWeight(),biometrics.getHeight());
        calculator.setHealthyCalorieDeficit();
        calculator.setHealthyCalorieSurplus();
        for (Food f : foodArrayList) {
            newCaloriesConsumedEntry = calculator.calculateTotalCaloriesConsumed(foodArrayList, f.getDateString());
            if (!datesConsumption.contains(f.getDateString())) {
                caloriesConsumed.add(newCaloriesConsumedEntry);
                datesConsumption.add(f.getDateString());
            }
        }
        ArrayList<Exercise> completedExerciseArrayList = exerciseList.getCompletedExerciseList();
        for (Exercise e : completedExerciseArrayList) {
            newCaloriesBurntEntry = calculator.calculateTotalCaloriesBurnt(completedExerciseArrayList,
                    e.getDateString());
            if (!datesBurnt.contains(e.getDateString())) {
                caloriesBurnt.add(newCaloriesBurntEntry);
                datesBurnt.add(e.getDateString());
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
            String message;
            if (datesConsumption.indexOf(d) != -1) {
                inputCaloriesConsumedEntry = caloriesConsumed.get(datesConsumption.indexOf(d));
            }
            if (datesBurnt.indexOf(d) != -1) {
                inputCaloriesBurntEntry = caloriesBurnt.get(datesBurnt.indexOf(d));
            }
            if (datesNetCalories.indexOf(d) != -1) {
                inputNetCaloriesEntry = netCalories.get(datesNetCalories.indexOf(d));
            }
            if (calculator.getBmi() == 0){
                message = BIOMETRICS_NOT_SET;
            } else {
                message = calculator.calorieMessage(inputNetCaloriesEntry);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localdate = LocalDate.parse(d, formatter);
            Calories caloriesInput = new Calories(inputCaloriesConsumedEntry,
                    inputCaloriesBurntEntry, inputNetCaloriesEntry, localdate, message);
            calList.addCalories(caloriesInput);
        }
        ArrayList<Calories> caloriesList = calList.getCaloriesList();
        if (caloriesList.size() == EMPTY_LIST) {
            ui.output(CALORIES_NOT_FOUND);
        } else {
            CaloriesTable tableFrame = new CaloriesTable(
                    foodArrayList, weightAndFatList, exerciseArrayList, recordArrayList, caloriesList, CALORIES_FOUND);
            ArrayList<String> table = tableFrame.getCaloriesTable();
            ui.printTable(table);
        }
    }

    private void viewMaintenanceCalories() {

        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        calculator.setIdealMaintenanceCalories();
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
        throw new IllegalValueException(INVALID_VIEW_COMMAND_MESSAGE);
    }

    private void viewAll(String[] argumentList) throws IllegalValueException {
        handleInvalidViewAllCommand(argumentList);
        AllRecordsTable tableFrame = new AllRecordsTable(
                foodArrayList, weightAndFatList, exerciseArrayList, recordArrayList,
                "All Records");
        ArrayList<String> table = tableFrame.getAllRecordsTable();
        ui.printTable(table);
    }

    private void viewBiometrics() {
        ui.output(BIOMETRICS_LABEL + System.lineSeparator() + biometrics.toString());
    }

    private void viewWeight() {
        ui.output(biometrics.weightAndFatList.getSize() + " records of weight and fat percentage:");
        ArrayList<WeightAndFat> weightAndFatList = biometrics.weightAndFatList.getWeightAndFatList();
        ui.outputWeightList(weightAndFatList);
    }

    private void viewFood(String[] argumentList) throws IllegalValueException {
        handleInvalidViewFoodCommand(argumentList);
        FoodTable tableFrame = new FoodTable(
                foodArrayList, weightAndFatList, exerciseArrayList, recordArrayList,
                "Food Records");
        ArrayList<String> table = tableFrame.getFoodTable();
        ui.printTable(table);
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
        String caption = getExerciseListCaption(strengthExerciseArrayList.size(),
                argumentList, STRENGTH_EXERCISES_CAPTION);
        ExerciseTable exerciseTable = new ExerciseTable(foodArrayList, weightAndFatList,
                strengthExerciseArrayList, recordArrayList, caption);
        ui.printTable(exerciseTable.getExerciseTable());
    }

    private ArrayList<Exercise> getStrengthExerciseArrayListByCommand(String[] argumentList) {
        if (isViewingCurrentList(argumentList)) {
            return (ArrayList<Exercise>) exerciseList.getCurrentExerciseList()
                    .stream().filter(StrengthExercise.class::isInstance).collect(Collectors.toList());
        }
        return (ArrayList<Exercise>) exerciseList.getCompletedExerciseList()
                .stream().filter(StrengthExercise.class::isInstance).collect(Collectors.toList());
    }

    private void viewCardioExercise(String[] argumentList, int slashesCount) throws IllegalValueException {
        handleInvalidViewExerciseCommand(argumentList, slashesCount);
        ArrayList<Exercise> cardioExerciseArrayList = getCardioExerciseArrayListByCommand(argumentList);
        String caption = getExerciseListCaption(cardioExerciseArrayList.size(), argumentList, "Cardio exercises");
        ExerciseTable exerciseTable = new ExerciseTable(foodArrayList, weightAndFatList,
                cardioExerciseArrayList, recordArrayList, caption);
        ui.printTable(exerciseTable.getExerciseTable());
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
        String caption = getExerciseListCaption(exerciseArrayList.size(), argumentList, EXERCISE_LIST_CAPTION);
        ExerciseTable exerciseTable = new ExerciseTable(foodArrayList, weightAndFatList,
                exerciseArrayList, recordArrayList, caption);
        ArrayList<String> table = exerciseTable.getExerciseTable();
        ui.printTable(table);

    }

    private String getExerciseListCaption(int size, String[] argumentList, String caption) {
        if (isViewingCurrentList(argumentList)) {
            return System.lineSeparator() + caption + TO_BE_DONE_MESSAGE + size;
        }
        return System.lineSeparator() + caption + EXERCISE_COMPLETED_MESSAGE + size;
    }


    private ArrayList<Exercise> getExerciseArrayListByCommand(String[] argumentList) {
        if (isViewingCurrentList(argumentList)) {
            return exerciseList.getCurrentExerciseList();
        }
        return exerciseList.getCompletedExerciseList();
    }

    private static boolean isViewingCurrentList(String[] argumentList) {
        return argumentList.length == 1;
    }


    private void handleInvalidViewExerciseCommand(String[] argumentList,
                                                  int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, MINIMUM_VIEW_EXERCISE_SLASH_COUNT,
                MAXIMUM_VIEW_EXERCISE_SLASH_COUNT, INVALID_VIEW_EXERCISE_COMMAND,
                arguments.charAt(arguments.length() - 1));
        validateViewDone(argumentList, INVALID_VIEW_EXERCISE_COMMAND);
    }

    private void validateViewDone(String[] argumentList, String message) throws IllegalValueException {
        if (argumentList.length == ARRAY_LENGTH_FOR_VIEW_DONE && !argumentList[INDEX_FOR_DONE].equals(DONE)) {
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
