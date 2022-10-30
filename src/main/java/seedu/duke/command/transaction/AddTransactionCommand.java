package seedu.duke.command.transaction;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.duke.command.Command;
import seedu.duke.exception.DateFormatInvalidException;
import seedu.duke.exception.DurationInvalidException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidItemException;
import seedu.duke.exception.InvalidTransactionException;
import seedu.duke.exception.InvalidUserException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.ui.Ui;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CREATED_DATE_RANGE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_DATE_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_DURATION_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NUMBER_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_SELF_BORROWER;

//@@author bdthanh

/**
 * A representation of a command to add a new transaction.
 */
public class AddTransactionCommand extends Command {
    private final String[] parts;
    private final TransactionList transactionList;
    private final ItemList itemList;
    private final UserList userList;
    private static final String ITEM_ID_DELIMITER = "i";
    private static final String BORROWER_DELIMITER = "b";
    private static final String DURATION_DELIMITER = "d";
    private static final String CREATED_DATE_DELIMITER = "c";
    private static final int NUMBER_OF_ARGS = 4;
    private static final int ITEM_ID_INDEX = 0;
    private static final int BORROWER_INDEX = 1;
    private static final int DURATION_INDEX = 2;
    private static final int CREATED_DATE_INDEX = 3;

    /**
     * Constructor for AddTransactionCommand.
     *
     * @param parts           The parts from user input
     * @param userList        The list of users to work with
     * @param itemList        The list of items to work with
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public AddTransactionCommand(String[] parts, UserList userList, ItemList itemList,
                                 TransactionList transactionList) throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        this.itemList = itemList;
        this.userList = userList;
        if (parts.length != NUMBER_OF_ARGS) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    /**
     * Gets arg values from the given part.
     *
     * @return An array of arg values
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     */
    private String[] getArgsAddTxCmd() throws InvalidArgumentException {
        String[] args = new String[NUMBER_OF_ARGS];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals(ITEM_ID_DELIMITER)) {
                args[ITEM_ID_INDEX] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(BORROWER_DELIMITER)) {
                args[BORROWER_INDEX] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(DURATION_DELIMITER)) {
                args[DURATION_INDEX] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(CREATED_DATE_DELIMITER)) {
                args[CREATED_DATE_INDEX] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }

        }
        return args;
    }

    /**
     * Checks if an item name is valid or not.
     *
     * @param itemId The input item id
     * @return true If that item is available
     * @throws ItemNotFoundException If the item cannot be found
     */
    private boolean isValidItem(String itemId) throws ItemNotFoundException {
        try {
            itemList.getItemById(itemId);
            return true;
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException(e.getMessage());
        }
    }

    /**
     * Checks if a borrower is valid or not.
     *
     * @param itemId The input item id
     * @param userId The input user id
     * @return true If the item owner is not the borrower
     * @throws InvalidUserException  If the user borrows him/herself
     * @throws ItemNotFoundException If the item cannot be found
     * @throws UserNotFoundException If the user cannot be found
     */
    private boolean isValidBorrower(String itemId, String userId)
            throws InvalidUserException, ItemNotFoundException, UserNotFoundException {
        String itemOwnerName = itemList.getItemById(itemId).getOwnerId();
        if (!userList.getUserById(userId).getName().equals(itemOwnerName)) {
            return true;
        }
        throw new InvalidUserException(MESSAGE_SELF_BORROWER);
    }

    /**
     * Checks if the duration is valid or not.
     *
     * @param duration The input duration
     * @return true If that number can be parsed and greater than 0
     * @throws DurationInvalidException If the number is less than 0
     */
    private boolean isValidDuration(String duration) throws DurationInvalidException {
        try {
            if (Integer.parseInt(duration) < 0 || Integer.parseInt(duration) > 1461) {
                throw new DurationInvalidException(MESSAGE_DURATION_INVALID);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_NUMBER_FORMAT_INVALID);
        }
    }

    /**
     * Checks if the created Date is valid or not.
     *
     * @param createdAt The input created date of transaction
     * @return true If the date can be parsed and is in correct range
     * @throws DateFormatInvalidException If the date is in wrong format or after the current day
     */
    private boolean isValidCreatedDate(String createdAt) throws DateFormatInvalidException {
        LocalDate validBeginningDate = LocalDate.parse("2016-01-01");
        try {
            if (LocalDate.parse(createdAt).isAfter(LocalDate.now())
                    || LocalDate.parse(createdAt).isBefore(validBeginningDate)) {
                throw new DateFormatInvalidException(MESSAGE_CREATED_DATE_RANGE_INVALID);
            }
            return true;
        } catch (DateTimeParseException e) {
            throw new DateFormatInvalidException(MESSAGE_DATE_FORMAT_INVALID);
        }
    }

    private boolean areValidArgs(String[] args)
            throws InvalidUserException, DateFormatInvalidException,
            ItemNotFoundException, UserNotFoundException, DurationInvalidException {
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        return isValidItem(args[ITEM_ID_INDEX]) && isValidBorrower(args[ITEM_ID_INDEX], args[BORROWER_INDEX])
                && isValidDuration(args[DURATION_INDEX]) && isValidCreatedDate(args[CREATED_DATE_INDEX]);
    }

    /**
     * Executes AddTransactionCommand.
     *
     * @return false
     * @throws InvalidArgumentException   If there is a part that cannot be parsed
     * @throws DateFormatInvalidException If the number of args is incorrect
     * @throws InvalidUserException       If the user borrows themselves
     * @throws InvalidItemException       If the item is unavailable
     * @throws ItemNotFoundException      If the item cannot be found in the list
     * @throws UserNotFoundException      If the user cannot be found
     * @throws DurationInvalidException   If the number is less than 0
     */
    public boolean executeCommand() throws InvalidArgumentException, DateFormatInvalidException,
            InvalidUserException, InvalidItemException, ItemNotFoundException,
            UserNotFoundException, DurationInvalidException, InvalidTransactionException {
        String[] args = getArgsAddTxCmd();
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        if (areValidArgs(args)) {
            Transaction transaction = getTransactionFromArgs(args);
            transactionList.checkOldTransactionsOverlapWithNew(transaction);
            this.transactionList.addTransaction(transaction);
            Ui.addTransactionMessage(transaction, transactionList.getSize());
        }
        return false;
    }

    private Transaction getTransactionFromArgs(String[] args) throws ItemNotFoundException {
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        String itemId = args[0];
        String itemName = itemList.getItemById(args[ITEM_ID_INDEX]).getName();
        String borrowId = args[BORROWER_INDEX];
        int duration = Integer.parseInt(args[DURATION_INDEX]);
        LocalDate createdAt = LocalDate.parse(args[CREATED_DATE_INDEX]);
        double moneyTransacted = itemList.getItemById(args[ITEM_ID_INDEX]).getPricePerDay() * (double) duration;
        return new Transaction(itemName, itemId, borrowId, duration, createdAt, moneyTransacted);
    }
}
