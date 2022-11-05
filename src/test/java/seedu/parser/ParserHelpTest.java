package seedu.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import commands.Command;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;

public class ParserHelpTest {
    @Test
    void testHelp() throws FileWriteException, NoCarparkFoundException, InvalidCommandException {
        String input = "help";
        Command command = new Parser().parseCommand(input, null, null, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Here are the list of available commands to use! Refer to the user guide at "
                + "https://ay2223s1-cs2113-t17-4.github.io/tp/UserGuide.html for more information.\n"
                + "`help` or `h` \t: To display all possible commands.\n"
                + "`exit` or `e` \t: To quit parKING.\n"
                + "`list` or `l` \t: List the carparks and its details.\n"
                + "`auth API_KEY` or `a API_KEY` \t: to authenticate your personal API key.\n"
                + "`auth default` or `a default` \t: to authenticate using the default key provided by parKING.\n"
                + "`auth status` or `a status` \t: to get the authentication status.\n"
                + "`update` or `u` \t: To fetch the latest data from LTA.\n"
                + "`filter QUERY` or `fil QUERY`\t: Filter carparks based on Carpark information.\n"
                + "`filter -address QUERY` or `fil -add QUERY` \t: Filter carparks based on its Carpark address.\n"
                + "`filter -id QUERY` or `fil -id QUERY` \t: Filter carparks based on its Carpark Id.\n"
                + "`find CARPARK_ID` or `fin CARPARK_ID` \t: Display information about the specific queried carpark.\n"
                + "`favourite list` or `fav list` \t: to get the list of favourited carparks.\n"
                + "`favourite CARPARK_ID` or `fav CARPARK_ID` \t: favourite carpark by its ID.\n"
                + "`unfavourite CARPARK_ID` or `ufav CARPARK_ID` \t: unfavourite carpark by its ID.", result);
    }

    @Test
    void testHelpCaps() throws FileWriteException, NoCarparkFoundException, InvalidCommandException {
        String input = "HELP";
        Command command = new Parser().parseCommand(input, null, null, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Here are the list of available commands to use! Refer to the user guide at "
                + "https://ay2223s1-cs2113-t17-4.github.io/tp/UserGuide.html for more information.\n"
                + "`help` or `h` \t: To display all possible commands.\n"
                + "`exit` or `e` \t: To quit parKING.\n"
                + "`list` or `l` \t: List the carparks and its details.\n"
                + "`auth API_KEY` or `a API_KEY` \t: to authenticate your personal API key.\n"
                + "`auth default` or `a default` \t: to authenticate using the default key provided by parKING.\n"
                + "`auth status` or `a status` \t: to get the authentication status.\n"
                + "`update` or `u` \t: To fetch the latest data from LTA.\n"
                + "`filter QUERY` or `fil QUERY`\t: Filter carparks based on Carpark information.\n"
                + "`filter -address QUERY` or `fil -add QUERY` \t: Filter carparks based on its Carpark address.\n"
                + "`filter -id QUERY` or `fil -id QUERY` \t: Filter carparks based on its Carpark Id.\n"
                + "`find CARPARK_ID` or `fin CARPARK_ID` \t: Display information about the specific queried carpark.\n"
                + "`favourite list` or `fav list` \t: to get the list of favourited carparks.\n"
                + "`favourite CARPARK_ID` or `fav CARPARK_ID` \t: favourite carpark by its ID.\n"
                + "`unfavourite CARPARK_ID` or `ufav CARPARK_ID` \t: unfavourite carpark by its ID.", result);

    }

    @Test
    void testHelpExtraParameters() throws FileWriteException, NoCarparkFoundException, InvalidCommandException {
        String input = "help please";
        Command command = new Parser().parseCommand(input, null, null, null);
        String result = command.execute().showToUser;
        Assertions.assertEquals("There were unrecognized arguments after the `help` command. "
                + "Please try the `help` command again by itself.", result);
    }
}
