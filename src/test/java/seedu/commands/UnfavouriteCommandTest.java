package seedu.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.data.CarparkList;
import seedu.data.Favourite;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.files.FileStorage;


public class UnfavouriteCommandTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");
    private final String validFavouriteFile = "unfavouriteCommandValid.txt";
    private final String emptyFile = "unfavouriteCommandEmpty.txt";

    @Test
    void testUnfavouriteCommand() throws FileWriteException, NoFileFoundException {
        FileStorage fileStorage = new FileStorage(testFileDirectory, validFavouriteFile);
        fileStorage.writeDataToFile("1\n2\n");
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, validFavouriteFile);
        favourite.updateFavouriteList(carparkList);
        String argument = "1";
        String result = new UnfavouriteCommand(argument, favourite, carparkList).execute().showToUser;
        Assertions.assertEquals("Removed Carpark 1 from favourites!", result);
    }

    @Test
    void testNoCarparkFound() throws FileWriteException, NoFileFoundException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, emptyFile);
        String argument = "1";
        String result = new UnfavouriteCommand(argument, favourite, carparkList).execute().showToUser;
        Assertions.assertEquals("Nothing to remove from favourites!", result);
    }
}
