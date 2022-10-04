package seedu.moneygowhere.commands;

import seedu.moneygowhere.data.expense.Expense;

import java.math.BigDecimal;
import java.util.Comparator;

public class ConsoleCommandSortExpense extends ConsoleCommand {
    private String type;

    public ConsoleCommandSortExpense(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Comparator<Expense> sortByDate = new Comparator<Expense>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            //placeholder as date is not a type yet
            String expenseName1 = expense1.getName();
            String expenseName2 = expense2.getName();

            return expenseName1.compareTo(expenseName2);
        }
    };

    /**
     * Comparator sorts from A to Z.
     */
    public Comparator<Expense> sortByAlphabet = new Comparator<Expense>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            String expenseName1 = expense1.getName();
            String expenseName2 = expense2.getName();

            return expenseName1.compareTo(expenseName2);
        }
    };

    /**
     * Comparator sorts by amount, in ascending order.
     */
    public Comparator<Expense> sortByAmount = new Comparator<Expense>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            BigDecimal expenseName1 = expense1.getAmount();
            BigDecimal expenseName2 = expense2.getAmount();

            return expenseName1.compareTo(expenseName2);
        }
    };
}
