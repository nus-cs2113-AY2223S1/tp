package recipeditor.parser;

import recipeditor.command.AddCommand;
import recipeditor.command.InvalidCommand;
import recipeditor.command.ListCommand;
import recipeditor.command.Command;
import recipeditor.command.DeleteCommand;
import recipeditor.command.ExitCommand;
import recipeditor.ui.AddMode;


public class Parser {


    public Command parseCommand(String input) {
        String[] parsed = input.split(" ");
        String commandWord = parsed[0].toLowerCase();
        String argument = input.replace(commandWord, "");

        switch (commandWord) {
        case AddCommand.TYPE:
            return parseAddCommand();
        case ListCommand.TYPE:
            return new ListCommand();
        case ExitCommand.TYPE:
            return new ExitCommand();
        case DeleteCommand.TYPE:
            return new DeleteCommand(0); // TODO: This is dummy variable only
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
        return new AddCommand(); // At the moment, Add Command is like a Dummy Middle Man,
    }

}
