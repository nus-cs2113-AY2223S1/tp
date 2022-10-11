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
    private final LocalDate returnedAt;

    public Transaction(String itemId, String borrowerId, int duration, LocalDate createdAt) {
        this.transactionId = IdGenerator.generateId();
        this.item = itemId;
        this.borrower = borrowerId;
        this.duration = duration;
        this.createdAt = createdAt;
        this.returnedAt = createdAt.plusDays(duration);
    }

    public Transaction(String transactionId, String itemId, String borrowerId, int duration, LocalDate createdAt) {
        this.transactionId = transactionId;
        this.item = itemId;
        this.borrower = borrowerId;
        this.duration = duration;
        this.createdAt = createdAt;
        this.returnedAt = createdAt.plusDays(duration);
    }

    public String getTxId() {
        return transactionId;
    }

    public String getItemId() {
        return item;
    }

    public String getBorrower() {
        return borrower;
    }

    public LocalDate getReturnDate() {
        return returnedAt;
    }

    public boolean isFinished() {
        return returnedAt.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        String transactionIcon = "[" + (isFinished() ? "X" : " ") + "] ";
        String transactionId = "TransactionID: " + this.transactionId + " ";
        String itemId = "ItemID: " + this.item + " ";
        String usersId = "BorrowerID: " + this.borrower + " ";

        if (!isFinished()) {
            String remainDays = " (" + ChronoUnit.DAYS.between(LocalDate.now(), getReturnDate()) + " day(s) remaining)";
            String returnDate = "ReturnDate: " + DateParser.formatDateToString(returnedAt) + remainDays;
            return transactionIcon + transactionId + itemId + usersId + returnDate;
        }
        String returnedDate = "ReturnedDate: " + DateParser.formatDateToString(returnedAt);
        return transactionIcon + transactionId + itemId + usersId + returnedDate;
    }
}
