package seedu.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import seedu.exception.DuplicateCarparkException;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoCommandArgumentException;
import seedu.exception.NoFileFoundException;
import seedu.exception.UnneededArgumentsException;
import seedu.files.FileReader;
import seedu.files.FileStorage;
import seedu.parser.Parser;
import seedu.ui.Ui;

/**
 * Represents the 'favourite' and 'unfavourite' command to save carparks in a list.
 */
public class Favourite {
    private final FileStorage fileStorage;
    private ArrayList<String> favouriteList;
    private final String directory;
    private final String file;
    private final Ui ui;

    /**
     * Constructor for favourite class.
     *
     * @param directory Directory of the favourite file.
     * @param file Filename of the favourite file.
     */
    public Favourite(String directory, String file) {
        ui = new Ui();
        this.fileStorage = new FileStorage(directory, file);
        this.directory = directory;
        this.file = file;
    }

    /**
     * Inserts a carpark into the favourite list.
     *
     * @param carparkId Carpark ID to favourite.
     * @throws FileWriteException If unable to write to favourite.txt file.
     * @throws DuplicateCarparkException If carpark ID is already in favourites.
     */
    public void setFavourite(String carparkId) throws FileWriteException, DuplicateCarparkException {
        boolean containsSearchStr = favouriteList.stream().anyMatch(carparkId::equalsIgnoreCase);
        if (containsSearchStr) {
            throw new DuplicateCarparkException();
        }
        favouriteList.add(carparkId);
        writeFavouriteList();
    }

    /**
     * Remove a carpark from the favourite list.
     *
     * @param carparkId Carpark ID to remove.
     * @throws FileWriteException If unable to write to favourite.txt file.
     * @throws NoCarparkFoundException If carpark ID is not in favourites.
     */
    public void setUnfavourite(String carparkId) throws FileWriteException, NoCarparkFoundException {
        boolean containsSearchStr = favouriteList.stream().anyMatch(carparkId::equalsIgnoreCase);
        if (!containsSearchStr) {
            throw new NoCarparkFoundException();
        }
        favouriteList.removeIf(value->value.equalsIgnoreCase(carparkId));
        writeFavouriteList();
    }

    /**
     * Reads in data from favourite.txt file and saves it to this object.
     *
     * @throws IOException If unable to read from file.
     * @throws NoFileFoundException If no file found.
     */
    public void updateFavouriteList() throws IOException, NoFileFoundException {
        String content = FileReader.readStringFromTxt(file, directory, true);
        String[] lines = content.split("\\R");
        ArrayList<String> tempArray = new ArrayList<String>();
        Collections.addAll(tempArray, lines);
        this.favouriteList = tempArray;
    }

    /**
     * Writes all favourite carpark IDs to favourite.txt file.
     *
     * @throws FileWriteException If unable to write to file.
     */
    public void writeFavouriteList() throws FileWriteException {
        StringBuilder content = new StringBuilder();
        for (String id : favouriteList) {
            content.append(id).append("\n");
        }
        fileStorage.writeDataToFile(content.toString());
    }

    /**
     * Returns the carpark ID requested.
     *
     * @param input User command.
     * @return Carpark ID.
     * @throws UnneededArgumentsException If too many arguments.
     * @throws NoCommandArgumentException If not enough arguments.
     */
    public String getCarparkId(String input) throws UnneededArgumentsException, NoCommandArgumentException {
        String[] words = Parser.splitCommandArgument(input);
        if (words.length == 2 && words[1].trim().length() > 0) {
            return words[1];
        } else if (words.length > 2) {
            throw new UnneededArgumentsException("favourite");
        } else {
            throw new NoCommandArgumentException("favourite");
        }
    }

    /**
     * Returns the carpark IDs of all favourited carparks in a string.
     *
     * @return String of favourited carpark IDs.
     */
    public String showList() {
        StringBuilder result = new StringBuilder();
        for (String id : favouriteList) {
            result.append(id).append("\n");
            ui.print(id);
        }
        return result.toString();
    }
}
