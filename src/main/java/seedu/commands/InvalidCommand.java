package seedu.commands;

/**
 * Represents an invalid command.
 * Upon execution, the user will see a message explaining that the input command is invalid.
 */
public class InvalidCommand extends Command {
    public final String showToUser;

    /**
     * Constructor for InvalidCommand.
     *
     * @param showToUser Message to be displayed.
     */
    public InvalidCommand(String showToUser) {
        this.showToUser = showToUser;
    }

    /**
     * Executes InvalidCommand.
     *
     * @return CommandResult of InvalidCommand.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(showToUser, CommandStatus.FAIL);
    }
}
