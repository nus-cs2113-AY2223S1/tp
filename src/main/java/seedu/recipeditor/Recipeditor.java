package seedu.recipeditor;

import seedu.recipeditor.ui.Ui;

public class Recipeditor {
    /**
     * Main entry point
     */


    public static void main(String[] args) {
        Ui.printGreeting();
        while (true) {
            System.out.println("Please write your command below");
            String name = Ui.readInput();
            if (name.equals("")) {
                break;
            }
            System.out.println("Hello " + name);
        }
    }
}
