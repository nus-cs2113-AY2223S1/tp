package seedu.duke;

import seedu.duke.command.Command;

import java.io.IOException;


public class Duke {
    private Parser parser;
    private Ui ui;
    private Storage storage;
    private PropertyList propertyList;
    private ClientList clientList;


    public void run() throws IOException {
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage();
        this.propertyList = new PropertyList();
        this.clientList = new ClientList();

        Command command;
        String userInputText = "";

        do {
            userInputText = ui.readCommand();
            command = parser.parseCommand(userInputText);
            command.execute(ui, storage, propertyList, clientList);


        } while (!userInputText.equals("quit"));

    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
