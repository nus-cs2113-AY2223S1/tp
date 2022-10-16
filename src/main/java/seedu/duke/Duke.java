package seedu.duke;
import appointment.AppointmentList;
import command.Command;
import employee.EmployeeList;
import service.ServiceList;
import parser.Parser;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Parser parser = new Parser();

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * To run the Duke Application
     */

    public void run() {
        assert false : "input should be a tree";
        greetUser();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = readCommand();
                showLine();
                Command c = parser.parseCommand(fullCommand);
                c.execute();
                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.displayMessage(e.getErrorMessage());
            } finally {
                showLine();
            }
        }
        endProgram();
    }

    private static void greetUser(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I am the nurse helper!");
    }
    private static String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    private static void showLine(){
        System.out.println("____________________________");
    }

    private static void endProgram(){
        System.out.println("Bye!");
    }
}
