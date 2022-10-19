package recipeditor.command;

import recipeditor.ui.Ui;

public class InvalidCommand extends Command {
    public static final String COMMAND_TYPE = "/invalid";
    public static String INVALID_MESSAGE = "This command is invalid! Please write again!";

    public CommandResult execute() {
        //TODO: Execution of command
        // This is only the dummy code for testing
        return new CommandResult(INVALID_MESSAGE);
    }

}
