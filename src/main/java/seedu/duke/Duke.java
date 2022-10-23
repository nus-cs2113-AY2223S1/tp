package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;
import seedu.duke.parser.CommandParser;

import java.io.IOException;

public class Duke {
    //@@author paullowse
    private Storage storage;
    private TransactionList transactions;
    private Ui ui;

    //@@author chinhan99
    public Duke() { // TODO: Add a file path when implementing storage feature
        ui = new Ui();

        storage = new Storage();
        try {
            transactions = new TransactionList(storage.initializeFile());
        } catch (MoolahException e) {
            Ui.showErrorMessage(e.getMessage());
            transactions = new TransactionList();
        }
    }

    //@@author paullowse
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = CommandParser.parse(fullCommand);
                command.execute(transactions, ui, storage);
                isExit = command.isExit();
            } catch (MoolahException e) {
                Ui.showErrorMessage(e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        new Duke().run(); // TODO: Add a file path when implementing storage feature
    }
}
