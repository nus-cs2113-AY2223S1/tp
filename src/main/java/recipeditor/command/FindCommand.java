package recipeditor.command;

import recipeditor.recipe.RecipeList;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_TYPE = "/find";
    public char flagType;
    public String findInput;

    public FindCommand(char flagType, String findInput) {
        this.flagType = Character.toLowerCase(flagType);
        this.findInput = findInput;
    }

    public CommandResult execute() {
        ArrayList<String> foundRecipeTitlesList = RecipeList.findRecipeTitles(flagType, findInput);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < foundRecipeTitlesList.size(); i++) {
            output.append(String.format("%n%d. %s", i + 1, foundRecipeTitlesList.get(i)));
        }
        return new CommandResult(output.toString());
    }
}
