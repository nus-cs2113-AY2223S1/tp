package recipeditor.command;

public class ByeCommand extends Command {
    public static final String commandType = "bye";

    public ByeCommand() {
        System.out.println("End of RecipeEditor Function");
        System.exit(0);
    }
}
