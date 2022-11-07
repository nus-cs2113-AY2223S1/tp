package seedu.commands;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String COMMAND_WORD_SHORT = "e";
    public static final String MESSAGE_EXIT = "Exiting program.";

    /**
     * To tell if the program is to be terminated or not.
     *
     * @param command user input command.
     * @return True if user wishes to exit, else false.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    /**
     * Executes ExitCommand.
     *
     * @return Command result of ExitCommand.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT, CommandStatus.SUCCESS);
    }
}
