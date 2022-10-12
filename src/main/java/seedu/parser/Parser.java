package seedu.parser;

/**
 * Class to deal with parsing commands.
 */
public class Parser {
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_UPDATE = "update";
    private static final String COMMAND_AUTH = "auth";

    /**
     * To convert the user input into commands for the program.
     * @param input User input
     * @return Command that user wants to do.
     */
    public Command parseInputString(String input) {
        Command command;
        if (input.equals(COMMAND_EXIT)) {
            command = Command.BYE;
        } else {
            String instruction = input.split("\\s+")[0];
            switch (instruction) {
            case COMMAND_FIND:
                command = Command.FIND;
                break;
            case COMMAND_UPDATE:
                command = Command.UPDATE;
                break;
            case COMMAND_AUTH:
                command = Command.AUTH;
                break;
            default:
                command = Command.INVALID;
                break;
            }
        }
        return command;
    }
}
