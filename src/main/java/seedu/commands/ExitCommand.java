package seedu.commands;

/**
 * Terminates the program
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT = "Exiting programme.";

    public static boolean isExit (Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT);
    }
}
