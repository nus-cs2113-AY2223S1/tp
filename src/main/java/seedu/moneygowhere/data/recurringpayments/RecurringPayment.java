package seedu.moneygowhere.data.recurringpayments;

import java.math.BigDecimal;

/**
 * Stores data associated with a recurring payment.
 */
@SuppressWarnings("unused")
public class RecurringPayment {
    private String name;
    private int interval;
    private String description;
    private BigDecimal amount;

    public RecurringPayment(String name, int interval, String description, BigDecimal amount) {
        this.name = name;
        this.interval = interval;
        this.description = description;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
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
