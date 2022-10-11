package seedu.duke.command.passengercommand;

import seedu.duke.command.Command;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.operationlist.OperationList;

public class AddPassengerCommand extends Command {
    @Override
    public void execute(OperationList passengers, String lineInput) {
        try {
            getPassengerDetail(lineInput);
            passengers.addOperation(passengerDetail);
        } catch (SkyControlException e) {
            ui.showError(e.getMessage());
        }
    }
}
