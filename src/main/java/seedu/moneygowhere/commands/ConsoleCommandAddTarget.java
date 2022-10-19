package seedu.moneygowhere.commands;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Stores the add-target command and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandAddTarget extends ConsoleCommand {
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private BigDecimal amount;
    private BigDecimal currentAmount;

    public ConsoleCommandAddTarget(String name, LocalDateTime dateTime, String description, BigDecimal amount, BigDecimal currentAmount) {
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.amount = amount;
        this.currentAmount = currentAmount;
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

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal amount) {
        this.currentAmount = amount;
    }
}
