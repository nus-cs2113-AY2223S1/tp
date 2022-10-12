package seedu.duke.command.transaction;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.exception.TransactionNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

import static seedu.duke.exception.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_INVALID_PARTS;

public class RemoveTransactionCommand extends Command {
    private final String[] parts;
    private final TransactionList transactionList;

    public RemoveTransactionCommand(String[] parts, TransactionList txList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.transactionList = txList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    public String[] getArgsRemoveTxCmd() throws InvalidArgumentException {
        String[] args = new String[1];
        for (String part : parts) {
            if (part.startsWith("t")) {
                args[0] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return args;
    }

    public boolean executeCommand()
            throws TransactionNotFoundException, ItemNotFoundException, InvalidArgumentException {
        String[] args = getArgsRemoveTxCmd();
        String txId = args[0].trim();
        Transaction transaction = transactionList.getTransactionById(txId);
        transactionList.deleteTransaction(txId);
        Ui.deleteTransactionMessage(transaction, transactionList.getSize());
        return false;
    }
}
