package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

import java.io.FileNotFoundException;

public class Duke {

    static ModuleList modulelist;

    static String filePath = "data.txt";

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Storage storage = new Storage(filePath);
        try {
            modulelist = new ModuleList(storage.load());
        } catch (FileNotFoundException | InvalidInputFormatException | InvalidInputContentException e) {
            UI.fileLoadingErrorMessage();
            modulelist = new ModuleList();
        }
        UI.helloMessage();
        boolean isExit = false;
        while (!isExit) {
            String input = UI.getInput();
            UI.printSeparationLine();
            Command c = Parser.parse(input);
            if (c != null) {
                c.execute(modulelist);
                isExit = c.checkExit();
            }
            UI.printSeparationLine();
        }
        storage.save(modulelist);
    }

}
