package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;


public class CheckFileCreationTest {
    private static String DIRECTORY = "./test_dir/";

    @Test
    void checkDirectory_noDirectory_expectCreation() {

        File dir = new File(DIRECTORY);
        dir.mkdir();
        assertTrue(dir.exists());
        // Delete the test directory after test is completed.
        dir.delete();
    }


}
