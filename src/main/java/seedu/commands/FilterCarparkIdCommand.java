package seedu.commands;

import seedu.data.CarparkFilteredList;
import seedu.data.CarparkList;
import seedu.exception.NoCarparkFoundException;
import seedu.parser.search.Sentence;

/**
 * Represents a command to search for the carparks that contain the searchQuery.
 * Upon execution, it prints out all the carparks that contain the search keyword.
 */
public class FilterCarparkIdCommand extends Command {

    public static final String COMMAND_WORD = "filter -id";
    public static final String COMMAND_WORD_SHORT = "fil -id";
    private final CarparkList carparkList;

    private final Sentence searchQuery;

    /**
     * Constructor for SearchCommand
     *
     * @param carparkList carpark list of current api
     * @param searchQuery argument for the search command
     */
    public FilterCarparkIdCommand(CarparkList carparkList, Sentence searchQuery) {
        this.searchQuery = searchQuery;
        this.carparkList = carparkList;
    }


    @Override
    public CommandResult execute() {
        try {
            CarparkFilteredList result = carparkList.filterByCarparkId(searchQuery);
            if (result.getSearchListString().isEmpty()) {
                throw new NoCarparkFoundException();
            }
            carparkList.resetBoldForAllCarparks();
            return new CommandResult(result, CommandStatus.MESSAGE);
        } catch (NoCarparkFoundException e) {
            carparkList.resetBoldForAllCarparks();
            return new CommandResult(e.getMessage(), CommandStatus.FAIL);
        }
    }
}
