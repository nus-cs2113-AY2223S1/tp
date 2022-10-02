package seedu.duke.command;

import seedu.duke.Ui;

public abstract class Command {
    public Command() {
    }

    public abstract void execute(Ui ui);
}
