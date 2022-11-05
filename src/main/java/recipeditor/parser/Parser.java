package recipeditor.parser;

import recipeditor.command.Command;
import recipeditor.command.AddCommand;
import recipeditor.command.DeleteCommand;
import recipeditor.command.EditCommand;
import recipeditor.command.ExitCommand;
import recipeditor.command.FindCommand;
import recipeditor.command.HelpCommand;
import recipeditor.command.InvalidCommand;
import recipeditor.command.ListCommand;
import recipeditor.command.ViewCommand;

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
    private static final String NOT_PRESENT_IN_LIST_MESSAGE = " is not present in the list";
    private static final int COMMAND_INPUT_LENGTH = 2;
    private static final int COMMAND_INDEX_LENGTH = 2;
    private static final int INDEX_AFTER_COMMAND = 2;
    private static final int COMMAND_FLAG_INPUT_LENGTH = 3;

    /**
     * Parse the input command and returns respective executable command.
     *
     * @param input the input command as String
     * @return command that can be executed
     */
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
            return parseHelpCommand(parsed);
        default:
            return new InvalidCommand(InvalidCommand.INVALID_MESSAGE);
        }
    }

    private static Command parseAddCommand() {
        try {
            GuiWorkFlow returnValues = new GuiWorkFlow(Storage.TEMPLATE_FILE_PATH);
            return new AddCommand(returnValues.getValidity(), returnValues.getRecipe());
        } catch (FileNotFoundException e) {
            Storage.generateTemplateFile();
            return new InvalidCommand(InvalidCommand.TEMPLATE_FILE_MISSING_MESSAGE);
        }
    }

    private static Command parseDeleteCommand(String[] parsed) {
        String recipeTitleToDelete = "";
        try {
            if (parsed.length >= COMMAND_INPUT_LENGTH) {
                String[] recipeTitleToDeleteArray = Arrays.copyOfRange(parsed, 1, parsed.length);
                recipeTitleToDelete = convertStringArrayToString(recipeTitleToDeleteArray);
                // check if recipe title is inside the list
                String actualRecipeTitle = actualRecipeTitle(recipeTitleToDelete);
                if (actualRecipeTitle != null) {
                    return new DeleteCommand(actualRecipeTitle);
                } else {
                    int recipeIndexToDelete = Integer.parseInt(parsed[1]) - 1;
                    assert recipeIndexToDelete > -1;
                    return new DeleteCommand(recipeIndexToDelete);
                }
            }
            return new InvalidCommand(DeleteCommand.CORRECT_FORMAT);
        } catch (IndexOutOfBoundsException i) {
            Ui.showMessage(InvalidCommand.INDEX_NOT_PRESENT_IN_LIST);
            return new InvalidCommand(DeleteCommand.CORRECT_FORMAT);
        } catch (NumberFormatException n) {
            Ui.showMessage(recipeTitleToDelete + NOT_PRESENT_IN_LIST_MESSAGE);
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
        for (String recipeTitle : RecipeList.iterateRecipeTitles()) {
            if (recipeTitle.trim().equalsIgnoreCase(recipeTitleToBeFound)) {
                actualRecipeTitle = recipeTitle;
                break;
            }
        }
        return actualRecipeTitle;
    }

    private static Command parseViewCommand(String[] parsed) {
        String recipeTitleToDelete = "";
        try {
            if (parsed.length == COMMAND_INDEX_LENGTH) {
                String[] recipeTitleToDeleteArray = Arrays.copyOfRange(parsed, 1, parsed.length);
                recipeTitleToDelete = convertStringArrayToString(recipeTitleToDeleteArray);
                // check if recipe title is inside the list
                String actualRecipeTitle = actualRecipeTitle(recipeTitleToDelete);
                if (actualRecipeTitle != null) {
                    return new ViewCommand(actualRecipeTitle);
                } else {
                    int index = Integer.parseInt(parsed[1]) - 1; // to account for 0-based indexing in recipelist
                    assert index > -1;
                    return new ViewCommand(index);
                }
            }
        } catch (IndexOutOfBoundsException i) {
            Ui.showMessage(InvalidCommand.INDEX_NOT_PRESENT_IN_LIST);
            return new InvalidCommand(ViewCommand.COMMAND_SYNTAX);
        } catch (NumberFormatException n) {
            Ui.showMessage(recipeTitleToDelete + NOT_PRESENT_IN_LIST_MESSAGE);
            return new InvalidCommand(ViewCommand.COMMAND_SYNTAX);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File not found when deleting the recipe file");
            return new InvalidCommand(ViewCommand.COMMAND_SYNTAX);
        }
        return new InvalidCommand(ViewCommand.COMMAND_SYNTAX);
    }

    private static Command parseEditCommand(String[] parsed) {
        int index = -1;
        if (parsed.length == COMMAND_INDEX_LENGTH) {
            try {
                index = Integer.parseInt(parsed[1]) - 1; // to account for 0-based indexing in recipelist
                assert index > -1;
                Recipe targetRecipe = RecipeList.getRecipe(index);
                String title = targetRecipe.getTitle();
                String path = Storage.titleToFilePath(title);

                GuiWorkFlow returnValues = new GuiWorkFlow(path);
                return new EditCommand(returnValues.getValidity(), index, returnValues.getRecipe(), title);
            } catch (FileNotFoundException e) {
                logger.log(Level.INFO, e.getMessage());
                Recipe targetRecipe = RecipeList.getRecipe(index);
                String title = targetRecipe.getTitle();
                String path = Storage.titleToFilePath(title);
                Storage.saveRecipe(targetRecipe, "", path);
                return new InvalidCommand(InvalidCommand.RECIPE_FILE_MISSING_MESSAGE);
            } catch (IndexOutOfBoundsException e) {
                return new InvalidCommand(InvalidCommand.INDEX_OUT_OF_RANGE_MESSAGE);
            } catch (NumberFormatException e) {
                return new InvalidCommand(InvalidCommand.INDEX_NOT_POSITIVE_INTEGER);
            }
        } else if (parsed.length >= COMMAND_FLAG_INPUT_LENGTH) {
            try {
                index = Integer.parseInt(parsed[1]) - 1;
                assert index > -1;
                Recipe originalRecipe = RecipeList.getRecipe(index);
                Recipe editedRecipe = new Recipe(originalRecipe.getTitle(), originalRecipe.getDescription());

                editedRecipe.addIngredients(originalRecipe.getIngredients());
                editedRecipe.addSteps(originalRecipe.getSteps());

                FlagType[] flags = FlagParser.getCommandAndRecipeFlags(parsed);
                if (flags == null) {
                    return new InvalidCommand(EditCommand.COMMAND_SYNTAX);
                }
                return new EditCommand(flags, parsed, index, editedRecipe, originalRecipe.getTitle());
            } catch (NumberFormatException n) {
                return new InvalidCommand();
            } catch (IndexOutOfBoundsException e) {
                Ui.showMessage(InvalidCommand.RECIPE_INDEX_OUT_OF_RANGE_MESSAGE);
                return new InvalidCommand(EditCommand.COMMAND_SYNTAX);
            } catch (Exception e) {
                return new InvalidCommand(EditCommand.COMMAND_SYNTAX);
            }
        }
        return new InvalidCommand(EditCommand.COMMAND_SYNTAX);
    }

    private static Command parseFindCommand(String[] parsed) {

        if (parsed.length >= COMMAND_FLAG_INPUT_LENGTH) {
            FlagType flag = FlagParser.getRecipeFlag(parsed);
            String[] inputArray = Arrays.copyOfRange(parsed, INDEX_AFTER_COMMAND, parsed.length);
            String input = convertStringArrayToString(inputArray);
            return new FindCommand(flag, input);
        } else {
            return new InvalidCommand(FindCommand.CORRECT_FORMAT);
        }
    }


    public static Command parseHelpCommand(String[] parsed) {

        if (parsed.length == COMMAND_INPUT_LENGTH) {
            return new HelpCommand(parsed[1]);
        }
        return new InvalidCommand(HelpCommand.CORRECT_FORMAT + HelpCommand.HELP_MESSAGE);
    }
}
