package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.GlobalMissingPeriodNumberTagException;
import seedu.duke.exception.GlobalMissingYearTagException;
import seedu.duke.exception.GlobalUnsupportedTagException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ListCommandTest {
    //@@author chydarren
    @Test
    public void containMonthYear_YearAndMonth_expectContainBoth() {
        ListCommand listCommand = new ListCommand();
        listCommand.setGlobalYear(2022);
        listCommand.setGlobalMonth(10);

        assertEquals(listCommand.containMonthYear(), 1);
    }

    @Test
    public void containMonthYear_YearOnly_expectContainEither() {
        ListCommand listCommand = new ListCommand();
        listCommand.setGlobalYear(2022);

        assertEquals(listCommand.containMonthYear(), 2);
    }

    @Test
    public void containMonthYear_MonthOnly_expectContainEitherInvalid() {
        ListCommand listCommand = new ListCommand();
        listCommand.setGlobalMonth(10);

        assertEquals(listCommand.containMonthYear(), 3);
    }

    @Test
    public void containPeriodNumber_PeriodAndNumber_expectContainBoth() {
        ListCommand listCommand = new ListCommand();
        listCommand.setGlobalPeriod("weeks");
        listCommand.setGlobalNumber(5);

        assertEquals(listCommand.containPeriodNumber(), 1);
    }

    @Test
    public void containPeriodNumber_PeriodOnly_expectContainEither() {
        ListCommand listCommand = new ListCommand();
        listCommand.setGlobalPeriod("months");

        assertEquals(listCommand.containPeriodNumber(), 2);
    }

    @Test
    public void parseDateIntervalsTags_useYearAndPeriodNumberTags_exceptionThrown() {
        ListCommand listCommand = new ListCommand();
        listCommand.setGlobalYear(2022);
        listCommand.setGlobalPeriod("months");
        listCommand.setGlobalNumber(5);

        assertThrows(
                GlobalUnsupportedTagException.class,
                () -> listCommand.parseDateIntervalsTags()
        );
    }

    @Test
    public void parseDateIntervalsTags_useNumberWithoutPeriodTag_exceptionThrown() {
        ListCommand listCommand = new ListCommand();
        listCommand.setGlobalNumber(1);

        assertThrows(
                GlobalMissingPeriodNumberTagException.class,
                () -> listCommand.parseDateIntervalsTags()
        );
    }

    @Test
    public void parseDateIntervalsTags_usePeriodWithoutNumberTag_exceptionThrown() {
        ListCommand listCommand = new ListCommand();
        listCommand.setGlobalPeriod("weeks");

        assertThrows(
                GlobalMissingPeriodNumberTagException.class,
                () -> listCommand.parseDateIntervalsTags()
        );
    }

    @Test
    public void parseDateIntervalsTags_useMonthWithoutYearTag_exceptionThrown() {
        ListCommand listCommand = new ListCommand();
        listCommand.setGlobalMonth(10);

        assertThrows(
                GlobalMissingYearTagException.class,
                () -> listCommand.parseDateIntervalsTags()
        );
    }

    @Test
    public void parseDateIntervalsTags_useYearWithoutMonthTag_expectNoError() {
        ListCommand listCommand = new ListCommand();
        listCommand.setGlobalYear(2010);

        assertDoesNotThrow(
                () -> listCommand.parseDateIntervalsTags()
        );
    }
}
