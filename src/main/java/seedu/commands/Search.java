package seedu.commands;

import seedu.data.CarparkFilteredList;
import seedu.data.CarparkList;
import seedu.parser.search.Sentence;
import seedu.ui.Ui;


/**
 * Represents the 'search' command to search a list of carparks.
 */
public class Search {
    private final Ui ui = new Ui();

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

}
