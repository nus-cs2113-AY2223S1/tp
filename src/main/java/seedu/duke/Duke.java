package seedu.duke;
import command.Command;
import exception.DukeException;
import parser.Parser;
import ui.Ui;


public class Duke {
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * To run the Duke Application
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parseCommand(fullCommand);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        endProgram();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }


    private static void endProgram(){
        System.out.println("Bye!");
    }
}
