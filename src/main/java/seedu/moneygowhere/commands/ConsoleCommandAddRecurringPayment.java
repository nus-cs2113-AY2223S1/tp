package seedu.moneygowhere.commands;

import java.math.BigDecimal;

/**
 * Stores the command Add-RecurringPayment and its arguments.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandAddRecurringPayment extends ConsoleCommand {
    private String name;
    private int interval;
    private String description;
    private BigDecimal amount;
    private String category;
    private String currency;

    public ConsoleCommandAddRecurringPayment(
            String name,
            int interval,
            String description,
            BigDecimal amount,
            String category,
            String currency
    ) {
        this.name = name;
        this.interval = interval;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.currency = currency;
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

    public String getCategory() {
        return category;
    }

    public String getCurrency() {
        return currency;
    }
}
