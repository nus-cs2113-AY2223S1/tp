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
import seedu.moneygowhere.commands.ConsoleCommandAddRecurringPayment;
import seedu.moneygowhere.commands.ConsoleCommandAddTarget;
import seedu.moneygowhere.commands.ConsoleCommandBye;
import seedu.moneygowhere.commands.ConsoleCommandConvertCurrency;
import seedu.moneygowhere.commands.ConsoleCommandDeleteExpense;
import seedu.moneygowhere.commands.ConsoleCommandDeleteTarget;
import seedu.moneygowhere.commands.ConsoleCommandEditExpense;
import seedu.moneygowhere.commands.ConsoleCommandEditTarget;
import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewTarget;
import seedu.moneygowhere.commands.ConsoleCommandViewRecurringPayment;
import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddIncomeInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddRecurringPaymentInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddTargetInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandConvertCurrencyInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandDeleteExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandDeleteTargetInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandEditExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandEditTargetInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandNotFoundException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandSortExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandViewExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandViewTargetInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandViewRecurringPaymentInvalidException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Provide functions to parse inputs read from standard input.
 */
public class ConsoleParser {
    /**
     * Tokenizes command arguments for use by {@link DefaultParser#parse(Options, String[])}.
     *
     * @param arguments Arguments to tokenize.
     * @return Tokenized arguments.
     */
    private static String[] tokenizeCommandArguments(String arguments) {
        StringTokenizer stringTokenizer = new StringTokenizer(arguments);
        stringTokenizer.setQuoteMatcher(StringMatcherFactory.INSTANCE.quoteMatcher());

        List<String> tokenList = stringTokenizer.getTokenList();

        return tokenList.toArray(new String[0]);
    }

    /**
     * Parses command arguments.
     *
     * @param options   Command line options.
     * @param arguments Command line arguments.
     * @return Parsed command arguments.
     * @throws ParseException If an error is encountered during the parsing of command line arguments.
     */
    private static CommandLine parseCommandArguments(Options options, String arguments) throws ParseException {
        String[] argumentsArr = tokenizeCommandArguments(arguments);

        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, argumentsArr);

        return commandLine;
    }

    private static ConsoleCommandBye parseCommandBye() {
        return new ConsoleCommandBye();
    }

    private static void validateCommandAddExpenseOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DATE_TIME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_CATEGORY_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_REMARKS_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_CURRENCY_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandAddExpenseArguments(Options options, String arguments) throws
            ConsoleParserCommandAddExpenseInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandAddExpenseInvalidException(exception);
        }
    }

    private static void validateCommandAddExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandAddExpenseInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME_LONG
        );

        if (name.isBlank()) {
            throw new ConsoleParserCommandAddExpenseInvalidException();
        }
    }

    private static ConsoleCommandAddExpense parseCommandAddExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandAddExpenseInvalidException {
        try {
            String name = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME_LONG
            );
            String amountStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT_LONG
            );
            String dateTimeStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DATE_TIME_LONG
            );
            String description = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_LONG
            );
            String category = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_CATEGORY_LONG
            );
            String remarks = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_REMARKS_LONG
            );
            String currency = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_CURRENCY_LONG
            );

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

            if (currency == null) {
                currency = "SGD";
            }

            return new ConsoleCommandAddExpense(
                    name,
                    dateTime,
                    description,
                    amount,
                    category,
                    remarks,
                    currency
            );
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandAddExpenseInvalidException(exception);
        }
    }

    private static ConsoleCommandAddExpense normalizeCommandAddExpenseValues(
            ConsoleCommandAddExpense consoleCommandAddExpense
    ) {
        String currency = consoleCommandAddExpense.getCurrency();

        String currencyNormalized = currency.toUpperCase();

        consoleCommandAddExpense.setCurrency(currencyNormalized);

        return consoleCommandAddExpense;
    }

    private static ConsoleCommandAddExpense parseCommandAddExpense(String arguments) throws
            ConsoleParserCommandAddExpenseInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandAddExpenseOptions();

            validateCommandAddExpenseOptions(options);

            CommandLine commandLine = parseCommandAddExpenseArguments(options, arguments);

            validateCommandAddExpenseValues(commandLine);

            ConsoleCommandAddExpense consoleCommandAddExpense =
                    parseCommandAddExpenseValues(commandLine);

            ConsoleCommandAddExpense consoleCommandAddExpenseNormalized =
                    normalizeCommandAddExpenseValues(consoleCommandAddExpense);

            return consoleCommandAddExpenseNormalized;
        } catch (ConsoleParserCommandAddExpenseInvalidException exception) {
            throw new ConsoleParserCommandAddExpenseInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_ADD_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static void validateCommandViewExpenseOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandViewExpenseArguments(Options options, String arguments) throws
            ConsoleParserCommandViewExpenseInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandViewExpenseInvalidException(exception);
        }
    }

    private static void validateCommandViewExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandViewExpenseInvalidException {
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_LONG
        );

        if (expenseIndexStr != null) {
            int expenseIndex;

            try {
                expenseIndex = Integer.parseInt(expenseIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandViewExpenseInvalidException(exception);
            }

            if (expenseIndex < 0) {
                throw new ConsoleParserCommandViewExpenseInvalidException();
            }
        }
    }

    private static ConsoleCommandViewExpense parseCommandViewExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandViewExpenseInvalidException {
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_LONG
        );
        String expenseCategory = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_LONG
        );

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
    }

    private static ConsoleCommandViewExpense normalizeCommandViewExpenseValues(
            ConsoleCommandViewExpense consoleCommandViewExpense
    ) {
        return consoleCommandViewExpense;
    }

    private static ConsoleCommandViewExpense parseCommandViewExpense(String arguments) throws
            ConsoleParserCommandViewExpenseInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandViewExpenseOptions();

            validateCommandViewExpenseOptions(options);

            CommandLine commandLine = parseCommandViewExpenseArguments(options, arguments);

            validateCommandViewExpenseValues(commandLine);

            ConsoleCommandViewExpense consoleCommandViewExpense =
                    parseCommandViewExpenseValues(commandLine);

            ConsoleCommandViewExpense consoleCommandViewExpenseNormalized =
                    normalizeCommandViewExpenseValues(consoleCommandViewExpense);

            return consoleCommandViewExpenseNormalized;
        } catch (ConsoleParserCommandViewExpenseInvalidException exception) {
            throw new ConsoleParserCommandViewExpenseInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_VIEW_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static void validateCommandDeleteExpenseOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandDeleteExpenseArguments(Options options, String arguments) throws
            ConsoleParserCommandDeleteExpenseInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandDeleteExpenseInvalidException(exception);
        }
    }

    private static void validateCommandDeleteExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandDeleteExpenseInvalidException {
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_LONG
        );

        if (expenseIndexStr != null) {
            int expenseIndex;

            try {
                expenseIndex = Integer.parseInt(expenseIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandDeleteExpenseInvalidException(exception);
            }

            if (expenseIndex < 0) {
                throw new ConsoleParserCommandDeleteExpenseInvalidException();
            }
        }
    }

    private static ConsoleCommandDeleteExpense parseCommandDeleteExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandDeleteExpenseInvalidException {
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_LONG
        );

        int expenseIndex;
        if (expenseIndexStr == null) {
            expenseIndex = -1;
        } else {
            expenseIndex = Integer.parseInt(expenseIndexStr);
        }

        return new ConsoleCommandDeleteExpense(expenseIndex);
    }

    private static ConsoleCommandDeleteExpense normalizeCommandDeleteExpenseValues(
            ConsoleCommandDeleteExpense consoleCommandDeleteExpense
    ) {
        return consoleCommandDeleteExpense;
    }

    private static ConsoleCommandDeleteExpense parseCommandDeleteExpense(String arguments) throws
            ConsoleParserCommandDeleteExpenseInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandDeleteExpenseOptions();

            validateCommandDeleteExpenseOptions(options);

            CommandLine commandLine = parseCommandDeleteExpenseArguments(options, arguments);

            validateCommandDeleteExpenseValues(commandLine);

            ConsoleCommandDeleteExpense consoleCommandDeleteExpense
                    = parseCommandDeleteExpenseValues(commandLine);

            ConsoleCommandDeleteExpense consoleCommandDeleteExpenseNormalized
                    = normalizeCommandDeleteExpenseValues(consoleCommandDeleteExpense);

            return consoleCommandDeleteExpenseNormalized;
        } catch (ConsoleParserCommandDeleteExpenseInvalidException exception) {
            throw new ConsoleParserCommandDeleteExpenseInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_DELETE_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static void validateCommandEditExpenseOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_NAME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_AMOUNT_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CATEGORY_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_REMARKS_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CURRENCY_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandEditExpenseArguments(Options options, String arguments) throws
            ConsoleParserCommandEditExpenseInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandEditExpenseInvalidException(exception);
        }
    }

    private static void validateCommandEditExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandEditExpenseInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_NAME_LONG
        );
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_LONG
        );

        if (name != null && name.isBlank()) {
            throw new ConsoleParserCommandEditExpenseInvalidException();
        }

        if (expenseIndexStr != null) {
            int expenseIndex;

            try {
                expenseIndex = Integer.parseInt(expenseIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandEditExpenseInvalidException(exception);
            }

            if (expenseIndex < 0) {
                throw new ConsoleParserCommandEditExpenseInvalidException();
            }
        }
    }

    private static ConsoleCommandEditExpense parseCommandEditExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandEditExpenseInvalidException {
        try {
            String expenseIndexStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_LONG
            );
            String name = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_NAME_LONG
            );
            String dateTimeStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_LONG
            );
            String description = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_LONG
            );
            String amountStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_AMOUNT_LONG
            );
            String category = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CATEGORY_LONG
            );
            String remarks = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_REMARKS_LONG
            );
            String currency = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CURRENCY_LONG
            );

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
                    category,
                    remarks,
                    currency
            );
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandEditExpenseInvalidException(exception);
        }
    }

    private static ConsoleCommandEditExpense normalizeCommandEditExpenseValues(
            ConsoleCommandEditExpense consoleCommandEditExpense
    ) {
        return consoleCommandEditExpense;
    }

    private static ConsoleCommandEditExpense parseCommandEditExpense(String arguments) throws
            ConsoleParserCommandEditExpenseInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandEditExpenseOptions();

            validateCommandEditExpenseOptions(options);

            CommandLine commandLine = parseCommandEditExpenseArguments(options, arguments);

            validateCommandEditExpenseValues(commandLine);

            ConsoleCommandEditExpense consoleCommandEditExpense
                    = parseCommandEditExpenseValues(commandLine);

            ConsoleCommandEditExpense consoleCommandEditExpenseNormalized
                    = normalizeCommandEditExpenseValues(consoleCommandEditExpense);

            return consoleCommandEditExpenseNormalized;
        } catch (ConsoleParserCommandEditExpenseInvalidException exception) {
            throw new ConsoleParserCommandEditExpenseInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_EDIT_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static void validateCommandSortExpenseOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_LONG);
        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandSortExpenseArguments(Options options, String arguments) throws
            ConsoleParserCommandSortExpenseInvalidException, ParseException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandSortExpenseInvalidException(exception);
        }
    }

    private static void validateCommandSortExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandSortExpenseInvalidException {
        String type = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_LONG
        );
        String order = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_LONG
        );

        if (type == null || order == null) {
            throw new ConsoleParserCommandSortExpenseInvalidException();
        }

        if (
                !(type.equalsIgnoreCase(
                        ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_ALPHABETICAL)
                        || type.equalsIgnoreCase(
                        ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_AMOUNT)
                        || type.equalsIgnoreCase(
                        ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_DATE)
                        || type.equalsIgnoreCase(
                        ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_CURRENCY))) {
            throw new ConsoleParserCommandSortExpenseInvalidException();
        }

        if (
                !(order.equalsIgnoreCase(
                        ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING)
                        || order.equalsIgnoreCase(
                        ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_DESCENDING))) {
            throw new ConsoleParserCommandSortExpenseInvalidException();
        }
    }

    private static ConsoleCommandSortExpense parseCommandSortExpenseValues(CommandLine commandLine) {
        String type = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_LONG
        );
        String order = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_LONG
        );
        return new ConsoleCommandSortExpense(
                type,
                order
        );
    }

    private static ConsoleCommandSortExpense normalizeCommandSortExpenseValues(
            ConsoleCommandSortExpense consoleCommandSortExpense
    ) {
        return consoleCommandSortExpense;
    }

    private static ConsoleCommandSortExpense parseCommandSortExpense(String arguments) throws
            ConsoleParserCommandSortExpenseInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandSortExpenseOptions();

            validateCommandSortExpenseOptions(options);

            CommandLine commandLine = parseCommandSortExpenseArguments(options, arguments);

            validateCommandSortExpenseValues(commandLine);

            ConsoleCommandSortExpense consoleCommandSortExpense
                    = parseCommandSortExpenseValues(commandLine);

            ConsoleCommandSortExpense consoleCommandSortExpenseNormalized
                    = normalizeCommandSortExpenseValues(consoleCommandSortExpense);

            return consoleCommandSortExpenseNormalized;
        } catch (ParseException
                 | ConsoleParserCommandSortExpenseInvalidException
                 | RuntimeException exception) {
            throw new ConsoleParserCommandSortExpenseInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_SORT_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static void validateCommandConvertCurrencyOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_LONG);
        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandConvertCurrencyArguments(Options options, String arguments) throws
            ConsoleParserCommandConvertCurrencyInvalidException, ParseException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandConvertCurrencyInvalidException(exception);
        }
    }

    private static void validateCommandConvertCurrencyValues(CommandLine commandLine) throws
            ConsoleParserCommandConvertCurrencyInvalidException {
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_LONG
        );
        String currency = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_LONG
        );

        /* Checks if mandatory arguments are provided */

        if (expenseIndexStr == null || currency == null) {
            throw new ConsoleParserCommandConvertCurrencyInvalidException();
        }


    }

    private static ConsoleCommandConvertCurrency parseCommandConvertCurrencyValues(CommandLine commandLine) {
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_LONG
        );
        String currency = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_LONG
        );

        int expenseIndex = Integer.parseInt(expenseIndexStr);
        currency = currency.toUpperCase();

        return new ConsoleCommandConvertCurrency(
                expenseIndex,
                currency
        );
    }

    private static ConsoleCommandConvertCurrency normalizeCommandConvertCurrencyValues(
            ConsoleCommandConvertCurrency consoleCommandConvertCurrency
    ) {
        return consoleCommandConvertCurrency;
    }


    private static ConsoleCommandConvertCurrency parseCommandConvertCurrency(String arguments)
            throws ConsoleParserCommandConvertCurrencyInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandConvertCurrencyOptions();

            validateCommandConvertCurrencyOptions(options);

            CommandLine commandLine = parseCommandConvertCurrencyArguments(options, arguments);

            validateCommandConvertCurrencyValues(commandLine);

            ConsoleCommandConvertCurrency consoleCommandConvertCurrency
                    = parseCommandConvertCurrencyValues(commandLine);

            ConsoleCommandConvertCurrency consoleCommandConvertCurrencyNormalized
                    = normalizeCommandConvertCurrencyValues(consoleCommandConvertCurrency);

            return consoleCommandConvertCurrencyNormalized;
        } catch (ParseException
                 | NumberFormatException
                 | ConsoleParserCommandConvertCurrencyInvalidException exception) {
            throw new ConsoleParserCommandConvertCurrencyInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_CONVERT_CURRENCY_INVALID,
                    exception
            );
        }
    }

    private static void validateCommandAddTargetOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_NAME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_AMOUNT_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_DATE_TIME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_DESCRIPTION_LONG);


        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_ADD_TARGET_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandAddTargetArguments(Options options, String arguments) throws
            ConsoleParserCommandAddTargetInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandAddTargetInvalidException(exception);
        }
    }

    private static void validateCommandAddTargetValues(CommandLine commandLine) throws
            ConsoleParserCommandAddTargetInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_NAME_LONG
        );

        if (name.isBlank()) {
            throw new ConsoleParserCommandAddTargetInvalidException();
        }
    }

    private static ConsoleCommandAddTarget parseCommandAddTargetValues(CommandLine commandLine) throws
            ConsoleParserCommandAddTargetInvalidException {
        try {
            String name = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_NAME_LONG
            );
            String amountStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_AMOUNT_LONG
            );
            String currentAmountStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_LONG
            );
            String dateTimeStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_DATE_TIME_LONG
            );
            String description = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_DESCRIPTION_LONG
            );

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
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandAddTargetInvalidException(exception);
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

            /* Checks if mandatory arguments are provided */

            if (name == null || amountStr == null || currentAmountStr == null) {
                throw new ConsoleParserCommandAddTargetInvalidException();
            }

            String dateTimeStr = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_DATE_TIME_LONG
            );
            String description = cli.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_DESCRIPTION_LONG
            );

            /* Parses and normalizes arguments */

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

            /* Returns parsed arguments */

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

    private static void validateCommandViewTargetOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_VIEW_TARGET_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandViewTargetArguments(Options options, String arguments) throws
            ConsoleParserCommandViewTargetInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandViewTargetInvalidException(exception);
        }
    }

    private static void validateCommandViewTargetValues(CommandLine commandLine) throws
            ConsoleParserCommandViewTargetInvalidException {
        String targetIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_LONG
        );

        if (targetIndexStr != null) {
            int targetIndex;

            try {
                targetIndex = Integer.parseInt(targetIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandViewTargetInvalidException(exception);
            }

            if (targetIndex < 0) {
                throw new ConsoleParserCommandViewTargetInvalidException();
            }
        }
    }

    private static ConsoleCommandViewTarget parseCommandViewTargetValues(CommandLine commandLine) throws
            ConsoleParserCommandViewTargetInvalidException {
        String targetIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_LONG
        );

        int targetIndex;
        if (targetIndexStr == null) {
            targetIndex = -1;
        } else {
            targetIndex = Integer.parseInt(targetIndexStr);
        }

        return new ConsoleCommandViewTarget(
                targetIndex
        );
    }

    private static ConsoleCommandViewTarget parseCommandViewTarget(String arguments) throws
            ConsoleParserCommandViewTargetInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandViewTargetOptions();

            validateCommandViewTargetOptions(options);

            CommandLine commandLine = parseCommandViewTargetArguments(options, arguments);

            validateCommandViewTargetValues(commandLine);

            ConsoleCommandViewTarget consoleCommandViewTarget =
                    parseCommandViewTargetValues(commandLine);

            return consoleCommandViewTarget;
        } catch (ConsoleParserCommandViewTargetInvalidException exception) {
            throw new ConsoleParserCommandViewTargetInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_VIEW_TARGET_INVALID,
                    exception
            );
        }
    }

    private static void validateCommandDeleteTargetOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_DELETE_TARGET_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandDeleteTargetArguments(Options options, String arguments) throws
            ConsoleParserCommandDeleteTargetInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandDeleteTargetInvalidException(exception);
        }
    }

    private static void validateCommandDeleteTargetValues(CommandLine commandLine) throws
            ConsoleParserCommandDeleteTargetInvalidException {
        String targetIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_LONG
        );

        if (targetIndexStr != null) {
            int targetIndex;

            try {
                targetIndex = Integer.parseInt(targetIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandDeleteTargetInvalidException(exception);
            }

            if (targetIndex < 0) {
                throw new ConsoleParserCommandDeleteTargetInvalidException();
            }
        }
    }

    private static ConsoleCommandDeleteTarget parseCommandDeleteTargetValues(CommandLine commandLine) throws
            ConsoleParserCommandDeleteTargetInvalidException {
        String targetIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_LONG
        );

        int targetIndex;
        if (targetIndexStr == null) {
            targetIndex = -1;
        } else {
            targetIndex = Integer.parseInt(targetIndexStr);
        }

        return new ConsoleCommandDeleteTarget(targetIndex);
    }

    private static ConsoleCommandDeleteTarget parseCommandDeleteTarget(String arguments) throws
            ConsoleParserCommandDeleteTargetInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandDeleteTargetOptions();

            validateCommandDeleteTargetOptions(options);

            CommandLine commandLine = parseCommandDeleteTargetArguments(options, arguments);

            validateCommandDeleteTargetValues(commandLine);

            ConsoleCommandDeleteTarget consoleCommandDeleteTarget
                    = parseCommandDeleteTargetValues(commandLine);

            return consoleCommandDeleteTarget;
        } catch (ConsoleParserCommandDeleteTargetInvalidException exception) {
            throw new ConsoleParserCommandDeleteTargetInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_DELETE_EXPENSE_INVALID,
                    exception
            );
        }
    }

    private static void validateCommandEditTargetOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_NAME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_DATE_TIME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_DESCRIPTION_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_AMOUNT_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandEditTargetArguments(Options options, String arguments) throws
            ConsoleParserCommandEditTargetInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandEditTargetInvalidException(exception);
        }
    }

    private static void validateCommandEditTargetValues(CommandLine commandLine) throws
            ConsoleParserCommandEditTargetInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_NAME_LONG
        );
        String targetIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_LONG
        );

        if (name != null && name.isBlank()) {
            throw new ConsoleParserCommandEditTargetInvalidException();
        }

        if (targetIndexStr != null) {
            int targetIndex;

            try {
                targetIndex = Integer.parseInt(targetIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandEditTargetInvalidException(exception);
            }

            if (targetIndex < 0) {
                throw new ConsoleParserCommandEditTargetInvalidException();
            }
        }
    }

    private static ConsoleCommandEditTarget parseCommandEditTargetValues(CommandLine commandLine) throws
            ConsoleParserCommandEditTargetInvalidException {
        try {
            String targetIndexStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_LONG
            );
            String name = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_NAME_LONG
            );
            String dateTimeStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_DATE_TIME_LONG
            );
            String description = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_DESCRIPTION_LONG
            );
            String amountStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_AMOUNT_LONG
            );
            String currentAmountStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT_LONG
            );

            int targetIndex = Integer.parseInt(targetIndexStr);

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
            BigDecimal currentAmount;
            if (amountStr == null || currentAmountStr == null) {
                amount = null;
                currentAmount = null;
            } else {
                amount = new BigDecimal(amountStr);
                currentAmount = new BigDecimal(currentAmountStr);
            }

            return new ConsoleCommandEditTarget(
                    targetIndex,
                    name,
                    dateTime,
                    description,
                    amount,
                    currentAmount
            );
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandEditTargetInvalidException(exception);
        }
    }

    private static ConsoleCommandEditTarget parseCommandEditTarget(String arguments) throws
            ConsoleParserCommandEditTargetInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandEditTargetOptions();

            validateCommandEditTargetOptions(options);

            CommandLine commandLine = parseCommandEditTargetArguments(options, arguments);

            validateCommandEditTargetValues(commandLine);

            ConsoleCommandEditTarget consoleCommandEditTarget
                    = parseCommandEditTargetValues(commandLine);

            return consoleCommandEditTarget;
        } catch (ConsoleParserCommandEditTargetInvalidException exception) {
            throw new ConsoleParserCommandEditTargetInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_EDIT_TARGET_INVALID,
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

            /* Checks if mandatory arguments are provided */

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

            /* Parses and normalizes arguments */

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

            /* Returns parsed arguments */

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

    private static void validateCommandAddRecurringPaymentOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandAddRecurringPaymentArguments(Options options, String arguments) throws
            ConsoleParserCommandAddRecurringPaymentInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandAddRecurringPaymentInvalidException(exception);
        }
    }

    private static void validateCommandAddRecurringPaymentValues(CommandLine commandLine) throws
            ConsoleParserCommandAddRecurringPaymentInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_LONG
        );

        if (name.isBlank()) {
            throw new ConsoleParserCommandAddRecurringPaymentInvalidException();
        }
    }

    private static ConsoleCommandAddRecurringPayment parseCommandAddRecurringPaymentValues(
            CommandLine commandLine
    ) throws ConsoleParserCommandAddRecurringPaymentInvalidException {
        try {
            String name = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_LONG
            );
            String amountStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_LONG
            );
            String intervalStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_LONG
            );
            String description = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG
            );

            BigDecimal amount = new BigDecimal(amountStr);

            int interval = Integer.parseInt(intervalStr);

            return new ConsoleCommandAddRecurringPayment(
                    name,
                    interval,
                    description,
                    amount
            );
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandAddRecurringPaymentInvalidException(exception);
        }
    }

    private static ConsoleCommandAddRecurringPayment normalizeCommandAddRecurringPaymentValues(
            ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment
    ) {
        return consoleCommandAddRecurringPayment;
    }

    private static ConsoleCommandAddRecurringPayment parseCommandAddRecurringPayment(String arguments) throws
            ConsoleParserCommandAddRecurringPaymentInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandAddRecurringPaymentOptions();

            validateCommandAddRecurringPaymentOptions(options);

            CommandLine commandLine = parseCommandAddRecurringPaymentArguments(options, arguments);

            validateCommandAddRecurringPaymentValues(commandLine);

            ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment
                    = parseCommandAddRecurringPaymentValues(commandLine);

            ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPaymentNormalized
                    = normalizeCommandAddRecurringPaymentValues(consoleCommandAddRecurringPayment);

            return consoleCommandAddRecurringPaymentNormalized;
        } catch (ConsoleParserCommandAddRecurringPaymentInvalidException exception) {
            throw new ConsoleParserCommandAddRecurringPaymentInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_ADD_RECURRING_PAYMENT_INVALID,
                    exception
            );
        }
    }

    private static void validateCommandViewRecurringPaymentOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    private static CommandLine parseCommandViewRecurringPaymentArguments(Options options, String arguments) throws
            ConsoleParserCommandViewRecurringPaymentInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandViewRecurringPaymentInvalidException(exception);
        }
    }

    private static void validateCommandViewRecurringPaymentValues(CommandLine commandLine) throws
            ConsoleParserCommandViewRecurringPaymentInvalidException {
        String recurringPaymentIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG
        );

        if (recurringPaymentIndexStr != null) {
            int recurringPaymentIndex;

            try {
                recurringPaymentIndex = Integer.parseInt(recurringPaymentIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandViewRecurringPaymentInvalidException(exception);
            }

            if (recurringPaymentIndex < 0) {
                throw new ConsoleParserCommandViewRecurringPaymentInvalidException();
            }
        }
    }

    private static ConsoleCommandViewRecurringPayment parseCommandViewRecurringPaymentValues(
            CommandLine commandLine
    ) throws ConsoleParserCommandViewRecurringPaymentInvalidException {
        try {
            String recurringPaymentIndexStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG
            );

            int recurringPaymentIndex;
            if (recurringPaymentIndexStr == null) {
                recurringPaymentIndex = -1;
            } else {
                recurringPaymentIndex = Integer.parseInt(recurringPaymentIndexStr);
            }

            return new ConsoleCommandViewRecurringPayment(recurringPaymentIndex);
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandViewRecurringPaymentInvalidException(exception);
        }
    }

    private static ConsoleCommandViewRecurringPayment normalizeCommandViewRecurringPaymentValues(
            ConsoleCommandViewRecurringPayment consoleCommandViewRecurringPayment
    ) {
        return consoleCommandViewRecurringPayment;
    }

    private static ConsoleCommandViewRecurringPayment parseCommandViewRecurringPayment(String arguments) throws
            ConsoleParserCommandViewRecurringPaymentInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandViewRecurringPaymentOptions();

            validateCommandViewRecurringPaymentOptions(options);

            CommandLine commandLine = parseCommandViewRecurringPaymentArguments(options, arguments);

            validateCommandViewRecurringPaymentValues(commandLine);

            ConsoleCommandViewRecurringPayment consoleCommandViewRecurringPayment
                    = parseCommandViewRecurringPaymentValues(commandLine);

            ConsoleCommandViewRecurringPayment consoleCommandViewRecurringPaymentNormalized
                    = normalizeCommandViewRecurringPaymentValues(consoleCommandViewRecurringPayment);

            return consoleCommandViewRecurringPaymentNormalized;
        } catch (ConsoleParserCommandViewRecurringPaymentInvalidException exception) {
            throw new ConsoleParserCommandViewRecurringPaymentInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_VIEW_RECURRING_PAYMENT_INVALID,
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
     * @throws ConsoleParserCommandNotFoundException                    If the command is not found.
     * @throws ConsoleParserCommandAddExpenseInvalidException           If the command add-expense is invalid.
     * @throws ConsoleParserCommandViewExpenseInvalidException          If the command view-expense is invalid.
     * @throws ConsoleParserCommandDeleteExpenseInvalidException        If the command delete-expense is invalid.
     * @throws ConsoleParserCommandEditExpenseInvalidException          If the command edit-expense is invalid.
     * @throws ConsoleParserCommandSortExpenseInvalidException          If the command sort-expense is invalid.
     * @throws ConsoleParserCommandConvertCurrencyInvalidException      If the command convert-currency is invalid.
     * @throws ConsoleParserCommandAddTargetInvalidException            If the command add-target is invalid.
     * @throws ConsoleParserCommandViewTargetInvalidException           If the command view-target is invalid.
     * @throws ConsoleParserCommandDeleteTargetInvalidException         If the command delete-target is invalid.
     * @throws ConsoleParserCommandEditTargetInvalidException           If the command edit-target is invalid.     >>>>>>> Stashed changes
     * @throws ConsoleParserCommandAddIncomeInvalidException            If the command add-income is invalid.
     * @throws ConsoleParserCommandAddRecurringPaymentInvalidException  If the command Add-RecurringPayment is invalid.
     * @throws ConsoleParserCommandViewRecurringPaymentInvalidException If the command View-RecurringPayment is invalid.
     */
    public static ConsoleCommand parse(String consoleInput) throws
            ConsoleParserCommandNotFoundException,
            ConsoleParserCommandAddExpenseInvalidException,
            ConsoleParserCommandViewExpenseInvalidException,
            ConsoleParserCommandDeleteExpenseInvalidException,
            ConsoleParserCommandEditExpenseInvalidException,
            ConsoleParserCommandSortExpenseInvalidException,
            ConsoleParserCommandConvertCurrencyInvalidException,
            ConsoleParserCommandAddTargetInvalidException,
            ConsoleParserCommandViewTargetInvalidException,
            ConsoleParserCommandDeleteTargetInvalidException,
            ConsoleParserCommandEditTargetInvalidException,
            ConsoleParserCommandAddIncomeInvalidException,
            ConsoleParserCommandAddRecurringPaymentInvalidException,
            ConsoleParserCommandViewRecurringPaymentInvalidException {
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
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY)) {
            return parseCommandConvertCurrency(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_ADD_TARGET)) {
            return parseCommandAddTarget(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_VIEW_TARGET)) {
            return parseCommandViewTarget(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_DELETE_TARGET)) {
            return parseCommandDeleteTarget(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_EDIT_TARGET)) {
            return parseCommandEditTarget(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_ADD_INCOME)) {
            return parseCommandAddIncome(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT)) {
            return parseCommandAddRecurringPayment(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT)) {
            return parseCommandViewRecurringPayment(arguments);
        } else {
            throw new ConsoleParserCommandNotFoundException(Messages.CONSOLE_ERROR_COMMAND_NOT_FOUND);
        }
    }
}
