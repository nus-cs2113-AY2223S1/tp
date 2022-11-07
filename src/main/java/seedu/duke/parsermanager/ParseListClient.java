package seedu.duke.parsermanager;

//@@author zoranabc201
import seedu.duke.command.Command;
import seedu.duke.command.CommandListClients;
import seedu.duke.command.CommandListClientsWithTags;
import seedu.duke.exception.IncorrectListFlagsException;
import seedu.duke.CommandStructure;

import static seedu.duke.Messages.MESSAGE_INCORRECT_CLIENT_LIST_FLAG;

public class ParseListClient extends Parser {

    private final String commandTags;

    public ParseListClient(String listClientCommandDescription) {
        this.commandTags = listClientCommandDescription;
    }

    /**
     * Checks whether the input tags are present. If present, check their validity and returns the corresponding
     * command type.
     * @return CommandListClients object if no tags present. CommandListClientsWithTags if valid tag present.
     * @throws IncorrectListFlagsException if invalid tags present
     */

    @Override
    public Command parseCommand() throws IncorrectListFlagsException {
        if (commandTags.isEmpty()) {
            return new CommandListClients();
        } else if (isValidTag(commandTags)) {
            return new CommandListClientsWithTags(commandTags);
        } else {
            throw new IncorrectListFlagsException(MESSAGE_INCORRECT_CLIENT_LIST_FLAG);
        }
    }

    /**
     * Checks whether the TAG entered by the user is a valid one, as described in the UG.
     * @param commandTags Contains the information about what tags are entered by the user
     * @return true if valid flag. False otherwise.
     */

    boolean isValidTag(String commandTags) {
        switch (commandTags) {
        case CommandStructure.NAME_TAG:
            //deliberate fall through till SHORT_TAG
        case CommandStructure.CONTACT_NUMBER_TAG:
        case CommandStructure.EMAIL_TAG:
        case CommandStructure.BUDGET_TAG:
        case CommandStructure.SHORT_TAG:
            return true;
            //break not needed as we are returning
        default:
            return false;
        }
    }
}
