package seedu.duke.records.exercise;


import seedu.duke.logic.exception.IllegalValueException;

import java.util.ArrayList;

import static seedu.duke.logic.command.DateCommand.sortDateForExercise;

public class ExerciseList {
    private ArrayList<Exercise> currentExerciseList;
    private ArrayList<Exercise> completedExerciseList;

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
        sortDateForExercise(currentExerciseList);
        return currentExerciseList;
    }

    public ArrayList<Exercise> getCompletedExerciseList() {
        sortDateForExercise(completedExerciseList);
        return completedExerciseList;
    }

    public int getCurrentExerciseListSize() {
        return currentExerciseList.size();
    }

    public int getCompletedExerciseListSize() {
        return completedExerciseList.size();
    }

    public void markDone(int index, double time, int calories) throws IllegalValueException {
        Exercise exercise = getCurrentExercise(index);
        exercise.setTime(time);
        exercise.setCaloriesBurnt(calories);
        exercise.setDone(true);
        currentExerciseList.remove(index);
        completedExerciseList.add(exercise);
    }

    public void markUndone(int index) throws IllegalValueException {
        Exercise exercise = getCompletedExercise(index);
        exercise.setTime(0);
        exercise.setCaloriesBurnt(0);
        exercise.setDone(false);
        completedExerciseList.remove(index);
        currentExerciseList.add(exercise);
    }

    public void removeCurrentExercise(int index) {
        currentExerciseList.remove(index);
    }
}
