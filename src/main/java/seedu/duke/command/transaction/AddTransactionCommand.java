package seedu.duke.command.transaction;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.duke.command.Command;
import seedu.duke.exception.DateFormatInvalidException;
import seedu.duke.exception.DurationInvalidException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidItemException;
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
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_UNAVAILABLE;
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
        if (parts.length != 4) {
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
        String[] args = new String[4];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals("i")) {
                args[0] = CommandParser.getArgValue(part);
            } else if (delimiter.equals("b")) {
                args[1] = CommandParser.getArgValue(part);
            } else if (delimiter.equals("d")) {
                args[2] = CommandParser.getArgValue(part);
            } else if (delimiter.equals("c")) {
                args[3] = CommandParser.getArgValue(part);
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
     * @throws InvalidItemException  If the item is not available
     * @throws ItemNotFoundException If the item cannot be found
     */
    private boolean isValidItem(String itemId) throws InvalidItemException, ItemNotFoundException {
        if (itemList.getItemById(itemId).isAvailable(transactionList)) {
            return true;
        }
        throw new InvalidItemException(MESSAGE_ITEM_UNAVAILABLE);
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
            if (Integer.parseInt(duration) < 0) {
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
        try {
            if (LocalDate.parse(createdAt).isAfter(LocalDate.now())) {
                throw new DateFormatInvalidException(MESSAGE_CREATED_DATE_RANGE_INVALID);
            }
            return true;
        } catch (DateTimeParseException e) {
            throw new DateFormatInvalidException(MESSAGE_DATE_FORMAT_INVALID);
        }
    }

    private boolean areValidArgs(String[] args)
            throws InvalidItemException, InvalidUserException, DateFormatInvalidException,
            ItemNotFoundException, UserNotFoundException, DurationInvalidException {
        assert args.length == 4 : "Args length is invalid";
        return isValidItem(args[0]) && isValidBorrower(args[0], args[1]) && isValidDuration(args[2])
                && isValidCreatedDate(args[3]);
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
            UserNotFoundException, DurationInvalidException {
        String[] args = getArgsAddTxCmd();
        assert args.length == 4 : "Args length is invalid";
        if (areValidArgs(args)) {
            String itemId = args[0];
            String itemName = itemList.getItemById(args[0]).getName();
            String borrowId = args[1];
            int duration = Integer.parseInt(args[2]);
            LocalDate createdAt = LocalDate.parse(args[3]);
            Transaction transaction =
                    new Transaction(itemName, itemId, borrowId, duration, createdAt);
            this.transactionList.addTransaction(transaction);
            Ui.addTransactionMessage(transaction, transactionList.getSize());
        }
        return false;
    }
}
