package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.check.CommandCheckExtraParametersException;
import seedu.duke.exception.check.checkclient.CheckClientMissingFlagException;
import seedu.duke.exception.check.checkproperty.CheckPropertyMissingFlagException;
import seedu.duke.parsermanager.check.CommandCheckClientParser;
import seedu.duke.parsermanager.check.CommandCheckPropertyParser;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckTest {

    public static final String COMMAND_DETAILS_MISSING_FLAG = "1";
    public static final String COMMAND_DETAILS_EXTRA_FLAG = "n/1 i/1";


    @Test
    void checkForMissingFlags_missingCheckClientFlag_exceptionThrown() {
        ClientList clientList = new ClientList();
        CommandCheckClientParser commandCheckClientParser =
                new CommandCheckClientParser(COMMAND_DETAILS_MISSING_FLAG, clientList);
        assertThrows(CheckClientMissingFlagException.class, commandCheckClientParser::parseCommand);
    }

    @Test
    void checkForMissingFlags_missingCheckPropertyFlag_exceptionThrown() {
        CommandCheckPropertyParser commandCheckPropertyParser =
                new CommandCheckPropertyParser(COMMAND_DETAILS_MISSING_FLAG);
        assertThrows(CheckPropertyMissingFlagException.class, commandCheckPropertyParser::parseCommand);
    }

    @Test
    void checkExtraArguments_extraFlagCheckClient_exceptionThrown() {
        ClientList clientList = new ClientList();
        CommandCheckClientParser commandCheckClientParser =
                new CommandCheckClientParser(COMMAND_DETAILS_EXTRA_FLAG, clientList);
        assertThrows(CommandCheckExtraParametersException.class, commandCheckClientParser::parseCommand);
    }

    @Test
    void checkExtraArguments_extraFlagCheckProperty_exceptionThrown() {
        CommandCheckPropertyParser commandCheckPropertyParser =
                new CommandCheckPropertyParser(COMMAND_DETAILS_EXTRA_FLAG);
        assertThrows(CommandCheckExtraParametersException.class, commandCheckPropertyParser::parseCommand);
    }

}
