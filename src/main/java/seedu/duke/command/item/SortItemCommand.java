package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.exception.InvalidSortModeException;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidPriceException;
import seedu.duke.exception.InvalidPriceBoundariesException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_PRICE_BOUNDARIES_INVALID;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_PRICE_LESS_THAN_ZERO;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_SORT_MODE_INVALID;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.ExceptionMessages.MESSAGE_PRICE_FORMAT_INVALID;

public class SortItemCommand extends Command {

    private static final String LOW_HIGH = "lh";

    private static final String HIGH_LOW = "hl";

    private final String[] parts;

    private final ItemList itemList;

    private final TransactionList transactionList;

    public SortItemCommand(String[] parts, ItemList itemList, TransactionList transactionList) {
        this.parts = parts;
        this.itemList = itemList;
        this.transactionList = transactionList;
    }

    public String[] getArgsSortItemsCmd() throws InvalidArgumentException {
        String[] args = new String[3];
        for (String part : parts) {
            if (part.startsWith("mo")) {
                args[0] = CommandParser.getArgValue(part);
            } else if (part.startsWith("mi")) {
                args[1] = CommandParser.getArgValue(part);
            } else if (part.startsWith("ma")) {
                args[2] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return args;
    }

    private boolean isValidMode(String mode) throws InvalidSortModeException {
        try {
            if (mode.equals(LOW_HIGH) || mode.equals(HIGH_LOW)) {
                return true;
            }
            throw new InvalidSortModeException(MESSAGE_SORT_MODE_INVALID);
        } catch (InvalidSortModeException e) {
            throw new InvalidSortModeException(MESSAGE_SORT_MODE_INVALID);
        }
    }

    private boolean isValidMin(String minPrice) throws InvalidPriceException {
        try {
            if (Double.parseDouble(minPrice) < 0) {
                throw new InvalidPriceException(MESSAGE_PRICE_LESS_THAN_ZERO);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_PRICE_FORMAT_INVALID);
        }
    }

    private boolean isValidMax(String maxPrice) throws InvalidPriceException {
        try {
            if (Double.parseDouble(maxPrice) < 0) {
                throw new InvalidPriceException(MESSAGE_PRICE_LESS_THAN_ZERO);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_PRICE_FORMAT_INVALID);
        }
    }

    private boolean isValidBoundaries(String minPrice, String maxPrice) throws InvalidPriceBoundariesException {
        try {
            if (Double.parseDouble(minPrice) > Double.parseDouble(maxPrice)) {
                throw new InvalidPriceBoundariesException(MESSAGE_PRICE_BOUNDARIES_INVALID);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_PRICE_FORMAT_INVALID);
        }
    }

    private boolean areValidArgs(String[] args)
            throws InvalidSortModeException, InvalidPriceException, InvalidPriceBoundariesException {
        return isValidMode(args[0]) && isValidMin(args[1]) && isValidMax(args[2])
                && isValidBoundaries(args[1], args[2]);
    }

    private List<Item> sortAndFilter()
            throws InvalidArgumentException, InvalidSortModeException,
            InvalidPriceException, InvalidPriceBoundariesException {
        String[] args = getArgsSortItemsCmd();
        List<Item> sortedItems = new ArrayList<>();
        if (areValidArgs(args)) {
            String mode = args[0];
            double min = Double.parseDouble(args[1]);
            double max = Double.parseDouble(args[2]);
            if (mode.equals(LOW_HIGH)) {
                sortedItems = itemList.getItemList().stream()
                        .sorted(comparingDouble(Item::getPricePerDay))
                        .filter(item -> item.getPricePerDay() >= min && item.getPricePerDay() <= max)
                        .collect(Collectors.toList());
            } else if (mode.equals(HIGH_LOW)) {
                sortedItems = itemList.getItemList().stream()
                        .sorted(Comparator.comparingDouble(Item::getPricePerDay).reversed())
                        .filter(item -> item.getPricePerDay() >= min && item.getPricePerDay() <= max)
                        .collect(Collectors.toList());
            }
        }
        return sortedItems;
    }

    public boolean executeCommand()
            throws InvalidArgumentException, InvalidSortModeException,
            InvalidPriceException, InvalidPriceBoundariesException {
        StringBuilder listString = new StringBuilder();
        List<Item> itemsList = sortAndFilter();
        if (itemsList.size() == 0) {
            listString.append("There is no items in your filtered list right now");
        } else {
            listString.append("Here are ").append(itemsList.size()).append(" item(s) in your filtered list:");
        }
        int index = 1;
        for (Item item : itemsList) {
            listString.append('\n').append("   ").append(index++).append(". ").append(item.toString(transactionList));
        }
        Ui.printResponse(listString.toString());
        return false;
    }
}
