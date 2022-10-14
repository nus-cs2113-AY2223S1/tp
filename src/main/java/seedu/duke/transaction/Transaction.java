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

    public Transaction(String itemName, String borrowerId, int duration, LocalDate createdAt) {
        this.transactionId = IdGenerator.generateId();
        this.item = itemName;
        this.borrower = borrowerId;
        this.duration = duration;
        this.createdAt = createdAt;
        this.returnedAt = createdAt.plusDays(duration);
    }

    public Transaction(String transactionId, String itemName, String borrowerId, int duration, LocalDate createdAt) {
        this.transactionId = transactionId;
        this.item = itemName;
        this.borrower = borrowerId;
        this.duration = duration;
        this.createdAt = createdAt;
        this.returnedAt = createdAt.plusDays(duration);
    }

    public String getTxId() {
        return transactionId;
    }

    public String getItemName() {
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

    public String convertTransactionToFileFormat() {
        String separator = " | ";
        return transactionId + separator + item + separator + borrower + separator + duration + separator + createdAt;
    }

    @Override
    public String toString() {
        String transactionIcon = "Status: [" + (isFinished() ? "Returned" : "On loan") + "] ";
        String transactionId = "TransactionID: " + this.transactionId + " ";
        String itemName = "itemName: " + this.item + " ";
        String usersId = "BorrowerID: " + this.borrower + " ";

        if (!isFinished()) {
            String remainDays = " (" + ChronoUnit.DAYS.between(LocalDate.now(), getReturnDate()) + " day(s) remaining)";
            String returnDate = "ReturnDate: " + DateParser.formatDateToString(returnedAt) + remainDays;
            return transactionIcon + transactionId + itemName + usersId + returnDate;
        }
        String returnedDate = "ReturnedDate: " + DateParser.formatDateToString(returnedAt);
        return transactionIcon + transactionId + itemName + usersId + returnedDate;
    }
}
