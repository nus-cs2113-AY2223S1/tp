package recipeditor.command;

public class ByeCommand {
    public static final String COMMAND_TYPE = "bye";

    public ByeCommand() {
        System.out.println("Closing RecipeEditor...");
        System.exit(0);
    }

}
