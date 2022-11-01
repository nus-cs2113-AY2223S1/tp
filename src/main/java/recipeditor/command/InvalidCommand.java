package recipeditor.command;


public class InvalidCommand extends Command {
    public static final String COMMAND_TYPE = "/invalid";
    public static String INVALID_MESSAGE = "This is not a valid command! " + HelpCommand.CORRECT_FORMAT + " to view the correct syntax." + HelpCommand.HELP_MESSAGE;
    private final String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    public InvalidCommand() {
        this.message = INVALID_MESSAGE;
    }

    /**
     * Execute invalid result, which only returns a message on invalid command details.
     *
     * @return CommandResult message on invalid command details
     */
    public CommandResult execute() {
        return new CommandResult(message);
    }

}
