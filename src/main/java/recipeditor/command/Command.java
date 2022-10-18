package recipeditor.command;

public abstract class Command {
    public static String COMMAND_TYPE;

    public abstract CommandResult execute();

    /** Check Exit to exit program */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    public static String getCommandType() {
        return COMMAND_TYPE;
    }

}
