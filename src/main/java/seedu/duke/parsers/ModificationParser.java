package seedu.duke.parsers;

import seedu.duke.command.flightcommand.DelayFlightCommand;
import seedu.duke.command.flightcommand.ModifyFlightNumCommand;
import seedu.duke.command.Command;
import seedu.duke.command.flightcommand.ModifyGateNumCommand;
import seedu.duke.exceptions.SkyControlException;

public class ModificationParser extends Parser {

    protected static boolean isFlightNumber;
    protected static boolean isGateNumber;
    protected static boolean isDepartureTime;
    protected static final String FLIGHT_NUMBER_DELIMITER = "fn/";
    protected static final String GATE_NUMBER_DELIMITER = "gn/";
    protected static final String DEPARTURE_TIME_DELIMITER = "dt/";
    protected static final int INPUT_WORDS_LENGTH = 3;
    protected static final int DETAIL_NUMBER = 2;

    public static Command parse(String[] inputWords) throws SkyControlException {
        checkInputWords(inputWords);
        checkDetailToBeModified(inputWords);
        if (isFlightNumber && isGateNumber && isDepartureTime) {
            throw new SkyControlException(ui.getErrorMessage());
        } else if (isFlightNumber) {
            command = new ModifyFlightNumCommand();
        } else if (isGateNumber) {
            command = new ModifyGateNumCommand();
        } else if (isDepartureTime){
            command = new DelayFlightCommand();
        }else {
            throw new SkyControlException(ui.getErrorMessage());
        }
        return command;
    }

    private static void checkDetailToBeModified(String[] inputWords) throws SkyControlException {
        String modifiedDetail = inputWords[DETAIL_NUMBER];
        String delimiter = getDelimiter(modifiedDetail);
        isFlightNumber = delimiter.equals(FLIGHT_NUMBER_DELIMITER);
        isGateNumber = delimiter.equals(GATE_NUMBER_DELIMITER);
        isDepartureTime = delimiter.equals(DEPARTURE_TIME_DELIMITER);
    }

    private static void checkInputWords(String[] inputWords) throws SkyControlException {
        if (inputWords.length != INPUT_WORDS_LENGTH) {
            throw new SkyControlException(ui.getErrorMessage());
        }
    }

    private static String getDelimiter(String modifiedDetail) throws SkyControlException {
        String delimiter = null;
        try {
            delimiter = modifiedDetail.substring(0, 3);
        } catch (Exception e) {
            throw new SkyControlException(ui.getErrorMessage());
        }
        return delimiter;
    }
}
