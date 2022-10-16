package seedu.duke.exercise;

public class StrengthExercise extends Exercise {
    private int set;

    public StrengthExercise(String exerciseName, int set, int repetitions, int caloriesBurnt) {
        super(exerciseName, repetitions, caloriesBurnt);
        this.set = set;
    }

    @Override
    public int getSet() {
        return set;
    }

    @Override
    public String toString() {
        return "Exercise: " + getExerciseName() + System.lineSeparator()
                + "Sets: " + getSet() + System.lineSeparator()
                + "Repetitions: " + getRepetitions() + System.lineSeparator()
                + "Calories Burnt: " + getCaloriesBurnt() + System.lineSeparator()
                + String.format("Status: %s", getTaskStatus());
    }
}
