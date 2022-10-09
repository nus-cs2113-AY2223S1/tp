package seedu.duke;

import seedu.duke.item.ItemList;
import seedu.duke.transaction.TransactionList;

import java.util.Scanner;

public class Duke {
    private final Ui ui;
    private final ItemList itemList;
    private final TransactionList transactionList;
    public static final String BYE = "bye";
    public static final int TASK = 0;

    private Duke(ItemList itemList, TransactionList transactionList) {
        this.itemList = itemList;
        this.transactionList = transactionList;
        ui = new Ui();
        ui.printGreeting();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
