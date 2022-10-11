package seedu.duke.command.flightcommand;

import seedu.duke.command.Command;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.operationlist.OperationList;

public class DeleteFlightCommand extends Command {
    @Override
    public void execute(OperationList flights, String lineInput) {
        try {
            flights.deleteOperation(lineInput);
        } catch (SkyControlException e) {
            ui.showError(e.getMessage());
        }
    }
}
