package recipeditor.command;

import recipeditor.recipe.RecipeList;

import java.util.ArrayList;

public class FindCommandByTitle extends Command {
    public static final String COMMAND_TYPE = "find";
    public String recipeTitle;

    public FindCommandByTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public CommandResult execute() {
        ArrayList<String> foundRecipeTitlesList = RecipeList.findRecipeTitlesFromTitle(recipeTitle);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < foundRecipeTitlesList.size(); i++) {
            output.append(String.format("%n%d. %s", i + 1, foundRecipeTitlesList.get(i)));
        }
        return new CommandResult(output.toString());
    }
}
