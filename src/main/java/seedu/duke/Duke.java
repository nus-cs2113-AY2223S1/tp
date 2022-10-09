package seedu.duke;

import java.util.Scanner;
import seedu.duke.command.Command;
import seedu.duke.model.ModuleLoader;
import seedu.duke.parser.Parser;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.utils.State;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static Ui ui;
    private static Storage storage;
    private static State state;

    public Duke() {
        this.state = new State();
        this.ui = new Ui();
        this.storage = new Storage();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String fullCommand = ui.readCommand();
        do{
            Command keyword = Parser.parse(fullCommand);
            keyword.execute(state, ui, storage);
            fullCommand = ui.readCommand();
        } while (!fullCommand.equals("bye"));

    }
}
