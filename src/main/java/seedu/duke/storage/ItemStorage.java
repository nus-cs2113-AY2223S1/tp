package seedu.duke.storage;

import seedu.duke.exception.ItemFileNotFoundException;
import seedu.duke.exception.StoreFailureException;
import seedu.duke.item.Item;
import seedu.duke.item.ItemList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_FILE_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STORE_INVALID;

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
    public ArrayList<Item> loadData() throws ItemFileNotFoundException {
        try {
            File itemFile = new File(itemFilePath);
            ArrayList<Item> itemList = new ArrayList<>();
            Scanner scanner = new Scanner(itemFile);
            while (scanner.hasNext()) {
                String itemLine = scanner.nextLine();
                String[] splitItemLine = itemLine.split(SEPARATOR);
                Item item = handleItemLine(splitItemLine);
                itemList.add(item);
            }
            return itemList;
        } catch (FileNotFoundException e) {
            throw new ItemFileNotFoundException(MESSAGE_FILE_NOT_FOUND);
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
    }

    /**
     * Analyses the information the items stored in the file.
     *
     * @param splitItemLine The raw item information.
     * @return An Item with full information.
     */
    public Item handleItemLine(String[] splitItemLine) {
        String itemId = splitItemLine[0];
        String itemName = splitItemLine[1];
        double price = Double.parseDouble(splitItemLine[2]);
        String ownerId = splitItemLine[3];
        int categoryNumber = Integer.parseInt(splitItemLine[4]);
        return new Item(itemId, itemName, categoryNumber, price, ownerId);
    }
}
