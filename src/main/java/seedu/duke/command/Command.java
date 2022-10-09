package seedu.duke.command;

import seedu.duke.biometrics.Biometrics;
import seedu.duke.Ui;

public abstract class Command {
    public Command() {
    }

    public abstract void execute();

    public abstract void setData(Ui ui, Biometrics biometrics);
}
