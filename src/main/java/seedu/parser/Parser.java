package seedu.parser;


public class Parser {
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_EXIT = "bye";

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
            //use switch format when there are more commands in future
            if (instruction.equals(COMMAND_FIND)) {
                command = Command.FIND;
            } else {
                command = Command.INVALID;
            }
        }
        return command;
    }
}
