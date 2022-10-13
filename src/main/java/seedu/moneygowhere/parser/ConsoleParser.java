package seedu.moneygowhere.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.text.StringTokenizer;
import org.apache.commons.text.matcher.StringMatcherFactory;
import seedu.moneygowhere.commands.ConsoleCommand;
import seedu.moneygowhere.commands.ConsoleCommandAddExpense;
import seedu.moneygowhere.commands.ConsoleCommandAddIncome;
import seedu.moneygowhere.commands.ConsoleCommandAddTarget;
import seedu.moneygowhere.commands.ConsoleCommandBye;
import seedu.moneygowhere.commands.ConsoleCommandDeleteExpense;
import seedu.moneygowhere.commands.ConsoleCommandEditExpense;
import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewExpense;
import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddIncomeInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddTargetInvalidException;
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
public class ConsoleParser {
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

            Options cliOptions = ConsoleParserConfigurations.getCommandAddExpenseOptions();
            CommandLineParser cliParser = new DefaultParser();
            CommandLine cli = cliParser.parse(cliOptions, argumentsArr);

            String name = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME_LONG
            );
            String amountStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT_LONG
            );

            // Guard clause for mandatory arguments
            if (name == null || amountStr == null) {
                throw new ConsoleParserCommandAddExpenseInvalidException();
            }

            String dateTimeStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DATE_TIME_LONG
            );
            String description = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_LONG
            );
            String category = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_CATEGORY_LONG
            );

            // Parse and normalize input
            BigDecimal amount = new BigDecimal(amountStr);
            LocalDateTime dateTime;
            if (dateTimeStr == null) {
                dateTime = LocalDateTime.now();
            } else {
                dateTime = LocalDateTime.parse(
                        dateTimeStr,
                        DateTimeFormatter.ofPattern(Configurations.CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT)
                );
            }

            return new ConsoleCommandAddExpense(
                    name,
                    dateTime,
                    description,
                    amount,
                    category
            );
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

            Options cliOptions = ConsoleParserConfigurations.getCommandViewExpenseOptions();
            CommandLineParser cliParser = new DefaultParser();
            CommandLine cli = cliParser.parse(cliOptions, argumentsArr);

            String expenseIndexStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_LONG
            );
            String expenseCategory = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_LONG
            );

            // Parse and normalize input
            int expenseIndex;
            if (expenseIndexStr == null) {
                expenseIndex = -1;
            } else {
                expenseIndex = Integer.parseInt(expenseIndexStr);
            }

            return new ConsoleCommandViewExpense(
                    expenseIndex,
                    expenseCategory
            );
        } catch (ParseException | NumberFormatException exception) {
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

            Options cliOptions = ConsoleParserConfigurations.getCommandDeleteExpenseOptions();
            CommandLineParser cliParser = new DefaultParser();
            CommandLine cli = cliParser.parse(cliOptions, argumentsArr);

            String expenseIndexStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_LONG
            );

            // Guard clause for mandatory arguments
            if (expenseIndexStr == null) {
                throw new ConsoleParserCommandDeleteExpenseInvalidException();
            }

            // Parse and normalize input
            int expenseIndex = Integer.parseInt(expenseIndexStr);

            return new ConsoleCommandDeleteExpense(expenseIndex);
        } catch (ParseException | NumberFormatException exception) {
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

            Options cliOptions = ConsoleParserConfigurations.getCommandEditExpenseOptions();
            CommandLineParser cliParser = new DefaultParser();
            CommandLine cli = cliParser.parse(cliOptions, argumentsArr);

            String expenseIndexStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_LONG
            );

            // Guard clause for mandatory arguments
            if (expenseIndexStr == null) {
                throw new ConsoleParserCommandEditExpenseInvalidException();
            }

            String name = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_NAME_LONG
            );
            String dateTimeStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_LONG
            );
            String description = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_LONG
            );
            String amountStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_AMOUNT_LONG
            );
            String category = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CATEGORY_LONG
            );

            // Parse and normalize input
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

    private static ConsoleCommandSortExpense parseCommandSortExpense(String arguments) throws
            ConsoleParserCommandSortExpenseInvalidTypeException {
        try {
            String[] argumentsArr = tokenizeCommandArguments(arguments);

            Options cliOptions = ConsoleParserConfigurations.getCommandSortExpenseOptions();
            CommandLineParser cliParser = new DefaultParser();
            CommandLine cli = cliParser.parse(cliOptions, argumentsArr);

            String type = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_LONG
            );
            String order = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_LONG
            );

            // Guard clause for mandatory arguments
            if (type == null
                    || !(type.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_ALPHABETICAL
            )
                    || type.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_DATE
            )
                    || type.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_AMOUNT
            ))) {
                throw new ConsoleParserCommandSortExpenseInvalidTypeException();
            }
            if (order == null
                    || !(order.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING
            )
                    || order.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_DESCENDING
            ))) {
                throw new ConsoleParserCommandSortExpenseInvalidTypeException();
            }

            return new ConsoleCommandSortExpense(
                    type,
                    order
            );
        } catch (ParseException
                 | ConsoleParserCommandSortExpenseInvalidTypeException
                 | RuntimeException exception) {
            throw new ConsoleParserCommandSortExpenseInvalidTypeException(
                    Messages.CONSOLE_ERROR_COMMAND_SORT_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static ConsoleCommandAddTarget parseCommandAddTarget(String arguments) throws
            ConsoleParserCommandAddTargetInvalidException {
        try {
            String[] argumentsArr = tokenizeCommandArguments(arguments);

            Options cliOptions = ConsoleParserConfigurations.getCommandAddTargetOptions();
            CommandLineParser cliParser = new DefaultParser();
            CommandLine cli = cliParser.parse(cliOptions, argumentsArr);

            String name = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_NAME_LONG
            );
            String amountStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_AMOUNT_LONG
            );
            String currentAmountStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_LONG
            );

            // Guard clause for mandatory arguments
            if (name == null || amountStr == null || currentAmountStr == null) {
                throw new ConsoleParserCommandAddTargetInvalidException();
            }

            String dateTimeStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_DATE_TIME_LONG
            );
            String description = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_DESCRIPTION_LONG
            );

            // Parse and normalize input
            BigDecimal amount = new BigDecimal(amountStr);
            BigDecimal currentAmount = new BigDecimal(currentAmountStr);
            LocalDateTime dateTime;
            if (dateTimeStr == null) {
                dateTime = LocalDateTime.now();
            } else {
                dateTime = LocalDateTime.parse(
                        dateTimeStr,
                        DateTimeFormatter.ofPattern(Configurations.CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT)
                );
            }

            return new ConsoleCommandAddTarget(
                    name,
                    dateTime,
                    description,
                    amount,
                    currentAmount
            );
        } catch (ParseException
                 | DateTimeParseException
                 | NumberFormatException
                 | ConsoleParserCommandAddTargetInvalidException exception) {
            throw new ConsoleParserCommandAddTargetInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_ADD_TARGET_INVALID,
                    exception
            );
        }
    }

    private static ConsoleCommandAddIncome parseCommandAddIncome(String arguments) throws
            ConsoleParserCommandAddIncomeInvalidException {
        try {
            String[] argumentsArr = tokenizeCommandArguments(arguments);

            Options cliOptions = ConsoleParserConfigurations.getCommandAddIncomeOptions();
            CommandLineParser cliParser = new DefaultParser();
            CommandLine cli = cliParser.parse(cliOptions, argumentsArr);

            String name = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_NAME_LONG
            );
            String amountStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_AMOUNT_LONG
            );

            // Guard clause for mandatory arguments
            if (name == null || amountStr == null) {
                throw new ConsoleParserCommandAddIncomeInvalidException(
                        Messages.CONSOLE_ERROR_COMMAND_ADD_INCOME_INVALID
                );
            }

            String dateTimeStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_DATE_TIME_LONG
            );
            String description = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_DESCRIPTION_LONG
            );

            // Parse and normalize input
            BigDecimal amount = new BigDecimal(amountStr);
            LocalDateTime dateTime;
            if (dateTimeStr == null) {
                dateTime = LocalDateTime.now();
            } else {
                dateTime = LocalDateTime.parse(
                        dateTimeStr,
                        DateTimeFormatter.ofPattern(Configurations.CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT)
                );
            }

            return new ConsoleCommandAddIncome(
                    name,
                    dateTime,
                    description,
                    amount
            );
        } catch (ParseException
                 | DateTimeParseException
                 | NumberFormatException
                 | ConsoleParserCommandAddIncomeInvalidException exception) {
            throw new ConsoleParserCommandAddIncomeInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_ADD_INCOME_INVALID,
                    exception
            );
        }
    }

    private static String getConsoleCommand(String consoleInput) {
        int numOperands = 2;

        String[] consoleInputArr = consoleInput.split(" ", numOperands);

        return consoleInputArr[0];
    }

    private static String getConsoleCommandArguments(String consoleInput) {
        int numOperands = 2;

        String[] consoleInputArr = consoleInput.split(" ", numOperands);
        String arguments = "";

        if (consoleInputArr.length == numOperands) {
            arguments = consoleInputArr[1];
        }

        return arguments;
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
     * @throws ConsoleParserCommandAddTargetInvalidException       If the command add-target is invalid.
     * @throws ConsoleParserCommandAddIncomeInvalidException       If the command add-income is invalid.
     */
    public static ConsoleCommand parse(String consoleInput) throws
            ConsoleParserCommandNotFoundException,
            ConsoleParserCommandAddExpenseInvalidException,
            ConsoleParserCommandViewExpenseInvalidException,
            ConsoleParserCommandDeleteExpenseInvalidException,
            ConsoleParserCommandEditExpenseInvalidException,
            ConsoleParserCommandSortExpenseInvalidTypeException,
            ConsoleParserCommandAddTargetInvalidException,
            ConsoleParserCommandAddIncomeInvalidException {
        String command = getConsoleCommand(consoleInput);
        String arguments = getConsoleCommandArguments(consoleInput);

        if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_BYE)) {
            return parseCommandBye();
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_ADD_EXPENSE)) {
            return parseCommandAddExpense(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE)) {
            return parseCommandViewExpense(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE)) {
            return parseCommandDeleteExpense(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE)) {
            return parseCommandEditExpense(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_SORT_EXPENSE)) {
            return parseCommandSortExpense(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_ADD_TARGET)) {
            return parseCommandAddTarget(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_ADD_INCOME)) {
            return parseCommandAddIncome(arguments);
        } else {
            throw new ConsoleParserCommandNotFoundException(Messages.CONSOLE_ERROR_COMMAND_NOT_FOUND);
        }
    }
}
