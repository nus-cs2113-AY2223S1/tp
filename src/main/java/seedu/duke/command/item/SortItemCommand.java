package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.exception.InvalidPriceBoundariesException;
import seedu.duke.exception.InvalidPriceException;
import seedu.duke.exception.InvalidSortModeException;
import seedu.duke.item.Category;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.parser.CommandParser;
import seedu.duke.transaction.TransactionList;
import seedu.duke.ui.Ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CATEGORY_INDEX_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_INVALID_PARTS;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_BOUNDARIES_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_OUT_OF_RANGE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_SORT_MODE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_PRICE_TOO_MANY_DECIMALS;

// @@author chiewyx

/**
 * A representation of a command to sort items based on its price.
 */
public class SortItemCommand extends Command {
    private static final String LOW_HIGH = "lh";
    private static final String HIGH_LOW = "hl";
    private static final String MIN_AMT = "0";
    private static final String MAX_AMT = "10000";
    private static final String DEFAULT_SORT = "lh";
    private static final String NO_CATEGORY = "0";
    private static final String MODE_DELIM = "mode";
    private static final String MIN_DELIM = "min";
    private static final String MAX_DELIM = "max";
    private static final String CAT_DELIM = "cat";

    private final String[] parts;

    private final ItemList itemList;

    private final TransactionList transactionList;

    /**
     * Constructor for SortItemCommand.
     *
     * @param parts The parts from user input
     * @param itemList The list of items to work with
     * @param transactionList The list of transactions to work with
     */
    public SortItemCommand(String[] parts, ItemList itemList, TransactionList transactionList) {
        this.parts = parts;
        this.itemList = itemList;
        this.transactionList = transactionList;
    }

    /**
     * Get arg values from the respective parts.
     *
     * @return An array of arg values
     * @throws InvalidArgumentException if there is a part that does not fit the command
     */
    private String[] getArgsSortItemsCmd() throws InvalidArgumentException {
        String[] args = new String[4];
        for (String part : parts) {
            String delimiter = CommandParser.getArgsDelimiter(part);
            if (delimiter.equals(MODE_DELIM)) {
                args[0] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(MIN_DELIM)) {
                args[1] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(MAX_DELIM)) {
                args[2] = CommandParser.getArgValue(part);
            } else if (delimiter.equals(CAT_DELIM)) {
                args[3] = CommandParser.getArgValue(part);
            } else {
                throw new InvalidArgumentException(MESSAGE_INVALID_PARTS);
            }
        }
        return args;
    }

    /**
     * Remove the optional args if there are any from the array.
     *
     * @param args an array containing the arg values including null
     * @return an array with optional arguments removed
     */
    private String[] removeOptionalArgs(String[] args) {
        if (args[0] == null) {
            args[0] = DEFAULT_SORT;
        }
        if (args[1] == null) {
            args[1] = MIN_AMT;
        }
        if (args[2] == null) {
            args[2] = MAX_AMT;
        }
        if (args[3] == null) {
            args[3] = NO_CATEGORY;
        }
        return args;
    }

    /**
     * Checks if mode of sorting is valid.
     *
     * @param mode the mode of sorting input by the user
     * @return true if is valid
     * @throws InvalidSortModeException if the mode of sorting is invalid
     */
    private boolean isValidMode(String mode) throws InvalidSortModeException {
        if (mode.equals(LOW_HIGH) || mode.equals(HIGH_LOW)) {
            return true;
        }
        throw new InvalidSortModeException(MESSAGE_SORT_MODE_INVALID);
    }

    /**
     * Checks if the minimum price is valid.
     *
     * @param minPrice the minPrice argument
     * @return true if the minPrice > 0
     * @throws InvalidPriceException if minPrice < 0
     */
    private boolean isValidMin(String minPrice) throws InvalidPriceException {
        try {
            if (Double.parseDouble(minPrice) < 0 || Double.parseDouble(minPrice) > 10000) {
                throw new InvalidPriceException(MESSAGE_PRICE_OUT_OF_RANGE);
            } else if (BigDecimal.valueOf(Double.parseDouble(minPrice)).scale() > 2) {
                throw new InvalidPriceException(MESSAGE_PRICE_TOO_MANY_DECIMALS);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_PRICE_FORMAT_INVALID);
        }
    }

    /**
     * Checks if the maximum price is valid.
     *
     * @param maxPrice the maximum price argument
     * @return true if maxPrice > 0
     * @throws InvalidPriceException if maxPrice < 0
     */
    private boolean isValidMax(String maxPrice) throws InvalidPriceException {
        try {
            if (Double.parseDouble(maxPrice) < 0 || Double.parseDouble(maxPrice) > 10000) {
                throw new InvalidPriceException(MESSAGE_PRICE_OUT_OF_RANGE);
            } else if (BigDecimal.valueOf(Double.parseDouble(maxPrice)).scale() > 2) {
                throw new InvalidPriceException(MESSAGE_PRICE_TOO_MANY_DECIMALS);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_PRICE_FORMAT_INVALID);
        }
    }

    /**
     * Checks if the price boundaries are valid.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return true if minPrice < maxPrice
     * @throws InvalidPriceBoundariesException if minPrice > maxPrice
     */
    private boolean isValidBoundaries(String minPrice, String maxPrice)
            throws InvalidPriceBoundariesException {
        try {
            if (Double.parseDouble(minPrice) > Double.parseDouble(maxPrice)) {
                throw new InvalidPriceBoundariesException(MESSAGE_PRICE_BOUNDARIES_INVALID);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_PRICE_FORMAT_INVALID);
        }
    }

    /**
     * Checks if a categoryNumber is valid or not.
     *
     * @param categoryNumber the input category number
     * @return true if that number can be parsed
     */
    private boolean isValidCategoryNumber(String categoryNumber) {
        try {
            Integer.parseInt(categoryNumber);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_CATEGORY_INDEX_FORMAT_INVALID);
        }
    }

    private boolean areValidArgs(String[] args) throws InvalidSortModeException,
            InvalidPriceException, InvalidPriceBoundariesException {
        return isValidMode(args[0]) && isValidMin(args[1]) && isValidMax(args[2])
                && isValidBoundaries(args[1], args[2]) && isValidCategoryNumber(args[3]);
    }

    /**
     * Sorts the list of items.
     *
     * @return a list containing the sorted items
     * @throws InvalidArgumentException if the arguments are invalid
     * @throws InvalidSortModeException if the mode of sorting is invalid
     * @throws InvalidPriceException if minPrice and maxPrice are invalid
     * @throws InvalidPriceBoundariesException if minPrice > maxPrice
     */
    private List<Item> sortItems() throws InvalidArgumentException, InvalidSortModeException,
            InvalidPriceException, InvalidPriceBoundariesException {
        String[] args = getArgsSortItemsCmd();
        String[] mainArgs = removeOptionalArgs(args);
        List<Item> sortedItems = new ArrayList<>();
        if (areValidArgs(mainArgs)) {
            String mode = mainArgs[0];
            if (mode.equals(LOW_HIGH)) {
                sortedItems = itemList.getItemList().stream()
                        .sorted(comparingDouble(Item::getPricePerDay)).collect(Collectors.toList());
            } else if (mode.equals(HIGH_LOW)) {
                sortedItems = itemList.getItemList().stream()
                        .sorted(Comparator.comparingDouble(Item::getPricePerDay).reversed())
                        .collect(Collectors.toList());
            }
        }
        return sortedItems;
    }

    /**
     * Filters the list of items.
     *
     * @param sortedItems takes in sorted list to filter
     * @return filtered list based on price and category
     * @throws InvalidArgumentException if arguments are invalid
     * @throws InvalidSortModeException if mode of sorting is invalid
     * @throws InvalidPriceException if minPrice and maxPrice are invalid
     * @throws InvalidPriceBoundariesException if minPrice > maxPrice
     * @throws InvalidCategoryException if category number is invalid
     */
    private List<Item> filterItems(List<Item> sortedItems)
            throws InvalidArgumentException, InvalidSortModeException, InvalidPriceException,
            InvalidPriceBoundariesException, InvalidCategoryException {
        String[] args = getArgsSortItemsCmd();
        String[] mainArgs = removeOptionalArgs(args);
        if (areValidArgs(mainArgs)) {
            double min = Double.parseDouble(mainArgs[1]);
            double max = Double.parseDouble(mainArgs[2]);
            sortedItems = sortedItems.stream()
                    .filter(item -> item.getPricePerDay() >= min && item.getPricePerDay() <= max)
                    .collect(Collectors.toList());
            if (!mainArgs[3].equals(NO_CATEGORY)) {
                Category.Categories cat = Category.mapCategory(Integer.parseInt(mainArgs[3]));
                sortedItems = sortedItems.stream().filter(item -> item.getCategory() == cat)
                        .collect(Collectors.toList());
            }
        }
        return sortedItems;
    }

    /**
     * Executes SortItemsCommand.
     *
     * @return false
     * @throws InvalidArgumentException if the arguments are invalid
     * @throws InvalidSortModeException if the mode of sorting is invalid
     * @throws InvalidPriceException if minPrice and maxPrice are invalid
     * @throws InvalidPriceBoundariesException if minPrice > maxPrice
     */
    public boolean executeCommand() throws InvalidArgumentException, InvalidSortModeException,
            InvalidPriceException, InvalidPriceBoundariesException, InvalidCategoryException {
        StringBuilder listString = new StringBuilder();
        List<Item> itemsList = filterItems(sortItems());
        if (itemsList.size() == 0) {
            listString.append("There is no items in your filtered list right now");
        } else {
            listString.append("Here are ").append(itemsList.size())
                    .append(" item(s) in your filtered list:");
        }
        int index = 1;
        for (Item item : itemsList) {
            listString.append('\n').append(index++).append(". ")
                    .append(item.toString(transactionList));
        }
        Ui.printResponse(listString.toString());
        return false;
    }
}
