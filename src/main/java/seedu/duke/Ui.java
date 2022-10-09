package seedu.duke;

import seedu.duke.item.Item;
import seedu.duke.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    public static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private final Scanner input;

    /**
     * display the entire list of items to the user.
     * @param items list of all the items the user has added
     */
    public void showList(ArrayList<Item> items) {
        int itemNumber = 1;
        System.out.println(LINE + "Here are your list of tasks:");
        for (Item item : items) {
            System.out.println(itemNumber + "." + item.getName());
            itemNumber++;
        }
        System.out.println(LINE);
    }

    public void showUserList(ArrayList<User> users) {
        int userNumber = 1;
        System.out.println(LINE + "Here are your list of users:");
        for (User user : users) {
            System.out.println(userNumber + " " + user.toString());
            userNumber++;
        }
        System.out.println(LINE);
    }

    public Ui() {
        input = new Scanner(System.in);
    }

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public String greeting = "Hello from\n" + logo;

    public void printGreeting() {
        System.out.print(greeting);
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void addItemMessage(Item item, List<Item> items) {
        System.out.println(
                LINE
                + "Noted. Following task has been added: " + '\n'
                + item.getDescription() + "\n"
                + "Total tasks in list: " + items.size() + '\n'
                + LINE
        );
    }

    public void deleteItemMessage(Item item, List<Item> items) {
        System.out.println(
                LINE
                + "OK! I will remove the following item:\n"
                + item.getDescription() + "\n"
                + "Total items in list: " + items.size() + '\n'
                + LINE
        );
    }

    public static void printExitMessage() {
        showLine();
        System.out.println("Bye!");
        showLine();
    }

    public String readCommand() {
        return input.nextLine();
    }
}
