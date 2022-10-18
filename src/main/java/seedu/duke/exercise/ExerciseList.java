package seedu.duke.exercise;

import seedu.duke.exception.IllegalValueException;

import java.util.ArrayList;

public class ExerciseList {
    ArrayList<Exercise> currentExerciseList;
    ArrayList<Exercise> completedExerciseList;

    public ExerciseList() {
        this.currentExerciseList = new ArrayList<>();
        this.completedExerciseList = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        currentExerciseList.add(exercise);
    }


    public Exercise getCurrentExercise(int index) throws IllegalValueException {
        if (index >= getCurrentExerciseListSize() || index < 0) {
            throw new IllegalValueException("Exercise not found");
        }
        return currentExerciseList.get(index);
    }

    public Exercise getCompletedExercise(int index) throws IllegalValueException {
        if (index >= getCompletedExerciseListSize() || index < 0) {
            throw new IllegalValueException("Exercise not found");
        }
        return completedExerciseList.get(index);
    }

    public ArrayList<Exercise> getCurrentExerciseList() {
        return currentExerciseList;
    }

    public ArrayList<Exercise> getCompletedExerciseList() {
        return completedExerciseList;
    }

    public int getCurrentExerciseListSize() {
        return currentExerciseList.size();
    }

    public int getCompletedExerciseListSize() {
        return completedExerciseList.size();
    }

    public void markDone(int index) throws IllegalValueException {
        Exercise exercise = getCurrentExercise(index);
        exercise.setDone(true);
        currentExerciseList.remove(index);
        completedExerciseList.add(exercise);
    }

    public void markUndone(int index) throws IllegalValueException {
        Exercise exercise = getCompletedExercise(index);
        exercise.setDone(false);
        completedExerciseList.remove(index);
        currentExerciseList.add(exercise);
    }
}
