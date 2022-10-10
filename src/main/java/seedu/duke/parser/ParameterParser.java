package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.common.DateFormats.DATE_INPUT_PATTERN;

/**
 * Provides a set of extra utilities for processing of different inputs in various commands.
 */
public class ParameterParser {
    private static final String CLASS_TYPE_EXPENSE = "seedu.duke.data.transaction.Expense";
    private static final String CLASS_TYPE_INCOME = "seedu.duke.data.transaction.Income";

    /**
     * To parse the parameters input into proper parameters of the command object
     *
     * @param command A command object created based on the command word given by user.
     * @throws MoolahException Any command input exceptions captured by Moolah Manager.
     */
    public static void parse(Command command, String parametersInput) throws MoolahException {
        assert command != null;


    }


    /**
     * Checks if there are extra tag(s) within the user input.
     *
     * @param splits The user input after the command word, split into a list for every space found.
     * @throws InputTransactionExtraTagException Extra tag exception.
     */
    public static void checkExtraTagExist(String[] splits, int tagLimit) throws InputTransactionExtraTagException {
        int countNumberOfTags = 0;
        for (String split : splits) {
            countNumberOfTags += 1;
        }
        if (countNumberOfTags > tagLimit) {
            throw new InputTransactionExtraTagException();
        }
    }

    /**
     * Checks if the parameter contains numeric characters.
     *
     * @param parameter The user input after the user tag.
     * @return A boolean value indicating whether there are numeric characters within the parameter.
     */
    public static boolean containNumeric(String parameter) {
        char[] characters = parameter.toCharArray();
        for (char character : characters) {
            if (Character.isDigit(character)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses the user parameter input for the description and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The class type if no exceptions are thrown.
     * @throws InputTransactionUnknownTypeException Invalid type format exception.
     */
    public static String parseTypeTag(String parameter) throws InputTransactionUnknownTypeException {
        switch (parameter) {
        case "expense":
            return CLASS_TYPE_EXPENSE;
        case "income":
            return CLASS_TYPE_INCOME;
        default:
            throw new InputTransactionUnknownTypeException();
        }
    }

    /**
     * Parses the user parameter input for the description and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The category parameter if no exceptions are thrown.
     * @throws InputTransactionInvalidCategoryException Invalid category format exception.
     */
    public static String parseCategoryTag(String parameter) throws InputTransactionInvalidCategoryException {
        Pattern specialSymbols = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecialSymbols = specialSymbols.matcher(parameter);
        if (containNumeric(parameter) || hasSpecialSymbols.find()) {
            throw new InputTransactionInvalidCategoryException();
        }
        return parameter;
    }

    /**
     * Parses the user parameter input for date into a LocalDate object and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The LocalDate object parsed from user input given.
     * @throws InputTransactionInvalidDateException Invalid date format exception.
     */
    public static LocalDate parseDateTag(String parameter) throws InputTransactionInvalidDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_INPUT_PATTERN.toString());
            LocalDate date = LocalDate.parse(parameter, formatter);
            return date;
        } catch (DateTimeParseException exception) {
            throw new InputTransactionInvalidDateException();
        }
    }
}
