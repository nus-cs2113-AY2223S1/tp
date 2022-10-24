package seedu.duke.exercise;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.exception.IllegalValueException;

public class ExerciseCaloriesCalculator {
    public static int calculateCalories(Biometrics biometrics, double time, double metabolicEquivalent) throws IllegalValueException {
        if (biometrics.getWeight() == 0) {
            throw new IllegalValueException("Weight cannot be 0. Pls update biometrics");
        }
        return (int) (0.175 * biometrics.getWeight() * metabolicEquivalent * time);
    }
}
