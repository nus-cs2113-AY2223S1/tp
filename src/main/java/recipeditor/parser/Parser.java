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

import recipeditor.exception.ParseFileException;
import recipeditor.recipe.Recipe;
import recipeditor.ui.Editor;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.EditMode;
import recipeditor.ui.Ui;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    private static Logger logger = Logger.getLogger("LOGS");
    private static String recipeTitle = null;

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
            return new InvalidCommand();
        }
    }


    private static Command parseAddCommand() {
        boolean saveToTemp = new Editor().enterEditor(Storage.TEMPLATE_FILE_PATH);
        boolean exitLoop = (saveToTemp) ? false : true;
        boolean valid = false;
        Recipe addRecipe = new Recipe();
        while (!exitLoop) {
            try {
                String content = Storage.loadFileContent(Storage.TEMPORARY_FILE_PATH);
                addRecipe = new TextFileParser().parseTextToRecipe(content);
                valid = true;
                exitLoop = true;
            } catch (ParseFileException | FileNotFoundException e) {
                Ui.showMessage(e.getMessage());
                Ui.showMessage("Do you want to ABORT? (Y/N)");
                String text = Ui.readInput();
                if (text.equalsIgnoreCase("n")) {
                    new Editor().enterEditor(Storage.TEMPORARY_FILE_PATH);
                } else {
                    exitLoop = true;
                }
            }
        }
        return new AddCommand(valid, addRecipe);
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
            } else {
                Ui.showMessage(DeleteCommand.CORRECT_FORMAT);
            }
            return new InvalidCommand();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File not found when deleting the recipe file");
            return new InvalidCommand();
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
        String recipeTitles = Storage.loadFileContent(Storage.ALL_RECIPES_FILE_PATH);
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
                return new InvalidCommand();
            }
        }
        return new InvalidCommand();
    }

    private static Command parseEditCommand(String[] parsed) {
        int index = -1;
        if (parsed.length >= 2) {
            try {
                index = Integer.parseInt(parsed[1]) - 1;
            } catch (NumberFormatException n) {
                index = RecipeList.getRecipeIndexFromTitle(parsed[1]);
            }
            EditMode edit = new EditMode();
            edit.enterEditMode(index);
            return new EditCommand(edit.exitEditMode(), index, edit.getEditedRecipe());
        }
        return new InvalidCommand();
    }

    private static Command parseFindCommand(String[] parsed) {
        if (parsed.length >= 2) {
            String[] inputArray = Arrays.copyOfRange(parsed, 1, parsed.length);
            String input = convertStringArrayToString(inputArray);
            return new FindCommand(input);
        } else {
            Ui.showMessage(FindCommand.CORRECT_FORMAT);
        }
        return new InvalidCommand();
    }
}
