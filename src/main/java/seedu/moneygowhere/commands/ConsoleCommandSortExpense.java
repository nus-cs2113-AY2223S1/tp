package seedu.moneygowhere.commands;

import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.parser.ConsoleParser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

public class ConsoleCommandSortExpense extends ConsoleCommand {
    private String type;
    private String order;

    public ConsoleCommandSortExpense(String type, String order) {
        this.type = type;
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public Comparator<Expense> sortByDate = new Comparator<Expense>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            LocalDateTime expenseName1 = expense1.getDateTime();
            LocalDateTime expenseName2 = expense2.getDateTime();
            if (order.equalsIgnoreCase(ConsoleParser.CONSOLE_COMMAND_SORT_EXPENSE_ORDER_ASCENDING)) {
                return expenseName1.compareTo(expenseName2);
            }
            return expenseName2.compareTo(expenseName1);
        }
    };

    /**
     * Comparator sorts from A to Z if ascending order, Z to A if descending order.
     */
    public Comparator<Expense> sortByAlphabet = new Comparator<Expense>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            String expenseName1 = expense1.getName();
            String expenseName2 = expense2.getName();
            if (order.equalsIgnoreCase(ConsoleParser.CONSOLE_COMMAND_SORT_EXPENSE_ORDER_ASCENDING)) {
                return expenseName1.compareTo(expenseName2);
            }
            return expenseName2.compareTo(expenseName1);
        }
    };

    /**
     * Comparator sorts by amount, in ascending/descending order.
     */
    public Comparator<Expense> sortByAmount = new Comparator<Expense>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            BigDecimal expenseName1 = expense1.getAmount();
            BigDecimal expenseName2 = expense2.getAmount();
            if (order.equalsIgnoreCase(ConsoleParser.CONSOLE_COMMAND_SORT_EXPENSE_ORDER_ASCENDING)) {
                return expenseName1.compareTo(expenseName2);
            }
            return expenseName2.compareTo(expenseName1);
        }
    };
}
