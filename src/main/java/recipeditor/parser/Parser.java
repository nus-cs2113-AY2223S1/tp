package recipeditor.parser;

import recipeditor.command.Command;
import recipeditor.command.AddCommand;
import recipeditor.command.ListCommand;
import recipeditor.command.ExitCommand;
import recipeditor.command.DeleteCommand;
import recipeditor.command.EditCommand;
import recipeditor.command.ViewCommand;
import recipeditor.command.FindCommand;
import recipeditor.command.HelpCommand;
import recipeditor.command.InvalidCommand;

import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    private static final Logger logger = Logger.getLogger("LOGS");
    private static final String recipeTitle = null;

    public static Command parseCommand(String input) {
        String[] parsed = input.split(" ");
        String commandWord = parsed[0].toLowerCase();

        switch (commandWord) {
        case AddCommand.COMMAND_TYPE:
            return parseAddCommand();
        case ListCommand.COMMAND_TYPE:
            return new ListCommand();
        case ExitCommand.COMMAND_TYPE:
            return new ExitCommand();
        case DeleteCommand.COMMAND_TYPE:
            return parseDeleteCommand(parsed);
        case EditCommand.COMMAND_TYPE:
            return parseEditCommand(parsed);
        case ViewCommand.COMMAND_TYPE:
            return parseViewCommand(parsed);
        case FindCommand.COMMAND_TYPE:
            return parseFindCommand(parsed);
        case HelpCommand.COMMAND_TYPE:
            return new HelpCommand();
        default:
            return new InvalidCommand(InvalidCommand.INVALID_MESSAGE);
        }
    }


    private static Command parseAddCommand() {
        GuiWorkFlow returnValues = new GuiWorkFlow(Storage.TEMPLATE_FILE_PATH);
        return new AddCommand(returnValues.getValidity(), returnValues.getRecipe());
    }

    private static Command parseDeleteCommand(String[] parsed) {
        try {
            if (parsed.length >= 2) {
                String[] recipeTitleToDeleteArray = Arrays.copyOfRange(parsed, 1, parsed.length);
                String recipeTitleToDelete = convertStringArrayToString(recipeTitleToDeleteArray);
                // check if recipe title is inside the list
                String actualRecipeTitle = actualRecipeTitle(recipeTitleToDelete);
                if (actualRecipeTitle != null) {
                    return new DeleteCommand(actualRecipeTitle);
                } else {
                    Ui.showMessage(recipeTitleToDelete + " is not present in the list");
                }
            }
            return new InvalidCommand(DeleteCommand.CORRECT_FORMAT);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File not found when deleting the recipe file");
            return new InvalidCommand(DeleteCommand.CORRECT_FORMAT);
        }
    }

    private static String convertStringArrayToString(String[] stringArray) {
        StringBuilder content = new StringBuilder();
        for (String string : stringArray) {
            content.append(string + " ");
        }
        content.deleteCharAt(content.length() - 1);
        return content.toString();
    }

    // To account for case insensitivity of user
    private static String actualRecipeTitle(String recipeTitleToBeFound) throws FileNotFoundException {
        String actualRecipeTitle = null;
        String recipeTitles = Storage.loadFileContent(Storage.ALL_RECIPES_FILE_PATH);//TODO: Load from model is better!
        String[] recipeTitlesArray = recipeTitles.split("\\r?\\n");
        for (String recipeTitle : recipeTitlesArray) {
            if (recipeTitle.trim().equalsIgnoreCase(recipeTitleToBeFound)) {
                actualRecipeTitle = recipeTitle;
                break;
            }
        }
        return actualRecipeTitle;
    }

    private static Command parseViewCommand(String[] parsed) {
        if (parsed.length == 2) {
            try {
                int index = Integer.parseInt(parsed[1]) - 1; // to account for 0-based indexing in recipelist
                return new ViewCommand(index);
            } catch (Exception e) {
                return new InvalidCommand(ViewCommand.COMMAND_SYNTAX);
            }
        }
        return new InvalidCommand(ViewCommand.COMMAND_SYNTAX);
    }

    private static Command parseEditCommand(String[] parsed) {
        int index = -1;
        if (parsed.length == 2) {
            try {
                index = Integer.parseInt(parsed[1]) - 1; // to account for 0-based indexing in recipelist
                String name = RecipeList.getTitleFromIndex(index);
                String path = Storage.RECIPES_FOLDER_PATH + "/" + name;
                GuiWorkFlow returnValues = new GuiWorkFlow(path);
                return new EditCommand(returnValues.getValidity(), index, returnValues.getRecipe(), name);
            } catch (Exception e) {
                logger.log(Level.INFO, e.getMessage());
                return new InvalidCommand("Edit GUI Error");
            }
        } else if (parsed.length > 2) {
            try {
                index = Integer.parseInt(parsed[1]) - 1;
                Recipe originalRecipe = RecipeList.getRecipe(index);
                Recipe editedRecipe = new Recipe(originalRecipe.getTitle(), originalRecipe.getDescription());

                editedRecipe.addIngredients(originalRecipe.getIngredients());
                editedRecipe.addSteps(originalRecipe.getSteps());

                FlagType[] flags = FlagParser.getFlags(parsed);
                if (flags == null) {
                    return new InvalidCommand();
                }
                return new EditCommand(flags, parsed, index, editedRecipe, originalRecipe.getTitle());
            } catch (NumberFormatException n) {
                return new InvalidCommand();
            } catch (Exception e) {
                return new InvalidCommand(e.getMessage());
            }
        }
        return new InvalidCommand(EditCommand.COMMAND_FORMAT);
    }

    private static Command parseFindCommand(String[] parsed) {

        if (parsed.length >= 2) {
            String[] inputArray = Arrays.copyOfRange(parsed, 1, parsed.length);
            String input = convertStringArrayToString(inputArray);
            return new FindCommand(input);
        } else {
            Ui.showMessage(FindCommand.CORRECT_FORMAT);
        }
        return new InvalidCommand(FindCommand.CORRECT_FORMAT);
    }


}
