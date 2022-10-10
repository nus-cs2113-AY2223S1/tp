package seedu.moneygowhere.data.target;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Stores data associated with a target.
 */
@SuppressWarnings("unused")
public class Target {
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private BigDecimal amount;
    private BigDecimal current_amount;

    public Target(String name, LocalDateTime dateTime, String description, BigDecimal amount, BigDecimal current_amount) {
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.amount = amount;
        this.current_amount = current_amount;
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
        return current_amount;
    }

    public void setCurrentAmount(BigDecimal current_amount) {
        this.current_amount = current_amount;
    }
}

