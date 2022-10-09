package seedu.duke;

public class Exercise {
    private String exerciseName;
    private int repetitions;
    private int caloriesBurnt;
    private boolean isDone;

    public Exercise(String exerciseName, int repetitions, int caloriesBurnt) {
        this.exerciseName = exerciseName;
        this.repetitions = repetitions;
        this.caloriesBurnt = caloriesBurnt;
        this.isDone = false;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public int getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getTaskStatus() {
        return String.format("%s", isDone ? "\u2713" : "X");
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "Exercise: " + exerciseName + System.lineSeparator()
                + "Repetitions: " + repetitions + System.lineSeparator()
                + "Calories Burnt: " + caloriesBurnt + System.lineSeparator()
                + String.format("Status: %s", isDone ? "\u2713" : "X");
    }
}
