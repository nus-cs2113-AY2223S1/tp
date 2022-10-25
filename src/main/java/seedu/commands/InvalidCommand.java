package seedu.commands;

/**
 * Represents an invalid command.
 * Upon execution, the user will see a message explaining that the input command is invalid.
 */
public class InvalidCommand extends Command {

    public final String showToUser;
    public InvalidCommand(String showToUser) {
        this.showToUser = showToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(showToUser);
    }
}
