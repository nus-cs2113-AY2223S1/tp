package seedu.duke.userstorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**.
 * Deals with loading tasks from the specified file and saving tasks in the specified file
 * File name should be specified in Duke.java: "data/duke.txt"
 */
public class UserStorage {
    private static Logger logger = Logger.getLogger("UserStorage");
    private static final String USER_STORAGE_FILE_PATH = "data/duke.txt";

    /**.
     * Clears content in the file and writes new text into the file.
     * @param textToAdd Text to write into the file.
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public static void saveFile(String textToAdd) throws IOException {
        logger.log(Level.INFO, "Going to start file saving");
        FileWriter fw = new FileWriter(USER_STORAGE_FILE_PATH, false);
        logger.log(Level.INFO, "Going to add text into file");
        fw.write(textToAdd);
        fw.close();
        logger.log(Level.INFO, "End of file saving");
    }

    /**.
     * Loads file that holds universities and modules information which
     * the user saves after exiting the app most recently
     * @return fileContent Content of the file
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public static String loadFile() throws IOException {
        logger.log(Level.INFO, "Going to start loading file");
        File f = new File(USER_STORAGE_FILE_PATH);
        String[] words = USER_STORAGE_FILE_PATH.split("/");
        String dirName = words[0] + '/';
        File dir = new File(dirName);
        if (!dir.isDirectory()) {
            logger.log(Level.INFO, "Creating 'data/' directory as it does not exist yet");
            dir.mkdir();
        }
        if (!f.exists()) {
            logger.log(Level.INFO, "Creating 'duke.txt' file in duke directory as it does not exist yet");
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        String fileContent = "";
        logger.log(Level.INFO, "Going to start retrieving file information from 'data/duke.txt'");
        while (s.hasNext()) {
            fileContent += s.nextLine();
        }
        logger.log(Level.INFO, "End of file loading");
        return fileContent;
    }
}