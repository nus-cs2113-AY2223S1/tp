package seedu.duke.parsers;

import seedu.duke.command.flightcommand.DelayFlightCommand;
import seedu.duke.command.flightcommand.ModifyFlightNumCommand;
import seedu.duke.command.Command;
import seedu.duke.command.flightcommand.ModifyGateNumCommand;
import seedu.duke.exceptions.SkyControlException;

/**
 * Parses user input for commands involving modification of existing details.
 */
public class ModificationParser extends Parser {

    protected static boolean isFlightNumber;
    protected static boolean isGateNumber;
    protected static boolean isDepartureTime;
    protected static final String FLIGHT_NUMBER_DELIMITER = "fn/";
    protected static final String GATE_NUMBER_DELIMITER = "gn/";
    protected static final String DEPARTURE_TIME_DELIMITER = "dt/";
    protected static final int INPUT_WORDS_LENGTH = 3;
    protected static final int DETAIL_NUMBER = 2;

    /**
     * Parses the array of inputWords and checks which command to be returned based on user input.
     *
     * @param inputWords User input in the form of an array of words.
     * @return command modification command specified by the user.
     * @throws SkyControlException If incorrect modify message is input into CLI
     */
    public static Command parse(String[] inputWords) throws SkyControlException {
        checkInputWords(inputWords);
        checkDetailToBeModified(inputWords);
        if (isFlightNumber && isGateNumber && isDepartureTime) {
            throw new SkyControlException(ui.getErrorMessage());
        } else if (isFlightNumber) {
            command = new ModifyFlightNumCommand();
        } else if (isGateNumber) {
            command = new ModifyGateNumCommand();
        } else if (isDepartureTime) {
            command = new DelayFlightCommand();
        } else {
            throw new SkyControlException(ui.getErrorMessage());
        }
        return command;
    }

    /**
     * Checks which detail is being modified and stores the values in boolean objects
     * to specify the attribute being modified.
     *
     * @param inputWords User input in the form of an array of words.
     * @throws SkyControlException If incorrect input is entered for modify
     */
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
