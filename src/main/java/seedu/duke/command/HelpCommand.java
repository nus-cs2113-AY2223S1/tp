package seedu.duke.command;

import seedu.duke.Biometrics;
import seedu.duke.Ui;

public class HelpCommand extends Command {

    private  Ui ui;

    public HelpCommand() {
    }

    @Override
    public void execute() {
        ui.output("Help will always be given at Hogwarts to those who ask for it\n"
                + "Available commands:\n"
                + "help - to display the help message\n"
                + "exit - to exit the program");
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics) {
        this.ui = ui;
    }
}
