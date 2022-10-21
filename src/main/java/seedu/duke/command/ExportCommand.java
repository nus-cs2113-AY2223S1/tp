package seedu.duke.command;

import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.utils.Link;

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";

    public ExportCommand(String[] input) {
        super(input);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {

        String nusModLink = Link.getLink(state);

        ui.addMessage(getExecutionMessage());
        ui.addMessage(nusModLink, true);
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return "Here is your NUSMod Link: ";
    }
}
