package seedu.files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;

public class FavouriteTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");
    private final String newDirectory = "./new_directory";
    private final String newFilename = "new_filename.txt";
    private final String validFavouriteFile = "favouriteValid.txt";
    private final String invalidFavouriteFile = "favouriteInvalid.txt";
    private final String emptyFile = "favouriteEmpty.txt";

    @Test
    void newFilePathTest() throws NoFileFoundException, FileWriteException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(newDirectory, newFilename);
        Assertions.assertDoesNotThrow(() -> favourite.updateFavouriteList(carparkList));
    }

    @Test
    void testEnsureValidInput() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, validFavouriteFile);
        favourite.updateFavouriteList(carparkList);
        Assertions.assertTrue(favourite.ensureValidity(carparkList, Favourite.getFavouriteList()));
    }

    @Test
    void testEnsureRemoveInvalidInput() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        FileStorage fileStorage = new FileStorage(testFileDirectory, invalidFavouriteFile);
        fileStorage.writeDataToFile("1\nnot_a_valid_id\n2\n");
        Favourite favourite = new Favourite(testFileDirectory, invalidFavouriteFile);
        favourite.updateFavouriteList(carparkList);
        favourite.ensureValidity(carparkList, Favourite.getFavouriteList());
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("1");
        expected.add("2");
        Assertions.assertEquals(expected, Favourite.getFavouriteList());
    }

    @Test
    void testWriteToFile() throws FileWriteException, NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        FileStorage fileStorage = new FileStorage(testFileDirectory, emptyFile);
        fileStorage.writeDataToFile(" ");
        Favourite favourite = new Favourite(testFileDirectory, emptyFile);
        Favourite.getFavouriteList().add("1");
        Favourite.getFavouriteList().add("2");
        favourite.writeFavouriteList();
        favourite.updateFavouriteList(carparkList);
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("1");
        expected.add("2");
        Assertions.assertEquals(expected, Favourite.getFavouriteList());
    }

    @Test
    void testGetFavouriteListString() throws FileWriteException, NoFileFoundException, NoCarparkFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, emptyFile);
        Favourite.getFavouriteList().add("1");
        Favourite.getFavouriteList().add("2");
        Assertions.assertEquals("===========================================\n"
                + "CarparkID @|yellow,bold 1|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total\n"
                + "===========================================\n"
                + "CarparkID @|yellow,bold 2|@ at Marina Square\n"
                + "@|faint -->|@ @|yellow 1003|@ available lots total\n",
                favourite.getFavouriteListString(carparkList));
    }
}
