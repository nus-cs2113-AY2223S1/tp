package seedu.duke.exercise;

import seedu.duke.command.DateCommand;

public class Exercise {
    private String date;
    private String exerciseName;
    private int repetition;
    private int caloriesBurnt;
    private boolean isDone;


    public Exercise(String exerciseName, int repetitions, int caloriesBurnt, String date) {
        this.exerciseName = exerciseName;
        this.repetition = repetitions;
        this.caloriesBurnt = caloriesBurnt;
        this.isDone = false;
        this.date = date;
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

    public int getTime() {
        return 1;
    }

    public String getDate() {
        return date;
    }

    public String getTaskStatus() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
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
}
