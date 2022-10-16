package seedu.duke;

import seedu.duke.commands.Command;

public class Duke {

    static ModuleList modulelist = new ModuleList();

    public static void main(String[] args) {
        run();
    }

    public static void run() {
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
    }

}
