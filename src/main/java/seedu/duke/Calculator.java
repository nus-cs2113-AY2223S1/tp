package seedu.duke;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;


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
        this.bmi = 0;
        this.maintenanceCalories = 0;
        this.multiplier = 0;
    }


    public String getBmiStatus() {
        String bmiStatus;
        if (bmi < 18.5) {
            bmiStatus = "You are currently in the underweight range";
        } else if ((bmi >= 18.5) && (bmi <= 24.9)) {
            bmiStatus = "You are currently in the healthy range";
        } else if ((bmi >= 25) && (bmi <= 29.9)) {
            bmiStatus = "You are currently in the overweight range";
        } else {
            bmiStatus = "You are currently in the obese range. Start your workout journey! ";
        }
        return bmiStatus;
    }

    public double getBmi() {
        bmi = Double.valueOf(weight / ((height / 100) * (height / 100)));
        return bmi;
    }

    public String getActivityStatus() {
        String activityStatus;
        switch (activityLevel) {
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
        switch (activityLevel) {
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

    public static int calculateCalories(Biometrics biometrics, double time,
                                        double metabolicEquivalent) throws IllegalValueException {
        if (biometrics.getWeight() == 0) {
            throw new IllegalValueException("Weight cannot be 0. Pls update biometrics");
        }
        return (int) (0.0175 * biometrics.getWeight() * metabolicEquivalent * time);
    }
}

