package seedu.duke.parsers;

import seedu.duke.command.flightcommand.AddFlightCommand;
import seedu.duke.command.Command;
import seedu.duke.command.flightcommand.DeleteFlightCommand;
import seedu.duke.command.flightcommand.ListFlightCommand;
import seedu.duke.exceptions.SkyControlException;

public class FlightParser extends Parser {
    public static Command parse(String[] inputWords) throws SkyControlException {
        checkOperation(inputWords);
        switch (operation) {
        case "add":
            command = new AddFlightCommand();
            //FlightList.addFlight(operation);
            break;
        case "list":
            command = new ListFlightCommand();
            //FlightList.printFlights();
            break;
        case "delete":
            command = new DeleteFlightCommand();
            //FlightList.deleteFlight(operation);
            break;
        default:
            throw new SkyControlException(ui.getErrorMessage());
        }
        return command;
    }
}

