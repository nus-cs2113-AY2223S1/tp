package seedu.parser;

import seedu.exception.NoCommandArgumentException;
import seedu.exception.UnneededArgumentsException;

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
    public Command parseInputString(String input) throws NoCommandArgumentException, UnneededArgumentsException {
        Command command;
        boolean hasCommandArgumentFlag = hasCommandArguments(input);
        if (input.equals(COMMAND_EXIT)) {
            command = Command.EXIT;
        } else {
            String instruction = input.trim().split("\\s+")[0];
            switch (instruction) {
            case COMMAND_FIND:
                if (!hasCommandArgumentFlag) {
                    throw new NoCommandArgumentException("find");
                }
                command = Command.FIND;
                break;
            case COMMAND_UPDATE:
                if (hasCommandArgumentFlag) {
                    throw new UnneededArgumentsException("update");
                }
                command = Command.UPDATE;
                break;
            case COMMAND_AUTH:
                if (!hasCommandArgumentFlag) {
                    throw new NoCommandArgumentException("auth");
                }
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

    public boolean hasCommandArguments(String input) {
        String[] words = input.trim().split("\\s+");
        return words.length > 1;
    }
}
