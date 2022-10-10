package seedu.moneygowhere.data.income;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Stores data associated with an income.
 */
@SuppressWarnings("unused")
public class Income {
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private BigDecimal amount;

    public Income(String name, LocalDateTime dateTime, String description, BigDecimal amount) {
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.amount = amount;
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

    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
