package recipeditor.parser;

import recipeditor.command.Command;
import recipeditor.command.AddCommand;
import recipeditor.command.DeleteCommand;
import recipeditor.command.ExitCommand;
import recipeditor.command.ListCommand;
import recipeditor.command.EditCommand;
import recipeditor.command.ViewCommand;

import recipeditor.exception.ParseFileException;
import recipeditor.recipe.Recipe;
import recipeditor.ui.Editor;
import recipeditor.command.FindCommand;
import recipeditor.command.InvalidCommand;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.EditMode;
import recipeditor.ui.Ui;
import recipeditor.parser.TextFileParser;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class Parser {
    private static Logger logger = Logger.getLogger("LOGS");

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
            return parseEditCommand(parsed, input);
        case ViewCommand.COMMAND_TYPE:
            return parseViewCommand(parsed);
        case FindCommand.COMMAND_TYPE:
            return parseFindCommand(parsed);
        default:
            return new InvalidCommand();
        }
    }


    private static Command parseAddCommand() {
        boolean saveToTemp = new Editor().enterEditor(Storage.TEMPLATE_PATH);
        boolean exitLoop = (saveToTemp) ? false : true;
        boolean valid = false;
        Recipe addRecipe = new Recipe();
        while (!exitLoop) {
            try {
                String content = Storage.loadFileContent(Storage.TEMPORARY_PATH);
                addRecipe = new TextFileParser().parseTextToRecipe(content);
                valid = true;
                exitLoop = true;
            } catch (ParseFileException | FileNotFoundException e) {
                Ui.showMessage(e.getMessage());
                Ui.showMessage("Do you want to ABORT? (Y/N)");
                String text = Ui.readInput();
                if (text.equalsIgnoreCase("n")) {
                    new Editor().enterEditor(Storage.TEMPORARY_PATH);
                } else {
                    exitLoop = true;
                }
            }
        }
        return new AddCommand(valid, addRecipe);
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

    private static Command parseDeleteCommand(String[] parsed) {
        if (parsed.length == 2) {
            try {
                int index = Integer.parseInt(parsed[1]) - 1; // to account for 0-based indexing in recipelist
                return new DeleteCommand(index);
            } catch (Exception e) {
                return new InvalidCommand();
            }
        }
        return new InvalidCommand();
    }


    private static Command parseEditCommand(String[] parsed, String input) {
        int index = -1;
        if (parsed.length > 1) {
            try {
                index = Integer.parseInt(parsed[1]) - 1;
            } catch (NumberFormatException n) {
                return new InvalidCommand();
            }
            if (parsed.length == 2) {
                /**
                 * PLACE GUI HERE
                 */
                EditMode edit = new EditMode();
                edit.enterEditMode(index);
                return new EditCommand(edit.exitEditMode(), index, edit.getEditedRecipe());
                /**
                 * PLACE GUI HERE
                 */
            } else {
                Recipe originalRecipe = RecipeList.getRecipe(index);
                Recipe editedRecipe = new Recipe(originalRecipe.getTitle(), originalRecipe.getDescription());

                editedRecipe.addIngredients(originalRecipe.getIngredients());
                editedRecipe.addSteps(originalRecipe.getSteps());

                FlagType[] flags = FlagParser.getFlags(parsed);
                if (flags == null) {
                    return new InvalidCommand();
                }
                return new EditCommand(flags[0], parsed, index, editedRecipe);
            }
        }
        return new InvalidCommand();
    }

    private static Command parseFindCommand(String[] parsed) {
        if (parsed.length < 2) {
            return new InvalidCommand();
        }
        String flagAndInputString = convertStringArrayToString(parsed);
        String[] flagAndInput = flagAndInputString.split(" ", 2);
        char flag = flagAndInput[0].charAt(0);
        String input = flagAndInput[1];
        return new FindCommand(flag, input);
    }

    private static String convertStringArrayToString(String[] stringArray) {
        StringBuilder output = new StringBuilder();
        // Finding the flag in the string array input
        if (stringArray[1].contains("-")) {
            String[] flagAndInput = stringArray[1].split("-");
            String flag = flagAndInput[1];
            output.append(flag + " ");
        }
        for (int i = 2; i < stringArray.length; i++) {
            if (i == stringArray.length - 1) {
                output.append(stringArray[i]);
            } else {
                output.append(stringArray[i] + " ");
            }
        }
        return output.toString();
    }

}
