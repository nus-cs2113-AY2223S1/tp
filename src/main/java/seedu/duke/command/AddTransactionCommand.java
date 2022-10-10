package seedu.duke.command;

import java.time.LocalDate;

import seedu.duke.Ui;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.transaction.Transaction;
import seedu.duke.transaction.TransactionList;

public class AddTransactionCommand extends Command {
    private final String[] args;
    private final TransactionList txList;

    public AddTransactionCommand(String[] args, TransactionList txList)
            throws InsufficientArgumentsException {
        this.args = args;
        this.txList = txList;
        if (args.length != 3) {
            throw new InsufficientArgumentsException();
        }
    }

    public boolean executeCommand() {
        String itemId = this.args[0];
        String borrowId = this.args[1];
        int duration = Integer.parseInt(this.args[2]);
        Transaction transaction = new Transaction(itemId, borrowId, duration, LocalDate.now().toString());
        this.txList.add(transaction);
        Ui.addTransactionMessage(transaction, txList.getSize());
        return false;
    }
}
