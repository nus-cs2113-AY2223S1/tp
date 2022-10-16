package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.exception.*;
import seedu.duke.ui.Ui;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.user.UserList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_SAME_OWNER;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NUMBER_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_LESS_THAN_ZERO;

public class FindItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;

    public FindItemCommand(String[] parts, ItemList itemList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        if (parts.length != 3) {
            throw new InsufficientArgumentsException("Too few arguments!");
        }
    }

    @Override
    public boolean executeCommand() {
        try {
            Ui.printResponse(itemList.getItemsByKeyword(parts[3]).toString());
            // may face problems if search term has more than one word
        } catch (ItemNotFoundException e) {
            Ui.printResponse(e.getMessage());
        }
        return false;
    }
}
