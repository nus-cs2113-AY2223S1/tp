package seedu.moneygowhere.commands;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Stores the edit-expense command and its arguments.
 */
@SuppressWarnings("unused")
public class ConsoleCommandEditExpense extends ConsoleCommand {
    private int expenseIndex;
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private BigDecimal amount;
    private String category;

    public ConsoleCommandEditExpense(
            int expenseIndex,
            String name,
            LocalDateTime dateTime,
            String description,
            BigDecimal amount,
            String category
    ) {
        this.expenseIndex = expenseIndex;
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public int getExpenseIndex() {
        return expenseIndex;
    }

    public void setExpenseIndex(int expenseIndex) {
        this.expenseIndex = expenseIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
