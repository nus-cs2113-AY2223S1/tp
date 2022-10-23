package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.exception.InsufficientArgumentsException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;
import seedu.duke.item.ItemList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;

//@@author jorellesee
public class FindItemCommand extends Command {
    private final String[] parts;
    private final ItemList itemList;

    public FindItemCommand(String[] parts, ItemList itemList)
            throws InsufficientArgumentsException {
        this.parts = parts;
        this.itemList = itemList;
        if (parts.length != 1) {
            throw new InsufficientArgumentsException(MESSAGE_INSUFFICIENT_ARGUMENTS);
        }
    }

    private String getArgsFindItemCommand() throws InvalidArgumentException {
        String arg = "";
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals("k")) {
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
        Ui.printResponse(itemList.getItemsByKeyword(arg).toString());
        return false;
    }
}
