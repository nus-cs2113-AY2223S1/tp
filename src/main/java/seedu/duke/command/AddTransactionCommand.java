package seedu.duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.duke.exception.DateFormatInvalidException;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidBorrowerException;
import seedu.duke.exception.InvalidItemException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.parser.DateParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.UserList;

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
            throw new InsufficientArgumentsException();
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
            } else if (part.startsWith("ca")) {
                args[3] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException("One of the parts is in incorrect format");
            }

        }
        return args;
    }

    private boolean isValidItem(String id) throws InvalidItemException, ItemNotFoundException {
        if (itemList.getItemById(id).isAvailable()) {
            return true;
        }
        throw new InvalidItemException("This item is currently unavailable");
    }

    private boolean isValidBorrower(String itemId, String userId)
            throws InvalidBorrowerException, ItemNotFoundException, UserNotFoundException {
        String itemOwnerName = itemList.getItemById(itemId).getOwnerId();
        if (!userList.getUserById(userId).getName().equals(itemOwnerName)) {
            return true;
        }
        throw new InvalidBorrowerException("Borrower cannot borrow items from his/herself");
    }

    private boolean isValidDuration(String duration) {
        try {
            Integer.parseInt(duration);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Number should only contain digit 0-9");
        }
    }

    private boolean isValidCreatedDate(String createdAt) throws DateFormatInvalidException {
        try {
            LocalDate.parse(createdAt);
            return true;
        } catch (DateTimeParseException e) {
            throw new DateFormatInvalidException("The date format is incorrect(YYYY-MM-DD");
        }
    }

    private boolean areValidArgs(String[] args)
            throws InvalidItemException, InvalidBorrowerException, DateFormatInvalidException,
            ItemNotFoundException, UserNotFoundException {
        return isValidItem(args[0]) && isValidBorrower(args[0], args[1])
                && isValidDuration(args[2]) && isValidCreatedDate(args[3]);
    }

    public boolean executeCommand()
            throws InvalidArgumentException, DateFormatInvalidException,
            InvalidBorrowerException, InvalidItemException, ItemNotFoundException, UserNotFoundException {
        String[] args = getArgsAddTxCmd();
        if (areValidArgs(args)) {
            String itemId = args[0];
            String borrowId = args[1];
            int duration = Integer.parseInt(args[2]);
            LocalDate createdAt = DateParser.convertStringDateToLocalDate(args[3]);
            Transaction transaction = new Transaction(itemId, borrowId, duration, createdAt);
            this.transactionList.add(transaction);
            //ui.confirmAddItem(transaction);
        }
        return false;
    }
}
