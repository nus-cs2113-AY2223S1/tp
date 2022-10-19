package seedu.duke.parsers;

import seedu.duke.command.passengercommand.AddPassengerCommand;
import seedu.duke.command.Command;
import seedu.duke.command.passengercommand.DeletePassengerCommand;
import seedu.duke.command.passengercommand.ListPassengerCommand;
import seedu.duke.exceptions.SkyControlException;

//@@author shengiv
public class PassengerParser extends Parser {

    public static Command parse(String[] inputWords) throws SkyControlException {
        checkOperation(inputWords);
        if (isAdd) {
            command = new AddPassengerCommand();
        } else if (isDelete) {
            command = new DeletePassengerCommand();
        } else if (isList) {
            command = new ListPassengerCommand();
        } else {
            throw new SkyControlException(ui.getErrorMessage());
        }
        return command;
    }
}
