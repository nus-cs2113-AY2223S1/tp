package seedu.duke.parsers;

import seedu.duke.command.flightcommand.AddFlightCommand;
import seedu.duke.command.Command;
import seedu.duke.command.flightcommand.DeleteFlightCommand;
import seedu.duke.command.flightcommand.ListFlightCommand;
import seedu.duke.exceptions.SkyControlException;

//@@author Franky4566
public class FlightParser extends Parser {

    private static final String ADD_COMMAND = "add";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";

    public static Command parse(String[] inputWords) throws SkyControlException {
        checkOperation(inputWords);
        switch (operation) {
        case ADD_COMMAND:
            command = new AddFlightCommand();
            break;
        case LIST_COMMAND:
            command = new ListFlightCommand();
            break;
        case DELETE_COMMAND:
            command = new DeleteFlightCommand();
            break;
        default:
            throw new SkyControlException(ui.getErrorMessage());
        }
        return command;
    }
}

