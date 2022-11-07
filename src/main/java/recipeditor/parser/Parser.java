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

import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.MissingFlagsException;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Ui;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main parser class to parse command.
 */
public class Parser {
    private static final Logger logger = Logger.getLogger("LOGS");
    private static final String INDEX_OUT_OF_BOUND_MESSAGE =
            "Index is not present in the list.";
    private static final String WRONG_COMMAND_FORMAT_MESSAGE =
            "Wrong command format. Missing title or index from input.";
    private static final int COMMAND_INDEX = 0;

    private static final int COMMAND_LENGTH = 1;
    private static final int COMMAND_INPUT_LENGTH = 2;
    private static final int COMMAND_INDEX_LENGTH = 2;
    private static final int INDEX_AFTER_COMMAND = 2;
    private static final int COMMAND_FLAG_INPUT_LENGTH = 3;
    private static final int DELETE_COMMAND_FLAG_INDEX = 1;
    private static final int DELETE_COMMAND_RECIPE_INDEX = 2;
    private static final int VIEW_COMMAND_FLAG_INDEX = 1;
    private static final int VIEW_COMMAND_RECIPE_INDEX = 2;
    private static final int EDIT_COMMAND_RECIPE_INDEX = 1;
    private static final int ACCOUNT_ZERO_INDEXING = -1;
    private static final int REMOVE_LAST_CHAR_INDEX = -1;
    private static final int DELETE_COMMAND_LENGTH = 3;
    private static final int VIEW_COMMAND_LENGTH = 3;

    /**
     * Parse the input command and returns respective executable command.
     *
     * @param input the input command as String
     * @return command that can be executed
     */
    public static Command parseCommand(String input) {
        String[] parsed = input.split(Ui.SPACE_DIVIDER);
        String commandWord = parsed[COMMAND_INDEX].toLowerCase();

        switch (commandWord) {
        case AddCommand.COMMAND_TYPE:
            if (parsed.length != COMMAND_LENGTH) {
                return parseHelpCommand(AddCommand.COMMAND_NAME);
            }
            return parseAddCommand();
        case ListCommand.COMMAND_TYPE:
            if (parsed.length != COMMAND_LENGTH) {
                return parseHelpCommand(ListCommand.COMMAND_NAME);
            }
            return new ListCommand();
        case ExitCommand.COMMAND_TYPE:
            if (parsed.length != COMMAND_LENGTH) {
                return parseHelpCommand(ExitCommand.COMMAND_NAME);
            }
            return new ExitCommand();
        case DeleteCommand.COMMAND_TYPE:
            if (parsed.length != DELETE_COMMAND_LENGTH) {
                return parseHelpCommand(DeleteCommand.COMMAND_NAME);
            }
            return parseDeleteCommand(parsed);
        case EditCommand.COMMAND_TYPE:
            return parseEditCommand(parsed);
        case ViewCommand.COMMAND_TYPE:
            if (parsed.length != VIEW_COMMAND_LENGTH) {
                return parseHelpCommand(ViewCommand.COMMAND_NAME);
            }
            return parseViewCommand(parsed);
        case FindCommand.COMMAND_TYPE:
            return parseFindCommand(parsed);
        case HelpCommand.COMMAND_TYPE:
            if (parsed.length != COMMAND_INPUT_LENGTH) {
                return new InvalidCommand(HelpCommand.CORRECT_FORMAT);
            }
            return parseHelpCommand(parsed[1]);
        default:
            return new InvalidCommand(InvalidCommand.INVALID_MESSAGE);
        }
    }

    private static Command parseAddCommand() {
        try {
            GuiWorkFlow returnValues = new GuiWorkFlow(Storage.TEMPLATE_FILE_PATH);

            logger.log(Level.INFO, "Add command initialised");
            return new AddCommand(returnValues.getValid(), returnValues.getRecipe());
        } catch (FileNotFoundException e) {
            Storage.generateTemplateFile();
            return new InvalidCommand(InvalidCommand.TEMPLATE_FILE_MISSING_MESSAGE);
        }
    }

    private static Command parseDeleteCommand(String[] parsed) {
        String recipeTitleToDelete;
        try {
            FlagType[] flags = FlagParser.getFlags(parsed);
            switch (flags[DELETE_COMMAND_FLAG_INDEX]) {
            case INDEX:
                int recipeIndexToDelete = Integer.parseInt(parsed[DELETE_COMMAND_RECIPE_INDEX]) + ACCOUNT_ZERO_INDEXING;
                assert recipeIndexToDelete > -1;
                return new DeleteCommand(recipeIndexToDelete);
            case TITLE:
                String[] recipeTitleToDeleteArray = Arrays.copyOfRange(parsed,
                        DELETE_COMMAND_RECIPE_INDEX, parsed.length);
                recipeTitleToDelete = convertStringArrayToString(recipeTitleToDeleteArray);
                // check if recipe title is inside the list
                String actualRecipeTitle = actualRecipeTitle(recipeTitleToDelete);
                if (actualRecipeTitle != null) {
                    logger.log(Level.INFO, "Delete command initialised");
                    return new DeleteCommand(actualRecipeTitle);
                }
                Ui.showMessage(recipeTitleToDelete + InvalidCommand.INVALID_TITLE_MESSAGE);
                break;
            case NULL:
                throw new MissingFlagsException();
            default:
                throw new InvalidFlagException();
            }
        } catch (IndexOutOfBoundsException i) {
            Ui.showMessage(WRONG_COMMAND_FORMAT_MESSAGE);
        } catch (NumberFormatException n) {
            Ui.showMessage(parsed[2] + InvalidCommand.INVALID_INDEX_MESSAGE);
        } catch (Exception e) {
            Ui.showMessage(e.getMessage());
        } catch (AssertionError e) {
            Ui.showMessage(INDEX_OUT_OF_BOUND_MESSAGE);
        }
        return new InvalidCommand(DeleteCommand.CORRECT_FORMAT);
    }

    private static Command parseViewCommand(String[] parsed) {
        String recipeTitleToView;
        try {
            FlagType[] flags = FlagParser.getFlags(parsed);
            switch (flags[VIEW_COMMAND_FLAG_INDEX]) {
            case INDEX:
                int index = Integer.parseInt(parsed[VIEW_COMMAND_RECIPE_INDEX]) + ACCOUNT_ZERO_INDEXING;
                assert index > -1;
                return new ViewCommand(index);
            case TITLE:
                String[] recipeTitleToViewArray = Arrays.copyOfRange(parsed, VIEW_COMMAND_RECIPE_INDEX, parsed.length);
                recipeTitleToView = convertStringArrayToString(recipeTitleToViewArray);
                // check if recipe title is inside the list
                String actualRecipeTitle = actualRecipeTitle(recipeTitleToView);
                if (actualRecipeTitle != null) {
                    logger.log(Level.INFO, "View command initialised");
                    return new ViewCommand(actualRecipeTitle);
                }
                break;
            case NULL:
                throw new MissingFlagsException();
            default:
                throw new InvalidFlagException();
            }
        } catch (IndexOutOfBoundsException i) {
            Ui.showMessage(WRONG_COMMAND_FORMAT_MESSAGE);
        } catch (NumberFormatException n) {
            Ui.showMessage(InvalidCommand.INVALID_MESSAGE);
        } catch (Exception e) {
            Ui.showMessage(e.getMessage());
        } catch (AssertionError e) {
            Ui.showMessage(INDEX_OUT_OF_BOUND_MESSAGE);
        }
        return new InvalidCommand(ViewCommand.COMMAND_SYNTAX);
    }

    private static Command parseEditCommand(String[] parsed) {
        int index = -1;
        if (parsed.length == COMMAND_INDEX_LENGTH) {
            try {
                index = Integer.parseInt(parsed[EDIT_COMMAND_RECIPE_INDEX]) + ACCOUNT_ZERO_INDEXING;
                assert index > -1;
                Recipe targetRecipe = RecipeList.getRecipe(index);
                String title = targetRecipe.getTitle();
                String path = Storage.titleToFilePath(title);

                GuiWorkFlow returnValues = new GuiWorkFlow(path);
                logger.log(Level.INFO, "Edit command initialised in GUI");
                return new EditCommand(returnValues.getValid(), index, returnValues.getRecipe(), title);
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
                index = Integer.parseInt(parsed[EDIT_COMMAND_RECIPE_INDEX]) + ACCOUNT_ZERO_INDEXING;
                assert index > -1;
                Recipe originalRecipe = RecipeList.getRecipe(index);
                Recipe editedRecipe = new Recipe(originalRecipe.getTitle(), originalRecipe.getDescription());

                editedRecipe.addIngredients(originalRecipe.getIngredients());
                editedRecipe.addSteps(originalRecipe.getSteps());

                FlagType[] flags = FlagParser.getFlags(parsed);
                if (flags == null) {
                    return new InvalidCommand(EditCommand.COMMAND_SYNTAX);
                }
                if (flags[0] == FlagType.NULL) {
                    throw new MissingFlagsException("command");
                }
                if (flags[1] == FlagType.NULL) {
                    throw new MissingFlagsException("recipe");
                }
                logger.log(Level.INFO, "Edit command initialised in CLI");
                return new EditCommand(flags, parsed, index, editedRecipe, originalRecipe.getTitle());
            } catch (NumberFormatException n) {
                return new InvalidCommand(InvalidCommand.INDEX_NOT_VALID);
            } catch (IndexOutOfBoundsException e) {
                Ui.showMessage(InvalidCommand.RECIPE_INDEX_OUT_OF_RANGE_MESSAGE);
                return new InvalidCommand(InvalidCommand.RECIPE_INDEX_OUT_OF_RANGE_MESSAGE);
            } catch (InvalidFlagException e) {
                return new InvalidCommand(e.getMessage());
            } catch (Exception e) {
                return new InvalidCommand(e.getMessage());
            }
        }
        return new InvalidCommand(EditCommand.COMMAND_SYNTAX);
    }

    private static Command parseFindCommand(String[] parsed) {

        if (parsed.length >= COMMAND_FLAG_INPUT_LENGTH) {
            FlagType flag = FlagParser.getRecipeFlag(parsed);
            String[] inputArray = Arrays.copyOfRange(parsed, INDEX_AFTER_COMMAND, parsed.length);
            String input = convertStringArrayToString(inputArray);
            logger.log(Level.INFO, "Find command initialised");
            return new FindCommand(flag, input);
        } else {
            return new InvalidCommand(FindCommand.CORRECT_FORMAT);
        }
    }


    public static Command parseHelpCommand(String params) {
        logger.log(Level.INFO, "Help command initialised");
        return new HelpCommand(params);
    }

    private static String convertStringArrayToString(String[] stringArray) {
        StringBuilder content = new StringBuilder();
        for (String string : stringArray) {
            content.append(string + Ui.SPACE_DIVIDER);
        }
        content.deleteCharAt(content.length() + REMOVE_LAST_CHAR_INDEX);
        return content.toString();
    }

    // To account for case insensitivity of user
    private static String actualRecipeTitle(String recipeTitleToBeFound) {
        String actualRecipeTitle = null;
        for (String recipeTitle : RecipeList.iterateRecipeTitles()) {
            if (recipeTitle.trim().equalsIgnoreCase(recipeTitleToBeFound)) {
                actualRecipeTitle = recipeTitle;
                break;
            }
        }
        return actualRecipeTitle;
    }
}
