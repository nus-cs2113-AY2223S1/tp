package recipeditor.parser;

import recipeditor.command.AddCommand;
import recipeditor.command.Command;
import recipeditor.command.DeleteCommand;
import recipeditor.exception.ExcessArgumentException;
import recipeditor.command.ListCommand;
import recipeditor.command.ExitCommand;
import recipeditor.command.InvalidCommand;
import recipeditor.command.ViewCommand;

import recipeditor.recipe.Recipe;
import recipeditor.storage.Storage;
import recipeditor.ui.AddMode;
import recipeditor.ui.Ui;

public class Parser {

    public Command parseCommand(String input) {
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
            try {
                int index = Integer.parseInt(parsed[1]) - 1;
                checkForExcessArgument(parsed, 2);
                return new DeleteCommand(index);
            } catch (Exception e) {
                System.out.format("Exception: Wrong command Format%n"
                        + "Try the command in correct format: mark <index of task>%n");
                return new InvalidCommand();
            }

        case ViewCommand.COMMAND_TYPE:
            try {
                int index = Integer.parseInt(parsed[1]) - 1;
                checkForExcessArgument(parsed, 2);
                return new ViewCommand(index);
            } catch (Exception e) {
                System.out.format("Exception: Wrong command Format%n"
                        + "Try the command in correct format: view <index of task>%n");
                return new InvalidCommand();
            }

        default:
            return new InvalidCommand();
        }
    }

    private Command parseAddCommand() {
        AddMode add = new AddMode(); // Switch to Add Mode in here
        add.enterAddMode();
        add.exitAddMode();
        Recipe addedRecipe = add.getRecipe();
        Storage.loadRecipeToDataFile(addedRecipe);
        Ui.showMessage("Is the recipe valid? " + String.valueOf(add.isValid));
        return new AddCommand(add.isValid, add.addedRecipe); // Pass validty and potential recipe to AddCommand
    }

    private Command parseDeleteCommand(String[] parsed) {
        if (parsed.length == 2) {
            Integer index = Integer.valueOf(parsed[ 1 ]) - 1; // to account for 0-based indexing in recipelist
            return new DeleteCommand(index);
        }
        return new InvalidCommand();
    }

    private void checkForExcessArgument(String[] args, int length)
            throws ExcessArgumentException {
        if (args.length > length) {
            throw new ExcessArgumentException();
        }
    }
}
