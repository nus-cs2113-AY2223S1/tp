package seedu.duke.records.biometrics;

import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.food.Food;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Calculator {
    public static final String EMPTY_WEIGHT_MESSAGE = "Weight cannot be 0. Pls update biometrics";
    public static final double CALORIES_MULTIPLIER = 0.0175;
    public static final double MALE_WEIGHT_MULTIPLIER = 13.8;
    public static final double FEMALE_WEIGHT_MULTIPLIER = 9.56;

    public static final double MALE_HEIGHT_MULTIPLIER = 5.0;
    public static final double FEMALE_HEIGHT_MULTIPLIER = 1.85;
    public static final double MALE_AGE_MULTIPLIER = 6.8;
    public static final double FEMALE_AGE_MULTIPLIER = 4.7;
    public static final double MALE_CONSTANT_MULTIPLIER = 66;
    public static final double FEMALE_CONSTANT_MULTIPLIER = 655;
    public static final double SEDENTARY_MULTIPLIER = 1.2;
    public static final double LIGHTLY_ACTIVE_MULTIPLIER = 1.375;
    public static final double MODERATELY_ACTIVE_MULTIPLIER = 1.55;
    public static final double VERY_ACTIVE_MULTIPLIER = 1.725;
    public static final double EXTREMELY_ACTIVE_MULTIPLIER = 1.9;

    public static final String INVALID_BMI = "Re-check your height and weight inputs.";
    public static final String UNDERWEIGHT_BMI = "You are currently in the underweight range";
    public static final String HEALTHY_BMI = "You are currently in the healthy range";
    public static final String OVERWEIGHT_BMI = "You are currently in the overweight range";
    public static final String OBESE_BMI = "You are currently in the obese range. Start your workout journey! ";
    public static final String VERY_HIGH_BMI = "Re-check your height and weight inputs. "
            + "Your BMI is astonishingly high.";
    public static final String SEDENTARY_LIFESTYLE = "You have a sedentary lifestyle!";
    public static final String LIGHTLY_ACTIVE_LIFESTYLE = "You have a lightly active lifestyle!";
    public static final String MODERATELY_ACTIVE_LIFESTYLE = "You have a moderately active lifestyle!";
    public static final String VERY_ACTIVE_LIFESTYLE = "You have a very active lifestyle!";
    public static final String EXTREMELY_ACTIVE_LIFESTYLE = "You have an extremely active lifestyle!";
    public static final String UNDECLARED_LIFESTYLE = "You have not declared your activity level!";
    public static final String HIGH_CALORIE_DEFICIT = "Your calorie deficit is too high! ";
    public static final String ACCEPTABLE_CALORIE_DEFICIT = "Your calorie deficit is acceptable! ";
    public static final String HIGH_CALORIE_SURPLUS = "Your calorie surplus is too much! ";
    public static final String ACCEPTABLE_CALORIE_SURPLUS = "Your calorie surplus is acceptable! ";
    private int height;
    private int weight;
    private int maintenanceCalories;
    private int healthyCalorieSurplus;
    private int healthyCalorieDeficit;
    private int age;
    private String gender;
    private int activityLevel;
    private double multiplier;
    private double bmi;
    private double healthyweight = 18.5;
    private double overweight = 24.9;
    private double obese = 29.9;
    private double maxbmi = 105.3;


    public Calculator(String gender, int weight, int height, int age, int activityLevel) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.maintenanceCalories = 0;
        this.multiplier = 0;
    }


    /**
     * To get the bmistatus of the user after bmi has been set.
     * @return a message corresponding to the bmistatus of the user
     */
    public String getBmiStatus() {
        String bmiStatus;
        if (bmi == 0.0) {
            bmiStatus = INVALID_BMI;
        } else if (bmi < healthyweight) {
            bmiStatus = UNDERWEIGHT_BMI;
        } else if (bmi <= overweight) {
            bmiStatus = HEALTHY_BMI;
        } else if (bmi <= obese) {
            bmiStatus = OVERWEIGHT_BMI;
        } else if (bmi <= maxbmi) {
            bmiStatus = OBESE_BMI;
        } else {
            bmiStatus = VERY_HIGH_BMI;
        }
        return bmiStatus;
    }

    /**
     * To get the bmi of the user.
     * @return bmi of the user
     */
    public double getBmi() {
        return bmi;
    }

    /**
     * To set the bmi of the user with the height and weight user inputs.
     */
    public void setBmi(int weight, int height) {
        bmi = Math.round(Double.valueOf(Double.valueOf(weight)
                / (Double.valueOf(height) / 100 * Double.valueOf(height) / 100)) * 100.0) / 100.0;
    }

    /**
     * To get the activitystatus of the user after the user inputs his/her activity level.
     * @return a message corresponding to the activitystatus of the user
     */
    public String getActivityStatus() {
        String activityStatus;
        switch (this.activityLevel) {
        case (1):
            activityStatus = SEDENTARY_LIFESTYLE;
            break;
        case (2):
            activityStatus = LIGHTLY_ACTIVE_LIFESTYLE;
            break;
        case (3):
            activityStatus = MODERATELY_ACTIVE_LIFESTYLE;
            break;
        case (4):
            activityStatus = VERY_ACTIVE_LIFESTYLE;
            break;
        case (5):
            activityStatus = EXTREMELY_ACTIVE_LIFESTYLE;
            break;
        default:
            activityStatus = UNDECLARED_LIFESTYLE;
        }
        return activityStatus;
    }


    /**
     * To set the maintenance calories of the user based on his/her biometrics.
     */
    public void setIdealMaintenanceCalories() {
        switch (this.activityLevel) {
        case (1):
            multiplier = SEDENTARY_MULTIPLIER;
            break;
        case (2):
            multiplier = LIGHTLY_ACTIVE_MULTIPLIER;
            break;
        case (3):
            multiplier = MODERATELY_ACTIVE_MULTIPLIER;
            break;
        case (4):
            multiplier = VERY_ACTIVE_MULTIPLIER;
            break;
        case (5):
            multiplier = EXTREMELY_ACTIVE_MULTIPLIER;
            break;
        default:
            multiplier = 0;
        }
        if (gender.equals("male")) {
            maintenanceCalories = (int) ((MALE_CONSTANT_MULTIPLIER
                    + (MALE_WEIGHT_MULTIPLIER * weight) + (MALE_HEIGHT_MULTIPLIER * height)
                    - (MALE_AGE_MULTIPLIER * age)) * multiplier);
        } else if (gender.equals("female")) {
            maintenanceCalories = (int) ((FEMALE_CONSTANT_MULTIPLIER
                    + (FEMALE_WEIGHT_MULTIPLIER * weight) + (FEMALE_HEIGHT_MULTIPLIER * height)
                    - (FEMALE_AGE_MULTIPLIER * age)) * multiplier);
        }
    }

    /**
     * To get the maintenance calories based on user biometrics.
     * @return the maintenance calories of the user calculated by the formula
     */
    public int getIdealMaintenanceCalories() {
        return maintenanceCalories;
    }

    /**
     * To calculate and thus set the healthy calorie surplus of the user.
     */
    public void setHealthyCalorieSurplus() {
        healthyCalorieSurplus = (int) (maintenanceCalories * 0.1);
    }

    /**
     * To calculate and thus set the healthy calorie deficit of the user.
     */
    public void setHealthyCalorieDeficit() {
        healthyCalorieDeficit = -(int) (maintenanceCalories * 0.2);
    }

    /**
     * To return a message based on the net calorie surplus/deficit status.
     */
    public String calorieMessage(int netCalories) {
        String message;
        if (netCalories < 0) {
            if (netCalories < healthyCalorieDeficit) {
                message = HIGH_CALORIE_DEFICIT;
            } else {
                message = ACCEPTABLE_CALORIE_DEFICIT;
            }
        } else {
            if (netCalories > healthyCalorieSurplus) {
                message = HIGH_CALORIE_SURPLUS;
            } else {
                message = ACCEPTABLE_CALORIE_SURPLUS;
            }
        }
        return message;
    }


    public int calculateTotalCaloriesConsumed(ArrayList<Food> foodArrayList, String date) {
        int totalCaloriesConsumed = 0;
        ArrayList<Food> filteredFoodDateList = (ArrayList<Food>) foodArrayList
                .stream().filter(Food.class::isInstance)
                .filter(f -> f.getDateString().contains(date))
                .collect(Collectors.toList());
        for (Food f : filteredFoodDateList) {
            totalCaloriesConsumed += f.getCalories();
        }
        return totalCaloriesConsumed;
    }

    public static int calculateExerciseCalories(Biometrics biometrics, double time,
                                                double metabolicEquivalent) throws IllegalValueException {
        if (biometrics.getWeight() == 0) {
            throw new IllegalValueException(EMPTY_WEIGHT_MESSAGE);
        }
        return (int) Math.round(CALORIES_MULTIPLIER * biometrics.getWeight() * metabolicEquivalent * time);
    }

    public int calculateTotalCaloriesBurnt(ArrayList<Exercise> completedExerciseArrayList, String date) {
        int totalCaloriesBurnt = 0;
        ArrayList<Exercise> filteredExerciseDateList = (ArrayList<Exercise>) completedExerciseArrayList
                .stream().filter(Exercise.class::isInstance)
                .filter(e -> e.getDateString().contains(date))
                .collect(Collectors.toList());
        for (Exercise e : filteredExerciseDateList) {
            totalCaloriesBurnt += e.getCaloriesBurnt();
        }
        return totalCaloriesBurnt;
    }

    public int calculateNetCalories(ArrayList<Exercise> completedExerciseArrayList,
                                    ArrayList<Food> foodArrayList, String date) {
        return calculateTotalCaloriesConsumed(foodArrayList, date)
                - calculateTotalCaloriesBurnt(completedExerciseArrayList, date);
    }


}

