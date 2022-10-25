package seedu.duke.records.exercise;

import java.time.LocalDate;

public class CardioExercise extends Exercise {
    private double distance;

    public CardioExercise(String exerciseName, double distance, int repetitions, LocalDate date) {
        super(exerciseName, repetitions, date);
        this.distance = distance;
    }


    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public String saveExercise() {
        return "cardio /" + getExerciseName() + " /" + getDistance()
                + " /" + getRepetition() + " /" + getDate() + " /"
                + getTime() + " /" + getCaloriesBurnt() + " | " + getTaskStatusInNumber();
    }

    @Override
    public String toString() {
        return "Cardio Exercise: " + getExerciseName() + System.lineSeparator()
                + "Distance: " + getDistance() + "km" + System.lineSeparator()
                + "Repetitions: " + getRepetition() + System.lineSeparator()
                + "Date: " + getDate() + System.lineSeparator()
                + String.format("Status: %s", getTaskStatus());
    }
}