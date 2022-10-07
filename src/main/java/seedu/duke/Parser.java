package seedu.duke;

import seedu.duke.command.CommandAdd;

public class Parser {

    public CommandAdd parseCommand(String input) {

        String[] commands = input.trim().split(" ", 2);

        // This is the type of command that will be executed
        String commandType = commands[0];

        switch (commandType) {
        default:
            return new CommandAdd(commands);
        }
    }
}
