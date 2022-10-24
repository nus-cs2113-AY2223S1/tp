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
import seedu.moneygowhere.commands.ConsoleCommandDeleteIncome;
import seedu.moneygowhere.commands.ConsoleCommandDeleteRecurringPayment;
import seedu.moneygowhere.commands.ConsoleCommandDeleteTarget;
import seedu.moneygowhere.commands.ConsoleCommandEditExpense;
import seedu.moneygowhere.commands.ConsoleCommandEditIncome;
import seedu.moneygowhere.commands.ConsoleCommandEditRecurringPayment;
import seedu.moneygowhere.commands.ConsoleCommandEditTarget;
import seedu.moneygowhere.commands.ConsoleCommandMergeExternalFile;
import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewIncome;
import seedu.moneygowhere.commands.ConsoleCommandViewRecurringPayment;
import seedu.moneygowhere.commands.ConsoleCommandViewTarget;
import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandAddExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandAddIncomeInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandAddRecurringPaymentInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandAddTargetInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandConvertCurrencyInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandDeleteExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandDeleteIncomeInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandDeleteRecurringPaymentInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandDeleteTargetInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandEditExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandEditIncomeInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandEditRecurringPaymentInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandEditTargetInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandMergeExternalFileInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandNotFoundException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandSortExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandViewExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandViewIncomeInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandViewRecurringPaymentInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandViewTargetInvalidException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

//@@author xzynos

/**
 * Provide functions to parse inputs read from standard input.
 */
public class ConsoleParser {
    //@@author xzynos

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

    //@@author xzynos

    /**
     * Parses command arguments.
     *
     * @param options Command line options.
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

    //@@author xzynos
    private static ConsoleCommandBye parseCommandBye() {
        return new ConsoleCommandBye();
    }

    //@@author xzynos
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
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_CURRENCY_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author xzynos
    private static CommandLine parseCommandAddExpenseArguments(Options options, String arguments) throws
            ConsoleParserCommandAddExpenseInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandAddExpenseInvalidException(exception);
        }
    }

    //@@author xzynos
    private static void validateCommandAddExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandAddExpenseInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME_LONG
        );

        if (name.isBlank()) {
            throw new ConsoleParserCommandAddExpenseInvalidException();
        }

        String modeOfPayment = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT
        );

        if (modeOfPayment != null
                && !(modeOfPayment.equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CASH)
                || modeOfPayment.equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYNOW)
                || modeOfPayment.equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYLAH)
                || modeOfPayment.equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CARD))) {
            throw new ConsoleParserCommandAddExpenseInvalidException();
        }
    }

    //@@author xzynos
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
            String modeOfPayment = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_LONG
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
                    currency,
                    modeOfPayment
            );
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandAddExpenseInvalidException(exception);
        }
    }

    //@@author xzynos
    private static ConsoleCommandAddExpense normalizeCommandAddExpenseValues(
            ConsoleCommandAddExpense consoleCommandAddExpense
    ) {
        String currency = consoleCommandAddExpense.getCurrency();

        String currencyNormalized = currency.toUpperCase();

        consoleCommandAddExpense.setCurrency(currencyNormalized);

        String modeOfPayment = consoleCommandAddExpense.getModeOfPayment();

        if (modeOfPayment != null) {
            String modeOfPaymentNormalized = "";
            if (modeOfPayment.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CASH)) {
                modeOfPaymentNormalized = "Cash";
            } else if (modeOfPayment.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYNOW)) {
                modeOfPaymentNormalized = "PayNow";
            } else if (modeOfPayment.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYLAH)) {
                modeOfPaymentNormalized = "PayLah";
            } else if (modeOfPayment.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CARD)) {
                modeOfPaymentNormalized = "Card";
            }
            consoleCommandAddExpense.setModeOfPayment(modeOfPaymentNormalized);
        }


        return consoleCommandAddExpense;
    }

    //@@author xzynos
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

    //@@author xzynos
    private static void validateCommandViewExpenseOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author xzynos
    private static CommandLine parseCommandViewExpenseArguments(Options options, String arguments) throws
            ConsoleParserCommandViewExpenseInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandViewExpenseInvalidException(exception);
        }
    }

    //@@author xzynos
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

    //@@author xzynos
    private static ConsoleCommandViewExpense parseCommandViewExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandViewExpenseInvalidException {
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_LONG
        );
        String expenseCategory = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_LONG
        );
        String expenseName = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME_LONG
        );

        int expenseIndex;
        if (expenseIndexStr == null) {
            expenseIndex = -1;
        } else {
            expenseIndex = Integer.parseInt(expenseIndexStr);
        }

        return new ConsoleCommandViewExpense(
                expenseIndex,
                expenseCategory,
                expenseName
        );
    }

    //@@author xzynos
    private static ConsoleCommandViewExpense normalizeCommandViewExpenseValues(
            ConsoleCommandViewExpense consoleCommandViewExpense
    ) {
        return consoleCommandViewExpense;
    }

    //@@author xzynos
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

    //@@author xzynos
    private static void validateCommandDeleteExpenseOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author xzynos
    private static CommandLine parseCommandDeleteExpenseArguments(Options options, String arguments) throws
            ConsoleParserCommandDeleteExpenseInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandDeleteExpenseInvalidException(exception);
        }
    }

    //@@author xzynos
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

    //@@author xzynos
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

    //@@author xzynos
    private static ConsoleCommandDeleteExpense normalizeCommandDeleteExpenseValues(
            ConsoleCommandDeleteExpense consoleCommandDeleteExpense
    ) {
        return consoleCommandDeleteExpense;
    }

    //@@author xzynos
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

    //@@author xzynos
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
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CURRENCY_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author xzynos
    private static CommandLine parseCommandEditExpenseArguments(Options options, String arguments) throws
            ConsoleParserCommandEditExpenseInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandEditExpenseInvalidException(exception);
        }
    }

    //@@author xzynos
    private static void validateCommandEditExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandEditExpenseInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_NAME_LONG
        );
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_LONG
        );
        String modeOfPayment = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_LONG
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

        if (modeOfPayment != null
                && !(modeOfPayment.equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CASH)
                || modeOfPayment.equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYNOW)
                || modeOfPayment.equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYLAH)
                || modeOfPayment.equalsIgnoreCase(
                ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CARD))) {
            throw new ConsoleParserCommandEditExpenseInvalidException();
        }
    }

    //@@author xzynos
    private static ConsoleCommandEditExpense parseCommandEditExpenseValues(CommandLine commandLine) throws
            ConsoleParserCommandEditExpenseInvalidException {
        try {
            ConsoleCommandEditExpense consoleCommandEditExpense = new ConsoleCommandEditExpense();

            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_LONG
            )) {
                String expenseIndexStr = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_LONG
                );

                int expenseIndex = Integer.parseInt(expenseIndexStr);

                consoleCommandEditExpense.setExpenseIndex(expenseIndex);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_NAME_LONG
            )) {
                String name = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_NAME_LONG
                );

                consoleCommandEditExpense.setName(name);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_LONG
            )) {
                String dateTimeStr = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_LONG
                );

                LocalDateTime dateTime = LocalDateTime.parse(
                        dateTimeStr,
                        DateTimeFormatter.ofPattern(Configurations.CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT)
                );

                consoleCommandEditExpense.setDateTime(dateTime);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_LONG
            )) {
                String description = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_LONG
                );

                consoleCommandEditExpense.setDescription(description);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_AMOUNT_LONG
            )) {
                String amountStr = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_AMOUNT_LONG
                );

                BigDecimal amount = new BigDecimal(amountStr);

                consoleCommandEditExpense.setAmount(amount);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CATEGORY_LONG
            )) {
                String category = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CATEGORY_LONG
                );

                consoleCommandEditExpense.setCategory(category);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_REMARKS_LONG
            )) {
                String remarks = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_REMARKS_LONG
                );

                consoleCommandEditExpense.setRemarks(remarks);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CURRENCY_LONG
            )) {
                String currency = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CURRENCY_LONG
                );

                consoleCommandEditExpense.setCurrency(currency);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_LONG
            )) {
                String modeOfPayment = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_LONG
                );

                consoleCommandEditExpense.setModeOfPayment(modeOfPayment);
            }

            return consoleCommandEditExpense;
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandEditExpenseInvalidException(exception);
        }
    }

    //@@author xzynos
    private static ConsoleCommandEditExpense normalizeCommandEditExpenseValues(
            ConsoleCommandEditExpense consoleCommandEditExpense
    ) {

        String modeOfPayment = consoleCommandEditExpense.getModeOfPayment();

        if (modeOfPayment != null) {
            String modeOfPaymentNormalized = "";
            if (modeOfPayment.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CASH)) {
                modeOfPaymentNormalized = "Cash";
            } else if (modeOfPayment.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYNOW)) {
                modeOfPaymentNormalized = "PayNow";
            } else if (modeOfPayment.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYLAH)) {
                modeOfPaymentNormalized = "PayLah";
            } else if (modeOfPayment.equalsIgnoreCase(
                    ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CARD)) {
                modeOfPaymentNormalized = "Card";
            }
            consoleCommandEditExpense.setModeOfPayment(modeOfPaymentNormalized);
        }
        return consoleCommandEditExpense;
    }

    //@@author xzynos
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

    //@@author jeyvia
    private static void validateCommandSortExpenseOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_LONG);
        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author jeyvia
    private static CommandLine parseCommandSortExpenseArguments(Options options, String arguments) throws
            ConsoleParserCommandSortExpenseInvalidException, ParseException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandSortExpenseInvalidException(exception);
        }
    }

    //@@author jeyvia
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

    //@@author jeyvia
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

    //@@author jeyvia
    private static ConsoleCommandSortExpense normalizeCommandSortExpenseValues(
            ConsoleCommandSortExpense consoleCommandSortExpense
    ) {
        return consoleCommandSortExpense;
    }

    //@@author jeyvia
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

    //@@author jeyvia
    private static void validateCommandConvertCurrencyOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_RATE_LONG);
        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author jeyvia
    private static CommandLine parseCommandConvertCurrencyArguments(Options options, String arguments) throws
            ConsoleParserCommandConvertCurrencyInvalidException, ParseException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandConvertCurrencyInvalidException(exception);
        }
    }

    //@@author jeyvia
    private static void validateCommandConvertCurrencyValues(CommandLine commandLine) throws
            ConsoleParserCommandConvertCurrencyInvalidException {
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_LONG
        );
        String currency = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_LONG
        );
        String rateStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_RATE_LONG
        );

        if (expenseIndexStr == null || currency == null) {
            throw new ConsoleParserCommandConvertCurrencyInvalidException();
        }

        if (rateStr != null) {
            BigDecimal rate = new BigDecimal(rateStr);
            if (rate.compareTo(BigDecimal.ZERO) != 1) {
                throw new ConsoleParserCommandConvertCurrencyInvalidException();
            }
        }
    }

    //@@author jeyvia
    private static ConsoleCommandConvertCurrency parseCommandConvertCurrencyValues(CommandLine commandLine) {
        String expenseIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_LONG
        );
        String currency = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_LONG
        );
        String rateStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_CONVERT_CURRENCY_ARG_RATE_LONG
        );

        int expenseIndex = Integer.parseInt(expenseIndexStr);
        currency = currency.toUpperCase();
        BigDecimal rate = null;
        if (rateStr != null) {
            rate = new BigDecimal(rateStr);
        }

        return new ConsoleCommandConvertCurrency(
                expenseIndex,
                currency,
                rate
        );
    }

    //@@author jeyvia
    private static ConsoleCommandConvertCurrency normalizeCommandConvertCurrencyValues(
            ConsoleCommandConvertCurrency consoleCommandConvertCurrency
    ) {
        return consoleCommandConvertCurrency;
    }

    //@@author jeyvia
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

    //@@author penguin-s
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

    //@@author penguin-s
    private static CommandLine parseCommandAddTargetArguments(Options options, String arguments) throws
            ConsoleParserCommandAddTargetInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandAddTargetInvalidException(exception);
        }
    }

    //@@author penguin-s
    private static void validateCommandAddTargetValues(CommandLine commandLine) throws
            ConsoleParserCommandAddTargetInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_ADD_TARGET_ARG_NAME_LONG
        );

        if (name.isBlank()) {
            throw new ConsoleParserCommandAddTargetInvalidException();
        }
    }

    //@@author penguin-s
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

    //@@author penguin-s
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

    //@@author penguin-s
    private static void validateCommandViewTargetOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_VIEW_TARGET_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author penguin-s
    private static CommandLine parseCommandViewTargetArguments(Options options, String arguments) throws
            ConsoleParserCommandViewTargetInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandViewTargetInvalidException(exception);
        }
    }

    //@@author penguin-s
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

    //@@author penguin-s
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

    //@@author penguin-s
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

    //@@author penguin-s
    private static void validateCommandDeleteTargetOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_DELETE_TARGET_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author penguin-s
    private static CommandLine parseCommandDeleteTargetArguments(Options options, String arguments) throws
            ConsoleParserCommandDeleteTargetInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandDeleteTargetInvalidException(exception);
        }
    }

    //@@author penguin-s
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

    //@@author penguin-s
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

    //@@author penguin-s
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

    //@@author penguin-s
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

    //@@author penguin-s
    private static CommandLine parseCommandEditTargetArguments(Options options, String arguments) throws
            ConsoleParserCommandEditTargetInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandEditTargetInvalidException(exception);
        }
    }

    //@@author penguin-s
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

    //@@author penguin-s
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

    //@@author penguin-s
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

    //@@author penguin-s
    private static void validateCommandAddIncomeOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_NAME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_AMOUNT_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_DATE_TIME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_DESCRIPTION_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_ADD_INCOME_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author penguin-s
    private static CommandLine parseCommandAddIncomeArguments(Options options, String arguments) throws
            ConsoleParserCommandAddIncomeInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandAddIncomeInvalidException(exception);
        }
    }

    //@@author penguin-s
    private static void validateCommandAddIncomeValues(CommandLine commandLine) throws
            ConsoleParserCommandAddIncomeInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_NAME_LONG
        );

        if (name.isBlank()) {
            throw new ConsoleParserCommandAddIncomeInvalidException();
        }

    }

    //@@author penguin-s
    private static ConsoleCommandAddIncome parseCommandAddIncomeValues(CommandLine commandLine) throws
            ConsoleParserCommandAddIncomeInvalidException {
        try {
            String name = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_NAME_LONG
            );
            String amountStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_AMOUNT_LONG
            );
            String dateTimeStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_DATE_TIME_LONG
            );
            String description = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_INCOME_ARG_DESCRIPTION_LONG
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

            return new ConsoleCommandAddIncome(
                    name,
                    dateTime,
                    description,
                    amount
            );
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandAddIncomeInvalidException(exception);
        }
    }

    //@@author penguin-s
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

    //@@author penguin-s
    private static void validateCommandViewIncomeOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_VIEW_INCOME_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author penguin-s
    private static CommandLine parseCommandViewIncomeArguments(Options options, String arguments) throws
            ConsoleParserCommandViewIncomeInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandViewIncomeInvalidException(exception);
        }
    }

    //@@author penguin-s
    private static void validateCommandViewIncomeValues(CommandLine commandLine) throws
            ConsoleParserCommandViewIncomeInvalidException {
        String incomeIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_LONG
        );

        if (incomeIndexStr != null) {
            int incomeIndex;

            try {
                incomeIndex = Integer.parseInt(incomeIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandViewIncomeInvalidException(exception);
            }

            if (incomeIndex < 0) {
                throw new ConsoleParserCommandViewIncomeInvalidException();
            }
        }
    }

    //@@author penguin-s
    private static ConsoleCommandViewIncome parseCommandViewIncomeValues(CommandLine commandLine) throws
            ConsoleParserCommandViewIncomeInvalidException {
        String incomeIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_LONG
        );

        int incomeIndex;
        if (incomeIndexStr == null) {
            incomeIndex = -1;
        } else {
            incomeIndex = Integer.parseInt(incomeIndexStr);
        }

        return new ConsoleCommandViewIncome(
                incomeIndex
        );
    }

    //@@author penguin-s
    private static ConsoleCommandViewIncome parseCommandViewIncome(String arguments) throws
            ConsoleParserCommandViewIncomeInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandViewIncomeOptions();

            validateCommandViewIncomeOptions(options);

            CommandLine commandLine = parseCommandViewIncomeArguments(options, arguments);

            validateCommandViewIncomeValues(commandLine);

            ConsoleCommandViewIncome consoleCommandViewIncome =
                    parseCommandViewIncomeValues(commandLine);

            return consoleCommandViewIncome;
        } catch (ConsoleParserCommandViewIncomeInvalidException exception) {
            throw new ConsoleParserCommandViewIncomeInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_VIEW_INCOME_INVALID,
                    exception
            );
        }
    }

    //@@author penguin-s
    private static void validateCommandDeleteIncomeOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_DELETE_INCOME_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author penguin-s
    private static CommandLine parseCommandDeleteIncomeArguments(Options options, String arguments) throws
            ConsoleParserCommandDeleteIncomeInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandDeleteIncomeInvalidException(exception);
        }
    }

    //@@author penguin-s
    private static void validateCommandDeleteIncomeValues(CommandLine commandLine) throws
            ConsoleParserCommandDeleteIncomeInvalidException {
        String incomeIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_LONG
        );

        if (incomeIndexStr != null) {
            int incomeIndex;

            try {
                incomeIndex = Integer.parseInt(incomeIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandDeleteIncomeInvalidException(exception);
            }

            if (incomeIndex < 0) {
                throw new ConsoleParserCommandDeleteIncomeInvalidException();
            }
        }
    }

    //@@author penguin-s
    private static ConsoleCommandDeleteIncome parseCommandDeleteIncomeValues(CommandLine commandLine) throws
            ConsoleParserCommandDeleteIncomeInvalidException {
        String incomeIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_LONG
        );

        int incomeIndex;
        if (incomeIndexStr == null) {
            incomeIndex = -1;
        } else {
            incomeIndex = Integer.parseInt(incomeIndexStr);
        }

        return new ConsoleCommandDeleteIncome(incomeIndex);
    }

    //@@author penguin-s
    private static ConsoleCommandDeleteIncome parseCommandDeleteIncome(String arguments) throws
            ConsoleParserCommandDeleteIncomeInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandDeleteIncomeOptions();

            validateCommandDeleteIncomeOptions(options);

            CommandLine commandLine = parseCommandDeleteIncomeArguments(options, arguments);

            validateCommandDeleteIncomeValues(commandLine);

            ConsoleCommandDeleteIncome consoleCommandDeleteIncome
                    = parseCommandDeleteIncomeValues(commandLine);

            return consoleCommandDeleteIncome;
        } catch (ConsoleParserCommandDeleteIncomeInvalidException exception) {
            throw new ConsoleParserCommandDeleteIncomeInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_DELETE_INCOME_INVALID,
                    exception
            );
        }
    }

    //@@author penguin-s
    private static void validateCommandEditIncomeOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_NAME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_DATE_TIME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_DESCRIPTION_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_AMOUNT_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author penguin-s
    private static CommandLine parseCommandEditIncomeArguments(Options options, String arguments) throws
            ConsoleParserCommandEditIncomeInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandEditIncomeInvalidException(exception);
        }
    }

    //@@author penguin-s
    private static void validateCommandEditIncomeValues(CommandLine commandLine) throws
            ConsoleParserCommandEditIncomeInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_NAME_LONG
        );
        String incomeIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_LONG
        );

        if (name != null && name.isBlank()) {
            throw new ConsoleParserCommandEditIncomeInvalidException();
        }

        if (incomeIndexStr != null) {
            int incomeIndex;

            try {
                incomeIndex = Integer.parseInt(incomeIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandEditIncomeInvalidException(exception);
            }

            if (incomeIndex < 0) {
                throw new ConsoleParserCommandEditIncomeInvalidException();
            }
        }
    }

    //@@author penguin-s
    private static ConsoleCommandEditIncome parseCommandEditIncomeValues(CommandLine commandLine) throws
            ConsoleParserCommandEditIncomeInvalidException {
        try {
            String incomeIndexStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_LONG
            );
            String name = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_NAME_LONG
            );
            String dateTimeStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_DATE_TIME_LONG
            );
            String description = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_DESCRIPTION_LONG
            );
            String amountStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_EDIT_INCOME_ARG_AMOUNT_LONG
            );

            int incomeIndex = Integer.parseInt(incomeIndexStr);

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

            return new ConsoleCommandEditIncome(
                    incomeIndex,
                    name,
                    dateTime,
                    description,
                    amount
            );
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandEditIncomeInvalidException(exception);
        }
    }

    //@@author penguin-s
    private static ConsoleCommandEditIncome parseCommandEditIncome(String arguments) throws
            ConsoleParserCommandEditIncomeInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandEditIncomeOptions();

            validateCommandEditIncomeOptions(options);

            CommandLine commandLine = parseCommandEditIncomeArguments(options, arguments);

            validateCommandEditIncomeValues(commandLine);

            ConsoleCommandEditIncome consoleCommandEditIncome
                    = parseCommandEditIncomeValues(commandLine);

            return consoleCommandEditIncome;
        } catch (ConsoleParserCommandEditIncomeInvalidException exception) {
            throw new ConsoleParserCommandEditIncomeInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_EDIT_INCOME_INVALID,
                    exception
            );
        }
    }

    //@@author xzynos
    private static void validateCommandAddRecurringPaymentOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author xzynos
    private static CommandLine parseCommandAddRecurringPaymentArguments(Options options, String arguments) throws
            ConsoleParserCommandAddRecurringPaymentInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandAddRecurringPaymentInvalidException(exception);
        }
    }

    //@@author xzynos
    private static void validateCommandAddRecurringPaymentValues(CommandLine commandLine) throws
            ConsoleParserCommandAddRecurringPaymentInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_LONG
        );

        if (name.isBlank()) {
            throw new ConsoleParserCommandAddRecurringPaymentInvalidException();
        }
    }

    //@@author xzynos
    private static ConsoleCommandAddRecurringPayment parseCommandAddRecurringPaymentValues(
            CommandLine commandLine
    ) throws ConsoleParserCommandAddRecurringPaymentInvalidException {
        try {
            String name = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_LONG
            );
            String intervalStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_LONG
            );
            String amountStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_LONG
            );
            String description = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG
            );
            String category = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY_LONG
            );
            String currency = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY_LONG
            );

            BigDecimal amount = new BigDecimal(amountStr);

            int interval = Integer.parseInt(intervalStr);

            if (currency == null) {
                currency = "SGD";
            }

            return new ConsoleCommandAddRecurringPayment(
                    name,
                    interval,
                    description,
                    amount,
                    category,
                    currency
            );
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandAddRecurringPaymentInvalidException(exception);
        }
    }

    //@@author xzynos
    private static ConsoleCommandAddRecurringPayment normalizeCommandAddRecurringPaymentValues(
            ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment
    ) {
        return consoleCommandAddRecurringPayment;
    }

    //@@author xzynos
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

    //@@author xzynos
    private static void validateCommandViewRecurringPaymentOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author xzynos
    private static CommandLine parseCommandViewRecurringPaymentArguments(Options options, String arguments) throws
            ConsoleParserCommandViewRecurringPaymentInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandViewRecurringPaymentInvalidException(exception);
        }
    }

    //@@author xzynos
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

    //@@author xzynos
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

    //@@author xzynos
    private static ConsoleCommandViewRecurringPayment normalizeCommandViewRecurringPaymentValues(
            ConsoleCommandViewRecurringPayment consoleCommandViewRecurringPayment
    ) {
        return consoleCommandViewRecurringPayment;
    }

    //@@author xzynos
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

    //@@author xzynos
    private static void validateCommandDeleteRecurringPaymentOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author xzynos
    private static CommandLine parseCommandDeleteRecurringPaymentArguments(Options options, String arguments) throws
            ConsoleParserCommandDeleteRecurringPaymentInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandDeleteRecurringPaymentInvalidException(exception);
        }
    }

    //@@author xzynos
    private static void validateCommandDeleteRecurringPaymentValues(CommandLine commandLine) throws
            ConsoleParserCommandDeleteRecurringPaymentInvalidException {
        String recurringPaymentIndexStr = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG
        );

        if (recurringPaymentIndexStr != null) {
            int recurringPaymentIndex;

            try {
                recurringPaymentIndex = Integer.parseInt(recurringPaymentIndexStr);
            } catch (NumberFormatException exception) {
                throw new ConsoleParserCommandDeleteRecurringPaymentInvalidException(exception);
            }

            if (recurringPaymentIndex < 0) {
                throw new ConsoleParserCommandDeleteRecurringPaymentInvalidException();
            }
        }
    }

    //@@author xzynos
    private static ConsoleCommandDeleteRecurringPayment parseCommandDeleteRecurringPaymentValues(
            CommandLine commandLine
    ) throws ConsoleParserCommandDeleteRecurringPaymentInvalidException {
        try {
            String recurringPaymentIndexStr = commandLine.getOptionValue(
                    ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG
            );

            int recurringPaymentIndex;
            if (recurringPaymentIndexStr == null) {
                recurringPaymentIndex = -1;
            } else {
                recurringPaymentIndex = Integer.parseInt(recurringPaymentIndexStr);
            }

            return new ConsoleCommandDeleteRecurringPayment(recurringPaymentIndex);
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandDeleteRecurringPaymentInvalidException(exception);
        }
    }

    //@@author xzynos
    private static ConsoleCommandDeleteRecurringPayment normalizeCommandDeleteRecurringPaymentValues(
            ConsoleCommandDeleteRecurringPayment consoleCommandDeleteRecurringPayment
    ) {
        return consoleCommandDeleteRecurringPayment;
    }

    //@@author xzynos
    private static ConsoleCommandDeleteRecurringPayment parseCommandDeleteRecurringPayment(String arguments) throws
            ConsoleParserCommandDeleteRecurringPaymentInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandDeleteRecurringPaymentOptions();

            validateCommandDeleteRecurringPaymentOptions(options);

            CommandLine commandLine = parseCommandDeleteRecurringPaymentArguments(options, arguments);

            validateCommandDeleteRecurringPaymentValues(commandLine);

            ConsoleCommandDeleteRecurringPayment consoleCommandDeleteRecurringPayment
                    = parseCommandDeleteRecurringPaymentValues(commandLine);

            ConsoleCommandDeleteRecurringPayment consoleCommandDeleteRecurringPaymentNormalized
                    = normalizeCommandDeleteRecurringPaymentValues(consoleCommandDeleteRecurringPayment);

            return consoleCommandDeleteRecurringPaymentNormalized;
        } catch (ConsoleParserCommandDeleteRecurringPaymentInvalidException exception) {
            throw new ConsoleParserCommandDeleteRecurringPaymentInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_DELETE_RECURRING_PAYMENT_INVALID,
                    exception
            );
        }
    }

    //@@author xzynos
    private static void validateCommandEditRecurringPaymentOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_LONG)
                && options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author xzynos
    private static CommandLine parseCommandEditRecurringPaymentArguments(Options options, String arguments) throws
            ConsoleParserCommandEditRecurringPaymentInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);

            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandEditRecurringPaymentInvalidException(exception);
        }
    }

    //@@author xzynos
    private static void validateCommandEditRecurringPaymentValues(CommandLine commandLine) throws
            ConsoleParserCommandEditRecurringPaymentInvalidException {
        String name = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_LONG
        );

        if (name != null && name.isBlank()) {
            throw new ConsoleParserCommandEditRecurringPaymentInvalidException();
        }
    }

    //@@author xzynos
    private static ConsoleCommandEditRecurringPayment parseCommandEditRecurringPaymentValues(
            CommandLine commandLine
    ) throws ConsoleParserCommandEditRecurringPaymentInvalidException {
        try {
            ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment =
                    new ConsoleCommandEditRecurringPayment();

            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG
            )) {
                String recurringPaymentIndexStr = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG
                );

                int recurringPaymentIndex = Integer.parseInt(recurringPaymentIndexStr);

                consoleCommandEditRecurringPayment.setRecurringPaymentIndex(recurringPaymentIndex);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_LONG
            )) {
                String name = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_LONG
                );

                consoleCommandEditRecurringPayment.setName(name);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_LONG
            )) {
                String intervalStr = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_LONG
                );

                int interval = Integer.parseInt(intervalStr);

                consoleCommandEditRecurringPayment.setInterval(interval);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG
            )) {
                String description = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG
                );

                consoleCommandEditRecurringPayment.setDescription(description);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_LONG
            )) {
                String amountStr = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_LONG
                );

                BigDecimal amount = new BigDecimal(amountStr);

                consoleCommandEditRecurringPayment.setAmount(amount);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_LONG
            )) {
                String category = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_LONG
                );

                consoleCommandEditRecurringPayment.setCategory(category);
            }
            if (commandLine.hasOption(
                    ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_LONG
            )) {
                String currency = commandLine.getOptionValue(
                        ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_LONG
                );

                consoleCommandEditRecurringPayment.setCurrency(currency);
            }

            return consoleCommandEditRecurringPayment;
        } catch (DateTimeParseException | NumberFormatException exception) {
            throw new ConsoleParserCommandEditRecurringPaymentInvalidException(exception);
        }
    }

    //@@author xzynos
    private static ConsoleCommandEditRecurringPayment normalizeCommandEditRecurringPaymentValues(
            ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment
    ) {
        return consoleCommandEditRecurringPayment;
    }

    //@@author xzynos
    private static ConsoleCommandEditRecurringPayment parseCommandEditRecurringPayment(String arguments) throws
            ConsoleParserCommandEditRecurringPaymentInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandEditRecurringPaymentOptions();

            validateCommandEditRecurringPaymentOptions(options);

            CommandLine commandLine = parseCommandEditRecurringPaymentArguments(options, arguments);

            validateCommandEditRecurringPaymentValues(commandLine);

            ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment
                    = parseCommandEditRecurringPaymentValues(commandLine);

            ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPaymentNormalized
                    = normalizeCommandEditRecurringPaymentValues(consoleCommandEditRecurringPayment);

            return consoleCommandEditRecurringPaymentNormalized;
        } catch (ConsoleParserCommandEditRecurringPaymentInvalidException exception) {
            throw new ConsoleParserCommandEditRecurringPaymentInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_EDIT_RECURRING_PAYMENT_INVALID,
                    exception
            );
        }
    }

    //@@author LokQiJun
    private static void validateCommandMergeExternalFileOptions(Options options) {
        boolean hasAllCliOptions = options.hasLongOption(
                ConsoleParserConfigurations.COMMAND_MERGE_FILE_ARG_MERGE_FILE_PATH_LONG);

        assert hasAllCliOptions :
                ConsoleParserConfigurations.COMMAND_MERGE_EXTERNAL_FILE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS;
    }

    //@@author LokQiJun
    private static CommandLine parseCommandMergeExternalFileArguments(Options options, String arguments) throws
            ConsoleParserCommandMergeExternalFileInvalidException {
        try {
            CommandLine commandline = parseCommandArguments(options, arguments);
            return commandline;
        } catch (ParseException exception) {
            throw new ConsoleParserCommandMergeExternalFileInvalidException(exception);
        }
    }

    //@@author LokQiJun
    private static void validateCommandMergeExternalFileValues(CommandLine commandLine) throws
            ConsoleParserCommandMergeExternalFileInvalidException {
        String mergeFilePathString = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_MERGE_FILE_ARG_MERGE_FILE_PATH_LONG
        );

        if (mergeFilePathString != null) {
            if (mergeFilePathString.split(" ").length != 1) {
                throw new ConsoleParserCommandMergeExternalFileInvalidException();
            }
        }
    }

    //@@author LokQiJun
    private static ConsoleCommandMergeExternalFile parseCommandMergeExternalFileValues(
            CommandLine commandLine) {
        String filePath = commandLine.getOptionValue(
                ConsoleParserConfigurations.COMMAND_MERGE_FILE_ARG_MERGE_FILE_PATH_LONG
        );
        return new ConsoleCommandMergeExternalFile(filePath);
    }

    //@@author LokQiJun
    private static ConsoleCommandMergeExternalFile normalizeCommandMergeExternalFileValues(
            ConsoleCommandMergeExternalFile consoleCommandMergeExternalFile
    ) {
        return consoleCommandMergeExternalFile;
    }

    //@@author LokQiJun
    private static ConsoleCommandMergeExternalFile parseCommandMergeExternalFile(String arguments) throws
            ConsoleParserCommandMergeExternalFileInvalidException {
        try {
            Options options = ConsoleParserConfigurations.getCommandMergeExternalFileOptions();

            validateCommandMergeExternalFileOptions(options);

            CommandLine commandLine = parseCommandMergeExternalFileArguments(options, arguments);

            validateCommandMergeExternalFileValues(commandLine);

            ConsoleCommandMergeExternalFile consoleCommandMergeExternalFile
                    = parseCommandMergeExternalFileValues(commandLine);

            ConsoleCommandMergeExternalFile consoleCommandMergeExternalFileNormalized
                    = normalizeCommandMergeExternalFileValues(consoleCommandMergeExternalFile);

            return consoleCommandMergeExternalFileNormalized;
        } catch (ConsoleParserCommandMergeExternalFileInvalidException exception) {
            throw new ConsoleParserCommandMergeExternalFileInvalidException(
                    Messages.CONSOLE_ERROR_COMMAND_MERGE_FILE_INVALID,
                    exception
            );
        }
    }

    //@@author xzynos
    private static String getConsoleCommand(String consoleInput) {
        int numOperands = 2;

        String[] consoleInputArr = consoleInput.split(" ", numOperands);

        return consoleInputArr[0];
    }

    //@@author xzynos
    private static String getConsoleCommandArguments(String consoleInput) {
        int numOperands = 2;

        String[] consoleInputArr = consoleInput.split(" ", numOperands);
        String arguments = "";

        if (consoleInputArr.length == numOperands) {
            arguments = consoleInputArr[1];
        }

        return arguments;
    }

    //@@author xzynos

    /**
     * Parses an input read from standard input.
     *
     * @param consoleInput String read from standard input.
     * @return Parsed command and arguments
     * @throws ConsoleParserCommandNotFoundException                      If the command is not found.
     * @throws ConsoleParserCommandAddExpenseInvalidException             If the command Add-Expense is invalid.
     * @throws ConsoleParserCommandViewExpenseInvalidException            If the command View-Expense is invalid.
     * @throws ConsoleParserCommandDeleteExpenseInvalidException          If the command Delete-Expense is invalid.
     * @throws ConsoleParserCommandEditExpenseInvalidException            If the command Edit-Expense is invalid.
     * @throws ConsoleParserCommandSortExpenseInvalidException            If the command Sort-Expense is invalid.
     * @throws ConsoleParserCommandConvertCurrencyInvalidException        If the command Convert-Currency is invalid.
     * @throws ConsoleParserCommandAddTargetInvalidException              If the command Add-Target is invalid.
     * @throws ConsoleParserCommandViewTargetInvalidException             If the command View-Target is invalid.
     * @throws ConsoleParserCommandDeleteTargetInvalidException           If the command Delete-Target is invalid.
     * @throws ConsoleParserCommandEditTargetInvalidException             If the command Edit-Target is invalid.
     * @throws ConsoleParserCommandAddIncomeInvalidException              If the command Add-Income is invalid.
     * @throws ConsoleParserCommandViewIncomeInvalidException             If the command View-Income is invalid.
     * @throws ConsoleParserCommandDeleteIncomeInvalidException           If the command Delete-Income is invalid.
     * @throws ConsoleParserCommandEditIncomeInvalidException             If the command Edit-Income is invalid.
     * @throws ConsoleParserCommandAddRecurringPaymentInvalidException    If the command Add-RecurringPayment is
     *                                                                    invalid.
     * @throws ConsoleParserCommandViewRecurringPaymentInvalidException   If the command View-RecurringPayment is
     *                                                                    invalid.
     * @throws ConsoleParserCommandDeleteRecurringPaymentInvalidException If the command Delete-RecurringPayment is
     *                                                                    invalid.
     * @throws ConsoleParserCommandMergeExternalFileInvalidException      If the command Merge-File is invalid
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
            ConsoleParserCommandViewIncomeInvalidException,
            ConsoleParserCommandDeleteIncomeInvalidException,
            ConsoleParserCommandEditIncomeInvalidException,
            ConsoleParserCommandAddRecurringPaymentInvalidException,
            ConsoleParserCommandViewRecurringPaymentInvalidException,
            ConsoleParserCommandDeleteRecurringPaymentInvalidException,
            ConsoleParserCommandEditRecurringPaymentInvalidException,
            ConsoleParserCommandMergeExternalFileInvalidException {
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
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_VIEW_INCOME)) {
            return parseCommandViewIncome(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_DELETE_INCOME)) {
            return parseCommandDeleteIncome(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_EDIT_INCOME)) {
            return parseCommandEditIncome(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT)) {
            return parseCommandAddRecurringPayment(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT)) {
            return parseCommandViewRecurringPayment(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT)) {
            return parseCommandDeleteRecurringPayment(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT)) {
            return parseCommandEditRecurringPayment(arguments);
        } else if (command.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_MERGE_FILE)) {
            return parseCommandMergeExternalFile(arguments);
        } else {
            throw new ConsoleParserCommandNotFoundException(Messages.CONSOLE_ERROR_COMMAND_NOT_FOUND);
        }
    }
}
