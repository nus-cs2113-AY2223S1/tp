package seedu.duke.command.passengercommand;

import seedu.duke.command.Command;
import seedu.duke.operationlist.OperationList;

public class ListPassengerCommand extends Command {

    @Override
    public void execute(OperationList passengers, String lineInput) {
        passengers.listOperation();
    }
}
