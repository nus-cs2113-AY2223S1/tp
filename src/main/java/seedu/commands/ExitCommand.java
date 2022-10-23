package seedu.commands;

/**
 * Terminates the program
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT = "Exiting programme.";

    /**
     * To tell if the program is to be terminated or not
     *
     * @param command user input command
     * @return true if user wishes to exit, else false;
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    /**
     * Executes ExitCommand
     *
     * @return the command result of ExitCommand
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT);
    }
}
