package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ListCommand;

import seedu.duke.data.Budget;
import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;

import seedu.duke.exception.GlobalDuplicateTagException;
import seedu.duke.exception.GlobalEmptyParameterException;
import seedu.duke.exception.GlobalInvalidIndexException;
import seedu.duke.exception.GlobalInvalidMonthException;
import seedu.duke.exception.GlobalInvalidPeriodException;
import seedu.duke.exception.GlobalInvalidYearException;
import seedu.duke.exception.GlobalMissingTagException;
import seedu.duke.exception.GlobalIndexNotNumericException;
import seedu.duke.exception.GlobalNumberNotNumericException;
import seedu.duke.exception.GlobalUnsupportedTagException;
import seedu.duke.exception.HelpUnknownOptionException;
import seedu.duke.exception.InputBudgetDuplicateException;
import seedu.duke.exception.InputBudgetInvalidAmountException;
import seedu.duke.exception.InputTransactionInvalidAmountException;
import seedu.duke.exception.InputTransactionInvalidCategoryException;
import seedu.duke.exception.InputTransactionInvalidDateException;
import seedu.duke.exception.InputTransactionInvalidTypeException;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StatsInvalidNumberException;
import seedu.duke.exception.StatsInvalidTypeException;
import seedu.duke.exception.HelpUnknownCommandWordException;
import seedu.duke.exception.GlobalInvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.command.CommandTag.COMMAND_TAG_BUDGET_AMOUNT;
import static seedu.duke.command.CommandTag.COMMAND_TAG_HELP_OPTION;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_ENTRY_NUMBER;
import static seedu.duke.command.CommandTag.COMMAND_TAG_STATS_TYPE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_MONTH;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_NUMBER;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_PERIOD;
import static seedu.duke.command.CommandTag.COMMAND_TAG_GLOBAL_YEAR;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_AMOUNT;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_CATEGORY;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DATE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_DESCRIPTION;
import static seedu.duke.command.CommandTag.COMMAND_TAG_TRANSACTION_TYPE;
import static seedu.duke.command.CommandTag.COMMAND_TAG_FIND_KEYWORD;
import static seedu.duke.command.CommandTag.COMMAND_TAG_HELP_QUERY;
import static seedu.duke.common.Constants.MAX_BUDGET_VALUE;
import static seedu.duke.common.Constants.MIN_BUDGET_VALUE;
import static seedu.duke.common.Constants.MAX_AMOUNT_VALUE;
import static seedu.duke.common.Constants.MIN_AMOUNT_VALUE;
import static seedu.duke.common.Constants.MINIMUM_YEAR;
import static seedu.duke.common.Constants.MAXIMUM_YEAR;
import static seedu.duke.common.Constants.MAXIMUM_STATS_NUMBER;
import static seedu.duke.common.Constants.MINIMUM_STATS_NUMBER;
import static seedu.duke.common.Constants.MIN_PARAMETER_LENGTH;
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
    private static final String DELIMITER = " ";
    private static final int SPLIT_POSITION = 2;
    private static final int MINIMUM_TAG_LENGTH = 2;
    private static final int JANUARY = 1;
    private static final int DECEMBER = 12;
    private static final int MAX_NUM_OF_MINUS_SIGNS = 1;
    private static final String CLASS_TYPE_EXPENSE = "seedu.duke.data.transaction.Expense";
    private static final String CLASS_TYPE_INCOME = "seedu.duke.data.transaction.Income";
    private static final String CATEGORICAL_SAVINGS = "categorical_savings";
    private static final String MONTHLY_EXPENDITURE = "monthly_expenditure";
    private static final String TIME_INSIGHTS = "time_insights";
    private static final String DAYS = "days";
    private static final String WEEKS = "weeks";
    private static final String MONTHS = "months";


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
            // Might throw InputEmptyParameterException
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
     * @throws GlobalMissingTagException If there is a missing mandatory tag.
     */
    public static void checkMandatoryTagsExist(Command command, String[] splits) throws GlobalMissingTagException {
        parserLogger.setLevel(Level.SEVERE);
        String[] tags = command.getMandatoryTags();
        for (String tag : tags) {
            boolean found = findMatchingTagAmongInputs(tag, splits);
            if (!found) {
                parserLogger.log(Level.WARNING, "A missing tag error is caught for the given tag: " + tag);
                throw new GlobalMissingTagException();
            }
        }
    }

    //@@author chinhan99

    /**
     * Checks if the split user inputs contains any unsupported tag.
     *
     * @param command A command object created based on the command word given by user.
     * @param splits  The user input after the command word, split into a list for every space found.
     * @throws GlobalUnsupportedTagException If there is an extra tag that is not recognised.
     */
    public static void checkUnsupportedTagsNotExist(Command command, String[] splits)
            throws GlobalUnsupportedTagException {
        parserLogger.setLevel(Level.SEVERE);
        String[] mandatoryTags = command.getMandatoryTags();
        String[] optionalTags = command.getOptionalTags();

        for (String split : splits) {
            if (split.length() < MINIMUM_TAG_LENGTH) {
                // None of the tags is shorter than two characters
                parserLogger.log(Level.WARNING, "An unsupported tag error is caught for the given tag: " + split);
                throw new GlobalUnsupportedTagException();
            }
            boolean hasFoundAmongMandatoryTag = findIfParameterTagAmongTags(split, mandatoryTags);
            boolean hasFoundAmongOptionalTag = findIfParameterTagAmongTags(split, optionalTags);
            if (hasFoundAmongMandatoryTag || hasFoundAmongOptionalTag) {
                continue;
            }

            // Found a tag entered by the user but does not exist in the supported tag for the command
            parserLogger.log(Level.WARNING, "An unsupported tag error is caught for the given tag: " + split);
            throw new GlobalUnsupportedTagException();
        }
    }

    //@@author wcwy

    /**
     * Checks if the split user inputs contains a tag multiple times.
     *
     * @param splits The user input after the command word, split into a list for every space found.
     * @throws GlobalDuplicateTagException If there is an extra of the same tag.
     */
    public static void checkDuplicateTagsNotExist(String[] splits) throws GlobalDuplicateTagException {
        parserLogger.setLevel(Level.SEVERE);
        HashMap<String, Integer> tagOccurenceMap = new HashMap<>();
        for (String split : splits) {
            assert split.length() >= MINIMUM_TAG_LENGTH : "The function argument splits has more length than expected";
            String tag = split.substring(0, SPLIT_POSITION);

            // The duplicated tag can be found in the hash map
            if (tagOccurenceMap.containsKey(tag)) {
                parserLogger.log(Level.WARNING, "An duplicate tag error is caught for the given tag: " + tag);
                throw new GlobalDuplicateTagException();
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
     * @throws GlobalEmptyParameterException If there exists a tag without parameter.
     */
    public static void checkParameterNotEmpty(String[] splits) throws GlobalEmptyParameterException {
        parserLogger.setLevel(Level.SEVERE);
        for (String split : splits) {
            if (split.length() == 2) {
                parserLogger.log(Level.WARNING, "An empty parameter error is caught for the "
                        + "given tag input: " + split);
                throw new GlobalEmptyParameterException();
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
        parserLogger.setLevel(Level.SEVERE);
        switch (tag) {
        case COMMAND_TAG_TRANSACTION_TYPE:
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
        case COMMAND_TAG_GLOBAL_ENTRY_NUMBER:
            command.setEntryNumber(parseEntryTag(parameter));
            break;
        case COMMAND_TAG_FIND_KEYWORD:
            command.setKeyword(parameter);
            break;
        case COMMAND_TAG_HELP_OPTION:
            command.isDetailed(parseHelpOptionTag(parameter));
            break;
        case COMMAND_TAG_STATS_TYPE:
            command.setStatsType(parseStatsTypeTag(parameter));
            break;
        case COMMAND_TAG_GLOBAL_MONTH:
            command.setGlobalMonth(parseMonthTag(parameter));
            break;
        case COMMAND_TAG_GLOBAL_YEAR:
            command.setGlobalYear(parseYearTag(parameter));
            break;
        case COMMAND_TAG_GLOBAL_NUMBER:
            command.setGlobalNumber(parseNumberTag(parameter));
            break;
        case COMMAND_TAG_GLOBAL_PERIOD:
            command.setGlobalPeriod(parsePeriodTag(parameter));
            break;
        case COMMAND_TAG_BUDGET_AMOUNT:
            command.setBudgetAmount(parseBudgetTag(parameter));
            break;
        case COMMAND_TAG_HELP_QUERY:
            command.setQueryCommand(parseHelpQueryTag(parameter));
            break;
        default:
            parserLogger.log(Level.WARNING, "An unsupported tag exception is caught: " + tag);
            throw new GlobalUnsupportedTagException();
        }
    }

    //@@author chydarren

    /**
     * Parses the user parameter input for the description and returns it.
     *
     * @param parameter The user input after the user tag.
     * @return The class type if no exceptions are thrown.
     * @throws InputTransactionInvalidTypeException If the transaction type provided is not supported.
     */
    public static String parseTypeTagForListing(String parameter) throws InputTransactionInvalidTypeException {
        parserLogger.setLevel(Level.SEVERE);
        switch (parameter) {
        case "expense":
            return CLASS_TYPE_EXPENSE;
        case "income":
            return CLASS_TYPE_INCOME;
        default:
            parserLogger.log(Level.WARNING, "An invalid type error "
                    + "is caught for the given parameter: " + parameter);
            throw new InputTransactionInvalidTypeException();
        }
    }

    //@@author wcwy

    /**
     * Checks if the type parameter is a valid transaction type and returns the parameter if it is valid.
     *
     * @param parameter The user input after the user tag.
     * @return The user input after the user tag.
     * @throws InputTransactionInvalidTypeException If the transaction type provided is not supported.
     */
    public static String parseTypeTagForAdding(String parameter) throws InputTransactionInvalidTypeException {
        parserLogger.setLevel(Level.SEVERE);
        boolean isExpense = parameter.equals(Expense.TRANSACTION_NAME);
        boolean isIncome = parameter.equals(Income.TRANSACTION_NAME);

        if (!isExpense && !isIncome) {
            parserLogger.log(Level.WARNING, "An invalid type error "
                    + "is caught for the given parameter: " + parameter);
            throw new InputTransactionInvalidTypeException();
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
        parserLogger.setLevel(Level.SEVERE);
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
     * @throws InputTransactionInvalidAmountException If the transaction amount given is not a valid accepted integer.
     */
    private static int parseAmountTag(String parameter) throws MoolahException {
        parserLogger.setLevel(Level.SEVERE);
        try {

            //Checks if parameter is non-numerical or negative values.
            if (containAlphabet(parameter) || containSymbol(parameter)) {

                parserLogger.log(Level.WARNING, "An invalid amount error is caught for the given parameter: "
                        + parameter);
                throw new InputTransactionInvalidAmountException();
            }
            int amount = Integer.parseInt(parameter);
            if (amount < MIN_AMOUNT_VALUE || amount > MAX_AMOUNT_VALUE) {
                parserLogger.log(Level.WARNING, "An invalid amount error is caught for the given parameter: "
                        + parameter);
                throw new InputTransactionInvalidAmountException();
            }
            return amount;

        } catch (NumberFormatException e) { // error inclusive of int overflows
            parserLogger.log(Level.WARNING, "An invalid amount error is caught for the given parameter: "
                    + parameter);
            throw new InputTransactionInvalidAmountException();
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
        parserLogger.setLevel(Level.SEVERE);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_INPUT_PATTERN.toString());
            LocalDate date = LocalDate.parse(parameter, formatter.withResolverStyle(ResolverStyle.STRICT));
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
        parserLogger.setLevel(Level.SEVERE);
        int index;
        //@@chinhan99
        if (containAlphabet(parameter)) { // Checks if parameter contains alphabets
            throw new GlobalIndexNotNumericException();
        } else if (startsWithMinusSign(parameter)) { // Checks if parameter is negative value
            if (parameter.length() == MIN_PARAMETER_LENGTH) { //Means parameter == "-"
                throw new GlobalIndexNotNumericException();
            }
            throw new GlobalInvalidIndexException();
        } else if (containSymbol(parameter)) { // Checks if contains any other symbols
            throw new GlobalIndexNotNumericException();
        }
        //@@author brian-vb
        try {
            // At this stage, the parameter is only positive numerical values
            index = Integer.parseInt(parameter);
            assert index >= 0; // Due to earlier parsers, there are no negative values
        } catch (NumberFormatException e) { // If overflow from integer value , continue to throw as invalid index
            parserLogger.log(Level.WARNING, "An invalid entry number error is caught for the given parameter: "
                    + parameter);
            throw new GlobalInvalidIndexException();
        }
        return index;
    }


    //@@author wcwy

    /**
     * Return a boolean value indicating if the option selected by user is "detailed".
     *
     * @param parameter The user input after the user tag.
     * @return A boolean value indicating if the option selected by user is "detailed"
     * @throws HelpUnknownOptionException If the help option parameter selected is not 'detailed'.
     */
    public static boolean parseHelpOptionTag(String parameter) throws HelpUnknownOptionException {
        parserLogger.setLevel(Level.SEVERE);
        assert parameter != null : "The function argument passed must not be null!";
        boolean isValidHelpOption = parameter.equalsIgnoreCase("detailed");
        if (isValidHelpOption) {
            return true;
        } else {
            parserLogger.log(Level.WARNING, "An invalid help option error is caught for the given parameter: "
                    + parameter);
            throw new HelpUnknownOptionException();
        }
    }

    /**
     * Returns the string containing the command queried by user for the help message if the command is valid.
     *
     * @param parameter The user input after the user tag.
     * @return The received parameter.
     * @throws HelpUnknownCommandWordException If the command word queried is not a valid command.
     */
    public static String parseHelpQueryTag(String parameter) throws HelpUnknownCommandWordException {
        assert parameter != null : "Function argument passed must not be a null";
        // An invalid command word queried by the user will result in an exception
        try {
            CommandParser.getCommand(parameter, "");
        } catch (GlobalInvalidCommandException exception) {
            throw new HelpUnknownCommandWordException();
        }

        return parameter;
    }

    /**
     * Parses the user parameter input for the budget amount and returns it as a long value.
     *
     * <p>Note: This method is created separately from parseAmountTag due to the difference in the data type for
     * the parameter and its maximum amount.
     *
     * <p>For a new budget to be valid, it must satisfy the following requirements:
     * 1. The budget should not contain any alphabets or symbols
     * 2. The budget should be parsable into a long value
     * 3. The budget range should fall within the minimum and maximum budget allowed
     * 4. The budget should be different from the current budget
     *
     * @param parameter The user input after the user tag.
     * @return The amount in long if no exceptions are thrown.
     * @throws InputTransactionInvalidAmountException If the transaction amount given does not meet the 4 requirements.
     */
    public static long parseBudgetTag(String parameter) throws MoolahException {
        parserLogger.setLevel(Level.SEVERE);
        long newBudget;
        if (containAlphabet(parameter) || containSymbol(parameter)) {
            throw new InputBudgetInvalidAmountException();
        }

        // Long value is used here as the maximum of budget can go as high as 2 * (2^31 - 1)
        try {
            newBudget = Long.parseLong(parameter);
        } catch (NumberFormatException e) {
            parserLogger.log(Level.WARNING, "Invalid budget amount error found for the given parameter: " + parameter);
            throw new InputBudgetInvalidAmountException();
        }

        if (newBudget < MIN_BUDGET_VALUE || newBudget > MAX_BUDGET_VALUE) {
            throw new InputBudgetInvalidAmountException();
        }

        if (newBudget == Budget.getBudget()) {
            throw new InputBudgetDuplicateException();
        }

        return newBudget;
    }

    //@@author chydarren

    /**
     * Checks if the type parameter is a valid statistic type and returns the parameter if it is valid.
     *
     * @param parameter The user input after the user tag.
     * @return The statistic type.
     * @throws StatsInvalidTypeException If the statistic type given is not supported.
     */
    public static String parseStatsTypeTag(String parameter) throws StatsInvalidTypeException {
        parserLogger.setLevel(Level.SEVERE);
        switch (parameter) {
        case CATEGORICAL_SAVINGS:
            return CATEGORICAL_SAVINGS;
        case MONTHLY_EXPENDITURE:
            return MONTHLY_EXPENDITURE;
        case TIME_INSIGHTS:
            return TIME_INSIGHTS;
        default:
            parserLogger.log(Level.WARNING, "An invalid statistic type error is caught for the given parameter: "
                    + parameter);
            throw new StatsInvalidTypeException();
        }
    }

    //@@author paullowse

    public static int parseMonthTag(String parameter) throws GlobalInvalidMonthException,
            GlobalNumberNotNumericException {
        parserLogger.setLevel(Level.SEVERE);
        int month;
        try {
            month = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            parserLogger.log(Level.WARNING, "An invalid number error is caught for the given parameter: "
                    + parameter);
            throw new GlobalNumberNotNumericException();
        }

        if (month > DECEMBER || month < JANUARY) {
            parserLogger.log(Level.WARNING, "An invalid month number error is caught for the given parameter: "
                    + parameter);
            throw new GlobalInvalidMonthException();
        }
        return month;
    }

    public static int parseYearTag(String parameter) throws GlobalInvalidYearException,
            GlobalNumberNotNumericException {
        parserLogger.setLevel(Level.SEVERE);
        int year;
        try {
            year = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            parserLogger.log(Level.WARNING, "An invalid number error is caught for the given parameter: "
                    + parameter);
            throw new GlobalNumberNotNumericException();
        }
        if (year < MINIMUM_YEAR || year > MAXIMUM_YEAR) {
            parserLogger.log(Level.WARNING, "An invalid year number error is caught for the given parameter: "
                    + parameter);
            throw new GlobalInvalidYearException();
        }
        return year;
    }

    public static String parsePeriodTag(String parameter) throws GlobalInvalidPeriodException {
        parserLogger.setLevel(Level.SEVERE);
        String period;
        switch (parameter) {
        case DAYS:
            return DAYS;
        case WEEKS:
            return WEEKS;
        case MONTHS:
            return MONTHS;
        default:
            parserLogger.log(Level.WARNING, "An invalid period error is caught for the given parameter: "
                    + parameter);
            throw new GlobalInvalidPeriodException();
        }
    }

    public static int parseNumberTag(String parameter) throws MoolahException {
        parserLogger.setLevel(Level.SEVERE);
        int statsNumber;
        try {
            statsNumber = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            parserLogger.log(Level.WARNING, "An invalid number error is caught for the given parameter: "
                    + parameter);
            throw new StatsInvalidNumberException();
        }
        if (statsNumber < MINIMUM_STATS_NUMBER || statsNumber > MAXIMUM_STATS_NUMBER) {
            parserLogger.log(Level.WARNING, "An invalid number error is caught for the given parameter: "
                    + parameter);
            throw new StatsInvalidNumberException();
        }
        return statsNumber;
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

    /**
     * Checks if the parameter contains symbol characters.
     *
     * @param parameter The user input after the user tag.
     * @return true if there are symbol characters within the parameter.
     */
    public static boolean containSymbol(String parameter) {
        Pattern specialSymbols = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecialSymbols = specialSymbols.matcher(parameter);

        return hasSpecialSymbols.find();
    }


    /**
     * Checks if the parameter starts with the minus sign.
     * Typically called after an alphabetical check on the parameter.
     *
     * @param parameter The user input after the user tag.
     * @return true if the parameter starts with a minus sign only.
     */

    public static boolean startsWithMinusSign(String parameter) {
        Pattern rejectedSymbols = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~]");
        Pattern minusSymbol = Pattern.compile("-");
        Matcher hasRejectedSymbols = rejectedSymbols.matcher(parameter);
        Matcher hasMinusSymbol = minusSymbol.matcher(parameter);

        if (hasRejectedSymbols.find()) {
            return false;
        }

        int countOfMinusSymbol = 0;
        if (parameter.startsWith(String.valueOf(minusSymbol))) {
            while (hasMinusSymbol.find()) {
                countOfMinusSymbol += 1;
            }
        } else { // immediately return false as parameter does not start with minus sign
            return false;
        }

        // At this stage, parameter definitely starts with minus, and double check how many minus signs are in parameter
        if (countOfMinusSymbol == MAX_NUM_OF_MINUS_SIGNS) { // Only 1 minus sign allowed
            return true;
        }
        return false;
    }
}
