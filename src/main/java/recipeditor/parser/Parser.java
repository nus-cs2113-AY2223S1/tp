package recipeditor.parser;

import recipeditor.command.AddCommand;
import recipeditor.command.ByeCommand;
import recipeditor.command.DeleteCommand;
import recipeditor.command.ListCommand;

public class Parser {

    public static String parseCommand(String input) {
        String[] commandAndParams = input.split(" ", 2);
        String command = commandAndParams[ 0 ];
        switch (command) {
        case ByeCommand.COMMAND_TYPE:
            new ByeCommand();
            return "end of bye command";
        case AddCommand.COMMAND_TYPE:
            return "add command";
        case DeleteCommand.COMMAND_TYPE:
            return "delete command";
        case ListCommand.COMMAND_TYPE:
            return "list command";
        default:
            return "invalid command";
        }
    }
}
