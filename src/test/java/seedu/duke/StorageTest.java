package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

public class StorageTest {
    private String filepath = "./data/stored.txt";
    private String folderpath = "./data/";
    Storage store = new Storage(filepath, folderpath);
    
    @Test
    void checkFile() {
        store.getMakeFile();
        File f = new File(filepath);
        assertTrue(f.exists() && !f.isDirectory());
    }
}
