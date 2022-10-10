package seedu.duke.command;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidItemException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;

public class RemoveItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;

    public RemoveItemCommand(String[] parts, ItemList itemList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException();
        }
    }

    public String[] getArgsRemoveItemCmd() throws InvalidArgumentException {
        String[] args = new String[1];
        for (String part : parts) {
            if (part.startsWith("i")) {
                args[0] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException("One of the parts is in incorrect format");
            }

        }
        return args;
    }

    public boolean executeCommand()
            throws InvalidArgumentException, ItemNotFoundException, InvalidItemException {
        String[] args = getArgsRemoveItemCmd();
        String itemId = args[0];
        itemList.deleteItem(itemId);
        return false;
    }
}
