package seedu.moneygowhere.commands;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//@@author xzynos

/**
 * Stores the command Edit-Expense and its arguments.
 */
@SuppressWarnings("unused")
public class ConsoleCommandEditExpense extends ConsoleCommand {
    private int expenseIndex;
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private BigDecimal amount;
    private String category;
    private String remarks;
    private String currency;
    private String modeOfPayment;
    private boolean isExpenseIndexSet;
    private boolean isNameSet;
    private boolean isDateTimeSet;
    private boolean isDescriptionSet;
    private boolean isAmountSet;
    private boolean isCategorySet;
    private boolean isRemarksSet;
    private boolean isCurrencySet;
    private boolean isModeOfPaymentSet;

    public ConsoleCommandEditExpense() {
        isExpenseIndexSet = false;
        isNameSet = false;
        isDateTimeSet = false;
        isAmountSet = false;
        isCategorySet = false;
        isRemarksSet = false;
        isCurrencySet = false;
        isModeOfPaymentSet = false;
    }

    public int getExpenseIndex() {
        return expenseIndex;
    }

    public void setExpenseIndex(int expenseIndex) {
        this.expenseIndex = expenseIndex;
        isExpenseIndexSet = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        isNameSet = true;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        isDateTimeSet = true;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
        isRemarksSet = true;
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

    public boolean isExpenseIndexSet() {
        return isExpenseIndexSet;
    }

    public boolean isNameSet() {
        return isNameSet;
    }

    public boolean isDateTimeSet() {
        return isDateTimeSet;
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

    public boolean isRemarksSet() {
        return isRemarksSet;
    }

    public boolean isCurrencySet() {
        return isCurrencySet;
    }

    public boolean isModeOfPaymentSet() {
        return isModeOfPaymentSet;
    }
}
