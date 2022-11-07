package seedu.duke.records.exercise;

import java.time.LocalDate;

public class StrengthExercise extends Exercise {
    private int weight;
    private int set;


    public StrengthExercise(String exerciseName, int weight, int set, int repetitions, LocalDate date) {
        super(exerciseName, repetitions, date);
        this.weight = weight;
        this.set = set;
    }

    @Override
    public int getSet() {
        return set;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String saveExercise() {
        return "strength /" + getExerciseName() + " /" + getWeight() + " /"
                + getSet() + " /" + getRepetition() + " /" + getDateString() + " /" + getTime()
                + " /" + getCaloriesBurnt() + " | " + getTaskStatusInNumber();
    }

    @Override
    public String toString() {
        return "Strength Exercise: " + getExerciseName() + System.lineSeparator()
                + "Weight: " + getWeight() + "kg" + System.lineSeparator()
                + "Sets: " + getSet() + System.lineSeparator()
                + "Repetitions: " + getRepetition() + System.lineSeparator()
                + "Date: " + getDateString() + System.lineSeparator()
                + String.format("Status: %s", getTaskStatus());
    }
}
