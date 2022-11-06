package seedu.duke.records.biometrics;

import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.food.Food;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Calculator {
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
            multiplier = 1.2;
            break;
        case (2):
            multiplier = 1.375;
            break;
        case (3):
            multiplier = 1.55;
            break;
        case (4):
            multiplier = 1.725;
            break;
        case (5):
            multiplier = 1.9;
            break;
        default:
            multiplier = 0;
        }
        if (gender.equals("male")) {
            maintenanceCalories = (int) ((66 + (13.8 * weight) + (5.00 * height) - (6.8 * age))
                    * multiplier);// hard-coded formula which has to use magic numbers
        } else if (gender.equals("female")) {
            maintenanceCalories = (int) ((655 + (9.56 * weight) + (1.85 * height) - (4.7 * age)) * multiplier);
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
            throw new IllegalValueException("Weight cannot be 0. Pls update biometrics");
        }
        return (int) Math.round(0.0175 * biometrics.getWeight() * metabolicEquivalent * time);
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

