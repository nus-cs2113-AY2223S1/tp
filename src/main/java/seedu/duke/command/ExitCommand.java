package seedu.duke.command;

import seedu.duke.Duke;
import seedu.duke.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(Ui ui) {
        ui.output("Farewell!");
        Duke.isProgramFinished = true;
    }

}
