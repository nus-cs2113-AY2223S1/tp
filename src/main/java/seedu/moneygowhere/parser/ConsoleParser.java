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
import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewExpense;
import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandDeleteExpenseInvalidException;
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
public class ConsoleParser {
    public static final String CONSOLE_COMMAND_BYE = "bye";
    public static final String CONSOLE_COMMAND_ADD_EXPENSE = "add-expense";
    public static final String CONSOLE_COMMAND_VIEW_EXPENSE = "view-expense";
    public static final String CONSOLE_COMMAND_DELETE_EXPENSE = "delete-expense";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE = "sort-expense";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE_TYPE_ALPHABETICAL = "alphabetical";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE_TYPE_AMOUNT = "amount";
    public static final String CONSOLE_COMMAND_SORT_EXPENSE_TYPE_DATE = "date";

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
                    "name",
                    true,
                    "name"
            );
            Option optionDateTime = new Option(
                    "d",
                    "datetime",
                    true,
                    "date and time"
            );
            Option optionDescription = new Option(
                    "t",
                    "description",
                    true,
                    "description"
            );
            Option optionAmount = new Option(
                    "a",
                    "amount",
                    true,
                    "amount"
            );
            Option optionCategory = new Option(
                    "c",
                    "category",
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

            String name = commandLine.getOptionValue("name");
            String dateTimeStr = commandLine.getOptionValue("datetime");
            String description = commandLine.getOptionValue("description");
            String amountStr = commandLine.getOptionValue("amount");
            String category = commandLine.getOptionValue("category");

            // Guard clause for mandatory arguments
            if (name == null || amountStr == null) {
                throw new ConsoleParserCommandAddExpenseInvalidException();
            }

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

            Option optionExpenseNumber = new Option(
                    "e",
                    "expense-number",
                    true,
                    "expense number"
            );
            Options options = new Options();
            options.addOption(optionExpenseNumber);
            CommandLineParser commandLineParser = new DefaultParser();
            CommandLine commandLine = commandLineParser.parse(options, argumentsArr);

            String expenseNumberStr = commandLine.getOptionValue("expense-number");

            int expenseNumber;
            if (expenseNumberStr == null) {
                expenseNumber = -1;
            } else {
                expenseNumber = Integer.parseInt(expenseNumberStr);
            }

            return new ConsoleCommandViewExpense(expenseNumber);
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

            Option optionExpenseNumber = new Option(
                    "e",
                    "expense-number",
                    true,
                    "expense number"
            );
            Options options = new Options();
            options.addOption(optionExpenseNumber);
            CommandLineParser commandLineParser = new DefaultParser();
            CommandLine commandLine = commandLineParser.parse(options, argumentsArr);

            String expenseNumberStr = commandLine.getOptionValue("expense-number");

            // Guard clause for mandatory arguments
            if (expenseNumberStr == null) {
                throw new ConsoleParserCommandDeleteExpenseInvalidException();
            }

            int expenseNumber = Integer.parseInt(expenseNumberStr);

            return new ConsoleCommandDeleteExpense(expenseNumber);
        } catch (ParseException
                 | NumberFormatException exception) {
            throw new ConsoleParserCommandDeleteExpenseInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_DELETE_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static CommandLine parseSortTypeCommandLineArguments(String arguments) throws ParseException {
        String[] argumentsArr = tokenizeCommandArguments(arguments);

        Option optionIndex = new Option("t", "type", true, "type");
        Options options = new Options();
        options.addOption(optionIndex);
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, argumentsArr);

        return commandLine;
    }

    private static ConsoleCommandSortExpense parseCommandSortExpense(String arguments)
            throws ConsoleParserCommandSortExpenseInvalidTypeException {
        try {
            CommandLine commandline = parseSortTypeCommandLineArguments(arguments);
            String type = commandline.getOptionValue("type");
            if (type == null
                    || !(type.equalsIgnoreCase(CONSOLE_COMMAND_SORT_EXPENSE_TYPE_ALPHABETICAL)
                    || type.equalsIgnoreCase(CONSOLE_COMMAND_SORT_EXPENSE_TYPE_DATE)
                    || type.equalsIgnoreCase(CONSOLE_COMMAND_SORT_EXPENSE_TYPE_AMOUNT))) {
                throw new ConsoleParserCommandSortExpenseInvalidTypeException(
                        Messages.CONSOLE_ERROR_COMMAND_SORT_EXPENSE_INVALID);
            }
            return new ConsoleCommandSortExpense(type);
        } catch (ParseException
                 | ConsoleParserCommandSortExpenseInvalidTypeException e) {
            throw new ConsoleParserCommandSortExpenseInvalidTypeException(
                    Messages.CONSOLE_ERROR_COMMAND_SORT_EXPENSE_INVALID);
        }
    }

    /**
     * Parses an input read from standard input.
     *
     * @param consoleInput String read from standard input.
     * @return Parsed command and arguments
     * @throws ConsoleParserCommandNotFoundException           If the command is not found.
     * @throws ConsoleParserCommandAddExpenseInvalidException  If the command add-expense is invalid.
     * @throws ConsoleParserCommandViewExpenseInvalidException If the command view-expense is invalid.
     */
    public static ConsoleCommand parse(String consoleInput) throws
            ConsoleParserCommandNotFoundException,
            ConsoleParserCommandAddExpenseInvalidException,
            ConsoleParserCommandViewExpenseInvalidException,
            ConsoleParserCommandDeleteExpenseInvalidException,
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
        } else if (command.equalsIgnoreCase(CONSOLE_COMMAND_SORT_EXPENSE)) {
            return parseCommandSortExpense(arguments);
        } else {
            throw new ConsoleParserCommandNotFoundException(Messages.CONSOLE_ERROR_COMMAND_NOT_FOUND);
        }
    }
}
