package recipeditor.parser;

import recipeditor.command.ByeCommand;
import recipeditor.command.AddCommand;
import recipeditor.command.DeleteCommand;
import recipeditor.command.ListCommand;

public class Parser {

    public static String parseCommand(String input) {
        String[] commandAndParams = input.split(" ", 2);
        String command = commandAndParams[ 0 ];
        switch (command) {
        case ByeCommand.commandType:
            new ByeCommand();
            return "end of bye command";
        case AddCommand.commandType:
            return "add command";
        case DeleteCommand.commandType:
            return "delete command";
        case ListCommand.commandType:
            return "list command";
        default:
            return "invalid command";
        }
    }
}
