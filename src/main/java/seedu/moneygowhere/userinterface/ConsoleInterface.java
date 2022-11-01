package seedu.moneygowhere.userinterface;

import seedu.moneygowhere.apis.CurrencyApiManager;
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
import seedu.moneygowhere.commands.ConsoleCommandHelp;
import seedu.moneygowhere.commands.ConsoleCommandMergeExternalFile;
import seedu.moneygowhere.commands.ConsoleCommandPayRecurringPayment;
import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewIncome;
import seedu.moneygowhere.commands.ConsoleCommandViewRecurringPayment;
import seedu.moneygowhere.commands.ConsoleCommandViewTarget;
import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.data.currency.CurrencyManager;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.data.expense.ExpenseManager;
import seedu.moneygowhere.data.income.Income;
import seedu.moneygowhere.data.income.IncomeManager;
import seedu.moneygowhere.data.recurringpayments.RecurringPayment;
import seedu.moneygowhere.data.recurringpayments.RecurringPaymentManager;
import seedu.moneygowhere.data.target.Target;
import seedu.moneygowhere.data.target.TargetManager;
import seedu.moneygowhere.exceptions.data.currency.CurrencyInvalidException;
import seedu.moneygowhere.exceptions.data.currency.CurrencyRatesNotFoundException;
import seedu.moneygowhere.exceptions.data.expense.ExpenseManagerExpenseNotFoundException;
import seedu.moneygowhere.exceptions.data.income.IncomeManagerIncomeNotFoundException;
import seedu.moneygowhere.exceptions.data.recurringpayment.RecurringPaymentManagerRecurringPaymentNotFoundException;
import seedu.moneygowhere.exceptions.data.target.TargetManagerTargetNotFoundException;
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
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandPayRecurringPaymentInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandSortExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandViewExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandViewIncomeInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandViewRecurringPaymentInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandViewTargetInvalidException;
import seedu.moneygowhere.logger.LocalLogger;
import seedu.moneygowhere.parser.ConsoleParser;
import seedu.moneygowhere.parser.ConsoleParserConfigurations;
import seedu.moneygowhere.storage.LocalStorage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

//@@author xzynos

/**
 * Provide functions to interface with the user via standard input and standard output.
 */
@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class ConsoleInterface {
    private LocalLogger localLogger;

    private boolean isLocalLoggerInitialized() {
        return localLogger != null;
    }

    private Scanner scanner;
    private ExpenseManager expenseManager;
    private TargetManager targetManager;
    private IncomeManager incomeManager;
    private RecurringPaymentManager recurringPaymentManager;
    private CurrencyManager currencyManager;

    private CurrencyApiManager currencyApiManager;

    //@@author xzynos

    /**
     * Initializes the console interface.
     */
    public ConsoleInterface() {
        try {
            localLogger = new LocalLogger();
        } catch (IOException e) {
            printErrorMessage("An IO error occurred in the local logger. The logger will be disabled");
        }

        if (isLocalLoggerInitialized()) {
            localLogger.logInformationalMessage("Initializing MoneyGoWhere");
        }

        scanner = new Scanner(System.in);

        expenseManager = new ExpenseManager();
        targetManager = new TargetManager();
        incomeManager = new IncomeManager();
        recurringPaymentManager = new RecurringPaymentManager();
        currencyManager = new CurrencyManager();

        currencyApiManager = new CurrencyApiManager();
    }


    //@@author xzynos

    /**
     * Prints the logo to standard out.
     */
    public static void printLogo() {
        String logo = "";
        logo += "  __  __                         _____   __          ___                   \n";
        logo += " |  \\/  |                       / ____|  \\ \\        / / |                  \n";
        logo += " | \\  / | ___  _ __   ___ _   _| |  __  __\\ \\  /\\  / /| |__   ___ _ __ ___ \n";
        logo += " | |\\/| |/ _ \\| '_ \\ / _ \\ | | | | |_ |/ _ \\ \\/  \\/ / | '_ \\ / _ \\ '__/ _ \\\n";
        logo += " | |  | | (_) | | | |  __/ |_| | |__| | (_) \\  /\\  /  | | | |  __/ | |  __/\n";
        logo += " |_|  |_|\\___/|_| |_|\\___|\\__, |\\_____|\\___/ \\/  \\/   |_| |_|\\___|_|  \\___|\n";
        logo += "                           __/ |                                           \n";
        logo += "                          |___/                                            \n";

        System.out.println(logo);
    }

    //@@author xzynos

    /**
     * Prints a blank like to standard out.
     */
    public static void printBlankLine() {
        System.out.println();
    }

    //@@author xzynos

    /**
     * Prints the greeting message to standard out.
     */
    public static void printGreetingMessage() {
        System.out.println(Messages.CONSOLE_MESSAGE_GREETING);
    }

    //@@author xzynos

    /**
     * Prints the goodbye message to standard out.
     */
    public static void printGoodbyeMessage() {
        System.out.println(Messages.CONSOLE_MESSAGE_GOODBYE);
    }

    //@@author xzynos

    /**
     * Prints an informational message to standard out.
     *
     * @param message Message to print.
     */
    public void printInformationalMessage(String message) {
        System.out.println(message);

        localLogger.logInformationalMessage(message);
    }

    //@@author xzynos

    /**
     * Prints a warning message to standard out.
     *
     * @param message Message to print.
     */
    public void printWarningMessage(String message) {
        String warningMessageHeader = "WARN: ";
        System.out.println(warningMessageHeader + message);

        localLogger.logWarningMessage(message);
    }

    //@@author xzynos

    /**
     * Prints an error message to standard out.
     *
     * @param message Message to print.
     */
    public void printErrorMessage(String message) {
        String errorMessageHeader = "ERROR: ";
        System.out.println(errorMessageHeader + message);

        localLogger.logErrorMessage(message);
    }

    //@@author xzynos

    /**
     * Reads an input from standard input.
     *
     * @return Input read from standard input.
     */
    public String getConsoleInput() {
        return scanner.nextLine();
    }

    //@@author xzynos

    /**
     * Converts an expense to a console string for use by {@link ConsoleInterface}.
     *
     * @param expense expense to convert.
     * @return Expense formatted as a console string.
     */
    public static String convertExpenseToConsoleString(Expense expense) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(
                Configurations.CONSOLE_INTERFACE_DATE_TIME_OUTPUT_FORMAT
        );

        String expenseStr = "";
        expenseStr += "Name            : " + expense.getName() + "\n";
        expenseStr += "Date and Time   : " + expense.getDateTime().format(dateTimeFormat) + "\n";
        if (expense.getDescription() != null) {
            expenseStr += "Description     : " + expense.getDescription() + "\n";
        }
        expenseStr += "Amount          : " + expense.getAmount().toPlainString() + "\n";
        if (expense.getCategory() != null) {
            expenseStr += "Category        : " + expense.getCategory() + "\n";
        }
        if (expense.getRemarks() != null) {
            expenseStr += "Remarks         : " + expense.getRemarks() + "\n";
        }
        expenseStr += "Currency        : " + expense.getCurrency();
        if (expense.getModeOfPayment() != null) {
            expenseStr += "\n";
            expenseStr += "Mode of Payment : " + expense.getModeOfPayment();
        }

        return expenseStr;
    }

    //@@author xzynos

    /**
     * Converts a target to a console string for use by {@link ConsoleInterface}.
     *
     * @param target target to convert.
     * @return Target formatted as a console string.
     */
    public static String convertTargetToConsoleString(Target target) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(
                Configurations.CONSOLE_INTERFACE_DATE_TIME_OUTPUT_FORMAT
        );

        String targetStr = "";
        targetStr += "Name          : " + target.getName() + "\n";
        targetStr += "Date and Time : " + target.getDateTime().format(dateTimeFormat) + "\n";
        if (target.getDescription() != null) {
            targetStr += "Description   : " + target.getDescription() + "\n";
        }
        targetStr += "Amount        : " + target.getAmount().toPlainString() + "\n";
        targetStr += "Current Amount: " + target.getCurrentAmount().toPlainString();

        return targetStr;
    }

    //@@author xzynos

    /**
     * Converts an income to a console string for use by {@link ConsoleInterface}.
     *
     * @param income Income to convert.
     * @return Income formatted as a console string.
     */
    public static String convertIncomeToConsoleString(Income income) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(
                Configurations.CONSOLE_INTERFACE_DATE_TIME_OUTPUT_FORMAT
        );

        String incomeStr = "";
        incomeStr += "Name          : " + income.getName() + "\n";
        incomeStr += "Date and Time : " + income.getDateTime().format(dateTimeFormat) + "\n";
        if (income.getDescription() != null) {
            incomeStr += "Description   : " + income.getDescription() + "\n";
        }
        incomeStr += "Amount        : " + income.getAmount().toPlainString();

        return incomeStr;
    }

    //@@author xzynos

    /**
     * Converts a recurring payment to a console string for use by {@link ConsoleInterface}.
     *
     * @param recurringPayment Recurring payment to convert.
     * @return Recurring payment formatted as a console string.
     */
    public static String convertRecurringPaymentToConsoleString(RecurringPayment recurringPayment) {
        String recurringPaymentStr = "";
        recurringPaymentStr += "Name            : " + recurringPayment.getName() + "\n";
        recurringPaymentStr += "Interval (Days) : " + recurringPayment.getInterval() + "\n";
        if (recurringPayment.getDescription() != null) {
            recurringPaymentStr += "Description     : " + recurringPayment.getDescription() + "\n";
        }
        recurringPaymentStr += "Amount          : " + recurringPayment.getAmount().toPlainString() + "\n";
        if (recurringPayment.getCategory() != null) {
            recurringPaymentStr += "Category        : " + recurringPayment.getCategory() + "\n";
        }
        recurringPaymentStr += "Currency        : " + recurringPayment.getCurrency();
        if (recurringPayment.getModeOfPayment() != null) {
            recurringPaymentStr += "\n";
            recurringPaymentStr += "Mode of Payment : " + recurringPayment.getModeOfPayment();
        }

        return recurringPaymentStr;
    }

    //@@author xzynos
    private void runCommandBye(ConsoleCommandBye consoleCommandBye) {
        if (localLogger != null) {
            localLogger.logInformationalMessage("Terminating MoneyGoWhere");
        }
    }

    private void runCommandHelp(ConsoleCommandHelp consoleCommandHelp) {
        String helpStr = "";
        helpStr += "---- EXPENSE-RELATED-COMMANDS ----" + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_ADD_EXPENSE
                + Messages.CONSOLE_COMMAND_ADD_EXPENSE_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_VIEW_EXPENSE
                + Messages.CONSOLE_COMMAND_VIEW_EXPENSE_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_DELETE_EXPENSE
                + Messages.CONSOLE_COMMAND_DELETE_EXPENSE_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_EDIT_EXPENSE
                + Messages.CONSOLE_COMMAND_EDIT_EXPENSE_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_SORT_EXPENSE
                + Messages.CONSOLE_COMMAND_SORT_EXPENSE_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_CONVERT_CURRENCIES
                + Messages.CONSOLE_COMMAND_CONVERT_CURRENCY_FORMAT
                + "\n";

        helpStr += "\n";

        helpStr += "---- RECURRING-PAYMENT-RELATED-COMMANDS ----" + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_ADD_RECURRING_PAYMENT
                + Messages.CONSOLE_COMMAND_ADD_RECURRING_PAYMENT_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_VIEW_RECURRING_PAYMENT
                + Messages.CONSOLE_COMMAND_VIEW_RECURRING_PAYMENT_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_DELETE_RECURRING_PAYMENT
                + Messages.CONSOLE_COMMAND_DELETE_RECURRING_PAYMENT_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_EDIT_RECURRING_PAYMENT
                + Messages.CONSOLE_COMMAND_EDIT_RECURRING_PAYMENT_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_PAY_RECURRING_PAYMENT
                + Messages.CONSOLE_COMMAND_PAY_RECURRING_PAYMENT_FORMAT
                + "\n";

        helpStr += "\n";

        helpStr += "---- INCOME-RELATED-COMMANDS ----" + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_ADD_INCOME
                + Messages.CONSOLE_COMMAND_ADD_INCOME_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_VIEW_INCOME
                + Messages.CONSOLE_COMMAND_VIEW_INCOME_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_DELETE_INCOME
                + Messages.CONSOLE_COMMAND_DELETE_INCOME_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_EDIT_INCOME
                + Messages.CONSOLE_COMMAND_EDIT_INCOME_FORMAT
                + "\n";

        helpStr += "\n";

        helpStr += "---- TARGET-RELATED-COMMANDS ----" + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_ADD_TARGET
                + Messages.CONSOLE_COMMAND_ADD_TARGET_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_VIEW_TARGET
                + Messages.CONSOLE_COMMAND_VIEW_TARGET_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_DELETE_TARGET
                + Messages.CONSOLE_COMMAND_DELETE_TARGET_FORMAT
                + "\n";
        helpStr += Messages.CONSOLE_COMMAND_HELP_EDIT_TARGET
                + Messages.CONSOLE_COMMAND_EDIT_TARGET_FORMAT;

        printInformationalMessage(helpStr);
    }

    //@@author xzynos
    private void runCommandAddExpense(ConsoleCommandAddExpense consoleCommandAddExpense) {
        try {
            currencyManager.hasCurrency(consoleCommandAddExpense.getCurrency());
        } catch (CurrencyInvalidException
                 | CurrencyRatesNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        Expense expense = new Expense(
                consoleCommandAddExpense.getName(),
                consoleCommandAddExpense.getDateTime(),
                consoleCommandAddExpense.getDescription(),
                consoleCommandAddExpense.getAmount(),
                consoleCommandAddExpense.getCategory(),
                consoleCommandAddExpense.getRemarks(),
                consoleCommandAddExpense.getCurrency(),
                consoleCommandAddExpense.getModeOfPayment());

        if (expenseManager.hasExpense(expense)) {
            printErrorMessage(Messages.CONSOLE_ERROR_COMMAND_ADD_EXPENSE_DUPLICATE_EXPENSE);

            return;
        }

        expenseManager.addExpense(expense);

        printInformationalMessage(convertExpenseToConsoleString(expense));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_ADD_EXPENSE_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author xzynos
    private void viewExpenseByExpenseIndex(int expenseIndex) {
        Expense expense;
        try {
            expense = expenseManager.getExpense(expenseIndex);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        printInformationalMessage("---- EXPENSE INDEX " + expenseIndex + " ----");
        printInformationalMessage(convertExpenseToConsoleString(expense));
    }

    //@@author yuu-chennn
    private void viewExpenseByExpenseCategory(String expenseCategory) {
        ArrayList<Expense> expenses;

        try {
            expenses = expenseManager.getExpensesByCategory(expenseCategory);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        if (expenses.isEmpty()) {
            printErrorMessage(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }

        for (int index = 0; index < expenses.size(); index++) {
            Expense expense = expenses.get(index);

            printInformationalMessage("---- EXPENSE INDEX " + index + " ----");
            printInformationalMessage(convertExpenseToConsoleString(expense));
        }
    }

    //@@author yuu-chennn
    private void viewExpenseByExpenseName(String expenseName) {
        ArrayList<Expense> expenses;

        try {
            expenses = expenseManager.getExpensesByName(expenseName);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        if (expenses.isEmpty()) {
            printErrorMessage(Messages.EXPENSE_MANAGER_ERROR_EXPENSE_NOT_FOUND);
        }

        for (int index = 0; index < expenses.size(); index++) {
            Expense expense = expenses.get(index);

            printInformationalMessage("---- EXPENSE INDEX " + index + " ----");
            printInformationalMessage(convertExpenseToConsoleString(expense));
        }
    }

    //@@author xzynos
    private void viewExpense() {
        ArrayList<Expense> expenses = expenseManager.getExpenses();

        if (expenses.isEmpty()) {
            printErrorMessage(Messages.CONSOLE_ERROR_COMMAND_VIEW_EXPENSE_EMPTY_LIST);
        }

        for (int index = 0; index < expenses.size(); index++) {
            Expense expense = expenses.get(index);

            printInformationalMessage("---- EXPENSE INDEX " + index + " ----");
            printInformationalMessage(convertExpenseToConsoleString(expense));
        }
    }

    //@@author xzynos
    private void runCommandViewExpense(ConsoleCommandViewExpense consoleCommandViewExpense) {
        int expenseIndex = consoleCommandViewExpense.getExpenseIndex();
        String expenseCategory = consoleCommandViewExpense.getExpenseCategory();
        String expenseName = consoleCommandViewExpense.getExpenseName();

        if (expenseIndex >= 0) {
            viewExpenseByExpenseIndex(expenseIndex);
        } else if (expenseCategory != null && !expenseCategory.isEmpty()) {
            viewExpenseByExpenseCategory(expenseCategory);
        } else if (expenseName != null && !expenseName.isEmpty()) {
            viewExpenseByExpenseName(expenseName);
        } else {
            viewExpense();
        }
    }

    //@@author xzynos
    private void runCommandDeleteExpense(ConsoleCommandDeleteExpense consoleCommandDeleteExpense) {
        int expenseIndex = consoleCommandDeleteExpense.getExpenseIndex();

        try {
            expenseManager.deleteExpense(expenseIndex);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_DELETE_EXPENSE_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author xzynos
    private void runCommandEditExpense(ConsoleCommandEditExpense consoleCommandEditExpense) {
        int expenseIndex = consoleCommandEditExpense.getExpenseIndex();

        Expense expense;
        try {
            expense = expenseManager.getExpense(expenseIndex);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        if (consoleCommandEditExpense.isNameSet()) {
            expense.setName(consoleCommandEditExpense.getName());
        }
        if (consoleCommandEditExpense.isDateTimeSet()) {
            expense.setDateTime(consoleCommandEditExpense.getDateTime());
        }
        if (consoleCommandEditExpense.isDescriptionSet()) {
            expense.setDescription(consoleCommandEditExpense.getDescription());
        }
        if (consoleCommandEditExpense.isAmountSet()) {
            expense.setAmount(consoleCommandEditExpense.getAmount());
        }
        if (consoleCommandEditExpense.isCategorySet()) {
            expense.setCategory(consoleCommandEditExpense.getCategory());
        }
        if (consoleCommandEditExpense.isRemarksSet()) {
            expense.setRemarks(consoleCommandEditExpense.getRemarks());
        }
        if (consoleCommandEditExpense.isCurrencySet()) {
            String currency = consoleCommandEditExpense.getCurrency().toUpperCase();

            try {
                currencyManager.hasCurrency(currency);
            } catch (CurrencyInvalidException | CurrencyRatesNotFoundException exception) {
                printErrorMessage(exception.getMessage());

                return;
            }

            expense.setCurrency(currency);
        }
        if (consoleCommandEditExpense.isModeOfPaymentSet()) {
            expense.setModeOfPayment(consoleCommandEditExpense.getModeOfPayment());
        }


        try {
            expenseManager.editExpense(expenseIndex, expense);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        printInformationalMessage("---- EXPENSE INDEX " + expenseIndex + " ----");
        printInformationalMessage(convertExpenseToConsoleString(expense));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_EDIT_EXPENSE_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author xzynos
    private void runCommandSortExpense(ConsoleCommandSortExpense commandSortExpense) {
        ArrayList<Expense> expenses = expenseManager.getExpenses();
        if (expenses.isEmpty()) {
            printErrorMessage(Messages.COMMAND_SORT_EXPENSE_EMPTY_LIST);
            return;
        }
        expenseManager.updateSortExpenses(commandSortExpense);
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_SORTED_EXPENSE_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author jeyvia
    private void runCommandConvertCurrency(ConsoleCommandConvertCurrency consoleCommandConvertCurrency) {
        int expenseIndex = consoleCommandConvertCurrency.getExpenseIndex();

        Expense expense;
        try {
            expense = expenseManager.getExpense(expenseIndex);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        BigDecimal rate = consoleCommandConvertCurrency.getRate();
        String currency = consoleCommandConvertCurrency.getCurrency();
        if (rate == null) {
            try {
                currencyManager.hasCurrency(currency);
            } catch (CurrencyInvalidException
                     | CurrencyRatesNotFoundException exception) {
                printErrorMessage(exception.getMessage());
                return;
            }
        }

        try {
            currencyManager.changeCurrency(expense, currency, rate);
            expenseManager.sortExpenses();
        } catch (CurrencyInvalidException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        printInformationalMessage(convertExpenseToConsoleString(expense));
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_CONVERT_CURRENCY_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author penguin-s
    private void runCommandAddTarget(ConsoleCommandAddTarget consoleCommandAddTarget) {
        Target target = new Target(
                consoleCommandAddTarget.getName(),
                consoleCommandAddTarget.getDateTime(),
                consoleCommandAddTarget.getDescription(),
                consoleCommandAddTarget.getAmount(),
                consoleCommandAddTarget.getCurrentAmount());
        targetManager.addTarget(target);

        printInformationalMessage(convertTargetToConsoleString(target));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_ADD_TARGET_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author penguin-s
    private void viewTargetByTargetIndex(int targetIndex) {
        Target target;
        try {
            target = targetManager.getTarget(targetIndex);
        } catch (TargetManagerTargetNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        printInformationalMessage("---- TARGET INDEX " + targetIndex + " ----");
        printInformationalMessage(convertTargetToConsoleString(target));
    }

    //@@author penguin-s
    private void viewTarget() {
        ArrayList<Target> targets = targetManager.getTargets();

        if (targets.isEmpty()) {
            printErrorMessage(Messages.COMMAND_VIEW_TARGET_EMPTY_LIST);
        }

        for (int index = 0; index < targets.size(); index++) {
            Target target = targets.get(index);

            printInformationalMessage("---- TARGET INDEX " + index + " ----");
            printInformationalMessage(convertTargetToConsoleString(target));
        }
    }

    //@@author penguin-s
    private void runCommandViewTarget(ConsoleCommandViewTarget consoleCommandViewTarget) {
        int targetIndex = consoleCommandViewTarget.getTargetIndex();

        if (targetIndex >= 0) {
            viewTargetByTargetIndex(targetIndex);
        } else {
            viewTarget();
        }
    }

    //@@author penguin-s
    private void runCommandDeleteTarget(ConsoleCommandDeleteTarget consoleCommandDeleteTarget) {
        int targetIndex = consoleCommandDeleteTarget.getTargetIndex();

        try {
            targetManager.deleteTarget(targetIndex);
        } catch (TargetManagerTargetNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_DELETE_TARGET_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author penguin-s
    private void runCommandEditTarget(ConsoleCommandEditTarget consoleCommandEditTarget) {
        int targetIndex = consoleCommandEditTarget.getTargetIndex();

        Target oldTarget;
        try {
            oldTarget = targetManager.getTarget(targetIndex);
        } catch (TargetManagerTargetNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        String name = consoleCommandEditTarget.getName();
        if (name == null) {
            name = oldTarget.getName();
        }
        LocalDateTime dateTime = consoleCommandEditTarget.getDateTime();
        if (dateTime == null) {
            dateTime = oldTarget.getDateTime();
        }
        String description = consoleCommandEditTarget.getDescription();
        if (description == null) {
            description = oldTarget.getDescription();
        }
        BigDecimal amount = consoleCommandEditTarget.getAmount();
        if (amount == null) {
            amount = oldTarget.getAmount();
        }
        BigDecimal currentAmount = consoleCommandEditTarget.getCurrentAmount();
        if (currentAmount == null) {
            currentAmount = oldTarget.getCurrentAmount();
        }

        Target newTarget = new Target(name, dateTime, description, amount, currentAmount);
        try {
            targetManager.editTarget(targetIndex, newTarget);
        } catch (TargetManagerTargetNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        printInformationalMessage("---- TARGET INDEX " + targetIndex + " ----");
        printInformationalMessage(convertTargetToConsoleString(newTarget));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_EDIT_TARGET_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author penguin-s
    private void runCommandAddIncome(ConsoleCommandAddIncome consoleCommandAddIncome) {
        Income income = new Income(
                consoleCommandAddIncome.getName(),
                consoleCommandAddIncome.getDateTime(),
                consoleCommandAddIncome.getDescription(),
                consoleCommandAddIncome.getAmount());
        incomeManager.addIncome(income);

        printInformationalMessage(convertIncomeToConsoleString(income));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_ADD_INCOME_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author penguin-s
    private void viewIncomeByIncomeIndex(int incomeIndex) {
        Income income;
        try {
            income = incomeManager.getIncome(incomeIndex);
        } catch (IncomeManagerIncomeNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        printInformationalMessage("---- INCOME INDEX " + incomeIndex + " ----");
        printInformationalMessage(convertIncomeToConsoleString(income));
    }

    //@@author penguin-s
    private void viewIncome() {
        ArrayList<Income> incomes = incomeManager.getIncomes();

        if (incomes.isEmpty()) {
            printErrorMessage(Messages.COMMAND_VIEW_INCOME_EMPTY_LIST);
        }

        for (int index = 0; index < incomes.size(); index++) {
            Income income = incomes.get(index);

            printInformationalMessage("---- INCOME INDEX " + index + " ----");
            printInformationalMessage(convertIncomeToConsoleString(income));
        }
    }

    //@@author penguin-s
    private void runCommandViewIncome(ConsoleCommandViewIncome consoleCommandViewIncome) {
        int incomeIndex = consoleCommandViewIncome.getIncomeIndex();

        if (incomeIndex >= 0) {
            viewIncomeByIncomeIndex(incomeIndex);
        } else {
            viewIncome();
        }
    }

    //@@author penguin-s
    private void runCommandDeleteIncome(ConsoleCommandDeleteIncome consoleCommandDeleteIncome) {
        int incomeIndex = consoleCommandDeleteIncome.getIncomeIndex();

        try {
            incomeManager.deleteIncome(incomeIndex);
        } catch (IncomeManagerIncomeNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_DELETE_INCOME_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author penguin-s
    private void runCommandEditIncome(ConsoleCommandEditIncome consoleCommandEditIncome) {
        int incomeIndex = consoleCommandEditIncome.getIncomeIndex();

        Income oldIncome;
        try {
            oldIncome = incomeManager.getIncome(incomeIndex);
        } catch (IncomeManagerIncomeNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        String name = consoleCommandEditIncome.getName();
        if (name == null) {
            name = oldIncome.getName();
        }
        LocalDateTime dateTime = consoleCommandEditIncome.getDateTime();
        if (dateTime == null) {
            dateTime = oldIncome.getDateTime();
        }
        String description = consoleCommandEditIncome.getDescription();
        if (description == null) {
            description = oldIncome.getDescription();
        }
        BigDecimal amount = consoleCommandEditIncome.getAmount();
        if (amount == null) {
            amount = oldIncome.getAmount();
        }

        Income newIncome = new Income(name, dateTime, description, amount);
        try {
            incomeManager.editIncome(incomeIndex, newIncome);
        } catch (IncomeManagerIncomeNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        printInformationalMessage("---- INCOME INDEX " + incomeIndex + " ----");
        printInformationalMessage(convertIncomeToConsoleString(newIncome));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_EDIT_INCOME_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author xzynos
    private void runCommandAddRecurringPayment(ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment) {
        RecurringPayment recurringPayment = new RecurringPayment(
                consoleCommandAddRecurringPayment.getName(),
                consoleCommandAddRecurringPayment.getInterval(),
                consoleCommandAddRecurringPayment.getDescription(),
                consoleCommandAddRecurringPayment.getAmount(),
                consoleCommandAddRecurringPayment.getCategory(),
                consoleCommandAddRecurringPayment.getCurrency(),
                consoleCommandAddRecurringPayment.getModeOfPayment()
        );

        if (recurringPaymentManager.hasRecurringPayment(recurringPayment)) {
            printErrorMessage(Messages.CONSOLE_ERROR_COMMAND_ADD_RECURRING_PAYMENT_DUPLICATE_RECURRING_PAYMENT);

            return;
        }

        recurringPaymentManager.addRecurringPayment(recurringPayment);

        printInformationalMessage(convertRecurringPaymentToConsoleString(recurringPayment));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_ADD_RECURRING_PAYMENT_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author xzynos
    private void viewRecurringPayment() {
        ArrayList<RecurringPayment> recurringPayments = recurringPaymentManager.getRecurringPayments();

        if (recurringPayments.isEmpty()) {
            printErrorMessage(Messages.COMMAND_VIEW_RECURRING_PAYMENT_EMPTY_LIST);
        }

        for (int index = 0; index < recurringPayments.size(); index++) {
            RecurringPayment recurringPayment = recurringPayments.get(index);

            String printStr = ""
                    + "---- RECURRING PAYMENT INDEX " + index + " ----\n"
                    + convertRecurringPaymentToConsoleString(recurringPayment);

            printInformationalMessage(printStr);
        }
    }

    //@@author xzynos
    private void viewRecurringPaymentByIndex(int recurringPaymentIndex) {
        RecurringPayment recurringPayment;
        try {
            recurringPayment = recurringPaymentManager.getRecurringPayment(recurringPaymentIndex);
        } catch (RecurringPaymentManagerRecurringPaymentNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        String printStr = ""
                + "---- RECURRING PAYMENT INDEX " + recurringPaymentIndex + " ----\n"
                + convertRecurringPaymentToConsoleString(recurringPayment);

        printInformationalMessage(printStr);
    }

    //@@author xzynos
    private void runCommandViewRecurringPayment(ConsoleCommandViewRecurringPayment consoleCommandViewRecurringPayment) {
        int recurringExpenseIndex = consoleCommandViewRecurringPayment.getRecurringPaymentIndex();

        if (recurringExpenseIndex >= 0) {
            viewRecurringPaymentByIndex(recurringExpenseIndex);
        } else {
            viewRecurringPayment();
        }
    }

    //@@author xzynos
    private void runCommandDeleteRecurringPayment(
            ConsoleCommandDeleteRecurringPayment consoleCommandDeleteRecurringPayment
    ) {
        int recurringPaymentIndex = consoleCommandDeleteRecurringPayment.getRecurringPaymentIndex();

        try {
            recurringPaymentManager.deleteRecurringPayment(recurringPaymentIndex);
        } catch (RecurringPaymentManagerRecurringPaymentNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_DELETE_RECURRING_PAYMENT_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author xzynos
    private void runCommandEditRecurringPayment(
            ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment
    ) {
        int recurringPaymentIndex = consoleCommandEditRecurringPayment.getRecurringPaymentIndex();

        RecurringPayment recurringPayment;
        try {
            recurringPayment = recurringPaymentManager.getRecurringPayment(recurringPaymentIndex);
        } catch (RecurringPaymentManagerRecurringPaymentNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        if (consoleCommandEditRecurringPayment.isNameSet()) {
            recurringPayment.setName(consoleCommandEditRecurringPayment.getName());
        }
        if (consoleCommandEditRecurringPayment.isIntervalSet()) {
            recurringPayment.setInterval(consoleCommandEditRecurringPayment.getInterval());
        }
        if (consoleCommandEditRecurringPayment.isDescriptionSet()) {
            recurringPayment.setDescription(consoleCommandEditRecurringPayment.getDescription());
        }
        if (consoleCommandEditRecurringPayment.isAmountSet()) {
            recurringPayment.setAmount(consoleCommandEditRecurringPayment.getAmount());
        }
        if (consoleCommandEditRecurringPayment.isCategorySet()) {
            recurringPayment.setCategory(consoleCommandEditRecurringPayment.getCategory());
        }
        if (consoleCommandEditRecurringPayment.isCurrencySet()) {
            recurringPayment.setCurrency(consoleCommandEditRecurringPayment.getCurrency());
        }
        if (consoleCommandEditRecurringPayment.isModeOfPaymentSet()) {
            recurringPayment.setModeOfPayment(consoleCommandEditRecurringPayment.getModeOfPayment());
        }

        try {
            recurringPaymentManager.editRecurringPayment(recurringPaymentIndex, recurringPayment);
        } catch (RecurringPaymentManagerRecurringPaymentNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_EDIT_RECURRING_PAYMENT_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author xzynos
    private void runCommandPayRecurringPayment(ConsoleCommandPayRecurringPayment consoleCommandPayRecurringPayment) {
        int recurringPaymentIndex = consoleCommandPayRecurringPayment.getRecurringPaymentIndex();

        RecurringPayment recurringPayment;
        try {
            recurringPayment = recurringPaymentManager.getRecurringPayment(recurringPaymentIndex);
        } catch (RecurringPaymentManagerRecurringPaymentNotFoundException exception) {
            printErrorMessage(exception.getMessage());

            return;
        }

        LocalDateTime dateTime = consoleCommandPayRecurringPayment.getDateTime();
        String remark = null;

        Expense expense = new Expense(
                recurringPayment.getName(),
                dateTime,
                recurringPayment.getDescription(),
                recurringPayment.getAmount(),
                recurringPayment.getCategory(),
                remark,
                recurringPayment.getCurrency(),
                recurringPayment.getModeOfPayment()
        );

        if (expenseManager.hasExpense(expense)) {
            printErrorMessage(Messages.CONSOLE_ERROR_COMMAND_PAY_RECURRING_PAYMENT_DUPLICATE_EXPENSE);

            return;
        }

        expenseManager.addExpense(expense);

        printInformationalMessage(convertExpenseToConsoleString(expense));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_PAY_RECURRING_PAYMENT_SUCCESS);

        runLocalStorageSaveToFile();
    }

    //@@author LokQiJun
    private void runCommandMergeExternalFile(ConsoleCommandMergeExternalFile consoleCommandMergeExternalFile) {
        String filePath = consoleCommandMergeExternalFile.getFilePath();
        ArrayList<Expense> savedExpenses = new ArrayList<>();
        ArrayList<RecurringPayment> savedRecurringPayments = new ArrayList<>();
        ArrayList<Target> savedTargets = new ArrayList<>();
        ArrayList<Income> savedIncomes = new ArrayList<>();
        LocalStorage.loadFromExternalFile(filePath, savedExpenses, savedRecurringPayments, savedTargets, savedIncomes);
        expenseManager.setExpenses(savedExpenses);
        recurringPaymentManager.setRecurringPayments(savedRecurringPayments);
        targetManager.setTargets(savedTargets);
        incomeManager.setIncomes(savedIncomes);
    }

    private void runLocalStorageLoadFromFile() {
        ArrayList<Expense> savedExpenses = new ArrayList<>();
        ConsoleCommandSortExpense sortCommandSetting = new ConsoleCommandSortExpense(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_ALPHABETICAL,
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING
        );
        ArrayList<RecurringPayment> savedRecurringPayments = new ArrayList<>();
        ArrayList<Target> savedTargets = new ArrayList<>();
        ArrayList<Income> savedIncomes = new ArrayList<>();
        LocalStorage.loadFromFile(
                savedExpenses,
                sortCommandSetting,
                savedRecurringPayments,
                savedTargets,
                savedIncomes);

        expenseManager.setExpenses(savedExpenses);
        recurringPaymentManager.setRecurringPayments(savedRecurringPayments);
        targetManager.setTargets(savedTargets);
        incomeManager.setIncomes(savedIncomes);
    }

    private void runLocalStorageSaveToFile() {
        ArrayList<Expense> currentExpenses = expenseManager.getExpenses();
        ConsoleCommandSortExpense sortCommandSetting = expenseManager.getSortCommandSetting();
        ArrayList<RecurringPayment> currentRecurringPayments = recurringPaymentManager.getRecurringPayments();
        ArrayList<Target> currentTargets = targetManager.getTargets();
        ArrayList<Income> currentIncomes = incomeManager.getIncomes();
        LocalStorage.saveToFile(
                currentExpenses,
                sortCommandSetting,
                currentRecurringPayments,
                currentTargets,
                currentIncomes);
    }

    //@@author xzynos
    private ConsoleCommand getConsoleCommand() {
        String consoleInput = getConsoleInput();

        printBlankLine();

        if (isLocalLoggerInitialized()) {
            localLogger.logCommand(consoleInput);
        }

        ConsoleCommand consoleCommand = null;
        try {
            consoleCommand = ConsoleParser.parse(consoleInput);
        } catch (ConsoleParserCommandNotFoundException
                 | ConsoleParserCommandAddExpenseInvalidException
                 | ConsoleParserCommandViewExpenseInvalidException
                 | ConsoleParserCommandDeleteExpenseInvalidException
                 | ConsoleParserCommandEditExpenseInvalidException
                 | ConsoleParserCommandSortExpenseInvalidException
                 | ConsoleParserCommandConvertCurrencyInvalidException
                 | ConsoleParserCommandAddTargetInvalidException
                 | ConsoleParserCommandViewTargetInvalidException
                 | ConsoleParserCommandDeleteTargetInvalidException
                 | ConsoleParserCommandEditTargetInvalidException
                 | ConsoleParserCommandAddIncomeInvalidException
                 | ConsoleParserCommandViewIncomeInvalidException
                 | ConsoleParserCommandDeleteIncomeInvalidException
                 | ConsoleParserCommandEditIncomeInvalidException
                 | ConsoleParserCommandAddRecurringPaymentInvalidException
                 | ConsoleParserCommandViewRecurringPaymentInvalidException
                 | ConsoleParserCommandDeleteRecurringPaymentInvalidException
                 | ConsoleParserCommandEditRecurringPaymentInvalidException
                 | ConsoleParserCommandPayRecurringPaymentInvalidException
                 | ConsoleParserCommandMergeExternalFileInvalidException exception) {
            printErrorMessage(exception.getMessage());
        }

        return consoleCommand;
    }

    //@@author xzynos

    /**
     * Runs the command line interface which the user interacts with.
     */
    public void run() {
        runLocalStorageLoadFromFile();

        currencyApiManager.getCurrencyApi(currencyManager);

        printBlankLine();

        while (true) {
            ConsoleCommand consoleCommand = getConsoleCommand();

            if (consoleCommand instanceof ConsoleCommandBye) {
                runCommandBye((ConsoleCommandBye) consoleCommand);

                return;
            } else if (consoleCommand instanceof ConsoleCommandHelp) {
                runCommandHelp((ConsoleCommandHelp) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandAddExpense) {
                runCommandAddExpense((ConsoleCommandAddExpense) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandViewExpense) {
                runCommandViewExpense((ConsoleCommandViewExpense) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandDeleteExpense) {
                runCommandDeleteExpense((ConsoleCommandDeleteExpense) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandEditExpense) {
                runCommandEditExpense((ConsoleCommandEditExpense) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandSortExpense) {
                runCommandSortExpense((ConsoleCommandSortExpense) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandConvertCurrency) {
                runCommandConvertCurrency((ConsoleCommandConvertCurrency) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandAddIncome) {
                runCommandAddIncome((ConsoleCommandAddIncome) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandViewIncome) {
                runCommandViewIncome((ConsoleCommandViewIncome) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandDeleteIncome) {
                runCommandDeleteIncome((ConsoleCommandDeleteIncome) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandEditIncome) {
                runCommandEditIncome((ConsoleCommandEditIncome) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandAddTarget) {
                runCommandAddTarget((ConsoleCommandAddTarget) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandViewTarget) {
                runCommandViewTarget((ConsoleCommandViewTarget) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandDeleteTarget) {
                runCommandDeleteTarget((ConsoleCommandDeleteTarget) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandEditTarget) {
                runCommandEditTarget((ConsoleCommandEditTarget) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandAddRecurringPayment) {
                runCommandAddRecurringPayment((ConsoleCommandAddRecurringPayment) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandViewRecurringPayment) {
                runCommandViewRecurringPayment((ConsoleCommandViewRecurringPayment) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandDeleteRecurringPayment) {
                runCommandDeleteRecurringPayment((ConsoleCommandDeleteRecurringPayment) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandEditRecurringPayment) {
                runCommandEditRecurringPayment((ConsoleCommandEditRecurringPayment) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandPayRecurringPayment) {
                runCommandPayRecurringPayment((ConsoleCommandPayRecurringPayment) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandMergeExternalFile) {
                runCommandMergeExternalFile((ConsoleCommandMergeExternalFile) consoleCommand);
            }

            printBlankLine();
        }
    }
}
