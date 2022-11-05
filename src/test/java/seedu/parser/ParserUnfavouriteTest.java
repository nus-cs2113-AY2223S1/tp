package seedu.parser;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.commands.Command;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.files.Favourite;
import seedu.files.FileStorage;

public class ParserUnfavouriteTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "ltaResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "ltaResponseBackup.json");
    private final String validFavouriteFile = "ParserFavouriteValid.txt";

    private final String validFavouriteList = "ParserFavouriteList.txt";
    private final String invalidFavouriteFile = "ParserFavouriteInvalid.txt";
    private final String emptyFile = "ParserFavouriteEmpty.txt";

    @Test
    void testUnfavourite() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, validFavouriteFile);
        favourite.updateFavouriteList(carparkList);
        String input = "unfavourite 1";
        Command command = new Parser().parseCommand(input, null, carparkList, favourite);
        String result = command.execute().showToUser;
        Assertions.assertEquals("Removed Carpark 1 from favourites!", result);
    }

    @Test
    void testFavouriteListValid() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        FileStorage fileStorage = new FileStorage(testFileDirectory, validFavouriteFile);
        fileStorage.writeDataToFile("1\n2\n");
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, validFavouriteList);
        String input = "favourite list";
        Command command = new Parser().parseCommand(input, null, carparkList, favourite);
        String result = command.execute().showToUser;
        Assertions.assertEquals("===========================================\n"
                + "CarparkID @|yellow,bold 1|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total\n"
                + "===========================================\n"
                + "CarparkID @|yellow,bold 2|@ at Marina Square\n"
                + "@|faint -->|@ @|yellow 1003|@ available lots total\n"
                + "===========================================\n"
                + "CarparkID @|yellow,bold 3|@ at Raffles City\n"
                + "@|faint -->|@ @|yellow 522|@ available lots total", result);
    }

    @Test
    void testFavouriteListInvalid() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        FileStorage fileStorage = new FileStorage(testFileDirectory, validFavouriteFile);
        fileStorage.writeDataToFile("1\ninalid\n2\n");
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, invalidFavouriteFile);
        String input = "favourite list";
        Command command = new Parser().parseCommand(input, null, carparkList, favourite);
        String result = command.execute().showToUser;
        Assertions.assertEquals("===========================================\n"
                + "CarparkID @|yellow,bold 1|@ at Suntec City\n"
                + "@|faint -->|@ @|yellow 1882|@ available lots total\n"
                + "===========================================\n"
                + "CarparkID @|yellow,bold 2|@ at Marina Square\n"
                + "@|faint -->|@ @|yellow 1003|@ available lots total", result);
    }

    @Test
    void testFavouriteEmpty() throws FileWriteException, NoCarparkFoundException, InvalidCommandException,
            NoFileFoundException {
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Favourite favourite = new Favourite(testFileDirectory, emptyFile);
        String input = "favourite list";
        Command command = new Parser().parseCommand(input, null, carparkList, favourite);
        String result = command.execute().showToUser;
        Assertions.assertEquals("There are no favourites in the list!", result);
    }
}
