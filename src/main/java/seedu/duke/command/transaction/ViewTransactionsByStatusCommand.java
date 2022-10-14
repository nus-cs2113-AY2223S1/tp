package seedu.duke.command.transaction;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static seedu.duke.exception.ExceptionMessages.MESSAGE_COMMAND_UNRECOGNIZABLE;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_INVALID_PARTS;


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

    public String getArgs() throws InvalidArgumentException {
        String args;
        if (parts[0].startsWith("s")) {
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


    void getTransactions(String arg, ArrayList<Transaction> transactions) {
        ArrayList<Transaction> transactionsToView = new ArrayList<>();
        if (arg.equals("finished")) {
            for (Transaction transaction : transactions) {
                if (transaction.isFinished()) {
                    transactionsToView.add(transaction);
                }
            }
            Ui.viewCompletedTransactionsMessage(transactionsToView);
        } else {
            for (Transaction transaction : transactions) {
                if (!transaction.isFinished()) {
                    transactionsToView.add(transaction);
                }
            }
            Ui.viewUncompletedTransactionsMessage(transactionsToView);
        }
    }

    @Override
    public boolean executeCommand() throws InsufficientArgumentsException, InvalidArgumentException {
        String arg = getArgs();
        ArrayList<Transaction> transactions = transactionList.getTransactionList();
        if (isValidArgument(arg)) {
            getTransactions(arg, transactions);
        }
        return false;
    }


}


