package seedu.duke.exercise;

public class CardioExercise extends Exercise {
    private int time;

    public CardioExercise(String exerciseName, int time, int repetitions, int caloriesBurnt, String date) {
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