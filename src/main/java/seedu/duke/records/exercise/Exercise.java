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

    /**
     * To get the name of the exercise.
     * @return the name of the exercise
     */
    public String getExerciseName() {
        return exerciseName;
    }

    /**
     * To get the number of repetitions of the exercise.
     * @return the number of repetitions of the exercise
     */
    public int getRepetition() {
        return repetition;
    }

    /**
     * To get the calories burnt by the user doing this exercise.
     * @return the calories burnt corresponding to the exercise
     */
    public int getCaloriesBurnt() {
        return caloriesBurnt;
    }

    /**
     * To get if the exercise has been completed.
     * @return a boolean to show if the exercise has been completed
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * To get the number of sets of the exercise.
     * @return the number of sets of the exercise
     */
    public int getSet() {
        return 1;
    }

    /**
     * To get the distance of the exercise.
     * @return the distance of the exercise
     */
    public double getDistance() {
        return 1;
    }

    /**
     * To get the status of the exercise based on whether it is completed.
     * @return a string which marks if the exercise is completed.
     */
    public String getTaskStatus() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    public String getTime() {
        return String.format("%.1f", time);
    }

    /**
     * To set the maintenance calories of the user based on his/her biometrics.
     */
    public int getTaskStatusInNumber() {
        if (isDone) {
            return 1;
        }
        return 0;
    }

    /**
     * To set the maintenance calories of the user based on his/her biometrics.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * To set the calories burnt by doing the exercise.
     */
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
