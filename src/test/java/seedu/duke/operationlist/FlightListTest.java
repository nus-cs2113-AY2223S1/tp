package seedu.duke.operationlist;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.parsers.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightListTest {
    protected static String addLineInput = "flight add fn/sq712 a/SINGAPORE Airlines "
            + "d/bangkok dt/1600 gn/11 c/03-03";
    protected static String deleteLineInput = "flight delete sq712";
    protected static FlightList testFlightList = new FlightList();

    @Test
    void checkAddOperation() throws SkyControlException {
        Command testCommand = Parser.parse(addLineInput);
        testCommand.execute(testFlightList, addLineInput);
        testFlightList.getNumberOfFlights();
    }

    @Test
    void checkDeleteOperation() throws SkyControlException {
        Command testCommand = Parser.parse(deleteLineInput);
        testCommand.execute(testFlightList, deleteLineInput);
        assertEquals(0, testFlightList.flights.size());
    }
}