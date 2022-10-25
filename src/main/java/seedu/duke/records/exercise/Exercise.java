package seedu.duke.records.exercise;
import seedu.duke.records.Record;

import java.time.LocalDate;

public class Exercise extends Record {
    private LocalDate date;
    private double time;
    private String exerciseName;
    private int repetition;
    private int caloriesBurnt;
    private boolean isDone;

    public Exercise(String exerciseName, int repetitions, LocalDate date) {
        super(date);
        this.exerciseName = exerciseName;
        this.repetition = repetitions;
        this.time = 0.0;
        this.caloriesBurnt = 0;
        this.isDone = false;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getRepetition() {
        return repetition;
    }

    public int getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public boolean getDone() {
        return isDone;
    }

    public int getSet() {
        return 1;
    }

    public double getDistance() {
        return 1;
    }

    public String getTaskStatus() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    public String getTime() {
        return String.format("%.1f", time);
    }

    public int getTaskStatusInNumber() {
        if (isDone) {
            return 1;
        }
        return 0;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setCaloriesBurnt(int caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Exercise: " + exerciseName + System.lineSeparator()
                + "Repetitions: " + repetition + System.lineSeparator()
                + "Calories Burnt: " + caloriesBurnt + System.lineSeparator()
                + String.format("Status: %s", getTaskStatus());
    }

    public String saveExercise() {
        return "exercise /" + getExerciseName() + " /"
                + getSet() + " /" + getRepetition() + " /" + getCaloriesBurnt() + " | " + getTaskStatusInNumber();
    }

    public int getWeight() {
        return 0;
    }
}
