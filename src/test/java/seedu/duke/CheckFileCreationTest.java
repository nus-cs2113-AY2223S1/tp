package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class CheckFileCreationTest {
    private static final String DIRECTORY = "./test_dir/";
    private static final String INCORRECT_FILE_PATH = "./test_dir2/test_file.txt";
    private static final String FILE_PATH = "./test_file.txt";
    private static final String SEPARATOR = " | ";

    @Test
    void checkDirectory_noDirectory_expectCreation() {

        File dir = new File(DIRECTORY);
        dir.mkdir();
        assertTrue(dir.exists());
        // Delete the test directory after test is completed.
        dir.delete();
    }

    @Test
    void storeFile_noDirectory_expectThrow() {
        assertThrows(FileNotFoundException.class, () -> new FileWriter(INCORRECT_FILE_PATH));
    }

    @Test
    void storeFile_fileExist_expectCorrectRead() {
        String name = "Bobby Tan";
        String contact = "91234567";
        String email = "abc123@example.com";
        int budget = 2000;
        String fileText = name + SEPARATOR + contact + SEPARATOR + email + SEPARATOR + budget;

        // Store text into the text file
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(fileText);
            fw.close();
        } catch (IOException e) {
            System.out.println("Write operation failed.");
        }

        // Read the text file.
        File file = new File(FILE_PATH);
        try {
            Scanner input = new Scanner(file);
            if (input.hasNext()) {
                String inputText = input.nextLine();
                file.delete();
                assertEquals(fileText, inputText);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found.");
        }
    }


}
