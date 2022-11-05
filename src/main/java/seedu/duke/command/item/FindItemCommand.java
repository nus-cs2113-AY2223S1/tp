package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.ui.Ui;
import seedu.duke.item.ItemList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_NUMBER_OF_ARGS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

// @@author jorellesee
public class FindItemCommand extends Command {
    private final String[] parts;
    private static final String KEYWORD_DELIMITER = "k";

    private final ItemList itemList;
    private final TransactionList transactionList;

    /**
     * Constructor for FindItemCommand.
     *
     * @param parts The parts from user input
     * @param itemList The list of items to work with
     * @param transactionList The list of transactions to work with
     * @throws InsufficientArgumentsException If the number of args is incorrect
     */
    public FindItemCommand(String[] parts, ItemList itemList, TransactionList transactionList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        this.transactionList = transactionList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INVALID_NUMBER_OF_ARGS);
        }
    }

    private String getArgsFindItemCommand() throws InvalidArgumentException {
        String arg = "";
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals(KEYWORD_DELIMITER)) {
                arg = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return arg;
    }

    @Override
    public boolean executeCommand() throws InvalidArgumentException, ItemNotFoundException {
        String arg = getArgsFindItemCommand();
        Ui.printResponse(itemList.getItemsByKeyword(arg).toString(this.transactionList));
        return false;
    }
}
