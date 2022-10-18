package seedu.duke;

import seedu.duke.command.Command;

import seedu.duke.command.CommandBye;
import seedu.duke.exception.DukeException;
import seedu.duke.parsermanager.Parser;
import seedu.duke.parsermanager.ParserManager;

public class Duke {
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
        this.storage = new Storage(clientList, propertyList, pairingList);
    }


    public void run() {

        Command command;
        Parser parser;
        ParserManager parserManager = new ParserManager(clientList, propertyList, pairingList);
        boolean isCommandBye = false;

        ui.showWelcomeMessage();

        do {
            try {
                //System.exit(0); //to pass CI
                String userInputText = ui.readCommand();


                parser = parserManager.parseCommand(userInputText);
                command = parser.parseCommand();
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
    public static void main(String[] args) {
        new Duke().run();
    }
}
