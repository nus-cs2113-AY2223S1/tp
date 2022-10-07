package recipeditor.command;

public class ByeCommand extends Command {
    public static final String commandType = "bye";

    private final String BYE_MESSAGE = "End of RecipeEditor Function";

    public ByeCommand() {
        System.out.println(BYE_MESSAGE);
        System.exit(0);
    }
}
