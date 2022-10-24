package seedu.duke.command;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.exception.InvalidDateException;
import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;
import seedu.duke.storage.Storage;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DateCommand extends Command {


    public static void extractDate(String arguments) throws InvalidDateException {
    }


    public int getYear(LocalDate date) {
        return date.getYear();
    }

    public Month getMonth(LocalDate date) {
        return date.getMonth();
    }

    public int getDay(LocalDate date) {
        return date.getDayOfMonth();
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static void sortDate(ArrayList<Exercise> list) {
        Collections.sort(list, new Comparator<Exercise>() {
            /**
             * This method compares two date strings.
             *
             * @return sorted array list by date.
             */
            public int compare(Exercise e1, Exercise e2) {
                LocalDate o1Date = LocalDate.parse(Parser.getDateNoDateTracker(e1.getDate()),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalDate o2Date = LocalDate.parse(Parser.getDateNoDateTracker(e2.getDate()),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                return o1Date.compareTo(o2Date);
            }
        });
    }

    @Override
    public void execute() throws IllegalValueException {

    }

    @Override
    public void setData(Ui ui, Storage storage, Biometrics biometrics, ExerciseList exerciseList, FoodList foodList) {

    }
}
