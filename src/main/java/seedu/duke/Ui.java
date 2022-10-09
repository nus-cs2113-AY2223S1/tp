package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.item.Item;
import seedu.duke.transaction.Transaction;
import seedu.duke.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Ui {

    private final Scanner input;

    public Ui() {
        input = new Scanner(System.in);
    }

    public final String logo =
            "                             _      \n"
                    + " /\\ /\\ _ __   ___ _   _  ___| | ___ \n"
                    + "/ / \\ \\ '_ \\ / __| | | |/ __| |/ _ \\\n"
                    + "\\ \\_/ / |_) | (__| |_| | (__| |  __/\n"
                    + " \\___/| .__/ \\___|\\__, |\\___|_|\\___|\n"
                    + "      |_|         |___/             ";

    public String greeting = "Hello from\n" + logo;

    public void printGreeting() {
        System.out.print(greeting);
    }

    public final String question = "What would you like to do?\n";

    public void printQuestion() {
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

    public String readCommand() {
        return input.nextLine();
    }


    public void addItemMessage(Item item, List<Item> items) {
        showLine();
        System.out.println(
                "Noted. Following item has been added: " + '\n'
                        + item.getDescription() + "\n"
                        + "Total item(s) in list: " + items.size() + '\n'
        );
        showLine();
    }

    public void deleteItemMessage(Item item, List<Item> items) {
        showLine();
        System.out.println(
                "OK! I will remove the following item:\n"
                        + item.getDescription() + "\n"
                        + "Total item(s) in list: " + items.size() + '\n'
        );
        showLine();
    }

    public void deleteTransactionMessage(Transaction transaction, ArrayList<Transaction> transactionList) {
        showLine();
        System.out.println(
                "OK! I will remove the following item:\n"
                        + transaction.getTxId() + "\n"
                        + "Total item(s) in list: " + transactionList.size() + '\n'
        );
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

    public void printResponse(String string) {
        System.out.println(string);
    }

    public void printErrorMessage(DukeException e) {
        System.out.println(e.getMessage());
    }

}
