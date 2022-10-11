package seedu.duke.command.flightcommand;

import seedu.duke.command.Command;
import seedu.duke.operationlist.OperationList;

public class AddFlightCommand extends Command {

    @Override
    public void execute(OperationList flights, String lineInput) {
        flights.addOperation(lineInput);
    }
}
