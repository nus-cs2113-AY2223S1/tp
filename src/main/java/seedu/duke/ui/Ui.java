package seedu.duke.ui;

import seedu.duke.item.Item;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.User;

import java.util.Scanner;

public class Ui {
    private static final Scanner input = new Scanner(System.in);

    public static final String logo =
            "                             _      \n"
                    + " /\\ /\\ _ __   ___ _   _  ___| | ___ \n"
                    + "/ / \\ \\ '_ \\ / __| | | |/ __| |/ _ \\\n"
                    + "\\ \\_/ / |_) | (__| |_| | (__| |  __/\n"
                    + " \\___/| .__/ \\___|\\__, |\\___|_|\\___|\n"
                    + "      |_|         |___/             \n";

    public static final String greeting = "Hello from\n" + logo;

    public static void printGreeting() {
        assert logo != null;
        showLine();
        System.out.print(greeting);
        printQuestion();
        showLine();
    }

    public static final String question = "What would you like to do?\n";

    public static void printQuestion() {
        assert question != null;
        System.out.print(question);
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printExitMessage() {
        showLine();
        System.out.println("Bye! See you again");
        showLine();
    }

    public static String readInput() {
        return input.nextLine();
    }


    public static void addItemMessage(Item item, int itemListSize, TransactionList transactionList) {
        showLine();
        assert itemListSize >= 0;
        System.out.print("Noted. Following item has been added: " + '\n' + item.toString(transactionList)
                + "\n" + "Total item(s) in list: " + itemListSize + '\n');
        showLine();
    }

    public static void viewItemMessage(Item item, TransactionList transactionList) {
        showLine();
        System.out.print("Here is the item you requested: " + '\n'
                + item.toString(transactionList) + "\n");
        showLine();
    }

    public static void deleteItemMessage(Item item, int itemListSize) {
        showLine();
        assert itemListSize >= 0;
        System.out.print("OK! I will remove the following item:\n" + item + "\n"
                + "Total item(s) in list: " + itemListSize + '\n');
        showLine();
    }

    public static void addTransactionMessage(Transaction transaction, int transactionListSize) {
        showLine();
        assert transactionListSize >= 0;
        System.out.print("OK! I will add the following transaction:\n" + transaction.toString() + "\n"
                + "Total transaction(s) in list: " + transactionListSize + '\n');
        showLine();
    }

    public static void viewTransactionMessage(Transaction transaction) {
        showLine();
        System.out.print("Here is the transaction you requested to view: " + '\n'
                + transaction + "\n");
        showLine();
    }

    public static void deleteTransactionMessage(Transaction transaction, int transactionListSize) {
        showLine();
        assert transactionListSize >= 0;
        System.out.print("OK! I will remove the following item:\n" + transaction.toString() + "\n"
                + "Total item(s) in list: " + transactionListSize + '\n');
        showLine();
    }

    public static void viewUserMessage(User user) {
        showLine();
        System.out.print("Here is the user you have requested to view: " + '\n'
                + user + "\n");
        showLine();
    }
    
    public static void addUserMessage(User user, int userListSize) {
        showLine();
        assert userListSize >= 0;
        System.out.print("Noted. Following user has been added: " + '\n' + user.toString()
                + "\n" + "Total user(s) in list: " + userListSize + '\n');
        showLine();
    }

    public static void deleteUserMessage(User user, int userListSize) {
        showLine();
        assert userListSize >= 0;
        System.out.print("Noted. Following user has been deleted: " + '\n' + user.toString()
                + "\n" + "Total user(s) in list: " + userListSize + '\n');
        showLine();
    }

    public static void printResponse(String string) {
        showLine();
        System.out.println(string);
        showLine();
    }

    public static void printErrorMessage(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }
}
