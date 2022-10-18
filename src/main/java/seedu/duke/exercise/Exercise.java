package seedu.duke.exercise;

public class Exercise {
    private String exerciseName;
    private int repetition;
    private int caloriesBurnt;
    private boolean isDone;


    public Exercise(String exerciseName, int repetitions, int caloriesBurnt) {
        this.exerciseName = exerciseName;
        this.repetition = repetitions;
        this.caloriesBurnt = caloriesBurnt;
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
