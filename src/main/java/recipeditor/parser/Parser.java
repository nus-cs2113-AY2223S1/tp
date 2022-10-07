package recipeditor.parser;

import recipeditor.command.*;

public class Parser {

    public static String parseCommand(String input) {
        String[] commandAndParams = input.split(" ", 2);
        String command = commandAndParams[ 0 ];
        switch (command) {
        case ByeCommand.commandType:
            new ByeCommand();
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
