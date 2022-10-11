package recipeditor.parser;

import recipeditor.command.AddCommand;
import recipeditor.command.Command;
import recipeditor.command.ListCommand;
import recipeditor.command.ExitCommand;
import recipeditor.command.InvalidCommand;
import recipeditor.command.ViewCommand;
import recipeditor.command.DeleteCommand;
import recipeditor.ui.AddMode;
import recipeditor.ui.Ui;

public class Parser {

    public Command parseCommand(String input) {
        String[] parsed = input.split(" ");
        String commandWord = parsed[0].toLowerCase();
        String argument = input.replace(commandWord, ""); // TODO: what is this

        switch (commandWord) {
        case AddCommand.COMMAND_TYPE:
            return parseAddCommand();
        case ListCommand.COMMAND_TYPE:
            return new ListCommand();
        case ExitCommand.COMMAND_TYPE:
            return new ExitCommand();
        case DeleteCommand.COMMAND_TYPE:
            return new DeleteCommand(0); // TODO: This is dummy variable only
        case ViewCommand.COMMAND_TYPE:
            return new ViewCommand();
        default:
            return new InvalidCommand();
        }

    }

    private Command parseAddCommand() {
        AddMode add = new AddMode(); // Switch to Add Mode in here
        add.enterAddMode();
        add.exitAddMode();
        Ui.showMessage("Is the recipe valid? " + String.valueOf(add.isValid));
        return new AddCommand(add.isValid, add.addedRecipe); // Pass validty and potential recipe to AddCommand
    }


}
