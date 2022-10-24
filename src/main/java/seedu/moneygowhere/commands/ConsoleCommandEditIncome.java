package seedu.moneygowhere.commands;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Stores the command Edit-Income and its arguments.
 */
@SuppressWarnings("unused")
public class ConsoleCommandEditIncome extends ConsoleCommand {
    private int incomeIndex;
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private BigDecimal amount;

    public ConsoleCommandEditIncome(
            int incomeIndex,
            String name,
            LocalDateTime dateTime,
            String description,
            BigDecimal amount
    ) {
        this.incomeIndex = incomeIndex;
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.amount = amount;
    }

    public int getIncomeIndex() {
        return incomeIndex;
    }

    public void setIncomeIndex(int incomeIndex) {
        this.incomeIndex = incomeIndex;
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
}
