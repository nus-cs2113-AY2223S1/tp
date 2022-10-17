package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandUndefined;

public class ParseUndefined extends Parser {
    public ParseUndefined() {

    }

    @Override
    public Command parseCommand() {
        return new CommandUndefined();
    }
}
