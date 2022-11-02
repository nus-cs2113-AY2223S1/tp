package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandQuit;
import seedu.duke.exception.DukeException;
import seedu.duke.parsermanager.Parser;
import seedu.duke.parsermanager.ParserManager;

import java.io.FileNotFoundException;

//@@author wilsonngja
public class Duke {

    private Ui ui;
    private Storage storage;
    private PropertyList propertyList;
    private ClientList clientList;
    private PairingList pairingList;


    public Duke() throws FileNotFoundException {
        this.ui = new Ui();
        this.propertyList = new PropertyList();
        this.clientList = new ClientList();
        this.pairingList = new PairingList();

        this.storage = new Storage(clientList, propertyList, pairingList);


    }


    public void run() {

        Command command;
        Parser parser;
        ParserManager parserManager = new ParserManager(clientList, propertyList, pairingList);
        boolean isCommandQuit = false;

        ui.showWelcomeMessage();

        do {
            try {
                String userInputText = ui.readCommand();
                parser = parserManager.parseCommand(userInputText);
                command = parser.parseCommand();
                command.execute(ui, storage, propertyList, clientList, pairingList);
                isCommandQuit = (command instanceof CommandQuit);

            } catch (DukeException e) {
                // For issue #174
                // Added some newline before and after exception message (Can try out to see what works best)
                // Not done for Invalid Command Message
                ui.printNewline();
                ui.showExceptionMessage(e);
                ui.printNewline();
            }
        } while (!isCommandQuit);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws DukeException, FileNotFoundException {
        new Duke().run();
    }
}
//@@author
