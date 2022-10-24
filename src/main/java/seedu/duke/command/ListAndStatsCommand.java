package seedu.duke.command;

import seedu.duke.data.TransactionList;
import seedu.duke.data.transaction.Transaction;
import seedu.duke.exception.GlobalMissingPeriodNumberTagException;
import seedu.duke.exception.GlobalMissingYearTagException;
import seedu.duke.exception.GlobalUnsupportedTagException;
import seedu.duke.exception.MoolahException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DateCommand extends Command {
    private static final int UNDEFINED_PARAMETER = -1;
    private static final int TRUE_AND = 1;
    private static final int TRUE_OR = 2;
    private static final int TRUE_INVALID_OR = 3;
    private static final int FALSE = 0;
    private static final String WEEKS = "weeks";
    private static final String MONTHS = "months";
    private static Logger dateLogger = Logger.getLogger(DateCommand.class.getName());

    private int month;
    private int year;
    private String period;
    private int number;

    public DateCommand(int month, int year, String period, int number) {
        this.month = month;
        this.year = year;
        this.period = period;
        this.number = number;
    }

    /**
     * Checks if the input contains month or/and year tags.
     *
     * @return 1 if both tags are given, 2 if only year is given, 3 if only month is given,
     * 0 if both are not given.
     */
    public int containMonthYear() {
        if (month != UNDEFINED_PARAMETER && year != UNDEFINED_PARAMETER) {
            return TRUE_AND;
        } else if (year != UNDEFINED_PARAMETER) {
            return TRUE_OR;
        } else if (month != UNDEFINED_PARAMETER) {
            return TRUE_INVALID_OR;
        }
        return FALSE;
    }

    /**
     * Checks if the input contains period and/or number tags.
     *
     * @return 1 if both tags are given, 2 if either period/number is given, 0 if both are not given.
     */
    public int containPeriodNumber() {
        if (period != null && number != UNDEFINED_PARAMETER) {
            return TRUE_AND;
        } else if (period != null || number != UNDEFINED_PARAMETER) {
            return TRUE_OR;
        }
        return FALSE;
    }

    /**
     *
     */
    public void parseDateIntervalsTags() throws MoolahException {
        if (containMonthYear() != FALSE && containPeriodNumber() != FALSE) {
            dateLogger.log(Level.WARNING, "An exception has been caught as an invalid combination of tags "
                    + "has been given.");
            throw new GlobalUnsupportedTagException();
        } else if (containMonthYear() == TRUE_INVALID_OR) {
            // Throws a missing tag if number and period was not given together
            dateLogger.log(Level.WARNING, "An exception has been caught as a month was given without a year.");
            throw new GlobalMissingYearTagException();
        } else if (containPeriodNumber() == TRUE_OR) {
            // Throws a missing tag if number and period was not given together
            dateLogger.log(Level.WARNING, "An exception has been caught as number and period needs to be "
                    + "given together.");
            throw new GlobalMissingPeriodNumberTagException();
        }
    }

    /**
     * Gets the complete transactions list by date intervals.
     *
     * @return An array list containing all transactions recorded in specified date interval.
     */
    public ArrayList<Transaction> getTimeTransactions(TransactionList transactions) throws MoolahException {
        ArrayList<Transaction> timeTransactions = transactions.getTransactions();

        if (containMonthYear() == TRUE_AND) {
            timeTransactions = transactions.getTransactionsByMonth(year, month);
        } else if (containMonthYear() == TRUE_OR) {
            assert month == UNDEFINED_PARAMETER;
            timeTransactions = transactions.getTransactionsByYear(year);
        } else if (containPeriodNumber() == TRUE_AND && period == MONTHS) {
            timeTransactions = transactions.getTransactionsByMonthRange(LocalDate.now(), number);
        } else if (containPeriodNumber() == TRUE_AND && period == WEEKS) {
            timeTransactions = transactions.getTransactionsByWeekRange(LocalDate.now(), number);
        }

        return timeTransactions;
    }
}
