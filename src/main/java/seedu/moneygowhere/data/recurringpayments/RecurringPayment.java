package seedu.moneygowhere.data.recurringpayments;

import java.math.BigDecimal;

//@@author xzynos

/**
 * Stores data associated with a recurring payment.
 */
@SuppressWarnings("unused")
public class RecurringPayment {
    private String name;
    private int interval;
    private String description;
    private BigDecimal amount;
    private String category;
    private String currency;
    private String modeOfPayment;

    public RecurringPayment(
            String name,
            int interval,
            String description,
            BigDecimal amount,
            String category,
            String currency,
            String modeOfPayment
    ) {
        assert !(currency == null || currency.isEmpty() || currency.trim().isEmpty()) :
                "There must be a currency";
        assert !(name == null || name.isEmpty() || name.trim().isEmpty()) :
                "There must be a name";
        assert amount != null : "There must be an amount";
        this.name = name;
        this.interval = interval;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.currency = currency;
        this.modeOfPayment = modeOfPayment;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }
}
