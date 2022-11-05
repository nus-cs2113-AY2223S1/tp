package seedu.duke.commands;

import seedu.duke.Module;
import seedu.duke.ModuleList;
import seedu.duke.UI;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

import java.util.ArrayList;

public class Overview extends Command {

    private ArrayList<Module> modules = ModuleList.modules;

    /**
     * Constructor to initialize an object of Overview class
     */
    public Overview() {
    }

    @Override
    public void execute(ModuleList modulelist) throws InvalidInputContentException {
        ModuleList ml = new ModuleList(modules);
        UI.overview(ml);
    }
}
