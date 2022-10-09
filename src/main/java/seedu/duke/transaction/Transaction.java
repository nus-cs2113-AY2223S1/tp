package seedu.duke.transaction;

import seedu.duke.id.IdGenerator;
import seedu.duke.item.Item;
import seedu.duke.parser.DateParser;
import seedu.duke.user.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private final String transactionId;
    private final Item item;
    private final User lender;
    private final User borrower;
    private final int duration;
    private final LocalDate createdAt;
    private LocalDate returnedAt;
    private boolean isFinished;

    public Transaction(Item item, User borrower, int duration, String createdAt) {
        this.transactionId = IdGenerator.generateId();
        //these three id will be changed to Item + User + User when having Item and User class
        this.item = item;
        this.lender = item.getOwner();
        this.borrower = borrower;
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
        String itemId = "ItemID: " + item.getItemId() + " ";
        String usersId = "LenderID: " + lender.getUserId() + " BorrowerID: " + borrower.getUserId() + " ";

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
