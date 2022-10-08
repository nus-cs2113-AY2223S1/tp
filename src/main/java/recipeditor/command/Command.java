package recipeditor.command;

public abstract class Command {

    public abstract CommandResult execute();


    /**
     * Check Exit to exit program.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
