package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ListCommand;
import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;

import seedu.duke.exception.AddTransactionInvalidAmountException;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.EntryNumberNotNumericException;
import seedu.duke.exception.InputDuplicateTagException;
import seedu.duke.exception.InputMissingTagException;
import seedu.duke.exception.InputTransactionInvalidCategoryException;
import seedu.duke.exception.InputTransactionInvalidDateException;
import seedu.duke.exception.InputTransactionUnknownTypeException;
import seedu.duke.exception.InputUnsupportedTagException;
import seedu.duke.exception.ListStatsInvalidStatsTypeException;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StatsInvalidMonthException;
import seedu.duke.exception.StatsInvalidYearException;
import seedu.duke.exception.UnknownHelpOptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.command.CommandTag.COMMAND_TAG_HELP_OPTION;
import static seedu.duke.command.CommandTag.COMMAND_TAG_LIST_ENTRY_NUMBER;
import static seedu.duke.command.CommandTag.COMMAND_TAG_STATISTICS_TYPE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_STATS_MONTH;
import static seedu.duke.command.CommandTag.COMMAND_TAG_STATS_YEAR;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_AMOUNT;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_CATEGORY;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DATE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DESCRIPTION;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_TYPE;
import static seedu.duke.common.DateFormats.DATE_INPUT_PATTERN;

/**
 * Parses the parameter portion of the user input and set the parameters into the Command object.
 *
 * <p>The ParameterParser will check that the parameter input portion contains only the supported tags,
 * for each of the supported tag, parses the parameter into the valid form required by the Command object
 * and to store the parsed value inside the command object.
 */
public class ParameterParser {
    //@@author chydarren
    private static final String EMPTY_STRING = "";
    private static final String DELIMITER = " ";
    private static final int SPLIT_POSITION = 2;
    private static final int MINIMUM_TAG_LENGTH = 2;
    private static final String CLASS_TYPE_EXPENSE = "seedu.duke.data.transaction.Expense";
    private static final String CLASS_TYPE_INCOME = "seedu.duke.data.transaction.Income";
    //@@author wcwy
    private static final Logger parserLogger = Logger.getLogger(ParameterParser.class.getName());

    //@@author chinhan99

    /**
     * Parses the parameters input into proper parameters of the command object.
     *
     * <p>The parameters will go through the following checks during the parsing:
     * 1. Check that the user input contains all mandatory tags of the command.
     * 2. Check that the user input does not contain tags not supported by the command.
     * 3. Check that the user input does not contain a same tag more than once.
     * 4. Check that the user input does not contain a tag without parameter.
     * 5. For each parameter, check that the format of the parameter is correct.
     *
     * @param command A command object created based on the command word given by user.
     * @throws MoolahException If Moolah Manager captures any command input exceptions.
     */
    public static void parse(Command command, String parametersInput) throws MoolahException {
        parserLogger.setLevel(Level.SEVERE);
        assert command != null;
        String[] splits = parametersInput.split(DELIMITER);

        // Might throw InputMissingTagException
        checkMandatoryTagsExist(command, splits);

        /*
        An empty parameterInput suggests that no tag is provided by user. Since it passes the check
        for mandatory check, it also means that the command does not have a mandatory tag. Therefore,
        there is no more need to further check and set the parameters for empty parameters input.
        */
        if (!parametersInput.isEmpty()) {
            // Might throw InputUnsupportedTagException
            checkUnsupportedTagsNotExist(command, splits);
            // Might throw InputDuplicateTagException
            checkDuplicateTagsNotExist(splits);
            // Might throw InputMissingParameterException
            checkParameterNotEmpty(splits);

            // The parameters input contains only the supported tags.
            // For each tag, check that the parameter is correct and set it inside the command.
            setCommand(command, splits);
        }
        parserLogger.log(Level.INFO, "Parameter parsed successfully: " + command + parametersInput);
    }

    //@@author wcwy

    /**
     * Checks if all the mandatory tags exists in the split user inputs.
     *
     * @param command A command object created based on the command word given by user.
     * @param splits  The user input after the command word, split into a list for every space found.
     * @throws InputMissingTagException If there is a missing mandatory tag.
     */
    public static void checkMandatoryTagsExist(Command command, String[] splits) throws InputMissingTagException {
        String[] tags = command.getMandatoryTags();
        for (String tag : tags) {
            boolean found = findMatchingTagAmongInputs(tag, splits);
            if (!found) {
                parserLogger.log(Level.WARNING, "A missing tag error is caught for the given tag: " + tag);
                throw new InputMissingTagException();
            }
        }
    }

    //@@author chinhan99

    /**
     * Checks if the split user inputs contains any unsupported tag.
     *
     * @param command A command object created based on the command word given by user.
     * @param splits  The user input after the command word, split into a list for every space found.
     * @throws InputUnsupportedTagException If there is an extra tag that is not recognised.
     */
    public static void checkUnsupportedTagsNotExist(Command command, String[] splits)
            throws InputUnsupportedTagException {
        String[] mandatoryTags = command.getMandatoryTags();
        String[] optionalTags = command.getOptionalTags();

        for (String split : splits) {
            if (split.length() < MINIMUM_TAG_LENGTH) {
                // None of the tags is shorter than two characters
                parserLogger.log(Level.WARNING, "An unsupported tag error is caught for the given tag: " + split);
                throw new InputUnsupportedTagException();
            }
            boolean hasFoundAmongMandatoryTag = findIfParameterTagAmongTags(split, mandatoryTags);
            boolean hasFoundAmongOptionalTag = findIfParameterTagAmongTags(split, optionalTags);
            if (hasFoundAmongMandatoryTag || hasFoundAmongOptionalTag) {
                continue;
            }

            // Found a tag entered by the user but does not exist in the supported tag for the command
            parserLogger.log(Level.WARNING, "An unsupported tag error is caught for the given tag: " + split);
            throw new InputUnsupportedTagException();
        }
    }

    //@@author wcwy

    /**
     * Checks if the split user inputs contains a tag multiple times.
     *
     * @param splits The user input after the command word, split into a list for every space found.
     * @throws InputDuplicateTagException If there is an extra of the same tag.
     */
    public static void checkDuplicateTagsNotExist(String[] splits) throws InputDuplicateTagException {
        HashMap<String, Integer> tagOccurenceMap = new HashMap<>();
        for (String split : splits) {
            assert split.length() >= MINIMUM_TAG_LENGTH;
            String tag = split.substring(0, SPLIT_POSITION);

            // The duplicated tag can be found in the hash map
            if (tagOccurenceMap.containsKey(tag)) {
                parserLogger.log(Level.WARNING, "An duplicate tag error is caught for the given tag: " + tag);
                throw new InputDuplicateTagException();
            }
            tagOccurenceMap.put(tag, 1);
        }
    }

    //@@author chinhan99

    /**
     * Checks if there are missing parameter within the user input.
     * If the split.length() is <= 2, it means that only the tag exists , and there is no parameter after the tag.
     *
     * @param splits The user input after the command word, split into a list for every space found.
     * @throws EmptyParameterException If there exists a tag without parameter.
     */
    public static void checkParameterNotEmpty(String[] splits) throws EmptyParameterException {
        for (String split : splits) {
            if (split.length() == 2) {
                parserLogger.log(Level.WARNING, "An empty parameter error is caught for the "
                        + "given tag input: " + split);
                throw new EmptyParameterException();
            }
        }
    }

    //@@author wcwy

    /**
     * Returns a boolean value on whether a tag can be found among the split user inputs.
     *
     * @param tag    A specific tag used to locate the command parameter.
     * @param splits The user input after the command word, split into a list for every space found.
     * @return Whether the tag is found within the split inputs.
     */
    private static boolean findMatchingTagAmongInputs(String tag, String[] splits) {
        boolean hasFound = false;
        for (String split : splits) {
            hasFound = split.startsWith(tag);
            if (hasFound) {
                break;
            }
        }
        return hasFound;
    }

    //@@author chinhan99

    /**
     * Returns a boolean value on whether a tag can be found among the split user inputs.
     *
     * @param parameter A user parameter input entered after the command word.
     * @param tags      An array of tags.
     * @return Whether the tag is found within the split inputs.
     */
    private static boolean findIfParameterTagAmongTags(String parameter, String[] tags) {
        assert parameter.length() >= MINIMUM_TAG_LENGTH;
        boolean hasFound = false;

        String parameterTag = parameter.substring(0, SPLIT_POSITION);
        for (String tag : tags) {
            hasFound = tag.equals(parameterTag);
            if (hasFound) {
                break;
            }
        }
        return hasFound;
    }

    //@@author paullowse

    /**
     * For each split parameters, split it into tag and parameter, then check and set the parameters into the Command.
     *
     * @param command A command object created based on the command word given by user.
     * @param splits  The user input after the command word, split into a list for every space found.
     * @throws MoolahException If Moolah Manager captures any command input exceptions.
     */
    public static void setCommand(Command command, String[] splits) throws MoolahException {
        assert command != null;
        for (String split : splits) {
            String tag = split.substring(0, SPLIT_POSITION);
            String parameter = split.substring(SPLIT_POSITION);
            setParameter(command, tag, parameter);
        }
    }

    //@@author wcwy

    /**
     * Sets the parsed parameter for each valid tag of the command.
     *
     * <p>The parameters set will be parsed into a correct format/data type before storing into a parameter
     * within the Command object given. This ensures that the execution of the command will not encounter
     * any unformatted input or unsupported data type issue, as far as possible.
     *
     * @param command   A command object created based on the command word given by user.
     * @param tag       The tag used before the parameter to indicate the type of the parameter provided.
     * @param parameter The value of a parameter given by the user for the chosen command.
     * @throws MoolahException If an error is found when the parameter is parsed.
     */
    private static void setParameter(Command command, String tag, String parameter) throws MoolahException {
        switch (tag) {
        case COMMAND_TAG_TRANSACTION_TYPE:
            // TODO: To standardise the format for transaction type for add and list
            if (command instanceof ListCommand) {
                command.setType(parseTypeTagForListing(parameter));
            } else {
                command.setType(parseTypeTagForAdding(parameter));
            }
            break;
        case COMMAND_TAG_TRANSACTION_CATEGORY:
            command.setCategory(parseCategoryTag(parameter));
            break;
        case COMMAND_TAG_TRANSACTION_AMOUNT:
            command.setAmount(parseAmountTag(parameter));
            break;
        case COMMAND_TAG_TRANSACTION_DATE:
            command.setDate(parseDateTag(parameter));
            break;
        case COMMAND_TAG_TRANSACTION_DESCRIPTION:
            command.setDescription(parameter);
            break;
        case COMMAND_TAG_LIST_ENTRY_NUMBER:
            command.setEntryNumber(parseEntryTag(parameter));
            break;
        case COMMAND_TAG_HELP_OPTION:
            command.setIsDetailedOption(parseHelpOptionTag(parameter));
            break;
        case COMMAND_TAG_STATISTICS_TYPE:
            command.setStatsType(parseStatsTypeTag(parameter));
            break;
        case COMMAND_TAG_STATS_MONTH:
            command.setStatsMonth(parseStatsMonthTag(parameter));
            break;
        case COMMAND_TAG_STATS_YEAR:
            command.setStatsYear(parseStatsYearTag(parameter));
            break;
        default:
            parserLogger.log(Level.WARNING, "An unsupported tag exception is caught: " + tag);
            throw new InputUnsupportedTagException();
        }
    }

    //@@author chydarren

    /**
     * Parses the user parameter input for the description and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The class type if no exceptions are thrown.
     * @throws InputTransactionUnknownTypeException If the transaction type provided is not supported.
     */
    public static String parseTypeTagForListing(String parameter) throws InputTransactionUnknownTypeException {
        switch (parameter) {
        case "expense":
            return CLASS_TYPE_EXPENSE;
        case "income":
            return CLASS_TYPE_INCOME;
        default:
            parserLogger.log(Level.WARNING, "An invalid type error "
                    + "is caught for the given parameter: " + parameter);
            throw new InputTransactionUnknownTypeException();
        }
    }

    //@@author wcwy

    /**
     * Checks if the type parameter is a valid transaction type and returns the parameter if it is valid.
     *
     * @param parameter The user input after the user tag.
     * @return The user input after the user tag.
     * @throws InputTransactionUnknownTypeException If the transaction type provided is not supported.
     */
    public static String parseTypeTagForAdding(String parameter) throws InputTransactionUnknownTypeException {
        boolean isExpense = parameter.equals(Expense.TRANSACTION_NAME);
        boolean isIncome = parameter.equals(Income.TRANSACTION_NAME);

        if (!isExpense && !isIncome) {
            parserLogger.log(Level.WARNING, "An invalid type error "
                    + "is caught for the given parameter: " + parameter);
            throw new InputTransactionUnknownTypeException();
        }

        return parameter;
    }

    //@@author chinhan99

    /**
     * Parses the user parameter input for the description and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The category parameter if no exceptions are thrown.
     * @throws InputTransactionInvalidCategoryException If the category provided contains numeric or symbols.
     */
    public static String parseCategoryTag(String parameter) throws InputTransactionInvalidCategoryException {
        Pattern specialSymbols = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecialSymbols = specialSymbols.matcher(parameter);
        if (containNumeric(parameter) || hasSpecialSymbols.find()) {
            parserLogger.log(Level.WARNING, "An invalid category error is caught for the given parameter: "
                    + parameter);
            throw new InputTransactionInvalidCategoryException();
        }
        return parameter;
    }

    /**
     * Parses the user parameter input for the amount and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The amount integer if no exceptions are thrown.
     * @throws AddTransactionInvalidAmountException If the transaction amount provided is not a valid accepted integer.
     */
    private static int parseAmountTag(String parameter) throws AddTransactionInvalidAmountException {
        Pattern specialSymbols = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecialSymbols = specialSymbols.matcher(parameter);
        try {
            if (containAlphabet(parameter) || hasSpecialSymbols.find()) {
                parserLogger.log(Level.WARNING, "An invalid amount error is caught for the given parameter: "
                        + parameter);
                throw new AddTransactionInvalidAmountException();
            }
            int amount = Integer.parseInt(parameter);
            if (amount < 0 || amount > 10000000) {
                parserLogger.log(Level.WARNING, "An invalid amount error is caught for the given parameter: "
                        + parameter);
                throw new AddTransactionInvalidAmountException();
            }
            return amount;

        } catch (NumberFormatException e) {
            parserLogger.log(Level.WARNING, "An invalid amount error "
                    + "is caught for the given parameter: " + parameter);
            throw new AddTransactionInvalidAmountException();
        }
    }

    //@@author wcwy

    /**
     * Parses the user parameter input for date into a LocalDate object and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The LocalDate object parsed from user input given.
     * @throws InputTransactionInvalidDateException If the format of the transaction date provided is incorrect.
     */
    public static LocalDate parseDateTag(String parameter) throws InputTransactionInvalidDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_INPUT_PATTERN.toString());
            LocalDate date = LocalDate.parse(parameter, formatter);
            return date;
        } catch (DateTimeParseException exception) {
            parserLogger.log(Level.WARNING, "An invalid date error "
                    + "is caught for the given parameter: " + parameter);
            throw new InputTransactionInvalidDateException();
        }
    }

    //@@author brian-vb

    /**
     * Parses the user parameter input for entry number into an integer and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The valid integer for list index parsed from user input given.
     * @throws MoolahException If the entry number provided is not parsable into integer.
     */
    public static int parseEntryTag(String parameter) throws MoolahException {
        int index;
        try {
            index = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            parserLogger.log(Level.WARNING, "An invalid entry number error is caught for the given parameter: "
                    + parameter);
            throw new EntryNumberNotNumericException();
        }

        return index;
    }

    //@@author wcwy

    /**
     * Return a boolean value indicating if the option selected by user is "detailed".
     *
     * @param parameter The user input after the user tag.
     * @return A boolean value indicating if the option selected by user is "detailed"
     * @throws UnknownHelpOptionException If the help option parameter selected is not 'detailed'.
     */
    public static boolean parseHelpOptionTag(String parameter) throws UnknownHelpOptionException {
        boolean isValidHelpOption = parameter.equals("detailed");
        if (isValidHelpOption) {
            return true;
        } else {
            parserLogger.log(Level.WARNING, "An invalid help option error is caught for the given parameter: "
                    + parameter);
            throw new UnknownHelpOptionException();
        }
    }

    //@@author chydarren

    /**
     * Check if the type parameter is a valid statistic type and returns the parameter if it is valid.
     *
     * @param parameter The user input after the user tag.
     * @return The statistic type.
     * @throws ListStatsInvalidStatsTypeException If the statistic type given is not supported.
     */
    public static String parseStatsTypeTag(String parameter) throws ListStatsInvalidStatsTypeException {
        String statsType;
        switch (parameter) {
        case "categories":
            statsType = "categories";
            break;
        case "time":
            statsType = "time";
            break;
        default:
            parserLogger.log(Level.WARNING, "An invalid statistic type error is caught for the given parameter: "
                    + parameter);
            throw new ListStatsInvalidStatsTypeException();
        }
        return statsType;
    }

    //@@author paullowse
    public static int parseStatsMonthTag(String parameter) throws StatsInvalidMonthException,
            EntryNumberNotNumericException {
        int month;
        try {
            month = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            parserLogger.log(Level.WARNING, "An invalid entry number error is caught for the given parameter: "
                    + parameter);
            throw new EntryNumberNotNumericException();
        }

        if (month > 12 || month < 0) {
            parserLogger.log(Level.WARNING, "An invalid month number error is caught for the given parameter: "
                    + parameter);
            throw new StatsInvalidMonthException();
        }
        return month;
    }

    //@@author paullowse
    public static int parseStatsYearTag(String parameter) throws StatsInvalidYearException,
            EntryNumberNotNumericException {
        int year;
        try {
            year = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            parserLogger.log(Level.WARNING, "An invalid entry number error is caught for the given parameter: "
                    + parameter);
            throw new EntryNumberNotNumericException();
        }
        if (year < 0) {
            parserLogger.log(Level.WARNING, "An invalid year number error is caught for the given parameter: "
                    + parameter);
            throw new StatsInvalidYearException();
        }
        return year;
    }

    //@@author chinhan99

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
     * Checks if the parameter contains alphabetical characters.
     *
     * @param parameter The user input after the user tag.
     * @return true if there are alphabetical characters within the parameter.
     */
    public static boolean containAlphabet(String parameter) {
        char[] characters = parameter.toCharArray();
        for (char character : characters) {
            if (Character.isAlphabetic(character)) {
                return true;
            }
        }
        return false;
    }
}
