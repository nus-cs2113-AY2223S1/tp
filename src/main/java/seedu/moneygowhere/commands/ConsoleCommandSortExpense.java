package seedu.moneygowhere.commands;

import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.parser.ConsoleParserConfigurations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;

@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandSortExpense extends ConsoleCommand {
    private String type;
    private boolean order;


    /**
     * Reads in type & order of sorting to run sorting command later.
     *
     * @param type defines the type of sorting to be done later, be it by date, amount or alphabetical
     * @param order if true, sort by ascending. If false, sort by descending
     */
    public ConsoleCommandSortExpense(String type, String order) {
        this.type = type;
        if (order.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING)) {
            this.order = true;
        } else if (order.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_DESCENDING)) {
            this.order = false;
        }
    }

    public String getType() {
        return type;
    }

    public String getOrder() {
        if (order) {
            return ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING;
        }
        return ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_DESCENDING;
    }

    /**
     * Function to get the comparator based on type of sorting.
     *
     * @return a comparator that corresponds to the type of sorting
     */
    public Comparator<Expense> getComparator() {
        Comparator<Expense> comparator = null;
        if (type.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_DATE)) {
            comparator = sortByDate;
        } else if (type.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_ALPHABETICAL)) {
            comparator = sortByAlphabet;
        } else if (type.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_AMOUNT)) {
            comparator = sortByAmount;
        } else if (type.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_CURRENCY)) {
            comparator = sortByCurrency;
        }
        return comparator;
    }

    /**
     * Comparator sorts by date in ascending or descending order.
     */
    private Comparator<Expense> sortByDate = new Comparator<>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            LocalDateTime expenseDate1 = expense1.getDateTime();
            LocalDateTime expenseDate2 = expense2.getDateTime();
            if (expenseDate1.equals(expenseDate2)) {
                return sortByAlphabet.compare(expense1, expense2);
            }
            if (order) {
                return expenseDate1.compareTo(expenseDate2);
            }
            return expenseDate2.compareTo(expenseDate1);
        }
    };

    /**
     * Comparator sorts from A to Z if ascending order, Z to A if descending order.
     */
    private Comparator<Expense> sortByAlphabet = new Comparator<>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            String expenseName1 = expense1.getName();
            String expenseName2 = expense2.getName();
            if (order) {
                return expenseName1.compareTo(expenseName2);
            }
            return expenseName2.compareTo(expenseName1);
        }
    };

    /**
     * Comparator sorts by amount, in ascending/descending order.
     */
    private Comparator<Expense> sortByAmount = new Comparator<>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            BigDecimal expenseAmount1 = expense1.getAmount();
            BigDecimal expenseAmount2 = expense2.getAmount();
            if (expenseAmount1.equals(expenseAmount2)) {
                return sortByAlphabet.compare(expense1, expense2);
            }
            if (order) {
                return expenseAmount1.compareTo(expenseAmount2);
            }
            return expenseAmount2.compareTo(expenseAmount1);
        }
    };

    /**
     * Comparator sorts currencies from A to Z if ascending order, Z to A if descending order.
     */
    private Comparator<Expense> sortByCurrency = new Comparator<>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            String expenseCurrency1 = expense1.getCurrency();
            String expenseCurrency2 = expense2.getCurrency();
            if (expenseCurrency1.equalsIgnoreCase(expenseCurrency2)) {
                return sortByAmount.compare(expense1, expense2);
            }
            if (order) {
                return expenseCurrency1.compareTo(expenseCurrency2);
            }
            return expenseCurrency2.compareTo(expenseCurrency1);
        }
    };
}
