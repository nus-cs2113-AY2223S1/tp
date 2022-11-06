//@@author FeliciaBeatrice

package seedu.duke.parsermanager;

import seedu.duke.ClientList;
import seedu.duke.command.Command;
import seedu.duke.command.check.CommandCheckClient;
import seedu.duke.exception.NotIntegerException;
import seedu.duke.exception.check.CommandCheckExtraParametersException;
import seedu.duke.exception.check.CommandCheckNotIntegerException;
import seedu.duke.exception.check.ParseCheckException;
import seedu.duke.exception.check.checkclient.CheckClientInvalidIndexException;
import seedu.duke.exception.check.checkclient.CheckClientMissingFlagException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.CHECK_CLIENT_FLAGS;
import static seedu.duke.CommandStructure.START_INDEX;

public class CommandCheckClientParser extends Parser {
    private final String commandDescription;

    private final ClientList clientList;


    public CommandCheckClientParser(String checkCommandDescription, ClientList clientList) {
        this.commandDescription = checkCommandDescription;
        this.clientList = clientList;
    }

    @Override
    public Command parseCommand() throws ParseCheckException {
        try {
            ArrayList<String> stringCheckDetails = processCommandDetails(commandDescription);
            ArrayList<Integer> integerCheckDetails = convertProcessedCommandDetailsToInteger(stringCheckDetails);

            validateCheckClientDetails(integerCheckDetails);
            return new CommandCheckClient(integerCheckDetails);
        } catch (NotIntegerException e) {
            throw new CommandCheckNotIntegerException();
        }
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws ParseCheckException {

        String[] flags = CHECK_CLIENT_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions);
        checkExtraArguments(commandDescription, flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private void checkExtraArguments(String commandDescription, int[] flagIndexPositions)
            throws CommandCheckExtraParametersException {
        int firstFlagIndex = flagIndexPositions[START_INDEX];
        if (firstFlagIndex != START_INDEX) {
            String extraArgument = commandDescription.substring(START_INDEX, firstFlagIndex);
            throw new CommandCheckExtraParametersException(extraArgument);
        }
    }

    private void validateCheckClientDetails(ArrayList<Integer> clientDetails) throws CheckClientInvalidIndexException {
        int clientIndex = clientDetails.get(START_INDEX);
        checkForClientListIndexOutOfBounds(clientIndex);
    }

    private void checkForMissingFlags(int[] flagIndexPositions) throws CheckClientMissingFlagException {
        for (int flagIndex : flagIndexPositions) {
            if (!isFlagPresent(flagIndex)) {
                throw new CheckClientMissingFlagException();
            }
        }
    }

    private void checkForClientListIndexOutOfBounds(int clientIndex) throws CheckClientInvalidIndexException {
        if (clientIndex < START_INDEX || clientIndex > clientList.getCurrentListSize() - 1) {
            throw new CheckClientInvalidIndexException();
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != FLAG_ABSENT_RETURN_VALUE);
    }
}
