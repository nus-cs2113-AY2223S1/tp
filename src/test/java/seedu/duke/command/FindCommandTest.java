package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.FindTransactionMissingKeywordsException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FindCommandTest {
    //@@author chydarren
    @Test
    public void checkFindFormat_noKeywords_exceptionThrown() {
        assertThrows(
            FindTransactionMissingKeywordsException.class,
            () -> FindCommand.checkFindFormat("")
        );
    }

    @Test
    public void checkFindFormat_oneKeyword_expectNoError() {
        assertDoesNotThrow(
            () -> FindCommand.checkFindFormat("Moolah")
        );
    }

    @Test
    public void checkFindFormat_moreThanOneKeyword_expectNoError() {
        assertDoesNotThrow(
            () -> FindCommand.checkFindFormat("Moolah Manager")
        );
    }
}
