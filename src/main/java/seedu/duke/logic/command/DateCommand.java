package seedu.duke.logic.command;

import seedu.duke.logic.exception.IllegalValueException;
import seedu.duke.logic.Parser;
import seedu.duke.records.Calories;
import seedu.duke.records.Record;
import seedu.duke.records.RecordList;
import seedu.duke.records.biometrics.Biometrics;
import seedu.duke.records.exercise.Exercise;
import seedu.duke.records.exercise.ExerciseList;
import seedu.duke.records.food.Food;
import seedu.duke.records.food.FoodList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DateCommand extends Command {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    /**
     * Sort Exercises according to date.
     *
     * @param list a list of Exercises
     *
     */
    public static void sortDateForExercise(ArrayList<Exercise> list) {
        Collections.sort(list, new Comparator<Exercise>() {
            /**
             * This method compares two date strings.
             *
             * @return sorted array list by date.
             */
            public int compare(Exercise e1, Exercise e2) {
                LocalDate o1Date = LocalDate.parse(Parser.getDateNoDateTracker(e1.getDateString()),
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                LocalDate o2Date = LocalDate.parse(Parser.getDateNoDateTracker(e2.getDateString()),
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                return o2Date.compareTo(o1Date);
            }
        });
    }

    /**
     * Sort Calories according to date.
     *
     * @param list a list of Calories
     *
     */
    public static void sortDateForCalories(ArrayList<Calories> list) {
        Collections.sort(list, new Comparator<Calories>() {
            /**
             * This method compares two date strings.
             *
             * @return sorted array list by date.
             */
            public int compare(Calories c1, Calories c2) {
                LocalDate o1Date = LocalDate.parse(Parser.getDateNoDateTracker(c1.getDate()),
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                LocalDate o2Date = LocalDate.parse(Parser.getDateNoDateTracker(c2.getDate()),
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                return o2Date.compareTo(o1Date);
            }
        });
    }

    /**
     * Sorts the food array list in descending order of date.
     *
     * @param foodArrayList the arraylist that stores all the food records.
     *
     */
    public static void sortDateForFood(ArrayList<Food> foodArrayList) {
        Collections.sort(foodArrayList, new Comparator<Food>() {
            @Override
            public int compare(Food f1, Food f2) {
                LocalDate f1Date = LocalDate.parse(Parser.getDateNoDateTracker(f1.getDateString()),
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                LocalDate f2Date = LocalDate.parse(Parser.getDateNoDateTracker(f2.getDateString()),
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                return f2Date.compareTo(f1Date);
            }
        });
    }

    /**
     * Sorts the record array list in descending order of date.
     *
     * @param recordArrayList the arraylist that stores all the records(Weight and Fat, Food, and Exercises).
     *
     */
    public static void sortDateForAll(ArrayList<Record> recordArrayList) {
        Collections.sort(recordArrayList, new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                LocalDate r1Date = LocalDate.parse(Parser.getDateNoDateTracker(r1.getDateString()),
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                LocalDate r2Date = LocalDate.parse(Parser.getDateNoDateTracker(r2.getDateString()),
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                return r2Date.compareTo(r1Date);
            }
        });
    }


    @Override
    public void execute() throws IllegalValueException {

    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList,
                        RecordList recordList) {

    }
}
