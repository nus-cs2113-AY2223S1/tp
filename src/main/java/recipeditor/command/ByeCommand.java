package recipeditor.command;

public class ByeCommand extends Command {
    public static final String COMMAND_TYPE = "bye";

    //TODO: printing is done in execute, while closing is ?
    public ByeCommand() {
        System.out.println("Closing RecipeEditor...");
        System.exit(0);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("Closing RecipeEditor...");
    }
}
