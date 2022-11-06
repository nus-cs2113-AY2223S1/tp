package recipeditor.command;


public class InvalidCommand extends Command {
    public static final String COMMAND_TYPE = "/invalid";
    public static String INVALID_MESSAGE = "This is not a valid command! "
            + HelpCommand.CORRECT_FORMAT + " to view the correct syntax." + HelpCommand.HELP_MESSAGE;
    public static String TEMPLATE_FILE_MISSING_MESSAGE = "Template file is missing! "
            + "Regenerate Template File... Please try again";
    public static String RECIPE_FILE_MISSING_MESSAGE = "Recipe File is missing! "
            + "Regenerate Recipe File! Please try again!";
    public static final String RECIPE_INDEX_OUT_OF_RANGE_MESSAGE = "Index specified is out of range!";

    public static final String INDEX_OUT_OF_RANGE_MESSAGE = "Index specified is out of range!";
    public static final String INDEX_NOT_PRESENT_IN_LIST = "Index is not present in the list";
    public static final String INDEX_NOT_POSITIVE_INTEGER = "The index format not in positive integer within list!";
    public static final String INDEX_NOT_VALID = "The index specified is not valid.";
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
