package seedu.commands;

import seedu.data.CarparkList;

/**
 * Represents a command to list all the carparks and their information in the api.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_WORD_SHORT = "l";

    private final CarparkList carparkList;

    public ListCommand(CarparkList carparkList) {
        this.carparkList = carparkList;
    }
    @Override
    public CommandResult execute() {
        String result = carparkList.toString().trim();
        return new CommandResult(result, CommandStatus.MESSAGE);
    }
}
