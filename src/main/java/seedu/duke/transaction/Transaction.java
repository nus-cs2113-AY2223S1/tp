package seedu.duke.transaction;

import seedu.duke.id.IdGenerator;
import seedu.duke.parser.DateParser;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private final String transactionId;
    private final String itemId;
    private final String borrowerId;
    private final int duration;
    private final LocalDate createdAt;
    private LocalDate returnedAt;
    private boolean isFinished;

    public Transaction(String itemId, String borrowerId, int duration, String createdAt) {
        this.transactionId = IdGenerator.generateId();
        this.itemId = itemId;
        this.borrowerId = borrowerId;
        this.duration = duration;
        this.isFinished = false;
        this.createdAt = LocalDate.parse(createdAt);
        this.returnedAt = null;
    }

    public String getTxId() {
        return transactionId;
    }

    public Integer getDuration() {
        return duration;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setAsFinished() {
        this.isFinished = true;
        this.returnedAt = LocalDate.now();
    }

    public void setAsNotFinished() {
        this.isFinished = false;
        this.returnedAt = null;
    }

    public LocalDate getReturnDate() {
        return createdAt.plusDays(duration);
    }

    public boolean isOverdue() {
        if (!isFinished) {
            return getReturnDate().isBefore(LocalDate.now());
        }
        return false;
    }

    @Override
    public String toString() {
        String transactionIcon = "[" + (isFinished ? "X" : " ") + "] ";
        String transactionId = "TransactionID: " + this.transactionId + " ";
        String itemId = "ItemID: " + this.itemId + " ";
        String usersId = "BorrowerID: " + this.borrowerId + " ";

        if (!isFinished) {
            String overdueDays = " (" + ChronoUnit.DAYS.between(getReturnDate(), LocalDate.now()) + "day(s) overdue";
            String remainDays = " (" + ChronoUnit.DAYS.between(LocalDate.now(), getReturnDate()) + " day(s) remaining)";
            String returnDate = "ReturnDate: " + getReturnDate() + (isOverdue() ? overdueDays : remainDays);
            return transactionIcon + transactionId + itemId + usersId + returnDate;
        }
        String returnedDate = "ReturnedOn: " + DateParser.formatDateToString(returnedAt);
        return transactionIcon + transactionId + itemId + usersId + returnedDate;
    }
}
