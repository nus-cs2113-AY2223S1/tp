package seedu.files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import seedu.exception.FileWriteException;
import seedu.exception.NoFileFoundException;

/**
 * Represents the 'favourite' class.
 */
public class Favourite {
    private final FileStorage fileStorage;
    public ArrayList<String> favouriteList;
    private final String directory;
    private final String file;

    /**
     * Constructor for favourite class.
     *
     * @param directory Directory of the favourite file.
     * @param file Filename of the favourite file.
     */
    public Favourite(String directory, String file) {
        this.fileStorage = new FileStorage(directory, file);
        this.directory = directory;
        this.file = file;
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
     * Returns the carpark IDs of all favourited carparks in a string.
     *
     * @return String of favourited carpark IDs.
     */
    public String showList() {
        StringBuilder result = new StringBuilder();
        for (String id : favouriteList) {
            result.append(id).append("\n");
        }
        return result.toString();
    }
}
