package seedu.parser;

/**
 * Class to deal with parsing commands.
 */
public class Parser {
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_UPDATE = "update";
    private static final String COMMAND_AUTH = "auth";
    private static final String COMMAND_LIST = "list";

    /**
     * To convert the user input into commands for the program.
     * @param input User input
     * @return Command that user wants to do.
     */
    public Command parseInputString(String input) {
        Command command;
        if (input.equalsIgnoreCase(COMMAND_EXIT)) {
            command = Command.EXIT;
        } else {
            String instruction = input.split("\\s+")[0];
            switch (instruction.toLowerCase()) {
            case COMMAND_FIND:
                command = Command.FIND;
                break;
            case COMMAND_UPDATE:
                command = Command.UPDATE;
                break;
            case COMMAND_AUTH:
                command = Command.AUTH;
                break;
            case COMMAND_LIST:
                command = Command.LIST;
                break;
            default:
                command = Command.INVALID;
                break;
            }
        }
        return command;
    }
}
