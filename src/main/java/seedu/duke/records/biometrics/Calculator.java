package seedu.duke.records.biometrics;

import seedu.duke.exception.IllegalValueException;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.food.Food;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Calculator {
    private int height;
    private int weight;
    private int maintenanceCalories;
    private int age;
    private String gender;
    private int activityLevel;
    private double multiplier;
    private double bmi;


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
        String bmiStatus = "You are currently unclassified as your BMI is not set yet!";
        double healthyweight = 18.5;
        double overweight = 24.9;
        double obese = 29.9;
        double extremelyobese = 50.0;
        if (this.bmi < healthyweight) {
            bmiStatus = "You are currently in the underweight range";
        } else if (this.bmi <= overweight) {
            bmiStatus = "You are currently in the healthy range";
        } else if (this.bmi <= obese) {
            bmiStatus = "You are currently in the overweight range";
        } else if (this.bmi <= extremelyobese) {
            bmiStatus = "You are currently in the obese range. Start your workout journey! ";
        }
        return bmiStatus;
    }

    public double getBmi() {
        return this.bmi;
    }

    public void setBmi(int weight,int height) {
        this.bmi = Double.valueOf(Double.valueOf(weight)
                / (Double.valueOf(height) / 100 * Double.valueOf(height) / 100));
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


    public int getIdealMaintenanceCalories() {
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
            maintenanceCalories = (int) ((66 + (13.8 * weight) + (5.00 * height) - (6.8 * age)) * multiplier);
        } else if (gender.equals("female")) {
            maintenanceCalories = (int) ((655 + (9.56 * weight) + (1.85 * height) - (4.7 * age)) * multiplier);
        }
        return maintenanceCalories;
    }


    public int calculateTotalCaloriesConsumed(ArrayList<Food> foodArrayList, String date) {
        int totalCaloriesConsumed = 0;
        ArrayList<Food> filteredFoodDateList = (ArrayList<Food>) foodArrayList
                .stream().filter(Food.class::isInstance)
                .filter(f -> f.getDate().contains(date))
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
        return (int) (0.0175 * biometrics.getWeight() * metabolicEquivalent * time);
    }

    public int calculateTotalCaloriesBurnt(ArrayList<Exercise> completedExerciseArrayList, String date) {
        int totalCaloriesBurnt = 0;
        ArrayList<Exercise> filteredExerciseDateList = (ArrayList<Exercise>) completedExerciseArrayList
                .stream().filter(Exercise.class::isInstance)
                .filter(e -> e.getDate().contains(date))
                .collect(Collectors.toList());
        for (Exercise e : filteredExerciseDateList) {
            totalCaloriesBurnt += e.getCaloriesBurnt();
        }
        return totalCaloriesBurnt;
    }

    public int calculateTotalCaloriesSurplusDeficit(ArrayList<Exercise> completedExerciseArrayList,
                                                    ArrayList<Food> foodArrayList, String date) {
        return calculateTotalCaloriesConsumed(foodArrayList, date)
                - calculateTotalCaloriesBurnt(completedExerciseArrayList, date);
    }


}

