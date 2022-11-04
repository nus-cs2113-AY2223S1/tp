package seedu.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.api.ApiStub;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;

public class UpdateCommandTest {
    private final String testFileDirectory = "./src/test/java/seedu/testfiles";
    private final String testFileName = "updateJsonResponse.json";
    private final Path validPathAndFile = Paths.get(testFileDirectory, "updateLocalResponse.json");
    private final Path validBackupPathAndFile = Paths.get(testFileDirectory, "updateLocalResponseBackup.json");

    @Test
    void updateCommandTest()
            throws FileWriteException, NoFileFoundException, NoCarparkFoundException, InvalidCommandException {
        ApiStub api = new ApiStub(testFileName, testFileDirectory);
        CarparkList carparkList = new CarparkList(validPathAndFile, validBackupPathAndFile);
        Command update = new UpdateCommand(api, carparkList);
        update.execute();
        String expectedResult = "CarparkID 1 at Suntec City: 1882 lots available";
        String actualResult = carparkList.findCarpark("1").toString();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
