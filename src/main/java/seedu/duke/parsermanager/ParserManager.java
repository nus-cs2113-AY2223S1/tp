package seedu.duke.parsermanager;

//@@author wilsonngja
import seedu.duke.ClientList;
import seedu.duke.PropertyList;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.pairunpair.pair.ClientAlreadyPairedException;
import seedu.duke.exception.DukeParseException;
import seedu.duke.exception.pairunpair.pair.ExistingPairException;
import seedu.duke.exception.pairunpair.unpair.NoExistingPairException;
import seedu.duke.exception.UndefinedSubCommandTypeException;
import seedu.duke.parsermanager.check.CommandCheckClientParser;
import seedu.duke.parsermanager.check.CommandCheckPropertyParser;
import seedu.duke.parsermanager.delete.CommandDeleteClientParser;
import seedu.duke.parsermanager.delete.CommandDeletePropertyParser;
import seedu.duke.parsermanager.pairunpair.CommandPairParser;
import seedu.duke.parsermanager.pairunpair.CommandUnpairParser;
import seedu.duke.parsermanager.add.CommandAddClientParser;
import seedu.duke.parsermanager.add.CommandAddPropertyParser;
import seedu.duke.parsermanager.find.CommandFindClientParser;
import seedu.duke.parsermanager.find.CommandFindPropertyParser;


import java.util.ArrayList;

import static seedu.duke.CommandStructure.COMMAND_ADD;
import static seedu.duke.CommandStructure.COMMAND_CHECK;
import static seedu.duke.CommandStructure.COMMAND_DELETE;
import static seedu.duke.CommandStructure.COMMAND_EXIT;
import static seedu.duke.CommandStructure.COMMAND_FIND;
import static seedu.duke.CommandStructure.COMMAND_LIST;
import static seedu.duke.CommandStructure.COMMAND_PAIR;
import static seedu.duke.CommandStructure.COMMAND_UNPAIR;
import static seedu.duke.CommandStructure.COMMAND_HELP;
import static seedu.duke.CommandStructure.EVERYTHING_TAG;
import static seedu.duke.CommandStructure.PROPERTY_TAG;
import static seedu.duke.CommandStructure.CLIENT_TAG;
import static seedu.duke.CommandStructure.PAIR_TAG;
import static seedu.duke.Messages.MESSAGE_CHECK_CLIENT_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_CHECK_PROPERTY_WRONG_FORMAT;
import static seedu.duke.Messages.MESSAGE_COMMAND_UNDEFINED;
import static seedu.duke.Messages.MESSAGE_INCORRECT_LIST_DETAILS;
import static seedu.duke.Messages.MESSAGE_MISSING_SUB_COMMAND_TYPE;


public class ParserManager {
    private static ClientList clientList;
    private static PropertyList propertyList;

    private static final String EMPTY_SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final int MAX_LENGTH = 2;
    private static final int COMMAND_TYPE_INDEX = 0;
    private static final int SUB_COMMAND_INDEX = 0;
    private static final int COMMAND_FLAG_INDEX = 1;
    private static final int COMMAND_DESCRIPTION_INDEX = 1;
    private static final int SINGLE_LENGTH = 1;


    public ParserManager(ClientList clientL, PropertyList propertyL) {
        clientList = clientL;
        propertyList = propertyL;
    }

    public Parser parseCommand(String input) throws DukeParseException, ExistingPairException,
            ClientAlreadyPairedException, NoExistingPairException, InvalidCommandException {

        ArrayList<String> processedCommandDetails = splitCommandAndCommandType(input);
        String commandType = processedCommandDetails.get(COMMAND_TYPE_INDEX);
        String commandDetail = processedCommandDetails.get(COMMAND_FLAG_INDEX);

        Parser parser;
        switch (commandType) {
        case COMMAND_ADD:
            parser = parseAddCommand(commandDetail);
            break;
        case COMMAND_DELETE:
            parser = parseDeleteCommand(commandDetail);
            break;
        case COMMAND_PAIR:
            parser = new CommandPairParser(commandDetail);
            break;
        case COMMAND_UNPAIR:
            parser = new CommandUnpairParser(commandDetail);
            break;
        case COMMAND_CHECK:
            parser = parseCheckCommand(commandDetail);
            break;
        case COMMAND_LIST:
            parser = parseListCommand(commandDetail);
            break;
        case COMMAND_FIND:
            parser = parseFindCommand(commandDetail);
            break;
        case COMMAND_EXIT:
            return new ParseExit(commandDetail);
        case COMMAND_HELP:
            return new CommandHelpParser(input);
        default:
            return new ParseUndefined();
        }
        return parser;
    }

    private Parser parseAddCommand(String commandDetail) throws UndefinedSubCommandTypeException,
            InvalidCommandException {
        ArrayList<String> processedAddCommandDetails = splitCommandAndCommandType(commandDetail);
        String subAddCommandType = processedAddCommandDetails.get(SUB_COMMAND_INDEX);
        String addCommandDescription = processedAddCommandDetails.get(COMMAND_DESCRIPTION_INDEX);

        boolean isAddProperty = subAddCommandType.equals(PROPERTY_TAG);
        boolean isAddClient = subAddCommandType.equals(CLIENT_TAG);

        if (isAddProperty) {
            return new CommandAddPropertyParser(addCommandDescription, propertyList);
        } else if (isAddClient) {
            return new CommandAddClientParser(addCommandDescription, clientList);
        } else {
            throw new UndefinedSubCommandTypeException(MESSAGE_MISSING_SUB_COMMAND_TYPE);
        }
    }

    private Parser parseDeleteCommand(String commandDetail) throws UndefinedSubCommandTypeException,
            InvalidCommandException {
        ArrayList<String> processedDeleteCommandDetails = splitCommandAndCommandType(commandDetail);
        String subDeleteCommandType = processedDeleteCommandDetails.get(SUB_COMMAND_INDEX);
        String deleteCommandDescription = processedDeleteCommandDetails.get(COMMAND_DESCRIPTION_INDEX);

        boolean isDeleteProperty = subDeleteCommandType.equals(PROPERTY_TAG);
        boolean isDeleteClient = subDeleteCommandType.equals(CLIENT_TAG);

        if (isDeleteProperty) {
            return new CommandDeletePropertyParser(deleteCommandDescription, propertyList);
        } else if (isDeleteClient) {
            return new CommandDeleteClientParser(deleteCommandDescription, clientList);
        } else {
            throw new UndefinedSubCommandTypeException(MESSAGE_MISSING_SUB_COMMAND_TYPE);
        }
    }

    private Parser parseCheckCommand(String commandDetail) throws UndefinedSubCommandTypeException,
            InvalidCommandException {
        ArrayList<String> processedCheckCommandDetail = splitCommandAndCommandType(commandDetail);
        String subCheckCommandType = processedCheckCommandDetail.get(SUB_COMMAND_INDEX);

        boolean isProperty = subCheckCommandType.equals(PROPERTY_TAG);
        boolean isClient = subCheckCommandType.equals(CLIENT_TAG);

        if (isClient) {
            String commandDescription = processedCheckCommandDetail.get(COMMAND_DESCRIPTION_INDEX);
            return new CommandCheckClientParser(commandDescription, clientList);
        } else if (isProperty) {
            String commandDescription = processedCheckCommandDetail.get(COMMAND_DESCRIPTION_INDEX);
            return new CommandCheckPropertyParser(commandDescription);
        } else {
            throw new UndefinedSubCommandTypeException(MESSAGE_CHECK_CLIENT_WRONG_FORMAT
                    + MESSAGE_CHECK_PROPERTY_WRONG_FORMAT);
        }
    }

    //@@author zoranabc201

    private Parser parseListCommand(String commandDetail) throws UndefinedSubCommandTypeException {
        ArrayList<String> listCommandTypeAndFlags = getListCommandType(commandDetail);
        boolean isListProperty = listCommandTypeAndFlags.get(SUB_COMMAND_INDEX).trim().equals(PROPERTY_TAG);
        boolean isListClient = listCommandTypeAndFlags.get(SUB_COMMAND_INDEX).equals(CLIENT_TAG);
        boolean isListEverything = listCommandTypeAndFlags.get(SUB_COMMAND_INDEX).equals(EVERYTHING_TAG);
        boolean isListPairs = listCommandTypeAndFlags.get(SUB_COMMAND_INDEX).equals(PAIR_TAG);
        if (isListProperty) {
            return new ParseListProperty(listCommandTypeAndFlags.get(COMMAND_FLAG_INDEX));
        } else if (isListClient) {
            return new ParseListClient(listCommandTypeAndFlags.get(COMMAND_FLAG_INDEX));
        } else if (isListEverything && listCommandTypeAndFlags.get(COMMAND_FLAG_INDEX).isEmpty()) {
            return new ParseListEverything();
        } else if (isListPairs) {
            return new ParseListPair(listCommandTypeAndFlags.get(COMMAND_FLAG_INDEX));
        } else {
            throw new UndefinedSubCommandTypeException(MESSAGE_INCORRECT_LIST_DETAILS);
        }
    }
    //@@author

    private Parser parseFindCommand(String commandDetail) throws UndefinedSubCommandTypeException,
            InvalidCommandException {
        ArrayList<String> findCommandTypeAndFlag = splitCommandAndCommandType(commandDetail);
        String findSubCommandType = findCommandTypeAndFlag.get(SUB_COMMAND_INDEX);
        boolean isFindClient = findSubCommandType.equals(CLIENT_TAG);
        boolean isFindProperty = findSubCommandType.equals(PROPERTY_TAG);

        String findCommandDescription = findCommandTypeAndFlag.get(COMMAND_DESCRIPTION_INDEX);
        if (isFindClient) {
            return new CommandFindClientParser(findCommandDescription);
        } else if (isFindProperty) {
            return new CommandFindPropertyParser(findCommandDescription);
        } else {
            throw new UndefinedSubCommandTypeException(MESSAGE_MISSING_SUB_COMMAND_TYPE);
        }
    }


    private ArrayList<String> splitCommandAndCommandType(String fullCommandDetail) throws InvalidCommandException {
        String[] inputDetails = fullCommandDetail.split(EMPTY_SPACE, MAX_LENGTH);

        String commandFlag = "";
        String commandType = inputDetails[COMMAND_TYPE_INDEX];
        try {
            commandFlag = fullCommandDetail.replaceFirst(commandType, EMPTY_STRING).trim();
        } catch (Exception e) {
            throw new InvalidCommandException(MESSAGE_COMMAND_UNDEFINED);
        }


        ArrayList<String> processedCommandDetails = new ArrayList<>();
        processedCommandDetails.add(commandType);
        processedCommandDetails.add(commandFlag);

        return processedCommandDetails;
    }

    //@@author zoranabc201
    private ArrayList<String> getListCommandType(String commandDetail) {
        ArrayList<String> listCommandTypeAndFlags = new ArrayList<>();
        String[] listTypeAndFlagsArray = commandDetail.split(EMPTY_SPACE, MAX_LENGTH);
        listCommandTypeAndFlags.add(listTypeAndFlagsArray[SUB_COMMAND_INDEX].trim());
        if (listTypeAndFlagsArray.length == SINGLE_LENGTH) {
            listCommandTypeAndFlags.add(EMPTY_STRING);
        } else {
            listCommandTypeAndFlags.add(listTypeAndFlagsArray[COMMAND_FLAG_INDEX].trim());
        }
        return listCommandTypeAndFlags;
    }
}