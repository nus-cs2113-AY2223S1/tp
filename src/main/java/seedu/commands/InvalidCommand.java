package seedu.commands;

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
