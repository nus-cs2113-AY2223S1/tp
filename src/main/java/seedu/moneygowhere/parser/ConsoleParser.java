package seedu.moneygowhere.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.text.StringTokenizer;
import org.apache.commons.text.matcher.StringMatcherFactory;
import seedu.moneygowhere.commands.ConsoleCommand;
import seedu.moneygowhere.commands.ConsoleCommandAddExpense;
import seedu.moneygowhere.commands.ConsoleCommandBye;
import seedu.moneygowhere.commands.ConsoleCommandDeleteExpense;
import seedu.moneygowhere.commands.ConsoleCommandEditExpense;
import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewExpense;
import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandDeleteExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandEditExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandNotFoundException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandSortExpenseInvalidTypeException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandViewExpenseInvalidException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Provide functions to parse inputs read from standard input.
 */
@SuppressWarnings("UnnecessaryLocalVariable")
public class ConsoleParser {
    public static final String CONSOLE_COMMAND_BYE = "bye";
    public static final String CONSOLE_COMMAND_ADD_EXPENSE = "add-expense";
    public static final String CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_NAME = "name";
    public static final String CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_DATE_TIME = "datetime";
    public static final String CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_DESCRIPTION = "description";
    public static final String CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_AMOUNT = "amount";
    public static final String CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_CATEGORY = "category";
    public static final String CONSOLE_COMMAND_VIEW_EXPENSE = "view-expense";
    public static final String CONSOLE_COMMAND_VIEW_EXPENSE_ARGUMENT_EXPENSE_INDEX = "expense-index";
    public static final String CONSOLE_COMMAND_DELETE_EXPENSE = "delete-expense";
    public static final String CONSOLE_COMMAND_DELETE_EXPENSE_ARGUMENT_EXPENSE_INDEX =
            CONSOLE_COMMAND_VIEW_EXPENSE_ARGUMENT_EXPENSE_INDEX;
    public static final String CONSOLE_COMMAND_EDIT_EXPENSE = "edit-expense";
    public static final String CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_EXPENSE_INDEX =
            CONSOLE_COMMAND_VIEW_EXPENSE_ARGUMENT_EXPENSE_INDEX;
    public static final String CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_NAME =
            CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_NAME;
    public static final String CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_DATE_TIME =
            CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_DATE_TIME;
    public static final String CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_DESCRIPTION =
            CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_DESCRIPTION;
    public static final String CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_AMOUNT =
            CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_AMOUNT;
    public static final String CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_CATEGORY =
            CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_CATEGORY;
    public static final String CONSOLE_COMMAND_SORT_EXPENSE = "sort-expense";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE_TYPE = "type";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE_ORDER = "order";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE_TYPE_ALPHABETICAL = "alphabetical";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE_TYPE_AMOUNT = "amount";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE_TYPE_DATE = "date";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE_ORDER_ASCENDING = "ascending";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE_ORDER_DESCENDING = "descending";

    private static String[] tokenizeCommandArguments(String arguments) {
        StringTokenizer stringTokenizer = new StringTokenizer(arguments);
        stringTokenizer.setQuoteMatcher(StringMatcherFactory.INSTANCE.quoteMatcher());

        List<String> tokenList = stringTokenizer.getTokenList();

        return tokenList.toArray(new String[0]);
    }

    private static ConsoleCommandBye parseCommandBye() {
        return new ConsoleCommandBye();
    }

    private static ConsoleCommandAddExpense parseCommandAddExpense(String arguments) throws
            ConsoleParserCommandAddExpenseInvalidException {
        try {
            String[] argumentsArr = tokenizeCommandArguments(arguments);

            Option optionName = new Option(
                    "n",
                    CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_NAME,
                    true,
                    "name"
            );
            Option optionDateTime = new Option(
                    "d",
                    CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_DATE_TIME,
                    true,
                    "date and time"
            );
            Option optionDescription = new Option(
                    "t",
                    CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_DESCRIPTION,
                    true,
                    "description"
            );
            Option optionAmount = new Option(
                    "a",
                    CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_AMOUNT,
                    true,
                    "amount"
            );
            Option optionCategory = new Option(
                    "c",
                    CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_CATEGORY,
                    true,
                    "category"
            );
            Options options = new Options();
            options.addOption(optionName);
            options.addOption(optionDateTime);
            options.addOption(optionDescription);
            options.addOption(optionAmount);
            options.addOption(optionCategory);
            CommandLineParser commandLineParser = new DefaultParser();
            CommandLine commandLine = commandLineParser.parse(options, argumentsArr);

            String name = commandLine.getOptionValue(CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_NAME);
            String amountStr = commandLine.getOptionValue(CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_AMOUNT);

            // Guard clause for mandatory arguments
            if (name == null || amountStr == null) {
                throw new ConsoleParserCommandAddExpenseInvalidException();
            }

            String dateTimeStr = commandLine.getOptionValue(CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_DATE_TIME);
            String description = commandLine.getOptionValue(CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_DESCRIPTION);
            String category = commandLine.getOptionValue(CONSOLE_COMMAND_ADD_EXPENSE_ARGUMENT_CATEGORY);

            LocalDateTime dateTime;
            if (dateTimeStr == null) {
                dateTime = LocalDateTime.now();
            } else {
                dateTime = LocalDateTime.parse(
                        dateTimeStr,
                        DateTimeFormatter.ofPattern(Configurations.CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT)
                );
            }
            BigDecimal amount = new BigDecimal(amountStr);

            return new ConsoleCommandAddExpense(
                    name,
                    dateTime,
                    description,
                    amount,
                    category);
        } catch (ParseException
                 | DateTimeParseException
                 | NumberFormatException
                 | ConsoleParserCommandAddExpenseInvalidException exception) {
            throw new ConsoleParserCommandAddExpenseInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_ADD_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static ConsoleCommandViewExpense parseCommandViewExpense(String arguments) throws
            ConsoleParserCommandViewExpenseInvalidException {
        try {
            String[] argumentsArr = tokenizeCommandArguments(arguments);

            Option optionExpenseIndex = new Option(
                    "e",
                    CONSOLE_COMMAND_VIEW_EXPENSE_ARGUMENT_EXPENSE_INDEX,
                    true,
                    "expense index"
            );
            Options options = new Options();
            options.addOption(optionExpenseIndex);
            CommandLineParser commandLineParser = new DefaultParser();
            CommandLine commandLine = commandLineParser.parse(options, argumentsArr);

            String expenseIndexStr = commandLine.getOptionValue("expense-index");

            int expenseIndex;
            if (expenseIndexStr == null) {
                expenseIndex = -1;
            } else {
                expenseIndex = Integer.parseInt(expenseIndexStr);
            }

            return new ConsoleCommandViewExpense(expenseIndex);
        } catch (ParseException
                 | NumberFormatException exception) {
            throw new ConsoleParserCommandViewExpenseInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_VIEW_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static ConsoleCommandDeleteExpense parseCommandDeleteExpense(String arguments) throws
            ConsoleParserCommandDeleteExpenseInvalidException {
        try {
            String[] argumentsArr = tokenizeCommandArguments(arguments);

            Option optionExpenseIndex = new Option(
                    "e",
                    CONSOLE_COMMAND_DELETE_EXPENSE_ARGUMENT_EXPENSE_INDEX,
                    true,
                    "expense index"
            );
            Options options = new Options();
            options.addOption(optionExpenseIndex);
            CommandLineParser commandLineParser = new DefaultParser();
            CommandLine commandLine = commandLineParser.parse(options, argumentsArr);

            String expenseIndexStr = commandLine.getOptionValue("expense-index");

            // Guard clause for mandatory arguments
            if (expenseIndexStr == null) {
                throw new ConsoleParserCommandDeleteExpenseInvalidException();
            }

            int expenseIndex = Integer.parseInt(expenseIndexStr);

            return new ConsoleCommandDeleteExpense(expenseIndex);
        } catch (ParseException
                 | NumberFormatException exception) {
            throw new ConsoleParserCommandDeleteExpenseInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_DELETE_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static ConsoleCommandEditExpense parseCommandEditExpense(String arguments) throws
            ConsoleParserCommandEditExpenseInvalidException {
        try {
            String[] argumentsArr = tokenizeCommandArguments(arguments);

            Option optionExpenseIndex = new Option(
                    "e",
                    CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_EXPENSE_INDEX,
                    true,
                    "expense index"
            );
            Option optionName = new Option(
                    "n",
                    CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_NAME,
                    true,
                    "name"
            );
            Option optionDateTime = new Option(
                    "d",
                    CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_DATE_TIME,
                    true,
                    "date and time"
            );
            Option optionDescription = new Option(
                    "t",
                    CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_DESCRIPTION,
                    true,
                    "description"
            );
            Option optionAmount = new Option(
                    "a",
                    CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_AMOUNT,
                    true,
                    "amount"
            );
            Option optionCategory = new Option(
                    "c",
                    CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_CATEGORY,
                    true,
                    "category"
            );
            Options options = new Options();
            options.addOption(optionExpenseIndex);
            options.addOption(optionName);
            options.addOption(optionDateTime);
            options.addOption(optionDescription);
            options.addOption(optionAmount);
            options.addOption(optionCategory);
            CommandLineParser commandLineParser = new DefaultParser();
            CommandLine commandLine = commandLineParser.parse(options, argumentsArr);

            String expenseIndexStr = commandLine.getOptionValue(CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_EXPENSE_INDEX);

            // Guard clause for mandatory arguments
            if (expenseIndexStr == null) {
                throw new ConsoleParserCommandEditExpenseInvalidException();
            }

            String name = commandLine.getOptionValue(CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_NAME);
            String dateTimeStr = commandLine.getOptionValue(CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_DATE_TIME);
            String description = commandLine.getOptionValue(CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_DESCRIPTION);
            String amountStr = commandLine.getOptionValue(CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_AMOUNT);
            String category = commandLine.getOptionValue(CONSOLE_COMMAND_EDIT_EXPENSE_ARGUMENT_CATEGORY);

            int expenseIndex = Integer.parseInt(expenseIndexStr);
            LocalDateTime dateTime;
            if (dateTimeStr == null) {
                dateTime = null;
            } else {
                dateTime = LocalDateTime.parse(
                        dateTimeStr,
                        DateTimeFormatter.ofPattern(Configurations.CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT)
                );
            }
            BigDecimal amount;
            if (amountStr == null) {
                amount = null;
            } else {
                amount = new BigDecimal(amountStr);
            }

            return new ConsoleCommandEditExpense(
                    expenseIndex,
                    name,
                    dateTime,
                    description,
                    amount,
                    category);
        } catch (ParseException
                 | DateTimeParseException
                 | NumberFormatException
                 | ConsoleParserCommandEditExpenseInvalidException exception) {
            throw new ConsoleParserCommandEditExpenseInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_EDIT_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static ConsoleCommandSortExpense parseCommandSortExpense(String arguments)
            throws ConsoleParserCommandSortExpenseInvalidTypeException {
        try {
            String[] argumentsArr = tokenizeCommandArguments(arguments);

            Option optionType = new Option(
                    "t",
                    "type",
                    true,
                    "type"
            );
            Option optionOrder = new Option(
                    "o",
                    "order",
                    true,
                    "Ascending/Descending"
            );
            Options options = new Options();
            options.addOption(optionType);
            options.addOption(optionOrder);
            CommandLineParser commandLineParser = new DefaultParser();
            CommandLine commandline = commandLineParser.parse(options, argumentsArr);
            String type = commandline.getOptionValue(CONSOLE_COMMAND_SORT_EXPENSE_TYPE);
            String order = commandline.getOptionValue(CONSOLE_COMMAND_SORT_EXPENSE_ORDER);
            if (type == null
                    || !(type.equalsIgnoreCase(CONSOLE_COMMAND_SORT_EXPENSE_TYPE_ALPHABETICAL)
                    || type.equalsIgnoreCase(CONSOLE_COMMAND_SORT_EXPENSE_TYPE_DATE)
                    || type.equalsIgnoreCase(CONSOLE_COMMAND_SORT_EXPENSE_TYPE_AMOUNT))) {
                throw new ConsoleParserCommandSortExpenseInvalidTypeException();
            }
            if (order == null
                    || !(order.equalsIgnoreCase(CONSOLE_COMMAND_SORT_EXPENSE_ORDER_ASCENDING)
                    || order.equalsIgnoreCase(CONSOLE_COMMAND_SORT_EXPENSE_ORDER_DESCENDING))) {
                throw new ConsoleParserCommandSortExpenseInvalidTypeException();
            }
            return new ConsoleCommandSortExpense(type, order);
        } catch (ParseException
                 | ConsoleParserCommandSortExpenseInvalidTypeException
                 | RuntimeException exception) {
            throw new ConsoleParserCommandSortExpenseInvalidTypeException(
                    Messages.CONSOLE_ERROR_COMMAND_SORT_EXPENSE_INVALID,
                    exception);
        }
    }

    /**
     * Parses an input read from standard input.
     *
     * @param consoleInput String read from standard input.
     * @return Parsed command and arguments
     * @throws ConsoleParserCommandNotFoundException               If the command is not found.
     * @throws ConsoleParserCommandAddExpenseInvalidException      If the command add-expense is invalid.
     * @throws ConsoleParserCommandViewExpenseInvalidException     If the command view-expense is invalid.
     * @throws ConsoleParserCommandDeleteExpenseInvalidException   If the command delete-expense is invalid.
     * @throws ConsoleParserCommandEditExpenseInvalidException     If the command edit-expense is invalid.
     * @throws ConsoleParserCommandSortExpenseInvalidTypeException If the command sort-expense is invalid.
     */
    public static ConsoleCommand parse(String consoleInput) throws
            ConsoleParserCommandNotFoundException,
            ConsoleParserCommandAddExpenseInvalidException,
            ConsoleParserCommandViewExpenseInvalidException,
            ConsoleParserCommandDeleteExpenseInvalidException,
            ConsoleParserCommandEditExpenseInvalidException,
            ConsoleParserCommandSortExpenseInvalidTypeException {
        String[] consoleInputArr = consoleInput.split(" ", 2);

        String command = consoleInputArr[0];
        String arguments = "";
        int numOperands = 2;
        if (consoleInputArr.length == numOperands) {
            arguments = consoleInputArr[1];
        }

        if (command.equalsIgnoreCase(CONSOLE_COMMAND_BYE)) {
            return parseCommandBye();
        } else if (command.equalsIgnoreCase(CONSOLE_COMMAND_ADD_EXPENSE)) {
            return parseCommandAddExpense(arguments);
        } else if (command.equalsIgnoreCase(CONSOLE_COMMAND_VIEW_EXPENSE)) {
            return parseCommandViewExpense(arguments);
        } else if (command.equalsIgnoreCase(CONSOLE_COMMAND_DELETE_EXPENSE)) {
            return parseCommandDeleteExpense(arguments);
        } else if (command.equalsIgnoreCase(CONSOLE_COMMAND_EDIT_EXPENSE)) {
            return parseCommandEditExpense(arguments);
        } else if (command.equalsIgnoreCase(CONSOLE_COMMAND_SORT_EXPENSE)) {
            return parseCommandSortExpense(arguments);
        } else {
            throw new ConsoleParserCommandNotFoundException(Messages.CONSOLE_ERROR_COMMAND_NOT_FOUND);
        }
    }
}
