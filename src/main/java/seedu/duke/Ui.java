package seedu.duke;

import java.util.List;

public class Ui {
    public static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    /**
     * display the entire list of items to the user
     * @param items list of all the items the user has added
     */
    public void showList(List<Item> items) {
        int itemNumber = 1;
        System.out.println(LINE + "Here are your list of tasks:");
        for (Item item : items) {
            System.out.println(itemNumber + "." + item.name);
            itemNumber++;
        }
        System.out.println(LINE);
    }
}
