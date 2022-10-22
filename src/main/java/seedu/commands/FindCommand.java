package seedu.commands;

import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoCommandArgumentException;
import seedu.exception.UnneededArgumentsException;
import seedu.parser.Parser;

import java.util.HashMap;

/**
 * Finds the carpark and prints out the informaiton of the carpark based on the carparkId input
 * CarparkId is not case sensitive
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String carparkID;

    public FindCommand(String carparkID) {
        this.carparkID = carparkID;
    }

    @Override
    public CommandResult execute() throws NoCarparkFoundException {
        final Carpark result = findThisCarpark(carparkID);
        return new CommandResult(result.toString());
    }

    /**
     * Finds the carpark with the associated carparkId
     *
     * @param searchString String of the carparkId that user has inputted.
     * @return Carpark with associated carparkId
     * @throws NoCarparkFoundException No such carparkId exists in the API.
     */
    public Carpark findThisCarpark(String searchString) throws NoCarparkFoundException {
        if (CarparkList.carparkHashMap.get(searchString.toLowerCase()) == null) {
            throw new NoCarparkFoundException();
        } else {
            return CarparkList.carparkHashMap.get(searchString.toLowerCase());
        }
    }


}
