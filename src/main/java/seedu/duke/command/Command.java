package seedu.duke.command;

import seedu.duke.*;

abstract public class Command {
    public abstract void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList);
}
