package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public abstract class Command {

    public abstract void execute(State state, Ui ui, Storage storage);

    public abstract boolean isExit();
}
