package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exceptions.YamomException;
import seedu.duke.parser.Parser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.io.IOException;

public class Duke {

    private static Storage storage;
    private static Ui ui;
    private static State state;
    private static String filePath = ""; // place holder for now, wait till implementation of storage

    private static final String IO_ERROR_MESSAGE = "File not found sorry.";

    public Duke(String filePath) {
        ui = new Ui();
        state = new State();
        storage = new Storage();
        storage.openPreviousState(state, ui);
    }

    /**
     * Running the YAMOM programme.
     * Main programme flow of YAMOM.
     */
    public void run() {
        startSequence();
        boolean isExit = false;

        while (!isExit) {
            try {
                ui.displayUserPrompt(state.getSemester());
                String userFullCommand = ui.readNext();
                ui.displayDivider();
                ui.addMessage("Processing \"" + userFullCommand + "\" ...\n");
                Command command = Parser.parse(userFullCommand);
                command.execute(state, ui, storage);
                isExit = command.isExit();
                storage.saveState(state, ui, isExit);
            } catch (IOException e) {
                ui.addMessage(IO_ERROR_MESSAGE);
                ui.displayUi();
            } catch (YamomException e) {
                ui.addMessage(e.getMessage());
                ui.displayUi();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ui.displayDivider();
            }
        }
        endSequence();
    }

    public void startSequence() {
        ui.startMessage();
    }

    public void endSequence() {
        ui.endMessage();
        ui.close();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        new Duke(filePath).run();
    }
}