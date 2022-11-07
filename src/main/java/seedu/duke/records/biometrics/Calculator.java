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


    public String getBmiStatus() {
        String bmiStatus;
        if (bmi == 0.0) {
            bmiStatus = "Re-check your height and weight inputs.";
        } else if (bmi < healthyweight) {
            bmiStatus = "You are currently in the underweight range";
        } else if (bmi <= overweight) {
            bmiStatus = "You are currently in the healthy range";
        } else if (bmi <= obese) {
            bmiStatus = "You are currently in the overweight range";
        } else if (bmi <= maxbmi) {
            bmiStatus = "You are currently in the obese range. Start your workout journey! ";
        } else {
            bmiStatus = "Re-check your height and weight inputs. Your BMI is astonishingly high.";
        }
        return bmiStatus;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(int weight, int height) {
        bmi = Math.round(Double.valueOf(Double.valueOf(weight)
                / (Double.valueOf(height) / 100 * Double.valueOf(height) / 100)) * 100.0) / 100.0;
    }

    public String getActivityStatus() {
        String activityStatus;
        switch (this.activityLevel) {
        case (1):
            activityStatus = "You have a sedentary lifestyle!";
            break;
        case (2):
            activityStatus = "You have a lightly active lifestyle!";
            break;
        case (3):
            activityStatus = "You have a moderately active lifestyle!";
            break;
        case (4):
            activityStatus = "You have a very active lifestyle!";
            break;
        case (5):
            activityStatus = "You have an extremely active lifestyle!";
            break;
        default:
            activityStatus = "You have not declared your activity level!";
        }
        return activityStatus;
    }


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
            maintenanceCalories = (int) ((MALE_CONSTANT_MULTIPLIER + (MALE_WEIGHT_MULTIPLIER * weight) + (MALE_HEIGHT_MULTIPLIER * height) - (MALE_AGE_MULTIPLIER * age))
                    * multiplier);
        } else if (gender.equals("female")) {
            maintenanceCalories = (int) ((FEMALE_CONSTANT_MULTIPLIER + (FEMALE_WEIGHT_MULTIPLIER * weight) + (FEMALE_HEIGHT_MULTIPLIER * height) - (FEMALE_AGE_MULTIPLIER * age)) * multiplier);
        }
    }

    public int getIdealMaintenanceCalories() {
        return maintenanceCalories;
    }

    public void setHealthyCalorieSurplus() {
        healthyCalorieSurplus = (int) (maintenanceCalories * 0.1);
    }

    public void setHealthyCalorieDeficit() {
        healthyCalorieDeficit = -(int) (maintenanceCalories * 0.2);
    }

    public String calorieMessage(int netCalories) {
        String message;
        if (netCalories < 0) {
            if (netCalories < healthyCalorieDeficit) {
                message = "Your calorie deficit is too high! ";
            } else {
                message = "Your calorie deficit is acceptable! ";
            }
        } else {
            if (netCalories > healthyCalorieSurplus) {
                message = "Your calorie surplus is too much! ";
            } else {
                message = "Your calorie surplus is acceptable! ";
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

