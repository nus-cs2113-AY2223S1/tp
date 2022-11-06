//@@author FeliciaBeatrice

package seedu.duke.parsermanager;

import seedu.duke.ClientList;
import seedu.duke.command.Command;
import seedu.duke.command.CommandDeleteClient;
import seedu.duke.exception.NotIntegerException;
import seedu.duke.exception.parsedeleteexception.parsedeleteclientexception.EmptyDeleteClientDetailException;
import seedu.duke.exception.parsedeleteexception.parsedeleteclientexception.ExtraDeleteClientFlagsException;
import seedu.duke.exception.parsedeleteexception.parsedeleteclientexception.InvalidDeleteClientIndexException;
import seedu.duke.exception.parsedeleteexception.parsedeleteclientexception.MissingDeleteClientFlagException;
import seedu.duke.exception.parsedeleteexception.parsedeleteclientexception.ParseDeleteClientException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.DELETE_CLIENT_FLAGS;
import static seedu.duke.Messages.MESSAGE_NOT_INTEGER;

public class ParseDeleteClient extends Parser {
    private final String commandDescription;
    private final ClientList clientList;


    private static final int CORRECT_FLAG_POSITION = 0;
    private static final int INDEX_POSITION = 2;


    public ParseDeleteClient(String deleteCommandDescription, ClientList clientList) {
        this.commandDescription = deleteCommandDescription;
        this.clientList = clientList;
    }

    @Override
    public Command parseCommand() throws ParseDeleteClientException,
            NotIntegerException {
        try {
            checkForEmptyDetails(commandDescription);

            ArrayList<String> deleteClientDetailsString = processCommandDetails(commandDescription);
            ArrayList<Integer> deleteClientDetailsInt = convertProcessedCommandDetailsToInteger(
                    deleteClientDetailsString);

            int clientIndex = deleteClientDetailsInt.get(0);
            checkForInvalidClientIndexDelete(clientIndex);
            return new CommandDeleteClient(clientIndex);
        } catch (NotIntegerException e) {
            throw new NotIntegerException(MESSAGE_NOT_INTEGER);
        }
    }

    private ArrayList<String> processCommandDetails(String rawCommandDetail)
            throws ParseDeleteClientException {

        String[] flags = DELETE_CLIENT_FLAGS;
        int[] flagIndexPositions = getFlagIndexPositions(rawCommandDetail, flags);
        checkForMissingFlags(flagIndexPositions);
        checkForExtraFlags(flagIndexPositions);
        return extractCommandDetails(rawCommandDetail, flags, flagIndexPositions);
    }

    private void checkForExtraFlags(int[] flagIndexPositions) throws ExtraDeleteClientFlagsException {
        if (flagIndexPositions[0] != CORRECT_FLAG_POSITION) {
            throw new ExtraDeleteClientFlagsException();
        }
    }

    private void checkForInvalidClientIndexDelete(int clientIndex) throws InvalidDeleteClientIndexException {
        int currentListSize = clientList.getCurrentListSize();
        if (clientIndex < 0 || clientIndex >= currentListSize) {
            throw new InvalidDeleteClientIndexException();
        }
    }

    private void checkForMissingFlags(int[] flagIndexPositions) throws MissingDeleteClientFlagException {
        for (int flagIndex : flagIndexPositions) {
            if (!isFlagPresent(flagIndex)) {
                throw new MissingDeleteClientFlagException();
            }
        }
    }

    private boolean isFlagPresent(int flagIndexPosition) {
        return (flagIndexPosition != -1);
    }

    private void checkForEmptyDetails(String commandDetail) throws EmptyDeleteClientDetailException {
        boolean isEmptyDetail = isEmptyString(commandDetail);
        if (isEmptyDetail) {
            throw new EmptyDeleteClientDetailException();
        }
    }
}
