package seedu.moneygowhere.commands;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Stores the edit-target command and its arguments.
 */
@SuppressWarnings("unused")
public class ConsoleCommandEditTarget extends ConsoleCommand {
    private int targetIndex;
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private BigDecimal amount;
    private BigDecimal currentAmount;

    public ConsoleCommandEditTarget(
            int targetIndex,
            String name,
            LocalDateTime dateTime,
            String description,
            BigDecimal amount,
            BigDecimal currentAmount) {
        this.targetIndex = targetIndex;
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.amount = amount;
        this.currentAmount = currentAmount;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
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
