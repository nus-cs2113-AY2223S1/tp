package seedu.duke.command;

import seedu.duke.Ui;

public class GreetCommand extends Command {

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    @Override
    public void execute(Ui ui) {
        System.out.println("Hello from\n" + logo);
        System.out.println("Howdy!");
    }

}
