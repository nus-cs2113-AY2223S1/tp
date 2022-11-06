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
import seedu.duke.ui.AllRecordsTable;
import seedu.duke.ui.ExerciseTable;
import seedu.duke.ui.FoodTable;
import seedu.duke.ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class FindCommand extends Command {
    public static final String STRENGTH_EXERCISE_NOT_FOUND = "No matching strength exercise found";
    public static final String STRENGTH_EXERCISE_FOUND = "Here are the matching strength exercises in your list:";
    public static final String CARDIO_EXERCISE_FOUND = "Here are the matching cardio exercises in your list:";
    public static final String CARDIO_EXERCISE_NOT_FOUND = "No matching cardio exercise found";
    public static final String INVALID_FIND_STRENGTH_COMMAND = "Invalid find strength command";
    public static final String INVALID_FIND_CARDIO_COMMAND = "Invalid find cardio command";
    public static final String INVALID_FIND_FOOD_COMMAND = "Invalid find food command";
    public static final String INVALID_FIND_DATE_COMMAND = "Invalid find date command";

    public static final int REQUIRED_COUNT = 1;
    public static final String STRENGTH = "strength";
    public static final String CARDIO = "cardio";
    public static final String FOOD = "food";
    public static final String CALORIES = "calories";
    public static final String INVALID_FIND_COMMAND_MESSAGE = "Invalid find command";
    public static final int EMPTY_LIST = 0;
    private String arguments;
    private ExerciseList exerciseList;
    private FoodList foodList;

    private RecordList recordList;
    private Ui ui;
    private Biometrics biometrics;

    private ArrayList<WeightAndFat> weightAndFatList;
    private ArrayList<Food> foodArrayList;
    private ArrayList<Exercise> exerciseArrayList;
    private ArrayList<Record> recordArrayList;

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalValueException {
        weightAndFatList = biometrics.weightAndFatList.getWeightAndFatList();
        foodArrayList = foodList.getFoodList();
        exerciseArrayList = getFilteredCardioExerciseList(arguments);
        recordArrayList = recordList.getRecordList(weightAndFatList,
                foodArrayList, exerciseArrayList);

        int slashesCount = Parser.getArgumentsCount(arguments);
        String[] argumentList = Parser.getArgumentList(arguments);
        String findType = Parser.getClassType(argumentList);
        switch (findType) {
        case STRENGTH:
            findStrength(argumentList, slashesCount);
            break;
        case CARDIO:
            findCardio(argumentList, slashesCount);
            break;
        case FOOD:
            findFood(argumentList, slashesCount);
            break;
        case CALORIES:
            findCalories(argumentList, slashesCount);
            break;
        default:
            handleInvalidFindType();
            break;
        }
    }

    private void findCalories(String[] argumentList, int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, REQUIRED_COUNT, INVALID_FIND_DATE_COMMAND,
                arguments.charAt(arguments.length() - 1));
        int caloriesConsumedEntry = getFilteredCaloriesConsumedList(argumentList);
        int caloriesBurntEntry = getFilteredCaloriesBurntList(argumentList);
        if (caloriesConsumedEntry == 0 && caloriesBurntEntry == 0) {
            throw new IllegalValueException("Input date does not have entries yet!");
        }
        int netCaloriesEntry = caloriesConsumedEntry - caloriesBurntEntry;
        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        calculator.setHealthyCalorieDeficit();
        calculator.setHealthyCalorieSurplus();
        String message = calculator.calorieMessage(netCaloriesEntry);
        LocalDate localdate = Validator.getDateWithValidation(argumentList[1]);
        Calories caloriesinput = new Calories(caloriesConsumedEntry,
                caloriesBurntEntry, netCaloriesEntry, localdate, message);
        CaloriesList caloriesList = new CaloriesList();
        caloriesList.addCalories(caloriesinput);
        ArrayList<Calories> clist = caloriesList.getCaloriesList();
        ui.outputCalories(clist);
    }

    private void handleInvalidFindDateCommand(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 2) {
            throw new IllegalValueException("Invalid find date command");
        }
        try {
            LocalDate.parse(argumentList[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeException e) {
            throw new IllegalValueException("Date is in the wrong format. Please follow the dd-MM-yyyy format");
        }
    }


    private void findCardio(String[] argumentList, int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, REQUIRED_COUNT, INVALID_FIND_CARDIO_COMMAND,
                arguments.charAt(arguments.length() - 1));
        String description = Validator.getDescriptionWithValidation(argumentList[1]);
        ArrayList<Exercise> filteredExerciseList = getFilteredCardioExerciseList(description);
        outputFilteredExerciseList(filteredExerciseList, CARDIO_EXERCISE_NOT_FOUND, CARDIO_EXERCISE_FOUND);
    }


    private void findStrength(String[] argumentList, int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, REQUIRED_COUNT, INVALID_FIND_STRENGTH_COMMAND,
                arguments.charAt(arguments.length() - 1));
        String description = Validator.getDescriptionWithValidation(argumentList[1]);
        ArrayList<Exercise> filteredExerciseList = getFilteredExerciseList(description);
        outputFilteredExerciseList(filteredExerciseList,
                STRENGTH_EXERCISE_NOT_FOUND, STRENGTH_EXERCISE_FOUND);
    }

    private void outputFilteredExerciseList(ArrayList<Exercise> filteredExerciseList,
                                            String failureFeedback, String successFeedback) {
        if (filteredExerciseList.size() == EMPTY_LIST) {
            ui.output(failureFeedback);
        } else {
            String caption = System.lineSeparator() + successFeedback;
            ExerciseTable filterTable = new ExerciseTable(foodArrayList, weightAndFatList,
                    filteredExerciseList, recordArrayList, caption);
            ui.printTable(filterTable.getExerciseTable());
        }
    }

    private void findFood(String[] argumentList, int slashesCount) throws IllegalValueException {
        Validator.validateCommandInput(slashesCount, REQUIRED_COUNT, INVALID_FIND_FOOD_COMMAND,
                arguments.charAt(arguments.length() - 1));
        handleInvalidFindFoodCommand(argumentList);
        ArrayList<Food> filteredFoodList = getFilteredFoodList(argumentList);
        FoodTable tableFrame = new FoodTable(
                filteredFoodList, weightAndFatList, exerciseArrayList, recordArrayList,
                "Here are the matching food in your food list:");
        ArrayList<String> table = tableFrame.getFoodTable();
        ui.printTable(table);
    }


    private static void handleInvalidFindFoodCommand(String[] argumentList) throws IllegalValueException {
        if (argumentList.length != 2) {
            throw new IllegalValueException("Invalid find food command");
        }
    }

    private ArrayList<Food> getFilteredFoodList(String[] argumentList) {
        ArrayList<Food> filteredFoodList = (ArrayList<Food>) foodList.getFoodList()
                .stream().filter(Food.class::isInstance)
                .filter(f -> f.getFoodDescription().contains(argumentList[1]))
                .collect(Collectors.toList());
        return filteredFoodList;
    }


    private ArrayList<Exercise> getFilteredCardioExerciseList(String description) {
        ArrayList<Exercise> filteredExerciseList = (ArrayList<Exercise>) exerciseList.getCompletedExerciseList()
                .stream().filter(CardioExercise.class::isInstance)
                .filter(e -> e.getExerciseName().contains(description))
                .collect(Collectors.toList());
        filteredExerciseList.addAll(exerciseList.getCurrentExerciseList()
                .stream().filter(CardioExercise.class::isInstance)
                .filter(e -> e.getExerciseName().contains(description))
                .collect(Collectors.toList()));
        return filteredExerciseList;
    }

    private int getFilteredCaloriesConsumedList(String[] argumentList) {
        int totalCaloriesConsumed;
        String filteredDate;
        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        filteredDate = argumentList[1];
        totalCaloriesConsumed = calculator.calculateTotalCaloriesConsumed(foodList.getFoodList(), filteredDate);
        return totalCaloriesConsumed;
    }

    private int getFilteredCaloriesBurntList(String[] argumentList) {
        int totalCaloriesBurnt;
        String filteredDate;
        Calculator calculator = new Calculator(biometrics.getGender(), biometrics.getWeight(),
                biometrics.getHeight(), biometrics.getAge(), biometrics.getActivityLevel());
        filteredDate = argumentList[1];
        totalCaloriesBurnt = calculator.calculateTotalCaloriesBurnt(
                exerciseList.getCompletedExerciseList(), filteredDate);
        return totalCaloriesBurnt;
    }

    private ArrayList<Exercise> getFilteredExerciseList(String description) {
        ArrayList<Exercise> filteredExerciseList = (ArrayList<Exercise>) exerciseList.getCompletedExerciseList()
                .stream().filter(StrengthExercise.class::isInstance)
                .filter(e -> e.getExerciseName().contains(description))
                .collect(Collectors.toList());
        filteredExerciseList.addAll(exerciseList.getCurrentExerciseList()
                .stream().filter(StrengthExercise.class::isInstance)
                .filter(e -> e.getExerciseName().contains(description))
                .collect(Collectors.toList()));
        return filteredExerciseList;
    }


    private void handleInvalidFindType() throws IllegalValueException {
        throw new IllegalValueException(INVALID_FIND_COMMAND_MESSAGE);
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
