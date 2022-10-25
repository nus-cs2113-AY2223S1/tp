package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.GlobalMissingTagException;
import seedu.duke.exception.GlobalUnsupportedTagException;
import seedu.duke.exception.StatsInvalidTypeException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatsCommandTest {
    //@@author chydarren
    @Test
    public void execute_useMonthYearTagsNotForTimeInsights_exceptionThrown()  {
        StatsCommand statsCommand = new StatsCommand();
        TransactionList transactions = new TransactionList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        statsCommand.setGlobalMonth(3);
        statsCommand.setGlobalYear(2012);
        statsCommand.setStatsType("categorical_savings");

        assertThrows(
                GlobalUnsupportedTagException.class,
                () -> statsCommand.execute(transactions, ui, storage)
        );
    }

    @Test
    public void execute_useNoTagsForTimeInsights_exceptionThrown() {
        StatsCommand statsCommand = new StatsCommand();
        TransactionList transactions = new TransactionList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        statsCommand.setStatsType("time_insights");

        assertThrows(
                GlobalMissingTagException.class,
                () -> statsCommand.execute(transactions, ui, storage)
        );
    }

    @Test
    public void listStatsByStatsType_useInvalidStatsType_exceptionThrown() {
        StatsCommand statsCommand = new StatsCommand();
        TransactionList transactions = new TransactionList();

        statsCommand.setStatsType("moolah");

        assertThrows(
                StatsInvalidTypeException.class,
                () -> statsCommand.listStatsByStatsType(transactions)
        );
    }

    @Test
    public void listStatsByStatsType_useCategoricalSavingsStatsType_noError() {
        StatsCommand statsCommand = new StatsCommand();
        TransactionList transactions = new TransactionList();

        statsCommand.setStatsType("categorical_savings");

        assertDoesNotThrow(
                () -> statsCommand.listStatsByStatsType(transactions)
        );
    }
}
