package seedu.duke.transaction;

import seedu.duke.id.IdGenerator;
import seedu.duke.parser.DateParser;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private final String transactionId;
    private final String item;
    private final String borrower;
    private final int duration;
    private final LocalDate createdAt;
    private LocalDate returnedAt;
    private boolean isFinished;

    public Transaction(String itemId, String borrowerId, int duration, LocalDate createdAt) {
        this.transactionId = IdGenerator.generateId();
        this.item = itemId;
        this.borrower = borrowerId;
        this.duration = duration;
        this.isFinished = false;
        this.createdAt = createdAt;
        this.returnedAt = null;
    }

    public String getTxId() {
        return transactionId;
    }

    public String getItem() {
        return item;
    }

    public String getBorrower() {
        return borrower;
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
        String itemId = "ItemID: " + this.item + " ";
        String usersId = "BorrowerID: " + this.borrower + " ";

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
