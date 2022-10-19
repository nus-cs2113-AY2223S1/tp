package seedu.moneygowhere.userinterface;

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
import seedu.moneygowhere.currency.CurrencyApi;
import seedu.moneygowhere.data.currency.CurrencyManager;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.data.expense.ExpenseManager;
import seedu.moneygowhere.data.income.Income;
import seedu.moneygowhere.data.income.IncomeManager;
import seedu.moneygowhere.data.recurringpayments.RecurringPayment;
import seedu.moneygowhere.data.recurringpayments.RecurringPaymentManager;
import seedu.moneygowhere.data.target.Target;
import seedu.moneygowhere.data.target.TargetManager;
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
import seedu.moneygowhere.exceptions.CurrencyInvalidException;
import seedu.moneygowhere.exceptions.ExpenseManagerExpenseNotFoundException;
import seedu.moneygowhere.exceptions.TargetManagerTargetNotFoundException;
import seedu.moneygowhere.logger.LocalLogger;
import seedu.moneygowhere.parser.ConsoleParser;
import seedu.moneygowhere.storage.LocalStorage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provide functions to interface with the user via standard input and standard output.
 */
@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class ConsoleInterface {
    private LocalLogger localLogger;
    private Scanner scanner;
    private ExpenseManager expenseManager;
    private TargetManager targetManager;
    private IncomeManager incomeManager;
    private RecurringPaymentManager recurringPaymentManager;
    private CurrencyManager currencyManager;

    private LocalStorage localStorage;

    /**
     * Initializes the console interface.
     */
    public ConsoleInterface() {
        try {
            localLogger = new LocalLogger();
        } catch (IOException e) {
            printErrorMessage("An IO error occurred in the console logger. The logger will be disabled");
        }

        if (localLogger != null) {
            localLogger.logInfo("Initializing MoneyGoWhere");
        }

        scanner = new Scanner(System.in);
        expenseManager = new ExpenseManager();
        targetManager = new TargetManager();
        incomeManager = new IncomeManager();
        recurringPaymentManager = new RecurringPaymentManager();
        currencyManager = new CurrencyManager();

        localStorage = new LocalStorage();
    }

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

    /**
     * Prints a blank like to standard out.
     */
    public static void printBlankLine() {
        System.out.println();
    }

    /**
     * Prints the greeting message to standard out.
     */
    public static void printGreetingMessage() {
        System.out.println(Messages.CONSOLE_MESSAGE_GREETING);
    }

    /**
     * Prints the goodbye message to standard out.
     */
    public static void printGoodbyeMessage() {
        System.out.println(Messages.CONSOLE_MESSAGE_GOODBYE);
    }

    /**
     * Prints an informational message to standard out.
     *
     * @param message Message to print.
     */
    public static void printInformationalMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a warning message to standard out.
     *
     * @param message Message to print.
     */
    public static void printWarningMessage(String message) {
        System.out.println("WARN: " + message);
    }

    /**
     * Prints an error message to standard out.
     *
     * @param message Message to print.
     */
    public static void printErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Reads an input from standard input.
     *
     * @return Input read from standard input.
     */
    public String getConsoleInput() {
        return scanner.nextLine();
    }

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
        expenseStr += "Name          : " + expense.getName() + "\n";
        expenseStr += "Date and Time : " + expense.getDateTime().format(dateTimeFormat) + "\n";
        expenseStr += "Description   : " + expense.getDescription() + "\n";
        expenseStr += "Amount        : " + expense.getAmount() + "\n";
        expenseStr += "Category      : " + expense.getCategory() + "\n";
        expenseStr += "Remarks       : " + expense.getRemarks() + "\n";
        expenseStr += "Currency      : " + expense.getCurrency();

        return expenseStr;
    }

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
        targetStr += "Description   : " + target.getDescription() + "\n";
        targetStr += "Amount        : " + target.getAmount() + "\n";
        targetStr += "Current Amount: " + target.getCurrentAmount();

        return targetStr;
    }

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
        incomeStr += "Description   : " + income.getDescription() + "\n";
        incomeStr += "Amount        : " + income.getAmount();

        return incomeStr;
    }

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
        recurringPaymentStr += "Description     : " + recurringPayment.getDescription() + "\n";
        recurringPaymentStr += "Amount          : " + recurringPayment.getAmount();

        return recurringPaymentStr;
    }

    private void runCommandBye(ConsoleCommandBye consoleCommandBye) {
        if (localLogger != null) {
            localLogger.logInfo("Terminating MoneyGoWhere");
        }
    }

    private void runCommandAddExpense(ConsoleCommandAddExpense consoleCommandAddExpense) {
        try {
            currencyManager.hasCurrency(consoleCommandAddExpense.getCurrency());
        } catch (CurrencyInvalidException exception) {
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
                consoleCommandAddExpense.getCurrency());
        expenseManager.addExpense(expense);

        printInformationalMessage(convertExpenseToConsoleString(expense));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_ADD_EXPENSE_SUCCESS);

        localStorage.saveToFile(expenseManager.getExpenses(), expenseManager.getSortCommandSetting());
    }

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

    private void viewExpenseByExpenseCategory(String expenseCategory) {
        ArrayList<Expense> expenses = expenseManager.getExpensesByCategory(expenseCategory);

        for (int index = 0; index < expenses.size(); index++) {
            Expense expense = expenses.get(index);

            printInformationalMessage("---- EXPENSE INDEX " + index + " ----");
            printInformationalMessage(convertExpenseToConsoleString(expense));
        }
    }

    private void viewExpense() {
        ArrayList<Expense> expenses = expenseManager.getExpenses();

        if (expenses.isEmpty()) {
            printInformationalMessage(Messages.COMMAND_VIEW_EXPENSE_EMPTY_LIST);
        }

        for (int index = 0; index < expenses.size(); index++) {
            Expense expense = expenses.get(index);

            printInformationalMessage("---- EXPENSE INDEX " + index + " ----");
            printInformationalMessage(convertExpenseToConsoleString(expense));
        }
    }

    private void runCommandViewExpense(ConsoleCommandViewExpense consoleCommandViewExpense) {
        int expenseIndex = consoleCommandViewExpense.getExpenseIndex();
        String expenseCategory = consoleCommandViewExpense.getExpenseCategory();

        if (expenseIndex >= 0) {
            viewExpenseByExpenseIndex(expenseIndex);
        } else if (expenseCategory != null && !expenseCategory.isEmpty()) {
            viewExpenseByExpenseCategory(expenseCategory);
        } else {
            viewExpense();
        }
    }

    private void runCommandDeleteExpense(ConsoleCommandDeleteExpense consoleCommandDeleteExpense) {
        int expenseIndex = consoleCommandDeleteExpense.getExpenseIndex();

        try {
            expenseManager.deleteExpense(expenseIndex);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_DELETE_EXPENSE_SUCCESS);

        localStorage.saveToFile(expenseManager.getExpenses(), expenseManager.getSortCommandSetting());
    }

    private void runCommandEditExpense(ConsoleCommandEditExpense consoleCommandEditExpense) {
        int expenseIndex = consoleCommandEditExpense.getExpenseIndex();

        Expense oldExpense;
        try {
            oldExpense = expenseManager.getExpense(expenseIndex);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        String name = consoleCommandEditExpense.getName();
        if (name == null) {
            name = oldExpense.getName();
        }
        LocalDateTime dateTime = consoleCommandEditExpense.getDateTime();
        if (dateTime == null) {
            dateTime = oldExpense.getDateTime();
        }
        String description = consoleCommandEditExpense.getDescription();
        if (description == null) {
            description = oldExpense.getDescription();
        }
        BigDecimal amount = consoleCommandEditExpense.getAmount();
        if (amount == null) {
            amount = oldExpense.getAmount();
        }
        String category = consoleCommandEditExpense.getCategory();
        if (category == null) {
            category = oldExpense.getCategory();
        }
        String remarks = consoleCommandEditExpense.getRemarks();
        if (remarks == null) {
            remarks = oldExpense.getRemarks();
        }
        String currency = consoleCommandEditExpense.getCurrency();
        if (currency == null) {
            currency = oldExpense.getCurrency();
        } else {
            try {
                currencyManager.hasCurrency(consoleCommandEditExpense.getCurrency());
            } catch (CurrencyInvalidException exception) {
                printErrorMessage(exception.getMessage());
                return;
            }
        }
        currency = currency.toUpperCase();

        Expense newExpense = new Expense(name, dateTime, description, amount, category, remarks, currency);
        try {
            expenseManager.editExpense(expenseIndex, newExpense);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        printInformationalMessage("---- EXPENSE INDEX " + expenseIndex + " ----");
        printInformationalMessage(convertExpenseToConsoleString(newExpense));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_EDIT_EXPENSE_SUCCESS);

        localStorage.saveToFile(expenseManager.getExpenses(), expenseManager.getSortCommandSetting());
    }

    private void runCommandSortExpense(ConsoleCommandSortExpense commandSortExpense) {
        expenseManager.updateSortExpenses(commandSortExpense);
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_SORTED_EXPENSE_SUCCESS);

        localStorage.saveToFile(expenseManager.getExpenses(), expenseManager.getSortCommandSetting());
    }

    private void runCommandConvertCurrency(ConsoleCommandConvertCurrency consoleCommandConvertCurrency) {
        int expenseIndex = consoleCommandConvertCurrency.getExpenseIndex();

        Expense expense;
        try {
            expense = expenseManager.getExpense(expenseIndex);
        } catch (ExpenseManagerExpenseNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        try {
            currencyManager.hasCurrency(consoleCommandConvertCurrency.getCurrency());
        } catch (CurrencyInvalidException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        consoleCommandConvertCurrency.changeCurrency(expense, currencyManager);
        expenseManager.sortExpenses();

        printInformationalMessage(convertExpenseToConsoleString(expense));
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_CONVERT_CURRENCY_SUCCESS);

        localStorage.saveToFile(expenseManager.getExpenses(), expenseManager.getSortCommandSetting());
    }

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

        /*
         TODO Add saveToFile for Target
         localStorage.saveToFile(targetManager.getTargets());
        */
    }

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

    private void viewTarget() {
        ArrayList<Target> targets = targetManager.getTargets();

        if (targets.isEmpty()) {
            printInformationalMessage(Messages.COMMAND_VIEW_TARGET_EMPTY_LIST);
        }

        for (int index = 0; index < targets.size(); index++) {
            Target target = targets.get(index);

            printInformationalMessage("---- TARGET INDEX " + index + " ----");
            printInformationalMessage(convertTargetToConsoleString(target));
        }
    }

    private void runCommandViewTarget(ConsoleCommandViewTarget consoleCommandViewTarget) {
        int targetIndex = consoleCommandViewTarget.getTargetIndex();

        if (targetIndex >= 0) {
            viewTargetByTargetIndex(targetIndex);
        } else {
            viewTarget();
        }
    }

    private void runCommandDeleteTarget(ConsoleCommandDeleteTarget consoleCommandDeleteTarget) {
        int targetIndex = consoleCommandDeleteTarget.getTargetIndex();

        try {
            targetManager.deleteTarget(targetIndex);
        } catch (TargetManagerTargetNotFoundException exception) {
            printErrorMessage(exception.getMessage());
            return;
        }

        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_DELETE_TARGET_SUCCESS);

        // localStorage.saveToFile(targetManager.getTargets());
    }

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

        // localStorage.saveToFile(targetManager.getTargets());
    }

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

        /*
        TODO Add saveToFile for Income
        saveToFile(incomeManager.getIncomes());
         */
    }

    private void runCommandAddRecurringPayment(ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment) {
        RecurringPayment recurringPayment = new RecurringPayment(
                consoleCommandAddRecurringPayment.getName(),
                consoleCommandAddRecurringPayment.getInterval(),
                consoleCommandAddRecurringPayment.getDescription(),
                consoleCommandAddRecurringPayment.getAmount()
        );

        recurringPaymentManager.addRecurringPayment(recurringPayment);

        printInformationalMessage(convertRecurringPaymentToConsoleString(recurringPayment));
        printBlankLine();
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_ADD_RECURRING_PAYMENT_SUCCESS);
    }

    private void viewRecurringPayment() {
        ArrayList<RecurringPayment> recurringPayments = recurringPaymentManager.getRecurringPayments();

        for (int index = 0; index < recurringPayments.size(); index++) {
            RecurringPayment recurringPayment = recurringPayments.get(index);

            String printStr = ""
                    + "---- RECURRING PAYMENT INDEX " + index + " ----\n"
                    + convertRecurringPaymentToConsoleString(recurringPayment);

            printInformationalMessage(printStr);
        }
    }

    private void viewRecurringPaymentByIndex(int recurringPaymentIndex) {
        RecurringPayment recurringPayment = recurringPaymentManager.getRecurringPayment(recurringPaymentIndex);

        String printStr = ""
                + "---- RECURRING PAYMENT INDEX " + recurringPaymentIndex + " ----\n"
                + convertRecurringPaymentToConsoleString(recurringPayment);

        printInformationalMessage(printStr);
    }

    private void runCommandViewRecurringPayment(ConsoleCommandViewRecurringPayment consoleCommandViewRecurringPayment) {
        int recurringExpenseIndex = consoleCommandViewRecurringPayment.getRecurringPaymentIndex();

        if (recurringExpenseIndex >= 0) {
            viewRecurringPaymentByIndex(recurringExpenseIndex);
        } else {
            viewRecurringPayment();
        }
    }

    private ConsoleCommand getConsoleCommand() {
        String consoleInput = getConsoleInput();

        printBlankLine();

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
                 | ConsoleParserCommandAddIncomeInvalidException
                 | ConsoleParserCommandAddRecurringPaymentInvalidException
                 | ConsoleParserCommandViewRecurringPaymentInvalidException exception) {
            printErrorMessage(exception.getMessage());
        }

        return consoleCommand;
    }

    /**
     * Runs the command line interface which the user interacts with.
     */
    public void run() {
        localStorage.loadFromFile(expenseManager);
        CurrencyApi.getCurrencyApi(currencyManager);

        printBlankLine();

        while (true) {
            ConsoleCommand consoleCommand = getConsoleCommand();

            if (consoleCommand instanceof ConsoleCommandBye) {
                runCommandBye((ConsoleCommandBye) consoleCommand);
                return;
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
            } else if (consoleCommand instanceof ConsoleCommandAddRecurringPayment) {
                runCommandAddRecurringPayment((ConsoleCommandAddRecurringPayment) consoleCommand);
            } else if (consoleCommand instanceof ConsoleCommandViewRecurringPayment) {
                runCommandViewRecurringPayment((ConsoleCommandViewRecurringPayment) consoleCommand);
            }

            printBlankLine();
        }
    }
}
