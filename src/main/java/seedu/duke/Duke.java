package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;
import seedu.duke.parser.CommandParser;

public class Duke {
    private Storage storage;
    private TransactionList transactions;
    private Ui ui;

    public Duke() { // TODO: Add a file path when implementing storage feature
        ui = new Ui();
        transactions = new TransactionList();

        // TODO: Ideal code after adding the storage feature
        /**storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }**/
    }

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
