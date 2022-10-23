package seedu.commands;

import seedu.data.CarparkFilteredList;
import seedu.data.CarparkList;
import seedu.parser.search.Sentence;

/**
 * Represents a command to search for the carparks that contain the searchQuery.
 * Upon execution, it prints out all the carparks that contain the search keyword.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";
    private final CarparkList carparkList;

    private final Sentence searchQuery;

    /**
     * Constructor for SearchCommand
     *
     * @param carparkList carpark list of current api
     * @param searchQuery argument for the search command
     */
    public SearchCommand(CarparkList carparkList, Sentence searchQuery) {
        this.searchQuery = searchQuery;
        this.carparkList = carparkList;
    }

    /**
     * Runs a search in the {@link CarparkList} class.
     *
     * @param carparkList {@link CarparkList} to run the search on.
     * @param searchQuery The search query to search with as a {@link Sentence} object.
     * @return {@link CarparkList} that is filtered down to {@link seedu.data.Carpark Carpark} items containing all
     *      words in the searchQuery.
     */
    public static CarparkFilteredList runSearch(CarparkList carparkList, Sentence searchQuery) {
        return carparkList.filterByAllStrings(searchQuery);
    }

    @Override
    public CommandResult execute() {
        String result = runSearch(carparkList, searchQuery).getSearchListString();
        carparkList.resetBoldForAllCarparks();
        return new CommandResult(result);
    }
}
