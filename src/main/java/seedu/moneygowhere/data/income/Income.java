package seedu.moneygowhere.data.income;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//@@author penguin-s

/**
 * Stores data associated with an income.
 */
@SuppressWarnings("unused")
public class Income {
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private BigDecimal amount;

    public Income(String name,
                  LocalDateTime dateTime,
                  String description,
                  BigDecimal amount) {
        assert !(name == null || name.isEmpty() || name.trim().isEmpty()) :
                "There must be a name";
        assert amount != null : "There must be an amount";
        assert dateTime != null : "There must be a date and time";
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
