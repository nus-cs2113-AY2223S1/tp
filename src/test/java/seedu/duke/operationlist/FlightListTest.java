package seedu.duke.operationlist;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.parsers.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FlightListTest {
    protected static String addLineInput = "flight add fn/sq712 a/SINGAPORE Airlines "
            + "d/bangkok t/1600 gn/b1 tl/T1 c/03-3";
    protected static String deleteLineInputWithError = "flight delete f/sq712";
    protected static String deleteLineInput = "flight delete fn/sq712";
    protected static Parser parserTest = new FlightList();
    protected static FlightList testFlightList = new FlightList();


    @Test
    public void deleteOperation_inputWithError_exceptionThrown() {
        try {
            Command testCommand = parserTest.parse(deleteLineInputWithError);
            testCommand.execute(testFlightList, deleteLineInputWithError);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), e.getMessage());
        }

    }

    @Test
    public void checkDeleteOperation() throws SkyControlException {
        Command testCommand = parserTest.parse(deleteLineInput);
        testCommand.execute(testFlightList, deleteLineInput);
        assertEquals(0, testFlightList.flights.size());
    }
}