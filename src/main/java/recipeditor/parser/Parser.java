package recipeditor.parser;

import recipeditor.command.AddCommand;
import recipeditor.command.InvalidCommand;
import recipeditor.command.Command;
import recipeditor.ui.AddMode;


public class Parser {


    public Command parseCommand(String input) {
        String[] parsed = input.split(" ");
        String commandWord = parsed[0].toLowerCase();
        String argument = input.replace(commandWord, "");
        switch (commandWord) {
        case AddCommand.TYPE:
            return parseAddCommand();
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
        AddMode add = new AddMode(); // Switch to Edit mode in here
        add.enterAddMode();
        return new AddCommand();
    }

}
