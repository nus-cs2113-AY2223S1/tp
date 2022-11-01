package seedu.duke.storage;

import seedu.duke.exception.ItemFileNotFoundException;
import seedu.duke.exception.StoreFailureException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.user.UserList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_FILE_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_STORAGE_ILLEGALLY_MODIFIED;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NUM_OF_ARGS_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STORAGE_REASON;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STORE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TO_FIX_FILES;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_VALUE_OF_ARGS_INVALID;

// @@author bdthanh
public class ItemStorage extends Storage {
    private static final String SEPARATOR = "\\|";
    private static final int ITEM_NAME_INDEX = 0;
    private static final int CATEGORY_INDEX = 1;
    private static final int PRICE_INDEX = 2;
    private static final int OWNER_INDEX = 3;
    private static final int ITEM_ID_INDEX = 4;
    private static final int NUM_OF_ARGS = 5;
    private final String itemFilePath;
    private final ItemList itemList;
    private final UserList userList;

    /**
     * Constructor for Storage of Items.
     */
    public ItemStorage(String itemFilePath, UserList userList) {
        this.itemFilePath = itemFilePath;
        this.itemList = new ItemList();
        this.userList = userList;
    }

    /**
     * Read the items from a given file.
     *
     * @return The list of items stored in the file
     * @throws ItemFileNotFoundException If the file cannot be found
     * @throws StoreFailureException     If there is a failure loading
     */
    public ItemList loadData() throws ItemFileNotFoundException, StoreFailureException {
        int lineNo = 0;
        try {
            File itemFile = new File(itemFilePath);
            Scanner scanner = new Scanner(itemFile);
            while (scanner.hasNext()) {
                lineNo++;
                String itemLine = scanner.nextLine();
                String[] splitItemLine = itemLine.split(SEPARATOR);
                trimArrayValues(splitItemLine);
                Item item = handleItemLine(splitItemLine);
                itemList.addItem(item);
            }
            return itemList;
        } catch (FileNotFoundException e) {
            throw new ItemFileNotFoundException(MESSAGE_FILE_NOT_FOUND);
        } catch (Exception e) {
            throw new StoreFailureException(MESSAGE_ITEM_STORAGE_ILLEGALLY_MODIFIED + lineNo
                    + MESSAGE_STORAGE_REASON + e.getMessage() + "\n" + MESSAGE_TO_FIX_FILES);
        }
    }

    /**
     * Writes the current items to a file when exiting Duke.
     *
     * @param itemList The list of items to be stored.
     * @throws StoreFailureException If there is an exception occurs.
     */
    public void writeData(ItemList itemList) throws StoreFailureException {
        try {
            FileWriter fileWriter = new FileWriter(itemFilePath);
            String formattedItemList = itemList.convertItemListToFileFormat();
            fileWriter.write(formattedItemList);
            fileWriter.close();
        } catch (IOException e) {
            makeItemDir(itemList);
        }
    }

    private void makeItemDir(ItemList itemList) throws StoreFailureException {
        int startIndex = itemFilePath.lastIndexOf("/");
        String fileDirectory = itemFilePath.replace(itemFilePath.substring(startIndex), "");
        File file = new File(fileDirectory);
        if (file.mkdir()) {
            writeData(itemList);
        } else {
            throw new StoreFailureException(MESSAGE_STORE_INVALID);
        }
    }

    /**
     * Analyses the information the items stored in the file
     * and checks if valid or not.
     *
     * @param splitItemLine The raw item information.
     * @return An Item with full information.
     */
    public Item handleItemLine(String[] splitItemLine) throws Exception {
        checkIfArgsEmpty(splitItemLine, NUM_OF_ARGS, MESSAGE_NUM_OF_ARGS_INVALID, MESSAGE_VALUE_OF_ARGS_INVALID);
        itemList.checkValidArgsForItem(userList, splitItemLine);
        itemList.checkValidId(splitItemLine[ITEM_ID_INDEX]);
        return getItemFromItemLine(splitItemLine);
    }

    private Item getItemFromItemLine(String[] splitItemLine) throws Exception {
        String itemId = splitItemLine[ITEM_ID_INDEX];
        String itemName = splitItemLine[ITEM_NAME_INDEX];
        double price = Double.parseDouble(splitItemLine[PRICE_INDEX]);
        String ownerId = splitItemLine[OWNER_INDEX];
        int categoryNumber = Integer.parseInt(splitItemLine[CATEGORY_INDEX]);
        return new Item(itemId, itemName, categoryNumber, price, ownerId);
    }
}
