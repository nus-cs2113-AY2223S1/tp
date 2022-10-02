package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Ui ui;
    public static boolean isProgramFinished = false;

    public Duke() {
        ui = new Ui();
    }
    private static void startDuke() {
        new Duke();
        ui.line();
    }
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
        startDuke();

        while (!isProgramFinished) {
                String input = ui.input();
                ui.line();
                Command command = Parser.parse(input);
                command.execute(ui);
                ui.line();
        }
    }
}
