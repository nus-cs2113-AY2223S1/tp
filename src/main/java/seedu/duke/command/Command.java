package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.ClientList;
import seedu.duke.PropertyList;
import seedu.duke.Storage;


public abstract class Command {
    public abstract void execute(Ui ui, Storage storage, PropertyList propertyList, ClientList clientList);
}
