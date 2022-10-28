package seedu.duke.command;

//@@author chydarren
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

/**
 * Represents an object that can be inherited by List and Stats command objects.
 */
public abstract class ListAndStatsCommand extends Command {
    //@@author chydarren
    private static final int UNDEFINED_PARAMETER = -1;
    private static final int CONTAIN_BOTH = 1;
    private static final int CONTAIN_EITHER = 2;
    private static final int CONTAIN_EITHER_INVALID = 3;
    private static final int FALSE = 0;
    private static final String WEEKS = "weeks";
    private static final String MONTHS = "months";
    private static Logger listStatsLogger = Logger.getLogger(ListAndStatsCommand.class.getName());

    //@@author paullowse
    public static int month;
    public static int year;
    public static String period;
    public static int number;

    public ListAndStatsCommand() {
        this.month = UNDEFINED_PARAMETER;
        this.year = UNDEFINED_PARAMETER;
        this.period = null;
        this.number = UNDEFINED_PARAMETER;

        //@@author chydarren
        listStatsLogger.setLevel(Level.SEVERE);
    }

    //@@author paullowse
    @Override
    public void setGlobalMonth(int month) {
        this.month = month;
    }

    @Override
    public void setGlobalYear(int year) {
        this.year = year;
    }

    @Override
    public void setGlobalNumber(int number) {
        this.number = number;
    }

    @Override
    public void setGlobalPeriod(String period) {
        this.period = period;
    }

    //@@author chydarren

    /**
     * Checks if the input contains month or/and year tags.
     *
     * @return 1 if both tags are given, 2 if only year is given, 3 if only month is given,
     *      0 if both are not given.
     */
    public static int containMonthYear() {
        if (month != UNDEFINED_PARAMETER && year != UNDEFINED_PARAMETER) {
            return CONTAIN_BOTH;
        } else if (year != UNDEFINED_PARAMETER) {
            return CONTAIN_EITHER;
        } else if (month != UNDEFINED_PARAMETER) {
            // Returns CONTAIN_EITHER_INVALID as it is not allowed for month to not have a year
            return CONTAIN_EITHER_INVALID;
        }
        return FALSE;
    }

    /**
     * Checks if the input contains period and/or number tags.
     *
     * @return 1 if both tags are given, 2 if either period/number is given, 0 if both are not given.
     */
    public static int containPeriodNumber() {
        if (period != null && number != UNDEFINED_PARAMETER) {
            return CONTAIN_BOTH;
        } else if (period != null || number != UNDEFINED_PARAMETER) {
            return CONTAIN_EITHER;
        }
        return FALSE;
    }

    /**
     * Parses the tags related to tag intervals and checks if there are any error in their combinations.
     *
     * @throws MoolahException If any of the below exception conditions are met.
     */
    public static void parseDateIntervalsTags() throws MoolahException {
        if (containMonthYear() != FALSE && containPeriodNumber() != FALSE) {
            // Throws an unsupported tag exception if tags are not supposed to be used together
            listStatsLogger.log(Level.WARNING, "Exception occurred as an invalid combination "
                    + "of tags has been given.");
            throw new GlobalUnsupportedTagException();
        } else if (containMonthYear() == CONTAIN_EITHER_INVALID) {
            // Throws a missing tag exception if number and period was not given together
            listStatsLogger.log(Level.WARNING, "Exception occurred as a month was given without "
                    + "a year.");
            throw new GlobalMissingYearTagException();
        } else if (containPeriodNumber() == CONTAIN_EITHER) {
            // Throws a missing tag exception if number and period was not given together
            listStatsLogger.log(Level.WARNING, "Exception occurred as number and period needs "
                    + "to be given together.");
            throw new GlobalMissingPeriodNumberTagException();
        }
    }

    //@@author paullowse

    /**
     * Gets the complete transactions list by date intervals.
     *
     * @param transactions  An instance of the TransactionList class.
     * @return An array list containing all transactions recorded in specified date interval.
     */
    public ArrayList<Transaction> getTimeTransactions(TransactionList transactions) {
        ArrayList<Transaction> timeTransactions = transactions.getTransactions();
        if (containMonthYear() == CONTAIN_BOTH) {
            timeTransactions = transactions.getTransactionsByMonth(year, month);
        } else if (containMonthYear() == CONTAIN_EITHER) {
            assert month == UNDEFINED_PARAMETER;
            timeTransactions = transactions.getTransactionsByYear(year);
        } else if (containPeriodNumber() == CONTAIN_BOTH && period == MONTHS) {
            timeTransactions = transactions.getTransactionsByMonthRange(LocalDate.now(), number);
        } else if (containPeriodNumber() == CONTAIN_BOTH && period == WEEKS) {
            timeTransactions = transactions.getTransactionsByWeekRange(LocalDate.now(), number);
        }

        return timeTransactions;
    }
}