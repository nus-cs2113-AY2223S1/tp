package seedu.moneygowhere.commands;

import java.math.BigDecimal;

/**
 * Stores the add-recurringpayment command and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandAddRecurringPayment extends ConsoleCommand {
    private String name;
    private int interval;
    private String description;
    private BigDecimal amount;

    public ConsoleCommandAddRecurringPayment(String name, int interval, String description, BigDecimal amount) {
        this.name = name;
        this.interval = interval;
        this.description = description;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getInterval() {
        return interval;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
