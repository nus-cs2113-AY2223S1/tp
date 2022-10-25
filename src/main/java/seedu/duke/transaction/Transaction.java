package seedu.duke.transaction;

import seedu.duke.id.IdGenerator;
import seedu.duke.parser.DateParser;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// @@author bdthanh
/**
 * A representation of transaction.
 */
public class Transaction {
    private final String transactionId;
    private final String itemName;
    private final String itemId;
    private final String borrower;
    private final int duration;
    private final LocalDate createdAt;
    private final LocalDate returnedAt;

    /**
     * Constructor method for transaction.
     *
     * @param itemName The name of the item involved.
     * @param itemId The id of the item involved.
     * @param borrowerId The id(name) of the user who borrows.
     * @param duration The length of transaction(days).
     * @param createdAt The day when transaction created.
     */
    public Transaction(String itemName, String itemId, String borrowerId, int duration,
            LocalDate createdAt) {
        this.transactionId = IdGenerator.generateId();
        this.itemName = itemName;
        this.borrower = borrowerId;
        this.duration = duration;
        this.createdAt = createdAt;
        this.returnedAt = createdAt.plusDays(duration);
        this.itemId = itemId;
    }

    /**
     * Constructor method for transaction.
     *
     * @param transactionId The id of the transaction.
     * @param itemName The name of the item involved.
     * @param itemId The id of the item involved.
     * @param borrowerId The id(name) of the user who borrows.
     * @param duration The length of transaction(days).
     * @param createdAt The day when transaction created.
     */
    public Transaction(String transactionId, String itemName, String itemId, String borrowerId,
            int duration, LocalDate createdAt) {
        this.transactionId = transactionId;
        this.itemName = itemName;
        this.borrower = borrowerId;
        this.duration = duration;
        this.createdAt = createdAt;
        this.returnedAt = createdAt.plusDays(duration);
        this.itemId = itemId;
    }

    /**
     * Gets the transaction id.
     *
     * @return The transaction id
     */
    public String getTxId() {
        return transactionId;
    }

    /**
     * Gets the item id.
     *
     * @return The item id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Gets the borrower id.
     *
     * @return The borrower id
     */
    public String getBorrower() {
        return borrower;
    }

    /**
     * Gets the Return Date.
     *
     * @return The Return date
     */
    public LocalDate getReturnDate() {
        return returnedAt;
    }

    /**
     * Checks if the transaction finished or not.
     *
     * @return true If the return date is before today
     */
    public boolean isFinished() {
        return returnedAt.isBefore(LocalDate.now());
    }

    /**
     * Formats the transaction information to store in hard-drive.
     *
     * @return A formatted string of transaction information
     */
    public String convertTransactionToFileFormat() {
        String separator = " | ";
        return transactionId + separator + itemName + separator + itemId + separator + borrower
                + separator + duration + separator + createdAt;
    }

    /**
     * Updates the duration of the transaction.
     * 
     * @param newDuration The new duration
     * @return The updated transaction
     */

    public Transaction updateDuration(int newDuration) {
        return new Transaction(this.transactionId, this.itemName, this.itemId, this.borrower,
                newDuration, this.createdAt);
    }

    /**
     * Overrides toString method of Object to get string representation of Transaction.
     *
     * @return A string representation of Transaction
     */
    @Override
    public String toString() {
        String itemId = "ItemID: " + this.itemId + " ";
        String transactionIcon = "Status: [" + (isFinished() ? "Returned" : "On loan") + "] ";
        String transactionId = "TransactionID: " + this.transactionId + " ";
        String itemName = "ItemName: " + this.itemName + " ";
        String usersId = "BorrowerID: " + this.borrower + " ";

        if (!isFinished()) {
            String remainDays = " (" + ChronoUnit.DAYS.between(LocalDate.now(), getReturnDate())
                    + " day(s) remaining)";
            String returnDate =
                    "ReturnDate: " + DateParser.formatDateToString(returnedAt) + remainDays;
            return transactionIcon + transactionId + itemName + itemId + usersId + returnDate;
        }
        String returnedDate = "ReturnedDate: " + DateParser.formatDateToString(returnedAt);
        return transactionIcon + transactionId + itemName + itemId + usersId + returnedDate;
    }
}
