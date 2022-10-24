package seedu.moneygowhere.commands;

import java.math.BigDecimal;

//@@author xzynos

/**
 * Stores the command Edit-RecurringPayment and its arguments.
 */
@SuppressWarnings("unused")
public class ConsoleCommandEditRecurringPayment extends ConsoleCommand {
    private int recurringPaymentIndex;
    private String name;
    private int interval;
    private String description;
    private BigDecimal amount;
    private String category;
    private String currency;
    private String modeOfPayment;
    private boolean isRecurringPaymentIndexSet;
    private boolean isNameSet;
    private boolean isIntervalSet;
    private boolean isDescriptionSet;
    private boolean isAmountSet;
    private boolean isCategorySet;
    private boolean isCurrencySet;
    private boolean isModeOfPaymentSet;

    public ConsoleCommandEditRecurringPayment() {
        isRecurringPaymentIndexSet = false;
        isNameSet = false;
        isIntervalSet = false;
        isDescriptionSet = false;
        isAmountSet = false;
        isCategorySet = false;
        isCurrencySet = false;
        isModeOfPaymentSet = false;
    }

    public int getRecurringPaymentIndex() {
        return recurringPaymentIndex;
    }

    public void setRecurringPaymentIndex(int recurringPaymentIndex) {
        this.recurringPaymentIndex = recurringPaymentIndex;
        isRecurringPaymentIndexSet = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        isNameSet = true;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
        isIntervalSet = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        isDescriptionSet = true;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
        isAmountSet = true;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        isCategorySet = true;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
        isCurrencySet = true;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
        isModeOfPaymentSet = true;
    }

    public boolean isRecurringPaymentIndexSet() {
        return isRecurringPaymentIndexSet;
    }

    public boolean isNameSet() {
        return isNameSet;
    }

    public boolean isIntervalSet() {
        return isIntervalSet;
    }

    public boolean isDescriptionSet() {
        return isDescriptionSet;
    }

    public boolean isAmountSet() {
        return isAmountSet;
    }

    public boolean isCategorySet() {
        return isCategorySet;
    }

    public boolean isCurrencySet() {
        return isCurrencySet;
    }

    public boolean isModeOfPaymentSet() {
        return isModeOfPaymentSet;
    }
}
