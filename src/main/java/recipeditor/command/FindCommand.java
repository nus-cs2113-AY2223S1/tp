package recipeditor.command;

import recipeditor.parser.FlagType;
import recipeditor.recipe.RecipeList;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_TYPE = "/find";
    private static final String COMMAND_SYNTAX = "/find -<flag> <recipe title/ingredient name>";
    private static final String COMMAND_FUNCTION = "For the given ingredient or title,"
            + " find recipes which contains it.";

    public static final String FLAG_SYNTAX = "\nFlags:" + "\n-t: Recipe Title" + "\n-i: Ingredient name";

    public static final String CORRECT_FORMAT = "The correct format should be " + "'" + COMMAND_SYNTAX + "'."
            + FLAG_SYNTAX;
    public static final String INCORRECT_FLAG_MESSGE = "Incorrect flag!\n";
    public String findInput;
    private FlagType flag;


    public FindCommand() {
        super(COMMAND_SYNTAX, COMMAND_FUNCTION);
    }

    public FindCommand(FlagType flag, String findInput) {
        this();
        this.flag = flag;
        this.findInput = findInput;
    }

    /**
     * Execute find command, which find the name of recipes containing a given string
     * in its title or ingredients.
     *
     * @return CommandResult list of found results
     */
    public CommandResult execute() {
        try {
            ArrayList<String> foundRecipeList = null;
            switch (flag) {
            case TITLE:
                foundRecipeList = RecipeList.findRecipeTitlesFromRecipeTitle(findInput);
                break;
            case INGREDIENT:
                foundRecipeList = RecipeList.findRecipeTitlesFromIngredientName(findInput);
                break;
            default:
                return new CommandResult(INCORRECT_FLAG_MESSGE + FindCommand.FLAG_SYNTAX);
            }
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < foundRecipeList.size(); i++) {
                output.append(String.format("%n%d. %s", i + 1, foundRecipeList.get(i)));
            }
            return new CommandResult(output.toString());
        } catch (NullPointerException e) {
            return new CommandResult(FindCommand.CORRECT_FORMAT);
        }
    }
}
