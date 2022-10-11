package seedu.duke.command.passengercommand;

import seedu.duke.command.Command;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.operationlist.OperationList;

public class DeletePassengerCommand extends Command {
    @Override
    public void execute(OperationList passengers, String lineInput) {
        getPassengerDetail(lineInput);
        try {
            passengers.deleteOperation(passengerDetail);
        } catch (SkyControlException e) {
            ui.showError(e.getMessage());
        }
    }
}
