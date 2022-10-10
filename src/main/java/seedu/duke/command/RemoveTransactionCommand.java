package seedu.duke.command;

import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.exception.TransactionNotFoundException;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

public class RemoveTransactionCommand extends Command {
    private final String[] parts;
    private final TransactionList txList;
    private final ItemList itemList;

    public RemoveTransactionCommand(String[] parts, ItemList itemList, TransactionList txList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.txList = txList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public String[] getArgsRemoveTxCmd() throws InvalidArgumentException {
        String[] args = new String[1];
        for (String part : parts) {
            if (part.startsWith("t")) {
                args[0] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException("One of the parts is in incorrect format");
            }
        }
        return args;
    }

    private void markAvailableForItem(String transactionId) throws ItemNotFoundException, TransactionNotFoundException {
        String itemId = txList.getTransactionById(transactionId).getItem();
        itemList.markAvailable(itemId);
    }

    public boolean executeCommand()
            throws TransactionNotFoundException, ItemNotFoundException, InvalidArgumentException {
        String[] args = getArgsRemoveTxCmd();
        String txId = args[0].trim();
        markAvailableForItem(txId);
        //Transaction transaction = txList.getTransactionById(txId);
        txList.deleteTransaction(txId);
        //ui.confirmDeleteTransaction(transaction, txList);
        return false;
    }
}
