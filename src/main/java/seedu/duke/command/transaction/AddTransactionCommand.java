package seedu.duke.command.transaction;

import java.time.LocalDate;

import seedu.duke.command.Command;
import seedu.duke.exception.DateFormatInvalidException;
import seedu.duke.exception.DuplicateException;
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

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

// @@author bdthanh

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
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
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

    private void checkValidInput(String[] args) throws ItemNotFoundException, InvalidUserException,
            UserNotFoundException, DateFormatInvalidException, DurationInvalidException {
        itemList.checkValidItem(args);
        userList.checkValidBorrower(args, itemList);
        transactionList.checkValidArgsForAdding(args);
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
            InvalidUserException, InvalidItemException, ItemNotFoundException, DuplicateException,
            UserNotFoundException, DurationInvalidException, InvalidTransactionException {
        String[] args = getArgsAddTxCmd();
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        checkValidInput(args);
        Transaction transaction = getTransactionFromArgs(args);
        transactionList.checkOldTransactionsOverlapWithNew(transaction);
        this.transactionList.addTransaction(transaction);
        Ui.addTransactionMessage(transaction, transactionList.getSize());
        return false;
    }

    private Transaction getTransactionFromArgs(String[] args) throws ItemNotFoundException {
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        String itemId = args[ITEM_ID_INDEX];
        String itemName = itemList.getItemById(args[ITEM_ID_INDEX]).getName();
        String borrowId = args[BORROWER_INDEX];
        String lenderId = itemList.getItemById(itemId).getOwnerId();
        int duration = Integer.parseInt(args[DURATION_INDEX]);
        LocalDate createdAt = LocalDate.parse(args[CREATED_DATE_INDEX]);
        double moneyTransacted =
                itemList.getItemById(args[ITEM_ID_INDEX]).getPricePerDay() * (double) duration;
        return new Transaction(itemName, itemId, borrowId, lenderId, duration, createdAt,
                moneyTransacted);
    }
}
