package seedu.duke.command.transaction;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_COMMAND_UNRECOGNIZABLE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;


public class ViewTransactionsByStatusCommand extends Command {

    private final String[] parts;
    private final TransactionList transactionList;

    public ViewTransactionsByStatusCommand(String[] parts, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    private String getArgs() throws InvalidArgumentException {
        String args;
        String delimiter = CommandParser.getArgsDelimiter(parts[0]);
        if (delimiter.equals("s")) {
            args = CommandParser.getArgValue(parts[0]);
        } else {
            throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
        }
        return args;
    }

    private boolean isValidArgument(String arg) throws InvalidArgumentException {
        if (arg.equals("finished") || arg.equals("unfinished")) {
            return true;
        }
        throw new InvalidArgumentException(MESSAGE_COMMAND_UNRECOGNIZABLE);
    }

    /**
     * Displays the list of transactions based on user input.
     * @param arg user input needed to return the appropriate list of transactions
     * @param transactions Overall list of transactions
     */
    void getTransactionsByStatus(String arg, ArrayList<Transaction> transactions) {
        ArrayList<Transaction> transactionsToView;
        if (arg.equals("finished")) {
            transactionsToView = getFinishedTransactions(transactions);
            Ui.viewCompletedTransactionsMessage(transactionsToView);
        } else {
            assert arg.equals("unfinished");
            transactionsToView = getUnfinishedTransactions(transactions);
            Ui.viewUncompletedTransactionsMessage(transactionsToView);
        }
    }

    /**
     * Search entire transaction list for transactions that are completed.
     * @param transactions list of transactions to search from
     * @return list of finished transactions
     */
    private ArrayList<Transaction> getFinishedTransactions(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> transactionsToView = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.isFinished()) {
                transactionsToView.add(transaction);
            }
        }
        return transactionsToView;
    }

    /**
     * Search entire transaction list for transactions that are still ongoing.
     * @param transactions list of transactions to search from
     * @return list of unfinished transactions
     */
    private ArrayList<Transaction> getUnfinishedTransactions(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> transactionsToView = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (!transaction.isFinished()) {
                transactionsToView.add(transaction);
            }
        }
        return transactionsToView;
    }

    @Override
    public boolean executeCommand()
            throws InsufficientArgumentsException, InvalidArgumentException {
        String arg = getArgs();
        ArrayList<Transaction> transactions = transactionList.getTransactionList();
        if (isValidArgument(arg)) {
            getTransactionsByStatus(arg, transactions);
        }
        return false;
    }
}
