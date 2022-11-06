package seedu.duke.command.user;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidTransactionException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;
import seedu.duke.user.UserList;
import seedu.duke.transaction.TransactionList;


import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

// @@author jorellesee
public class ViewUserGainCommand extends Command {
    private final String[] parts;
    private static final String USER_ID_DELIMITER = "u";

    private final UserList userList;
    private final TransactionList transactionList;


    /**
     * Constructor for ViewUserGainCommand.
     *
     * @param parts The parts from user input
     * @param userList The list of users to work with
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public ViewUserGainCommand(String[] parts, UserList userList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.userList = userList;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    private String getArgsViewUserProfitCmd() throws InvalidArgumentException {
        String arg;
        String delimiter = CommandParser.getArgsDelimiter(parts[0]);
        if (delimiter.equals(USER_ID_DELIMITER)) {
            arg = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
        }
        return arg;
    }

    private boolean isValidUser(String userId) throws UserNotFoundException {
        try {
            userList.getUserById(userId);
            return true;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    /**
     * Executes ViewUserGainCommand.
     *
     * @return false
     * @throws InvalidArgumentException If there is a part that cannot be parsed
     * @throws InvalidTransactionException If given transaction is invalid
     * @throws UserNotFoundException If the user cannot be found in the list
     */
    public boolean executeCommand()
            throws UserNotFoundException, InvalidArgumentException, InvalidTransactionException {
        String userName = getArgsViewUserProfitCmd();
        if (isValidUser(userName)) {
            double totalProfit =
                    transactionList.getLendTransactionsByUser(userName).getTotalMoneyTransacted();
            Ui.printResponse("The amount of money earned of "
                    + userName + " is: $" + String.format("%.2f", totalProfit));
        }
        return false;
    }
}
