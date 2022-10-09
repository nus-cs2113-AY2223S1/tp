package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.biometrics.Biometrics;

public class InvalidCommand extends Command {

    private Ui ui;

    @Override
    public void execute() {
        ui.output("Invalid command, enter help to view available commands");
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics) {
        this.ui = ui;
    }
}
