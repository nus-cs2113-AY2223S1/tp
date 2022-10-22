package seedu.duke.parsermanager;

import seedu.duke.ClientList;
import seedu.duke.PairingList;
import seedu.duke.PropertyList;
import seedu.duke.exception.ClientAlreadyPairedException;
import seedu.duke.exception.DukeParseException;
import seedu.duke.exception.ExistingPairException;
import seedu.duke.exception.NoExistingPairException;
import seedu.duke.exception.UndefinedSubCommandTypeException;

import java.util.ArrayList;

import static seedu.duke.CommandStructure.CLIENT_FLAG;
import static seedu.duke.CommandStructure.COMMAND_ADD;
import static seedu.duke.CommandStructure.COMMAND_CHECK;
import static seedu.duke.CommandStructure.COMMAND_DELETE;
import static seedu.duke.CommandStructure.COMMAND_EXIT;
import static seedu.duke.CommandStructure.COMMAND_LIST;
import static seedu.duke.CommandStructure.COMMAND_PAIR;
import static seedu.duke.CommandStructure.COMMAND_UNPAIR;
import static seedu.duke.CommandStructure.PROPERTY_FLAG;
import static seedu.duke.CommandStructure.EVERYTHING_FLAG;
import static seedu.duke.Messages.MESSAGE_INCORRECT_LIST_DETAILS;
import static seedu.duke.Messages.MESSAGE_MISSING_SUB_COMMAND_TYPE;


public class ParserManager {
    private static ClientList clientList;
    private static PropertyList propertyList;
    private static PairingList pairingList;

    private static final String EMPTY_SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final int MAX_LENGTH = 2;
    private static final int COMMAND_TYPE_INDEX = 0;
    private static final int SUB_COMMAND_INDEX = 0;
    private static final int COMMAND_FLAG_INDEX = 1;
    private static final int COMMAND_DESCRIPTION_INDEX = 1;
    private static final String PROPERTY_FLAG = "-property";
    private static final String CLIENT_FLAG = "-client";


    public ParserManager(ClientList clientL, PropertyList propertyL, PairingList pairingL) {
        clientList = clientL;
        propertyList = propertyL;
        pairingList = pairingL;
    }

    public Parser parseCommand(String input) throws DukeParseException, ExistingPairException,
            ClientAlreadyPairedException, NoExistingPairException {

        ArrayList<String> processedCommandDetails = splitCommandAndCommandType(input);
        String commandType = processedCommandDetails.get(COMMAND_TYPE_INDEX);
        String commandDetail = processedCommandDetails.get(COMMAND_FLAG_INDEX);

        switch (commandType) {
        case COMMAND_ADD:
            ArrayList<String> processedAddCommandDetails = splitCommandAndCommandType(commandDetail);
            String subAddCommandType = processedAddCommandDetails.get(SUB_COMMAND_INDEX);
            String addCommandDescription = processedAddCommandDetails.get(COMMAND_DESCRIPTION_INDEX);

            boolean isAddProperty = subAddCommandType.equals(PROPERTY_FLAG);
            boolean isAddClient = subAddCommandType.equals(CLIENT_FLAG);

            if (isAddProperty) {
                return new ParseAddProperty(addCommandDescription, propertyList);
            } else if (isAddClient) {
                return new ParseAddClient(addCommandDescription, clientList);
            } else {
                throw new UndefinedSubCommandTypeException(MESSAGE_MISSING_SUB_COMMAND_TYPE);
            }
        case COMMAND_DELETE:
            ArrayList<String> processedDeleteCommandDetails = splitCommandAndCommandType(commandDetail);
            String subDeleteCommandType = processedDeleteCommandDetails.get(SUB_COMMAND_INDEX);
            String deleteCommandDescription = processedDeleteCommandDetails.get(COMMAND_DESCRIPTION_INDEX);

            boolean isDeleteProperty = subDeleteCommandType.equals(PROPERTY_FLAG);
            boolean isDeleteClient = subDeleteCommandType.equals(CLIENT_FLAG);

            if (isDeleteProperty) {
                return new ParseDeleteProperty(deleteCommandDescription, propertyList);
            } else if (isDeleteClient) {
                return new ParseDeleteClient(deleteCommandDescription, clientList);
            } else {
                throw new UndefinedSubCommandTypeException(MESSAGE_MISSING_SUB_COMMAND_TYPE);
            }
        case COMMAND_PAIR:
            return new ParsePair(commandDetail, clientList, propertyList, pairingList);
        case COMMAND_UNPAIR:
            return new ParseUnpair(commandDetail, clientList, propertyList, pairingList);
        case COMMAND_CHECK:
            ArrayList<String> processedCheckCommandDetail = splitCommandAndCommandType(commandDetail);
            String subCheckCommandType = processedCheckCommandDetail.get(SUB_COMMAND_INDEX);

            boolean isProperty = subCheckCommandType.equals(subCheckCommandType);

            if (isProperty) {
                return new ParseCheckProperty(commandDetail, propertyList);
            } else {
                throw new UndefinedSubCommandTypeException(MESSAGE_MISSING_SUB_COMMAND_TYPE);
            }
        case COMMAND_LIST:
            ArrayList<String> listCommandTypeAndFlags = getListCommandType(commandDetail);
            boolean isListProperty = listCommandTypeAndFlags.get(0).trim().equals(PROPERTY_FLAG);
            boolean isListClient = listCommandTypeAndFlags.get(0).equals(CLIENT_FLAG);
            boolean isListEverything = listCommandTypeAndFlags.get(0).equals(EVERYTHING_FLAG);
            if (isListProperty) {
                return new ParseListProperty(listCommandTypeAndFlags.get(1));
            } else if (isListClient) {
                return new ParseListClient(listCommandTypeAndFlags.get(1));
            } else if (isListEverything && listCommandTypeAndFlags.get(1).isEmpty()) {
                return new ParseListEverything();
            } else {
                throw new UndefinedSubCommandTypeException(MESSAGE_INCORRECT_LIST_DETAILS);
            }
        case COMMAND_EXIT:
            return new ParseExit(commandDetail);
        default:
            return new ParseUndefined();
        }
    }

    private ArrayList<String> splitCommandAndCommandType(String fullCommandDetail) {
        String[] inputDetails = fullCommandDetail.split(EMPTY_SPACE, MAX_LENGTH);

        String commandType = inputDetails[COMMAND_TYPE_INDEX];
        String commandFlag = fullCommandDetail.replaceFirst(commandType, EMPTY_STRING).trim();

        ArrayList<String> processedCommandDetails = new ArrayList<>();
        processedCommandDetails.add(commandType);
        processedCommandDetails.add(commandFlag);

        return processedCommandDetails;
    }

    private ArrayList<String> getListCommandType(String commandDetail) {
        ArrayList<String> listCommandTypeAndFlags = new ArrayList<>();
        String[] listTypeAndFlagsArray = commandDetail.split(EMPTY_SPACE, MAX_LENGTH);
        listCommandTypeAndFlags.add(listTypeAndFlagsArray[0].trim());
        if (listTypeAndFlagsArray.length == 1) {
            listCommandTypeAndFlags.add("");
        } else {
            listCommandTypeAndFlags.add(listTypeAndFlagsArray[1].trim());
        }
        return listCommandTypeAndFlags;
    }
}
