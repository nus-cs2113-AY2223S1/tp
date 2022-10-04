package seedu.duke.command;

import seedu.duke.Biometrics;
import seedu.duke.Duke;
import seedu.duke.Ui;

public class ExitCommand extends Command {
    private Ui ui;
    @Override
    public void execute() {
        ui.output("Farewell!");
        Duke.isProgramFinished = true;
    }

    @Override
    public void setData(Ui ui, Biometrics biometrics) {
        this.ui = ui;
    }

}
