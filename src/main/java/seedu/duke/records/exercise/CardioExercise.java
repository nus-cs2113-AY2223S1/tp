package seedu.duke.records.exercise;

import java.time.LocalDate;

public class CardioExercise extends Exercise {
    private int time;

    public CardioExercise(String exerciseName, int time, int repetitions, int caloriesBurnt, LocalDate date) {
        super(exerciseName, repetitions, caloriesBurnt, date);
        this.time = time;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public String saveExercise() {
        return "cardio /" + getExerciseName() + " /"
                + getTime() + " /" + getRepetition() + " /" + getCaloriesBurnt() + " | " + getTaskStatusInNumber();
    }

    @Override
    public String toString() {
        return "Cardio Exercise: " + getExerciseName() + System.lineSeparator()
                + "Time: " + getTime() + System.lineSeparator()
                + "Repetitions: " + getRepetition() + System.lineSeparator()
                + "Calories Burnt: " + getCaloriesBurnt() + System.lineSeparator()
                + "Date: " + getDate() + System.lineSeparator()
                + String.format("Status: %s", getTaskStatus());
    }
}