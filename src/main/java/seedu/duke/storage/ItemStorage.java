package seedu.duke.storage;

import seedu.duke.exception.InvalidCategoryException;
import seedu.duke.exception.ItemFileNotFoundException;
import seedu.duke.exception.StoreFailureException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;
import seedu.duke.transaction.TransactionList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_FILE_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_ITEM_STORAGE_ILLEGALLY_MODIFIED;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STORE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TO_FIX_FILES;

//@@author bdthanh
public class ItemStorage extends Storage {
    private static final String SEPARATOR = " \\| ";
    private final String itemFilePath;

    /**
     * Constructor for Storage of Items.
     */
    public ItemStorage(String itemFilePath) {
        this.itemFilePath = itemFilePath;
    }

    /**
     * Read the items from a given file.
     *
     * @return The list of items stored in the file.
     * @throws ItemFileNotFoundException If the file cannot be found.
     */
    public ArrayList<Item> loadData() throws ItemFileNotFoundException, StoreFailureException {
        try {
            File itemFile = new File(itemFilePath);
            ArrayList<Item> itemList = new ArrayList<>();
            Scanner scanner = new Scanner(itemFile);
            int checkSum = Integer.parseInt(scanner.nextLine());
            while (scanner.hasNext()) {
                String itemLine = scanner.nextLine();
                String[] splitItemLine = itemLine.split(SEPARATOR);
                Item item = handleItemLine(splitItemLine);
                itemList.add(item);
            }
            checkCheckSumWhole(itemList, checkSum);
            return itemList;
        } catch (FileNotFoundException e) {
            throw new ItemFileNotFoundException(MESSAGE_FILE_NOT_FOUND);
        } catch (Exception e) {
            throw new StoreFailureException(MESSAGE_ITEM_STORAGE_ILLEGALLY_MODIFIED + MESSAGE_TO_FIX_FILES);
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
        String fileDirectory =
                itemFilePath.replace(itemFilePath.substring(startIndex), "");
        File file = new File(fileDirectory);
        if (file.mkdir()) {
            writeData(itemList);
        } else {
            throw new StoreFailureException(MESSAGE_STORE_INVALID);
        }
    }

    public boolean hasItemFile() {
        return new File(itemFilePath).exists();
    }

    /**
     * Analyses the information the items stored in the file.
     *
     * @param splitItemLine The raw item information.
     * @return An Item with full information.
     */
    public static Item handleItemLine(String[] splitItemLine) throws InvalidCategoryException, StoreFailureException {
        assert splitItemLine.length == 6 : "Invalid Transaction Line";
        try {
            Item item = getItemFromItemLine(splitItemLine);
            checkCheckSumLine(item, Integer.parseInt(splitItemLine[5].trim()));
            return item;
        } catch (Exception e) {
            throw new StoreFailureException(MESSAGE_ITEM_STORAGE_ILLEGALLY_MODIFIED + MESSAGE_TO_FIX_FILES);
        }
    }

    private static Item getItemFromItemLine(String[] splitItemLine) throws StoreFailureException {
        try {
            String itemId = splitItemLine[0].trim();
            String itemName = splitItemLine[1].trim();
            double price = Double.parseDouble(splitItemLine[2].trim());
            String ownerId = splitItemLine[3].trim();
            int categoryNumber = Integer.parseInt(splitItemLine[4].trim());
            return new Item(itemId, itemName, categoryNumber, price, ownerId);
        } catch (Exception e) {
            throw new StoreFailureException(MESSAGE_ITEM_STORAGE_ILLEGALLY_MODIFIED + MESSAGE_TO_FIX_FILES);
        }
    }

    private static void checkCheckSumLine(Item item, int checkSum) throws StoreFailureException {
        if (item.toString(new TransactionList()).length() != checkSum) {
            throw new StoreFailureException(MESSAGE_ITEM_STORAGE_ILLEGALLY_MODIFIED + MESSAGE_TO_FIX_FILES);
        }
    }

    private static void checkCheckSumWhole(ArrayList<Item> itemList, int checkSum) throws StoreFailureException {
        if (itemList.size() != checkSum) {
            throw new StoreFailureException(MESSAGE_ITEM_STORAGE_ILLEGALLY_MODIFIED + MESSAGE_TO_FIX_FILES);
        }
    }
}
