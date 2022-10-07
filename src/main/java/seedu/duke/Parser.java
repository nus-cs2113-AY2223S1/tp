package seedu.duke;

import seedu.duke.Command.CommandAdd;

public class Parser {
    public CommandAdd parseCommand(String input) {
        final int LIMIT = 2;
        String[] commands = input.trim().split(" ", LIMIT);

        // This is the type of command that will be executed
        String commandType = commands[0];

        switch (commandType) {
        default:
            return new CommandAdd(commands);
        }
    }
}
