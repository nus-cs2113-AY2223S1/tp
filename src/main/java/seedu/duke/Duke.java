package seedu.duke;

import seedu.duke.command.Command;

import java.util.Scanner;


public class Duke {
    private Parser parser;
    private Ui ui;
    private Storage storage;
    private PropertyList propertyList;
    private ClientList clientList;


    public void run() {
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage();
        this.propertyList = new PropertyList();
        this.clientList = new ClientList();

        Command command;

        do {
            String userInputText = ui.readCommand();
            command = parser.parseCommand(userInputText);
            command.execute(ui, storage, propertyList, clientList);

        } while (!command.equals("bye"));
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
