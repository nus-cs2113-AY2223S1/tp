package seedu.duke.parsers;

import seedu.duke.command.passengercommand.AddPassengerCommand;
import seedu.duke.command.Command;
import seedu.duke.command.passengercommand.DeletePassengerCommand;
import seedu.duke.command.passengercommand.ListPassengerCommand;
import seedu.duke.exceptions.SkyControlException;

//@@author shengiv

/**
 * Parses user input for passenger related commands.
 */
public class PassengerParser extends Parser {

    /**
     * Parses the array of inputWords and checks which command to be returned based on the
     * action word in user input.
     *
     * @param inputWords User input converted to array of words.
     * @return Passenger Command given by user in user input.
     * @throws SkyControlException If none of the valid operations are specified in the user input.
     */
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
