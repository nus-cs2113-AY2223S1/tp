package seedu.duke;
import appointment.AppointmentList;
import command.Command;
import employee.EmployeeList;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private AppointmentList appointmentList;
    private EmployeeList employeeList;

    public Duke() {
        this.appointmentList = new AppointmentList();
        this.employeeList = new EmployeeList();
    }
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * To run the Duke Application
     */

    public void run() {
        greetUser();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = readCommand();
                showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(appointmentList, employeeList);
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
