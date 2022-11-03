package seedu.duke.data;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataManagerTest {

    @BeforeAll
    public static void setUp() {
        DataManager.initDataFile("1", "TestData");
    }

    @AfterAll
    public static void tearDown() {
        DataManager.resetDataFiles();
    }

    @Test
    void getCurrentSem_noInput_returnCorrectSem() {
        assertEquals("1",
                DataManager.getCurrentSem());
    }

    @Test
    void getDirPath_noInput_returnCorrectPath() {
        assertEquals("./Sem1TestData",
                DataManager.getDirPath());
    }

}