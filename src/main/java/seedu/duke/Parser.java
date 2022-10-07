package seedu.duke;

import seedu.duke.command.CommandAdd;

public class Parser {
    final int LIMIT = 2;
    
    public CommandAdd parseCommand(String input) {

        String[] commands = input.trim().split(" ", LIMIT);

        // This is the type of command that will be executed
        String commandType = commands[0];

        switch (commandType) {
        default:
            return new CommandAdd(commands);
        }
    }
}
