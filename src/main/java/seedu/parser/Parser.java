package seedu.parser;

import seedu.exception.NoCommandArgumentException;
import seedu.exception.UnneededArgumentsException;

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
    public Command parseInputString(String input) throws NoCommandArgumentException, UnneededArgumentsException {
        Command command;
        boolean hasCommandArgumentFlag = hasCommandArguments(input);
        if (input.equals(COMMAND_EXIT)) {
            command = Command.BYE;
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
