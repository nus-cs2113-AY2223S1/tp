package seedu.moneygowhere.commands;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class ConsoleCommandEditRecurringPayment extends ConsoleCommand {
    private int recurringPaymentIndex;
    private String name;
    private int interval;
    private String description;
    private BigDecimal amount;
    private boolean isRecurringPaymentIndexSet;
    private boolean isNameSet;
    private boolean isIntervalSet;
    private boolean isDescriptionSet;
    private boolean isAmountSet;

    public ConsoleCommandEditRecurringPayment() {
        isRecurringPaymentIndexSet = false;
        isNameSet = false;
        isIntervalSet = false;
        isDescriptionSet = false;
        isAmountSet = false;
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
}
