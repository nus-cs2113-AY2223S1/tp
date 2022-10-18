package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.command.CommandListEverything;

public class ParseListEverything extends Parser {
    @Override
    public Command parseCommand() {
        return new CommandListEverything();
    }
}

