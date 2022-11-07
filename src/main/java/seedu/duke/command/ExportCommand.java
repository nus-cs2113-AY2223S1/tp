package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.parser.Parser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.utils.Link;

/**
 * Generates a shareable NUSMOD link.
 */
public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String COMMAND_USAGE = "export";
    public static final String COMMAND_DESCRIPTION = "Generates an NUSMod Link to be exported to the browser.";

    public ExportCommand(String[] input) throws YamomException {
        super(input);
        Parser.singleWordCommandError(input);
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
        return "Here is your NUSMod Link:";
    }
}
