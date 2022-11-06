package seedu.duke.logic;

import seedu.duke.exception.IllegalValueException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class Validator {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final char ARGUMENT_SEPARATOR = '/';
    public static final String INVALID_VALUE_FOR_REPETITIONS = "Invalid value for repetitions";
    public static final int MAXIMUM_SETS = 100;
    public static final int MAXIMUM_REPS = 100;
    public static final int MAXIMUM_WEIGHT = 1000;
    public static final String INVALID_NUMBER_FOR_WEIGHT = "Invalid number for weight";
    public static final String INVALID_VALUE_FOR_SETS = "Invalid value for sets";
    public static final int MAXIMUM_DESCRIPTION_LENGTH = 50;
    public static final String INVALID_DESCRIPTION = "Description of the exercise must be within 50 characters";

    public static final int MAXIMUM_TIME = 1440;
    public static final int ZERO = 0;
    public static final String INVALID_TIME_MESSAGE = "Invalid value for time";
    public static final String INVALID_MET_MESSAGE = "Invalid met value";
    public static final String INVALID_DATE = "Date is in the wrong format. Please follow the dd-MM-yyyy format";

    public static final int MAXIMUM_MET = 50;
    public static final String INVALID_DOUBLE = "Double must be 1 decimal place";
    public static final String DOUBLE_SEPARATOR = "\\.";
    public static final int DOUBLE_ARRAY_LENGTH = 2;
    public static final int ONE_DECIMAL_PLACE_LENGTH = 1;
    public static final String OUT_OF_BOUND = "Index out of bound";
    public static final int MAXIMUM_DISTANCE = 100;
    public static final int MINIMUM_DISTANCE = 0;
    public static final String INVALID_VALUE_FOR_DISTANCE = "Invalid value for distance";


    public static void validateCommandInput(int slashesCount, int minimum,
                                            int maximum, String message, char lastCharacter)
            throws IllegalValueException {
        if (slashesCount < minimum || slashesCount > maximum || lastCharacter == ARGUMENT_SEPARATOR) {
            LOGGER.warning(message);
            throw new IllegalValueException(message);
        }
    }

    public static void validateCommandInput(int slashesCount, int requiredCount,
                                            String message, char lastCharacter)
            throws IllegalValueException {
        if (slashesCount != requiredCount || lastCharacter == ARGUMENT_SEPARATOR) {
            LOGGER.warning(message);
            throw new IllegalValueException(message);
        }
    }

    public static void validateLoadingForExercise(int requiredLength, String message,
                                                  boolean toDisplay, int actualLength)
            throws IllegalValueException {
        if (!toDisplay && actualLength != requiredLength) {
            LOGGER.warning(message);
            throw new IllegalValueException(message);
        }
    }

    public static int getSetWithValidation(String setString) throws IllegalValueException {
        int set = Integer.parseInt(setString);
        validateData(set, MAXIMUM_SETS, INVALID_VALUE_FOR_SETS);
        return set;
    }

    public static int getWeightWithValidation(String weightString) throws IllegalValueException {
        int weight = Integer.parseInt(weightString);
        if (weight < 0 || weight > MAXIMUM_WEIGHT) {
            throw new IllegalValueException(INVALID_NUMBER_FOR_WEIGHT);
        }
        return weight;
    }

    public static String getDescriptionWithValidation(String description) throws IllegalValueException {
        if (description.length() > MAXIMUM_DESCRIPTION_LENGTH) {
            throw new IllegalValueException(INVALID_DESCRIPTION);
        }
        return description;
    }

    public static int getRepetitionWithValidation(String reps) throws IllegalValueException {
        int repetition = Integer.parseInt(reps);
        validateData(repetition, MAXIMUM_REPS, INVALID_VALUE_FOR_REPETITIONS);
        return repetition;
    }

    public static double getDistanceWithValidation(String dist) throws IllegalValueException {
        double distance = Math.round(Double.parseDouble(dist) * 1000.0) / 1000.0;
        validateDouble(distance, MAXIMUM_DISTANCE, MINIMUM_DISTANCE, INVALID_VALUE_FOR_DISTANCE);
        return distance;
    }

    public static int getIndexWithValidation(String indexString, int arraySize) throws IllegalValueException {
        int index = Integer.parseInt(indexString);
        validateData(index, arraySize, OUT_OF_BOUND);
        return index - 1;
    }

    public static LocalDate getDateWithValidation(String date) throws IllegalValueException {
        validateDate(date, INVALID_DATE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localdate = LocalDate.parse(date, formatter);
        return localdate;
    }

    private static void validateData(int data, int maximumValue, String message) throws IllegalValueException {
        if (data <= 0 || data > maximumValue) {
            throw new IllegalValueException(message);
        }
    }

    private static void validateDate(String date, String message) throws IllegalValueException {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeException e) {
            throw new IllegalValueException(message);
        }
    }

    public static double getMetabolicEquivalentWithValidation(String metabolicEquivalentString)
            throws IllegalValueException {
        validateDecimalPlace(metabolicEquivalentString);
        double metabolicEquivalent = Double.parseDouble(metabolicEquivalentString);
        validateDouble(metabolicEquivalent, MAXIMUM_MET, ZERO, INVALID_MET_MESSAGE);
        return metabolicEquivalent;
    }


    private static void validateDecimalPlace(String doubleString) throws IllegalValueException {
        String[] doubleArray = doubleString.split(DOUBLE_SEPARATOR);
        if (doubleArray.length == DOUBLE_ARRAY_LENGTH && doubleArray[1].length() > ONE_DECIMAL_PLACE_LENGTH) {
            throw new IllegalValueException(INVALID_DOUBLE);
        }
    }

    public static double getTimeWithValidation(String timeString) throws IllegalValueException {
        validateDecimalPlace(timeString);
        double time = Double.parseDouble(timeString);
        validateDouble(time, MAXIMUM_TIME, ZERO, INVALID_TIME_MESSAGE);
        return time;
    }

    private static void validateDouble(double value, int maximumAcceptableValue, int maximumRejectedValue,
                                       String rejectMessage) throws IllegalValueException {
        if (value <= maximumRejectedValue || value > maximumAcceptableValue) {
            throw new IllegalValueException(rejectMessage);
        }
    }
}
