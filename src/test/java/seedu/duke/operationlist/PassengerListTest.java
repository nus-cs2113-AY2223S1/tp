package seedu.duke.operationlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.parsers.Parser;
import seedu.duke.command.Command;
import seedu.duke.exceptions.SkyControlException;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PassengerListTest {
    protected SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
    protected Date dateTest = new Date();
    protected  String dateTodayTest = formatter.format(dateTest);
    protected static String addLineInput = "passenger add n/Ivan dt/2145 fn/sq832 "
            + "gn/05 bg/01 sn/17d bt/2100";
    protected static String deleteLineInput = "passenger delete n/Ivan fn/sq832 sn/17d dt/2145";
    protected static PassengerList testPassengerList = new PassengerList();

    @Test
    void checkAddOperation() throws SkyControlException {
        Command testCommand = Parser.parse(addLineInput);
        testCommand.execute(testPassengerList, addLineInput);
        testPassengerList.getNumberOfPassengers();
        assertEquals(1, testPassengerList.numOfPassengers);
        assertEquals("IVAN", testPassengerList.name);
        assertEquals(dateTodayTest, testPassengerList.departureDate);
        assertEquals("2145", testPassengerList.departureTime);
        assertEquals("SQ832", testPassengerList.flightNumber);
        assertEquals("05", testPassengerList.gateNumber);
        assertEquals(1, testPassengerList.boardingGroup);
        assertEquals("17D", testPassengerList.seatNumber);
        assertEquals("2100", testPassengerList.boardingTime);
    }

    @Test
    void checkDeleteOperation() throws SkyControlException {
        Command testCommand = Parser.parse(deleteLineInput);
        testCommand.execute(testPassengerList, deleteLineInput);
        testPassengerList.getNumberOfPassengers();
        assertEquals(0, testPassengerList.numOfPassengers);
    }
}


