package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TransactionList transactions;
    private Ui ui;

    public Duke() {    // NEED TO ADD FILE PATH
        ui = new Ui();
        transactions = new TransactionList();

        // ideal code after u add all the storage stuff
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
        String inData; // temp
        while (!isExit) {
            try {
                Scanner scan = new Scanner(System.in);
                inData = scan.nextLine();
                inData = inData.trim();

                Command c = Parser.parse(inData);
                c.execute(transactions, ui, storage);
                isExit = c.isExit();
            } catch (MoolahException e) {
                Ui.showErrorMessage(e.getMessage());
            }
            //ideal code
            /***try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // divider line
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getErrorMessage());
            } finally {
                ui.showLine();
            }**/
        }
    }

    public static void main(String[] args) {
        new Duke().run();  // NEED TO ADD FILE PATH
    }
}
