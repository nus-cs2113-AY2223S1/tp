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

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_DATE_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_DURATION_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_UNAVAILABLE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NUMBER_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_SELF_BORROWER;

public class AddTransactionCommand extends Command {
    private final String[] parts;
    private final TransactionList transactionList;
    private final ItemList itemList;
    private final UserList userList;

    public AddTransactionCommand(String[] parts, UserList userList, ItemList itemList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        this.itemList = itemList;
        this.userList = userList;
        if (parts.length != 4) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    public String[] getArgsAddTxCmd() throws InvalidArgumentException {
        String[] args = new String[4];
        for (String part : parts) {
            if (part.startsWith("i")) {
                args[0] = CommandParser.getArgValue(part);
            } else if (part.startsWith("b")) {
                args[1] = CommandParser.getArgValue(part);
            } else if (part.startsWith("d")) {
                args[2] = CommandParser.getArgValue(part);
            } else if (part.startsWith("c")) {
                args[3] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }

        }
        return args;
    }

    private boolean isValidItem(String itemId) throws InvalidItemException, ItemNotFoundException {
        if (itemList.getItemById(itemId).isAvailable(transactionList)) {
            return true;
        }
        throw new InvalidItemException(MESSAGE_ITEM_UNAVAILABLE);
    }

    private boolean isValidBorrower(String itemId, String userId)
            throws InvalidUserException, ItemNotFoundException, UserNotFoundException {
        String itemOwnerName = itemList.getItemById(itemId).getOwnerId();
        if (!userList.getUserById(userId).getName().equals(itemOwnerName)) {
            return true;
        }
        throw new InvalidUserException(MESSAGE_SELF_BORROWER);
    }

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

    private boolean isValidCreatedDate(String createdAt) throws DateFormatInvalidException {
        try {
            LocalDate.parse(createdAt);
            return true;
        } catch (DateTimeParseException e) {
            throw new DateFormatInvalidException(MESSAGE_DATE_FORMAT_INVALID);
        }
    }

    private boolean areValidArgs(String[] args)
            throws InvalidItemException, InvalidUserException, DateFormatInvalidException,
            ItemNotFoundException, UserNotFoundException, DurationInvalidException {
        return isValidItem(args[0]) && isValidBorrower(args[0], args[1])
                && isValidDuration(args[2]) && isValidCreatedDate(args[3]);
    }

    public boolean executeCommand()
            throws InvalidArgumentException, DateFormatInvalidException, InvalidUserException,
            InvalidItemException, ItemNotFoundException, UserNotFoundException, DurationInvalidException {
        String[] args = getArgsAddTxCmd();
        if (areValidArgs(args)) {
            String itemId = args[0];
            String itemName = itemList.getItemById(args[0]).getName();
            String borrowId = args[1];
            int duration = Integer.parseInt(args[2]);
            LocalDate createdAt = LocalDate.parse(args[3]);
            Transaction transaction = new Transaction(itemName, itemId, borrowId, duration, createdAt);
            this.transactionList.add(transaction);
            Ui.addTransactionMessage(transaction, transactionList.getSize());
        }
        return false;
    }
}
