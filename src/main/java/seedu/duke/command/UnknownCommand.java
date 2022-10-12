package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand(String[] input) {
        super(input);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return "Sorry, I do not understand your command. Enter \"help\" for the available commands";
    }
}
