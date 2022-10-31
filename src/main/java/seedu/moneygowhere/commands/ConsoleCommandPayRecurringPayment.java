package seedu.moneygowhere.commands;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class ConsoleCommandPayRecurringPayment extends ConsoleCommand {
    private int recurringPaymentIndex;
    private LocalDateTime dateTime;

    public ConsoleCommandPayRecurringPayment(int recurringPaymentIndex, LocalDateTime dateTime) {
        this.recurringPaymentIndex = recurringPaymentIndex;
        this.dateTime = dateTime;
    }

    public int getRecurringPaymentIndex() {
        return recurringPaymentIndex;
    }

    public void setRecurringPaymentIndex(int recurringPaymentIndex) {
        this.recurringPaymentIndex = recurringPaymentIndex;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
