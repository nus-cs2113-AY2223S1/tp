package recipeditor.command;

public abstract class Command {
    public static String COMMAND_TYPE;
    private String commandSyntax;
    private String commandFunction;

    Command() {

    }

    Command(String commandSyntax, String commandFunction) {
        this.commandSyntax = commandSyntax;
        this.commandFunction = commandFunction;
    }

    /**
     * Check Exit to exit program.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
    public abstract CommandResult execute();

    public String getCommandFunction() {
        return commandFunction;
    }

    public String getCommandSyntax() {
        return commandSyntax;
    }
}
