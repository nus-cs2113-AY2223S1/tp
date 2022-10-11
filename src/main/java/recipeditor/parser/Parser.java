package recipeditor.parser;

import recipeditor.command.AddCommand;
import recipeditor.command.InvalidCommand;
import recipeditor.command.ListCommand;
import recipeditor.command.Command;
import recipeditor.command.DeleteCommand;
<<<<<<< Updated upstream
import recipeditor.command.ExitCommand;
=======
import recipeditor.exception.ExcessArgumentException;
>>>>>>> Stashed changes
import recipeditor.ui.AddMode;
import recipeditor.ui.Ui;


public class Parser {


    public Command parseCommand(String input) {
        String[] parsed = input.split(" ");
        String commandWord = parsed[0].toLowerCase();
        //String argument = input.replace(commandWord, ""); TODO: what is this

        switch (commandWord) {
        case AddCommand.TYPE:
            return parseAddCommand();
        case ListCommand.TYPE:
            return new ListCommand();
        case ExitCommand.TYPE:
            return new ExitCommand();
        case DeleteCommand.COMMAND_TYPE:
            try {
                int index = Integer.parseInt(parsed[1]) - 1;
                checkForExcessArgument(parsed, 2);
                return new DeleteCommand(index);
            } catch (NumberFormatException | ExcessArgumentException e) {
                System.out.format("Exception: Wrong command Format%n" +
                        "Try the command in correct format: mark <index of task>%n");
                return new InvalidCommand();
            }
        case ViewCommand.COMMAND_TYPE:
            try {
                int index = Integer.parseInt(parsed[1]) - 1;
                checkForExcessArgument(parsed, 2);
                return new ViewCommand(index);
            } catch (NumberFormatException | ExcessArgumentException e) {
                System.out.format("Exception: Wrong command Format%n" +
                        "Try the command in correct format: view <index of task>%n");
                return new InvalidCommand();
            }
        default:
            return new InvalidCommand();
        }

    }

    //    public static Command parseCommand(String input) {
    //        String[] commandAndParams = input.split(" ", 2);
    //        String command = commandAndParams[ 0 ];
    //        switch (command) {
    //        case ByeCommand.BYE_COMMAND:
    //            return new ByeCommand();
    //        case AddCommand.ADD_COMMAND():
    //            return new AddCommand();
    //        case DeleteCommand.ADD_COMMAND():
    //            return new DeleteCommand();
    //        default:
    //            return new InvalidCommand();
    //        }
    //    }


    private Command parseAddCommand() {
        AddMode add = new AddMode(); // Switch to Add Mode in here
        add.enterAddMode();
        add.exitAddMode();
        Ui.showMessage("Is the recipe valid?" + String.valueOf(add.isValid));
        return new AddCommand(add.isValid, add.addedRecipe); // Pass validty and potential recipe to AddCommand
    }

    private void checkForExcessArgument(String[] args, int length)
            throws ExcessArgumentException {
        if(args.length > length) {
            throw new ExcessArgumentException();
        }
    }

}
