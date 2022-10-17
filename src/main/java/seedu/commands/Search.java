package seedu.commands;

import seedu.data.CarparkList;
import seedu.parser.search.Sentence;
import seedu.ui.Ui;


/**
 * Represents the 'search' command to authenticate user API key
 */
public class Search {
    private final Ui ui = new Ui();

    public static CarparkList runSearch(CarparkList carparkList, Sentence searchQuery){
        return carparkList.searchStrings(searchQuery);
    }

}
