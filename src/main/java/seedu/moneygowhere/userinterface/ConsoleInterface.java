package seedu.moneygowhere.userinterface;

import seedu.moneygowhere.commands.ConsoleCommand;
import seedu.moneygowhere.commands.ConsoleCommandAddExpense;
import seedu.moneygowhere.commands.ConsoleCommandBye;
import seedu.moneygowhere.commands.ConsoleCommandDeleteExpense;
import seedu.moneygowhere.commands.ConsoleCommandEditExpense;
import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewExpense;
import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.data.expense.ExpenseManager;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandDeleteExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandEditExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandNotFoundException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandSortExpenseInvalidTypeException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandViewExpenseInvalidException;
import seedu.moneygowhere.parser.ConsoleParser;

import static seedu.moneygowhere.storage.LocalStorage.loadFromFile;
import static seedu.moneygowhere.storage.LocalStorage.saveToFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



/**
 * Provide functions to interface with the user via standard input and standard output.
 */
@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class ConsoleInterface {
    private Scanner scanner;
    private ExpenseManager expenseManager;

    /**
     * Initializes the console interface.
     */
    public ConsoleInterface() {
        scanner = new Scanner(System.in);
        expenseManager = new ExpenseManager();
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

    private void runCommandAddExpense(ConsoleCommandAddExpense consoleCommandAddExpense) {
        Expense expense = new Expense(
                consoleCommandAddExpense.getName(),
                consoleCommandAddExpense.getDateTime(),
                consoleCommandAddExpense.getDescription(),
                consoleCommandAddExpense.getAmount(),
                consoleCommandAddExpense.getCategory());
        expenseManager.addExpense(expense);

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(
                Configurations.CONSOLE_INTERFACE_DATE_TIME_OUTPUT_FORMAT
        );
        String expenseStr = "";
        expenseStr += "Name          : " + expense.getName() + "\n";
        expenseStr += "Date and Time : " + expense.getDateTime().format(dateTimeFormat) + "\n";
        expenseStr += "Description   : " + expense.getDescription() + "\n";
        expenseStr += "Amount        : " + expense.getAmount() + "\n";
        expenseStr += "Category      : " + expense.getCategory() + "\n";
        printInformationalMessage(expenseStr);

        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_ADD_EXPENSE_SUCCESS);

        saveToFile(expenseManager.getExpenses());
    }

    private void viewExpense() {
        ArrayList<Expense> expenses = expenseManager.getExpenses();

        for (int index = 0; index < expenses.size(); index++) {
            Expense expense = expenses.get(index);

            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(
                    Configurations.CONSOLE_INTERFACE_DATE_TIME_OUTPUT_FORMAT
            );
            String expenseStr = "";
            expenseStr += "---- EXPENSE INDEX " + index + " ----\n";
            expenseStr += "Name          : " + expense.getName() + "\n";
            expenseStr += "Date and Time : " + expense.getDateTime().format(dateTimeFormat) + "\n";
            expenseStr += "Description   : " + expense.getDescription() + "\n";
            expenseStr += "Amount        : " + expense.getAmount() + "\n";
            expenseStr += "Category      : " + expense.getCategory();
            printInformationalMessage(expenseStr);
        }
    }

    private void viewExpenseByExpenseIndex(int expenseIndex) {
        Expense expense = expenseManager.getExpense(expenseIndex);

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(
                Configurations.CONSOLE_INTERFACE_DATE_TIME_OUTPUT_FORMAT
        );
        String expenseStr = "";
        expenseStr += "---- EXPENSE INDEX " + expenseIndex + " ----\n";
        expenseStr += "Name          : " + expense.getName() + "\n";
        expenseStr += "Date and Time : " + expense.getDateTime().format(dateTimeFormat) + "\n";
        expenseStr += "Description   : " + expense.getDescription() + "\n";
        expenseStr += "Amount        : " + expense.getAmount() + "\n";
        expenseStr += "Category      : " + expense.getCategory();
        System.out.println(expenseStr);
    }

    private void runCommandViewExpense(ConsoleCommandViewExpense consoleCommandViewExpense) {
        int expenseIndex = consoleCommandViewExpense.getExpenseIndex();

        if (expenseIndex >= 0) {
            viewExpenseByExpenseIndex(expenseIndex);
        } else {
            viewExpense();
        }
    }

    private void runCommandDeleteExpense(ConsoleCommandDeleteExpense consoleCommandDeleteExpense) {
        int expenseIndex = consoleCommandDeleteExpense.getExpenseIndex();
        expenseManager.deleteExpense(expenseIndex);

        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_DELETE_EXPENSE_SUCCESS);

        saveToFile(expenseManager.getExpenses());
    }

    private void runCommandEditExpense(ConsoleCommandEditExpense consoleCommandEditExpense) {
        int expenseIndex = consoleCommandEditExpense.getExpenseIndex();

        Expense oldExpense = expenseManager.getExpense(expenseIndex);

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

        Expense newExpense = new Expense(name, dateTime, description, amount, category);
        expenseManager.editExpense(expenseIndex, newExpense);

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(
                Configurations.CONSOLE_INTERFACE_DATE_TIME_OUTPUT_FORMAT
        );
        String expenseStr = "";
        expenseStr += "---- EXPENSE INDEX " + expenseIndex + " ----\n";
        expenseStr += "Name          : " + newExpense.getName() + "\n";
        expenseStr += "Date and Time : " + newExpense.getDateTime().format(dateTimeFormat) + "\n";
        expenseStr += "Description   : " + newExpense.getDescription() + "\n";
        expenseStr += "Amount        : " + newExpense.getAmount() + "\n";
        expenseStr += "Category      : " + newExpense.getCategory() + "\n";
        printInformationalMessage(expenseStr);
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_EDIT_EXPENSE_SUCCESS);

        saveToFile(expenseManager.getExpenses());
    }

    private void runCommandSortExpense(ConsoleCommandSortExpense commandSortExpense) {
        String type = commandSortExpense.getType();
        ArrayList<Expense> expenses = expenseManager.getExpenses();
        if (type.equalsIgnoreCase(ConsoleParser.CONSOLE_COMMAND_SORT_EXPENSE_TYPE_DATE)) {
            Collections.sort(expenses, commandSortExpense.sortByDate);
        } else if (type.equalsIgnoreCase(ConsoleParser.CONSOLE_COMMAND_SORT_EXPENSE_TYPE_ALPHABETICAL)) {
            Collections.sort(expenses, commandSortExpense.sortByAlphabet);
        } else if (type.equalsIgnoreCase(ConsoleParser.CONSOLE_COMMAND_SORT_EXPENSE_TYPE_AMOUNT)) {
            Collections.sort(expenses, commandSortExpense.sortByAmount);
        }
        expenseManager.updateExpenses(expenses);
        printInformationalMessage(Messages.CONSOLE_MESSAGE_COMMAND_SORTED_EXPENSE_SUCCESS);
    }

    /**
     * Runs the command line interface which the user interacts with.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    public void run() {
        loadFromFile(expenseManager);
        printBlankLine();

        while (true) {
            String consoleInput = getConsoleInput();
            printBlankLine();

            ConsoleCommand consoleCommand = null;
            boolean hasParseError = true;

            // Parse input command and arguments into a ConsoleCommand object
            try {
                consoleCommand = ConsoleParser.parse(consoleInput);
                hasParseError = false;
            } catch (ConsoleParserCommandNotFoundException
                     | ConsoleParserCommandAddExpenseInvalidException
                     | ConsoleParserCommandViewExpenseInvalidException
                     | ConsoleParserCommandDeleteExpenseInvalidException
                     | ConsoleParserCommandEditExpenseInvalidException
                     | ConsoleParserCommandSortExpenseInvalidTypeException exception) {
                printErrorMessage(exception.getMessage());
            }
            // Execute function according to the ConsoleCommand object returned by the parser
            if (hasParseError) {
                // Do nothing if there is a parse error
            } else if (consoleCommand instanceof ConsoleCommandBye) {
                // Terminate the program
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
            } else {
                // Do nothing if the command is not found
            }

            printBlankLine();
        }
    }
}
