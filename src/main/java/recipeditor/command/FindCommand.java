package recipeditor.command;

import recipeditor.recipe.RecipeList;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_TYPE = "/find";
    public static final String COMMAND_SYNTAX = "Syntax for /find \n";
    public char flagType;

    public static final String CORRECT_FORMAT = "The input should be '/find (recipeTitle or ingredientName).'";
    public String findInput;

    public FindCommand(String findInput) {
        this.findInput = findInput;
    }

    public CommandResult execute() {
        ArrayList<String> foundRecipeList = RecipeList.findRecipeTitles(findInput);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < foundRecipeList.size(); i++) {
            output.append(String.format("%n%d. %s", i + 1, foundRecipeList.get(i)));
        }
        return new CommandResult(output.toString());
    }
}
