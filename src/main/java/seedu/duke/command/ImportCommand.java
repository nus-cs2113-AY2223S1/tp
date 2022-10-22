package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.Link;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    private static String nusModLink;


    public ImportCommand(String[] input) throws YamomException {
        super(input);

        if (input.length != 2) {
            throw new YamomException("No nusmod link given");
        }
        nusModLink = input[1];
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {

        Link.parseLink(nusModLink, state);
        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return "Timetable imported to YAMOM!";
    }
}
