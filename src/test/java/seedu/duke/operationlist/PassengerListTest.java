package seedu.duke.operationlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import seedu.duke.parsers.Parser;
import seedu.duke.command.Command;
import seedu.duke.exceptions.SkyControlException;




public class PassengerListTest {
    protected static String addLineInput = "passenger add n/Ivan dod/08011998 dt/2145 fn/sq832 "
            + "gn/05 bg/01 sn/17d bt/2100";
    protected static String lineInputWithError = "passenger delete n/Ivan";
    protected static String deleteLineInput = "passenger delete n/Ivan fn/sq832 sn/17d";
    protected static PassengerList testPassengerList = new PassengerList();
    protected static Parser parserTest = new PassengerList();
    protected static String errorMessage = "The system is unable to delete the specified passenger "
            + "\nas he/she is not found in the passenger list "
            + "or his/her \ndetail have been input incorrectly.";


    @Test
    void checkAddOperation() throws SkyControlException {
        Command testCommand = parserTest.parse(addLineInput);
        testCommand.execute(testPassengerList, addLineInput);
        testPassengerList.getNumberOfPassengers();
        assertEquals(1, testPassengerList.numOfPassengers);
        assertEquals("IVAN", testPassengerList.name);
        assertEquals("08011998", testPassengerList.departureDate);
        assertEquals("2145", testPassengerList.departureTime);
        assertEquals("SQ832", testPassengerList.flightNumber);
        assertEquals("05", testPassengerList.gateNumber);
        assertEquals(1, testPassengerList.boardingGroup);
        assertEquals("17D", testPassengerList.seatNumber);
        assertEquals("2100", testPassengerList.boardingTime);
    }

    @Test
    void deletionWithout_relevantFields_exceptionThrown() {
        try {
            Command testCommand = parserTest.parse(lineInputWithError);
            testCommand.execute(testPassengerList, lineInputWithError);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), e.getMessage());
        }
    }

    @Test
    void checkDeleteOperation() throws SkyControlException {
        Command testCommand = parserTest.parse(deleteLineInput);
        testCommand.execute(testPassengerList, deleteLineInput);
        testPassengerList.getNumberOfPassengers();
        assertEquals(0, testPassengerList.numOfPassengers);
    }
}


