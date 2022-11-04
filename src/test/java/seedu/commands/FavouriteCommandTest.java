package seedu.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.files.Favourite;

public class FavouriteCommandTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");
    private final String newDirectory = "./new_directory";
    private final String newFilename = "new_filename.txt";
    private final String validFavouriteFile = "favouriteCommandValid.txt";

    private final String validFavouriteList = "favouriteCommandList.txt";
    private final String invalidFavouriteFile = "favouriteCommandInvalid.txt";
    private final String emptyFile = "favouriteCommandEmpty.txt";

    @Test
    void testFavouriteCommand() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, validFavouriteFile);
        String argument = "1";
        String result = new FavouriteCommand(argument, favourite, carparkList).execute().showToUser;
        Assertions.assertEquals("Added Carpark 1 to favourites!", result);
    }

    @Test
    void testFavouriteList() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, validFavouriteList);
        String argument = "list";
        String result = new FavouriteCommand(argument, favourite, carparkList).execute().showToUser;
        Assertions.assertEquals("===========================================\n"
                + "CarparkID @|yellow,bold 1|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total\n"
                + "===========================================\n"
                + "CarparkID @|yellow,bold 2|@ at Marina Square\n"
                + "@|faint -->|@ @|yellow 1003|@ available lots total", result);
    }

    @Test
    void testInvalidFavouriteList() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, invalidFavouriteFile);
        String argument = "list";
        String result = new FavouriteCommand(argument, favourite, carparkList).execute().showToUser;
        Assertions.assertEquals("===========================================\n"
                + "CarparkID @|yellow,bold 1|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total\n"
                + "===========================================\n"
                + "CarparkID @|yellow,bold 2|@ at Marina Square\n"
                + "@|faint -->|@ @|yellow 1003|@ available lots total", result);
    }

    @Test
    void testEmptyFavouriteList() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, emptyFile);
        String argument = "list";
        String result = new FavouriteCommand(argument, favourite, carparkList).execute().showToUser;
        Assertions.assertEquals("There are no favourites in the list!", result);
    }

    @Test
    void testNothingToAdd() throws FileWriteException, NoFileFoundException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(newDirectory, newFilename);
        String result = new FavouriteCommand("4", favourite, carparkList).execute().showToUser;
        Assertions.assertEquals("Nothing to add to favourites!", result);
    }
}
