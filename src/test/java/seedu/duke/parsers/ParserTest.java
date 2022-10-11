package seedu.duke.parsers;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.passengercommand.AddPassengerCommand;
import seedu.duke.exceptions.SkyControlException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    protected static String lineInput =
            "Passenger add n/Ivan dod/08011998 dt/2145 fn/sq832 gn/05 bg/01 sn/17d bt/2100";

    @Test
    void checkPassengerEntity() {
        boolean ans = true;
        boolean result = Parser.isPassengerEntity(lineInput);
        assertEquals(ans, result);
    }

    @Test
    void checkExitEntity() {
        boolean ans = false;
        boolean result = Parser.isExitEntity(lineInput);
        assertEquals(ans, result);
    }

    @Test
    void checkFlightEntity() {
        boolean ans = false;
        boolean result = Parser.isFlightEntity(lineInput);
        assertEquals(ans,result);
    }

    @Test
    void checkMainParser() throws SkyControlException {
        assertThrows(SkyControlException.class,
            () -> Parser.parse("Hi"));
        AddPassengerCommand ans = new AddPassengerCommand();
        Command result = Parser.parse(lineInput);
        assertEquals(ans.getClass(), result.getClass());
    }
}
