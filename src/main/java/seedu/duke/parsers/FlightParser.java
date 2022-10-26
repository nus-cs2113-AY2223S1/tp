package seedu.duke.parsers;

import seedu.duke.command.flightcommand.AddFlightCommand;
import seedu.duke.command.Command;
import seedu.duke.command.flightcommand.DeleteFlightCommand;
import seedu.duke.command.flightcommand.ListFlightCommand;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.operationlist.FlightList;

//@@author Franky4566
public class FlightParser extends Parser {
    public static Command parse(String[] inputWords) throws SkyControlException {
        checkOperation(inputWords);
        switch (operation) {
        case "add":
            command = new AddFlightCommand();
            break;
        case "list":
            command = new ListFlightCommand();
            break;
        case "delete":
            command = new DeleteFlightCommand();
            break;
        case "delay":
//            FlightList.delayOperation(command.toString());
            break;
        default:
            throw new SkyControlException(ui.getErrorMessage());
        }
        return command;
    }
}

