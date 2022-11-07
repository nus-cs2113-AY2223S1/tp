package seedu.commands;

import seedu.data.CarparkList;

/**
 * Represents a command to list all the carparks and their information in the api.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_WORD_SHORT = "l";
    private final CarparkList carparkList;

    /**
     * Constructor for ListCommand.
     *
     * @param carparkList CarparkList that contains carpark related information.
     */
    public ListCommand(CarparkList carparkList) {
        this.carparkList = carparkList;
    }

    /**
     * Executes ListCommand.
     *
     * @return CommandResult of ListCommand.
     */
    @Override
    public CommandResult execute() {
        String result = carparkList.toString().trim();
        return new CommandResult(result, CommandStatus.MESSAGE);
    }
}
