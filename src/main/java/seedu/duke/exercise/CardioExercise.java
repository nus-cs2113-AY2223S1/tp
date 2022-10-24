package seedu.duke.exercise;

public class CardioExercise extends Exercise {
    private double distance;

    public CardioExercise(String exerciseName, double distance, int repetitions, String date) {
        super(exerciseName, repetitions, date);
        this.distance = distance;
    }

    public CardioExercise(String exerciseName, double distance, String date) {
        this(exerciseName, distance, 1, date);
    }


    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public String saveExercise() {
        return "cardio /" + getExerciseName() + " /" + getDistance()
                + " /" + getRepetition() + " /" + getDate() + " /"
                + getCaloriesBurnt() + " /" + getTime() + " | " + getTaskStatusInNumber();
    }

    @Override
    public String toString() {
        return "Cardio Exercise: " + getExerciseName() + System.lineSeparator()
                + "Distance: " + getDistance() + "km" + System.lineSeparator()
                + "Repetitions: " + getRepetition() + System.lineSeparator()
                + "Date: " + getDate() + System.lineSeparator()
                + String.format("Status: %s", getTaskStatus());
    }
}