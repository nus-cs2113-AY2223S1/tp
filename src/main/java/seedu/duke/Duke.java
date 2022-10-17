package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandBye;

import seedu.duke.exception.DukeException;

import java.io.IOException;

public class Duke {
    private Parser parser;
    private Ui ui;
    private Storage storage;
    private PropertyList propertyList;
    private ClientList clientList;
    private PairingList pairingList;

    public Duke() {
        this.ui = new Ui();
        this.propertyList = new PropertyList();
        this.clientList = new ClientList();
        this.pairingList = new PairingList();
        this.parser = new Parser(clientList, propertyList, pairingList);
        this.storage = new Storage(clientList, propertyList, pairingList);
    }


    public void run() throws IOException {

        Command command;
        boolean isCommandBye = false;

        ui.showWelcomeMessage();

        do {
            try {
                //System.exit(0); //to pass CI
                String userInputText = ui.readCommand();
                command = parser.parseCommand(userInputText);
                command.execute(ui, storage, propertyList, clientList, pairingList);
                isCommandBye = (command instanceof CommandBye);
            } catch (DukeException e) {
                ui.showExceptionMessage(e);
            }
        } while (!isCommandBye);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
