package seedu.duke.ui;

import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.User;

import java.util.ArrayList;
import java.util.Scanner;

// @@author jorellesee
public class Ui {
    private static final Scanner input = new Scanner(System.in);

    public static final String logo =
            "                             _      \n" + " /\\ /\\ _ __   ___ _   _  ___| | ___ \n"
                    + "/ / \\ \\ '_ \\ / __| | | |/ __| |/ _ \\\n"
                    + "\\ \\_/ / |_) | (__| |_| | (__| |  __/\n"
                    + " \\___/| .__/ \\___|\\__, |\\___|_|\\___|\n"
                    + "      |_|         |___/             \n";

    public static final String greeting = "Hello from\n" + logo;
    public static final String CALL_TO_ACTION =
            "To get started, type \"help\" to see the list of available commands\n";

    /**
     * Prints a greeting.
     */
    public static void printGreeting() {
        assert logo != null;
        showLine();
        System.out.print(greeting);
        printQuestion();
        System.out.print(CALL_TO_ACTION);
        showLine();
    }

    public static final String question = "What would you like to do?\n";

    public static void printQuestion() {
        assert question != null;
        System.out.print(question);
    }

    /**
     * Shows a line.
     */
    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an exit message.
     */
    public static void printExitMessage() {
        showLine();
        System.out.println("Bye! See you again");
        showLine();
    }

    /**
     * Reads a line of input from user.
     * 
     * @return A String of user input
     */
    public static String readInput() {
        return input.nextLine();
    }


    /**
     * Prints message when an item is added to item list.
     * 
     * @param item item that is added
     * @param itemListSize size of current item list
     * @param transactionList list containing all transactions
     */
    public static void addItemMessage(Item item, int itemListSize,
            TransactionList transactionList) {
        showLine();
        assert itemListSize >= 0;
        System.out.print(
                "Noted. Following item has been added: " + '\n' + item.toString(transactionList)
                        + "\n" + "Total item(s) in database: " + itemListSize + '\n');
        showLine();
    }

    /**
     * Prints message when item is viewed.
     * 
     * @param item item that is wanted by user
     * @param transactionList list containing all transactions
     */
    public static void viewItemMessage(Item item, TransactionList transactionList) {
        showLine();
        System.out.print(
                "Here is the item you requested: " + '\n' + item.toString(transactionList) + "\n");
        showLine();
    }

    /**
     * Prints message when an item is being updated.
     * 
     * @param item item that is updated
     * @param transactionList list containing all transactions
     */
    public static void updateItemMessage(Item item, TransactionList transactionList) {
        showLine();
        System.out.print("Done! Here is the item you updated" + '\n'
                + item.toString(transactionList) + '\n');
        showLine();
    }

    /**
     * Prints a message when an item is deleted.
     * 
     * @param item item being deleted
     * @param itemListSize current number of items
     * @param transactionList list containing all transactions
     */
    public static void deleteItemMessage(Item item, int itemListSize,
            TransactionList transactionList) {
        showLine();
        assert itemListSize >= 0;
        System.out.print("OK! I will remove the following item:\n" + item.toString(transactionList)
                + "\n" + "Total item(s) in database: " + itemListSize + '\n');
        showLine();
    }

    /**
     * Prints a message when a transaction is added.
     * 
     * @param transaction transaction that is added
     * @param transactionListSize current number of all transactions
     */
    public static void addTransactionMessage(Transaction transaction, int transactionListSize) {
        showLine();
        assert transactionListSize >= 0;
        System.out.print("OK! I will add the following transaction:\n" + transaction.toString()
                + "\n" + "Total transaction(s) in database: " + transactionListSize + '\n');
        showLine();
    }

    /**
     * Prints a message when a transaction is viewed.
     * 
     * @param transaction transaction that is being viewed
     */
    public static void viewTransactionMessage(Transaction transaction) {
        showLine();
        System.out.print(
                "Here is the transaction you requested to view: " + '\n' + transaction + "\n");
        showLine();
    }

    /**
     * Prints a message when viewing completed transactions.
     * 
     * @param transactions list containing all transactions
     */
    public static void viewCompletedTransactionsMessage(ArrayList<Transaction> transactions) {
        showLine();
        System.out.print(transactions.size() == 0 ? "There is no completed transaction\n"
                : "Here are the completed transactions: \n");
        for (Transaction transaction : transactions) {
            System.out.print(transaction + "\n");
        }
        showLine();
    }

    /**
     * Prints a message when viewing uncompleted transactions.
     * 
     * @param transactions list containing all transactions
     */
    public static void viewUncompletedTransactionsMessage(ArrayList<Transaction> transactions) {
        showLine();
        System.out.print(transactions.size() == 0 ? "There is no uncompleted transaction\n"
                : "Here are the uncompleted transactions: \n");
        for (Transaction transaction : transactions) {
            System.out.print(transaction + "\n");
        }
        showLine();
    }

    /**
     * Prints a message when viewing all transactions of a user.
     * 
     * @param transactions list containing all transactions
     */
    public static void viewUserTransactionsMessage(ArrayList<Transaction> transactions) {
        showLine();
        System.out.print(transactions.size() == 0 ? "This user has no transactions\n"
                : "Here are this user's transactions: \n");
        for (Transaction transaction : transactions) {
            System.out.print(transaction + "\n");
        }
        showLine();
    }

    /**
     * Prints a message when a transaction is updated.
     * 
     * @param transaction transaction that is updated
     */
    public static void updateTransactionMessage(Transaction transaction) {
        showLine();
        System.out
                .print("Done! Here is the updated transaction:\n" + transaction.toString() + "\n");
        showLine();
    }

    /**
     * Prints a message when a transaction is deleted.
     *
     * @param transaction transaction that is deleted
     * @param transactionListSize current number of all transactions
     */
    public static void deleteTransactionMessage(Transaction transaction, int transactionListSize) {
        showLine();
        assert transactionListSize >= 0;
        System.out.print("OK! I will remove the following item:\n" + transaction.toString() + "\n"
                + "Total transactions(s) in database: " + transactionListSize + '\n');
        showLine();
    }

    /**
     * Prints a message when a user is viewed.
     * 
     * @param user user being viewed
     * @param userItems list of all items of user
     * @param transactionList list of all transactions
     * @param loss total loss of a user
     * @param gain total gain of a user
     */
    public static void viewUserMessage(User user, ItemList userItems,
            TransactionList transactionList, Double loss, Double gain) {
        showLine();
        System.out.print("Here is the user you have requested to view: " + '\n' + user + "\n");
        System.out.print("The user's gain is $" + String.format("%.2f", gain) + '\n');
        System.out.print("The user's loss is $" + String.format("%.2f", loss) + '\n');
        System.out.print(userItems.toString(transactionList) + '\n');
        showLine();
    }

    /**
     * Prints a message when a user is added.
     * 
     * @param user user that is added
     * @param userListSize current number of all users
     */
    public static void addUserMessage(User user, int userListSize) {
        showLine();
        assert userListSize >= 0;
        System.out.print("Noted. Following user has been added: " + '\n' + user.toString() + "\n"
                + "Total user(s) in database: " + userListSize + '\n');
        showLine();
    }

    /**
     * Prints a message when a user is deleted.
     * 
     * @param user user being deleted
     * @param userListSize current number of all users
     */
    public static void deleteUserMessage(User user, int userListSize) {
        showLine();
        assert userListSize >= 0;
        System.out.print("Noted. Following user has been deleted: " + '\n' + user.toString() + "\n"
                + "Total user(s) in database: " + userListSize + '\n');
        showLine();
    }

    /**
     * Prints a generic response.
     * 
     * @param string response to be printed
     */
    public static void printResponse(String string) {
        showLine();
        System.out.println(string);
        showLine();
    }

    /**
     * Prints a generic error response.
     * 
     * @param message error message
     */
    public static void printErrorMessage(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }
}
