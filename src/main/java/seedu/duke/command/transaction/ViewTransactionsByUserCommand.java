package seedu.duke.command.transaction;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;

import java.util.ArrayList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STATUS_INVALID;

public class ViewTransactionsByUserCommand extends Command {

    private final String[] parts;
    private final TransactionList transactionList;
    private final UserList userList;

    public ViewTransactionsByUserCommand(String[] parts, TransactionList transactionList, UserList userList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        this.userList = userList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    private String getArgs() throws InvalidArgumentException {
        String args;
        String delimiter = CommandParser.getArgsDelimiter(parts[0]);
        if (delimiter.equals("u")) {
            args = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
        }
        return args;
    }

    private boolean isValidUser(String arg) throws UserNotFoundException {
        try {
            userList.getUserById(arg);
            return true;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }



    @Override
    public boolean executeCommand()
            throws InsufficientArgumentsException, UserNotFoundException, InvalidArgumentException {
        String arg = getArgs();
        ArrayList<Transaction> transactions = transactionList.getTransactionList();
        if (isValidUser(arg)) {
            TransactionList returnList = transactionList.getBorrowTransactionsByUser(arg);
            Ui.printResponse(returnList.toString());
        }
        return false;
    }
}
