package seedu.duke;

import java.util.Scanner;

import seedu.duke.command.Database;
import seedu.duke.command.Parser;
import seedu.duke.command.Storage;
import seedu.duke.module.Module;
import seedu.duke.ui.Ui;
import seedu.duke.university.University;

public class Duke {
    private static Ui ui;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ui.greetUser();
        ui.printCommands();
        // Storage.loadDatabase();
        // System.out.println(Database.getUniversities());
        // System.out.println(Database.getpartnerUniversityModules());
        // System.out.println(Database.getnusModules());

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}