package seedu.duke;

import seedu.duke.item.Item;
import seedu.duke.transaction.Transaction;
import seedu.duke.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Ui {

    private static final Scanner input = new Scanner(System.in);
    private static Logger logger = Logger.getLogger("Foo");


    public static final String logo =
            "                             _      "
                    + "\n" + " /\\ /\\ _ __   ___ _   _  ___| | ___ \n"
                    + "/ / \\ \\ '_ \\ / __| | | |/ __| |/ _ \\\n"
                    + "\\ \\_/ / |_) | (__| |_| | (__| |  __/\n"
                    + " \\___/| .__/ \\___|\\__, |\\___|_|\\___|\n"
                    + "      |_|         |___/             \n";

    public static final String greeting = "Hello from\n" + logo;

    public static void printGreeting() {
        assert logo != null;
        System.out.print(logo);
        System.out.print(greeting);
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
        System.out.println("Bye!");
        showLine();
    }

    public static String readInput() {
        return input.nextLine();
    }


    public static void addItemMessage(Item item, int listSize) {
        showLine();
        assert listSize >= 0;
        System.out.println("Noted. Following item has been added: " + '\n' + item.getDescription()
                + "\n" + "Total item(s) in list: " + listSize + '\n');
        showLine();
    }

    public static void deleteItemMessage(Item item, int listSize) {
        showLine();
        assert listSize >= 0;
        System.out.println("OK! I will remove the following item:\n" + item.getDescription() + "\n"
                + "Total item(s) in list: " + listSize + '\n');
        showLine();
    }
    public static void addTransactionMessage(Transaction transaction, int listSize) {
        showLine();
        assert listSize >= 0;
        System.out.println("OK! I will add the following transaction:\n" + transaction.toString() + "\n"
                + "Total transaction(s) in list: " + listSize + '\n');
        showLine();
    }
    public static void deleteTransactionMessage(Transaction transaction,int listSize) {
        showLine();
        assert listSize >= 0;
        System.out.println("OK! I will remove the following item:\n" + transaction.getTxId() + "\n"
                + "Total item(s) in list: " + listSize + '\n');
        showLine();
    }
    public static void addUserMessage(User user, int listSize) {
        showLine();
        assert listSize >= 0;
        System.out.println("Noted. Following user has been added: " + '\n' + user.toString()
                + "\n" + "Total user(s) in list: " + listSize + '\n');
        showLine();
    }

    public static void deleteUserMessage(User user, int listSize) {
        showLine();
        assert listSize >= 0;
        System.out.println("Noted. Following user has been deleted: " + '\n' + user.toString()
                + "\n" + "Total user(s) in list: " + listSize + '\n');
        showLine();
    }



    /**
     * display the entire list of items to the user.
     *
     * @param items list of all the items the user has added
     */
    public void showItemList(List<Item> items) {
        int itemNumber = 1;
        showLine();
        System.out.println("Here are your list of item(s):");
        for (Item item : items) {
            System.out.println(itemNumber + "." + item.getName());
            itemNumber++;
        }
        showLine();
    }

    public static void printResponse(String string) {
        System.out.println(string);
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void exit() {
        input.close();
    }
}
