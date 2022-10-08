package seedu.duke;

public class Exercise {
    private String exerciseName;
    private int repetitions;
    private int caloriesBurnt;

    public Exercise(String exerciseName, int repetitions ,int calories) {
        this.exerciseName = exerciseName;
        this.repetitions = repetitions;
        this.caloriesBurnt = caloriesBurnt;
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

    @Override
    public String toString() {
        return "Exercise: " + exerciseName + System.lineSeparator()
                + "Repetitions: " + repetitions +System.lineSeparator()
                + "Calories Burnt: " + caloriesBurnt;
    }
}
