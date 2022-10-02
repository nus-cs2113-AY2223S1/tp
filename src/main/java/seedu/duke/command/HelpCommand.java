package seedu.duke.command;

import seedu.duke.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    @Override
    public void execute(Ui ui) {
        ui.output("Help will always be given at Hogwarts to those who ask for it\n"
                + "Available commands:\n"
                + "help - to display the help message\n"
                + "exit - to exit the program");
    }
}
