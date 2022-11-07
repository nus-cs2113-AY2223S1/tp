package recipeditor.command;

public class HelpCommand extends Command {

    public static final String COMMAND_TYPE = "/help";
    private static final String COMMAND_SYNTAX = "/help <command>";
    private static final String COMMAND_FUNCTION = "Show help message for the given command.";
    public static final String HELP_MESSAGE =
            "\n" + "Available commands: /add, /list, /view, /edit, /find, /delete, /exit, /help";
    public static final String CORRECT_FORMAT = "Try /help <command type>";
    private String cmd;

    public HelpCommand() {
        super(COMMAND_SYNTAX, COMMAND_FUNCTION);
    }

    public HelpCommand(String cmd) {
        this();
        this.cmd = cmd;
    }

    /**
     * Execute help command, which shows some helper messages according to the commands user want.
     *
     * @return CommandResult list of available commands
     */
    public CommandResult execute() {

        Command command;
        switch (cmd.toLowerCase()) {
        case "add":
            command = new AddCommand();
            break;
        case "delete":
            command = new DeleteCommand();
            break;
        case "edit":
            command = new EditCommand();
            break;
        case "exit":
            command = new ExitCommand();
            break;
        case "find":
            command = new FindCommand();
            break;
        case "help":
            command = new HelpCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "view":
            command = new ViewCommand();
            break;
        default:
            return new CommandResult(String.format("%s is not a valid command.%s",
                    cmd.toLowerCase(), HELP_MESSAGE));
        }

        String message = "Syntax: " + command.getCommandSyntax() + '\n'
                + "Description: " + command.getCommandFunction();

        return new CommandResult(message);
    }
}
