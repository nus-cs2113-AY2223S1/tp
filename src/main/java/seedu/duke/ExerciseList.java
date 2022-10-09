package seedu.duke;

import java.util.ArrayList;

public class ExerciseList {
    ArrayList<Exercise> exerciseList;
    ArrayList<Exercise> completedExerciseList;

    public ExerciseList() {
        this.exerciseList = new ArrayList<>();
        this.completedExerciseList = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }

    public Exercise getExercise(int index) {
        return exerciseList.get(index);
    }

    public Exercise getCompletedExercise(int index) {
        return completedExerciseList.get(index);
    }

    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    public ArrayList<Exercise> getCompletedExerciseList() {
        return completedExerciseList;
    }

    public int getExerciseListSize() {
        return exerciseList.size();
    }

    public int getCompletedExerciseListSize() {
        return completedExerciseList.size();
    }

    public void markDone(int index) {
        Exercise exercise = exerciseList.get(index);
        exerciseList.remove(index);
        completedExerciseList.add(exercise);
    }

    public void markUndone(int index) {
        Exercise exercise = completedExerciseList.get(index);
        completedExerciseList.remove(index);
        exerciseList.add(exercise);
    }
}
