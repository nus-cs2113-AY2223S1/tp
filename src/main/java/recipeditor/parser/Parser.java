package recipeditor.parser;

import recipeditor.command.AddCommand;
import recipeditor.command.DeleteCommand;
import recipeditor.command.ListCommand;

public class Parser {

    public static String parseCommand(String input) {
        String[] commandAndParams = input.split(" ", 2);
        String command = commandAndParams[ 0 ];
        switch (command) {
        //case ByeCommand.BYE_COMMAND:
        //    return new ByeCommand();
        case AddCommand.COMMANDTYPE:
            return "add command";
        case DeleteCommand.COMMANDTYPE:
            return "delete command";
        case ListCommand.COMMANDTYPE:
            return "list command";
        default:
            return "invalid command";
        }
    }
}
