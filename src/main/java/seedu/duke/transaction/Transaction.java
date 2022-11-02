package seedu.duke.transaction;

import seedu.duke.id.IdGenerator;
import seedu.duke.parser.DateParser;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final String lender;
    private final int duration;
    private final LocalDate createdAt;
    private final LocalDate returnedAt;
    private final double moneyTransacted;

    /**
     * Constructor method for transaction.
     *
     * @param itemName   The name of the item involved.
     * @param itemId     The id of the item involved.
     * @param borrowerId The id(name) of the user who borrows.
     * @param duration   The length of transaction(days).
     * @param createdAt  The day when transaction created.
     */
    public Transaction(String itemName, String itemId, String borrowerId, String lenderId,
                       int duration, LocalDate createdAt, double moneyTransacted) {
        this.transactionId = IdGenerator.generateId();
        this.itemName = itemName;
        this.borrower = borrowerId;
        this.lender = lenderId;
        this.duration = duration;
        this.createdAt = createdAt;
        this.returnedAt = createdAt.plusDays(duration);
        this.itemId = itemId;
        this.moneyTransacted = moneyTransacted;
    }

    /**
     * Constructor method for transaction.
     *
     * @param transactionId The id of the transaction.
     * @param itemName      The name of the item involved.
     * @param itemId        The id of the item involved.
     * @param borrowerId    The id(name) of the user who borrows.
     * @param duration      The length of transaction(days).
     * @param createdAt     The day when transaction created.
     */
    public Transaction(String transactionId, String itemName, String itemId, String borrowerId,
                       String lenderId, int duration, LocalDate createdAt, double moneyTransacted) {
        this.transactionId = transactionId;
        this.itemName = itemName;
        this.borrower = borrowerId;
        this.lender = lenderId;
        this.duration = duration;
        this.createdAt = createdAt;
        this.returnedAt = createdAt.plusDays(duration);
        this.itemId = itemId;
        this.moneyTransacted = moneyTransacted;
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

    public String getLender() {
        return lender;
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
     * Gets the Duration.
     *
     * @return The Duration of transaction
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets the moneyTransacted.
     *
     * @return The Duration of transaction
     */
    public double getMoneyTransacted() {
        return moneyTransacted;
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
        return itemId + separator + borrower + separator + duration + separator + createdAt + separator
                + lender + separator + itemName + separator + moneyTransacted + separator + transactionId;
    }

    // @@author winston-lim

    /**
     * Updates the duration of the transaction.
     *
     * @param newDuration The new duration
     * @return The updated transaction
     */
    public Transaction update(int newDuration, double newMoneyTransacted) {
        return new Transaction(this.transactionId, this.itemName, this.itemId, this.borrower,
                this.lender, newDuration, this.createdAt, newMoneyTransacted);
    }

    // @@author bdthanh

    /**
     * Checks if the new transaction overlaps with any old transactions of the same item.
     *
     * @param transactionToCheck The new transaction
     * @return true if they overlap
     */
    public boolean checkOverlapToAddTx(Transaction transactionToCheck) {
        return checkOverlapToUpdateTx(transactionToCheck)
                || transactionToCheck.createdAt.isEqual(this.createdAt);
    }

    /**
     * Checks if there is an overlap with a given transaction.
     *
     * @param transactionToCheck the transaction to compare with.
     * @return true if there is an overlap
     */
    public boolean checkOverlapToUpdateTx(Transaction transactionToCheck) {
        if (transactionToCheck.getTxId().equals(this.transactionId)) {
            return false;
        }
        return (transactionToCheck.createdAt.isAfter(this.createdAt)
                && transactionToCheck.getReturnDate().isBefore(this.getReturnDate()))
                || (transactionToCheck.getReturnDate().isAfter(this.createdAt)
                && transactionToCheck.getReturnDate().isBefore(this.getReturnDate()))
                || (transactionToCheck.createdAt.isBefore(this.createdAt)
                && transactionToCheck.getReturnDate().isAfter(this.getReturnDate()))
                || (transactionToCheck.createdAt.isAfter(this.createdAt)
                && transactionToCheck.createdAt.isBefore(this.getReturnDate())
                || transactionToCheck.getReturnDate().isEqual(this.getReturnDate()));
    }

    /**
     * Overrides toString method of Object to get string representation of Transaction.
     *
     * @return A string representation of Transaction
     */
    @Override
    public String toString() {
        String itemId = "ItemID: " + this.itemId + "\n";
        String transactionIcon = "[" + (isFinished() ? "Finished" : "Unfinished") + "] ";
        String transactionId = "TxID: " + this.transactionId + "\n";
        String itemName = "   ItemName: " + this.itemName + " ";
        String borrowerId = "   Borrower: " + this.borrower + "\n";
        String lenderId = "   Lender: " + this.lender + "\n";
        String duration = "   Duration: " + this.duration + "\n";
        BigDecimal money = new BigDecimal(this.moneyTransacted);
        String moneyTransactedString =
                "   MoneyTransacted: $" + money.setScale(2, RoundingMode.HALF_EVEN) + " ";

        if (!isFinished()) {
            String remainDays = " (" + ChronoUnit.DAYS.between(LocalDate.now(), getReturnDate())
                    + " day(s) left)";
            String returnDate = "   ReturnDate: " + DateParser.formatDateToString(returnedAt)
                    + remainDays + "\n";
            return transactionIcon + transactionId + itemName + itemId + lenderId + borrowerId
                    + duration + returnDate + moneyTransactedString;
        }

        String returnedDate =
                "   ReturnedDate: " + DateParser.formatDateToString(returnedAt) + "\n";
        return transactionIcon + transactionId + itemName + itemId + lenderId + borrowerId
                + duration + returnedDate + moneyTransactedString;
    }
}
