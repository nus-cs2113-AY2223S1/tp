package recipeditor.ui;

import com.sun.source.tree.ReturnTree;
import recipeditor.edit.*;
import recipeditor.exception.ParseException;
import recipeditor.recipe.Ingredient;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.command.Command;
import recipeditor.command.InvalidCommand;

public class EditMode {
    private static final String INVALID_INPUT = "Invalid input given. ";
    private static final String INVALID_FLAG = "Invalid flag given. ";
    private static final String INVALID_INDEX = "Invalid index given. ";
    private static final String GENERIC_ERROR = "Something happened. ";

    private static final String ENTER = "Entering edit mode. Currently editing: ";
    private static final String HELP_1 = "Available commands: /add, /del, /swap, /view, /done ";
    private static final String HELP_2 = "Available flags: -i (ingredients), -s (steps) ";
    private static final String EXIT = "Exiting edit mode. ";
    private static final String NOT_FOUND = "Recipe not found. ";
    private static final String OLD = "Before: ";
    private static final String NEW = "After: ";

    private Recipe originalRecipe;
    private Recipe editedRecipe;

    public void enterEditMode(int index) {
        if (index < 0) {
            Ui.showMessageInline(NOT_FOUND);
            return;
        }
        try {
            originalRecipe = RecipeList.getRecipe(index);
            Ui.showMessageInline(ENTER + originalRecipe.getTitle());

            editedRecipe = new Recipe(originalRecipe.getTitle(), originalRecipe.getDescription());
            editedRecipe.addIngredients(originalRecipe.getIngredients());
            editedRecipe.addSteps(originalRecipe.getSteps());

            Ui.showMessageInline(originalRecipe.getRecipeAttributesFormatted());

            String input = "";

            while (!input.equals("/done")) {
                Ui.showMessageInline(HELP_1);
                Ui.showMessageInline(HELP_2);
                input = Ui.readInput();
                Ui.showMessageInline(parseEditInput(input));
            }
        } catch (IndexOutOfBoundsException i) {
            Ui.showMessageInline(INVALID_INDEX);
        } catch (NumberFormatException n) {
            Ui.showMessageInline(INVALID_INPUT);
        } catch (Exception e) {
            Ui.showMessageInline(GENERIC_ERROR);
        }
    }

    private String parseEditInput(String input) {

        EditModeCommand cmd = new Invalid(editedRecipe);
        String[] parsed = input.split(" ");
        String commandWord = parsed[0].toLowerCase();
        Ui.showMessageInline(commandWord);
        switch (commandWord) {
        case "/add":
            cmd = new Add(parsed, editedRecipe);
            break;
        case "/del":
            cmd = new Delete(parsed, editedRecipe);
            break;
        case "/swap":
            cmd = new Swap(parsed, editedRecipe);
            break;
        case "/change":
            cmd = new Change(parsed, editedRecipe);
            break;
        case "/view":
            cmd = new View(parsed, editedRecipe, originalRecipe);
            break;
        case "/done":
            cmd = new Quit(editedRecipe);
            break;
        }

        try {
            editedRecipe = cmd.execute();
            return cmd.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public boolean exitEditMode() {
        Ui.showMessageInline(EXIT);
        if (editedRecipe == null) {
            return false;
        } else {
            Ui.showMessageInline(OLD);
            Ui.showMessageInline(originalRecipe.getRecipeAttributesFormatted());
            Ui.showMessageInline(NEW);
            Ui.showMessageInline(editedRecipe.getRecipeAttributesFormatted());
            return true;
        }
    }

    public Recipe getOriginalRecipe() {
        return originalRecipe;
    }

    public Recipe getEditedRecipe() {
        return editedRecipe;
    }

}
