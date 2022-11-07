package seedu.commands;

import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.NoCarparkFoundException;

/**
 * Finds the carpark and prints out the informaiton of the carpark based on the carparkId input.
 * CarparkId is not case sensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_WORD_SHORT = "fin";
    public static final int NUMBER_OF_ARGUMENTS = 1;
    private final String carparkID;

    /**
     * Constructor for the {@link FindCommand} class. Passes in a carparkID and carparkList.
     *
     * @param carparkID ID to search for.
     * @param carparkList carparkList to search against.
     */
    public FindCommand(String carparkID, CarparkList carparkList) {
        this.carparkID = carparkID;
        this.carparkList = carparkList;
    }

    /**
     * Executes FindCommand.
     *
     * @return CommandResult of FindCommand.
     * @throws NoCarparkFoundException If no such carparkId exists in the API.
     */
    @Override
    public CommandResult execute() throws NoCarparkFoundException {
        final Carpark result = findThisCarpark(carparkID);
        return new CommandResult(result.getDetailViewString(), CommandStatus.MESSAGE);
    }

    /**
     * Finds the carpark with the associated carparkId.
     *
     * @param searchString String of the carparkId that user has inputted.
     * @return Carpark with associated carparkId.
     * @throws NoCarparkFoundException No such carparkId exists in the API.
     */
    public Carpark findThisCarpark(String searchString) throws NoCarparkFoundException {
        return carparkList.findCarpark(searchString);
    }
}
