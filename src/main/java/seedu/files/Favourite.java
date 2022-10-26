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
    private static ArrayList<String> favouriteList;
    private final FileStorage fileStorage;
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
    public void updateFavouriteList() throws IOException, NoFileFoundException, FileWriteException {
        String content = FileReader.readStringFromTxt(file, directory, true);
        String[] lines = content.split("\\R");
        ArrayList<String> tempArray = new ArrayList<String>();
        Collections.addAll(tempArray, lines);
        favouriteList = tempArray;
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

    public static ArrayList<String> getFavouriteList() {
        return favouriteList;
    }
}
