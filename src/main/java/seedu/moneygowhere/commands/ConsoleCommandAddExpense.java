package seedu.moneygowhere.commands;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Stores the add-expense command and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandAddExpense extends ConsoleCommand {
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private BigDecimal amount;
    private String category;
    private String remarks;

    public ConsoleCommandAddExpense(
            String name,
            LocalDateTime dateTime,
            String description,
            BigDecimal amount,
            String category,
            String remarks
    ) {
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
    
    public String getRemarks() {
        return remarks;
    }
}
