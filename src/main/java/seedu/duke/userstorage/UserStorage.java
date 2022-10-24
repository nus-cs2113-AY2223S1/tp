package seedu.duke.userstorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**.
 * Deals with loading tasks from the specified file and saving tasks in the specified file
 * If saving university list and module info, file path = "data/uni_info.txt"
 * If saving timetable info, file path = "data/timetable_info.txt"
 */
public class UserStorage {
    private static Logger logger = Logger.getLogger("UserStorage");
    private static final String UNI_STORAGE_FILE_PATH = "data/uni_info.txt";
    private static final String TIMETABLE_STORAGE_FILE_PATH = "data/timetable_info.txt";
    /**.
     * Clears content in the file and writes new text into the file.
     * @param textToAdd Text to write into the file.
     * @param isUniStorage Boolean to check if loading from uni storage or timetable storage
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public static void saveFile(String textToAdd, boolean isUniStorage) throws IOException {
        logger.log(Level.INFO, "Going to start file saving");
        String userStorageFilePath = getFilePath(isUniStorage);
        FileWriter fw = new FileWriter(userStorageFilePath);
        logger.log(Level.INFO, "Going to add text into file");
        fw.write(textToAdd);
        fw.close();
        logger.log(Level.INFO, "End of file saving");
    }

    /**.
     * Loads file that holds universities and modules information which
     * the user saves after exiting the app most recently
     * @param isUniStorage Boolean to check if loading from uni storage or timetable storage
     * @return fileContent Content of the file
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public static String loadFile(boolean isUniStorage) throws IOException {
        logger.log(Level.INFO, "Going to start loading file");
        String userStorageFilePath = getFilePath(isUniStorage);
        File f = new File(userStorageFilePath);
        String[] words = userStorageFilePath.split("/");
        String dirName = words[0] + '/';
        File dir = new File(dirName);
        if (!dir.isDirectory()) {
            logger.log(Level.INFO, "Creating 'data/' directory as it does not exist yet");
            dir.mkdir();
        }
        if (!f.exists()) {
            logger.log(Level.INFO, "Creating new text file as it does not exist yet");
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        String fileContent = "";
        logger.log(Level.INFO, "Going to start retrieving file information from text file");
        while (s.hasNext()) {
            fileContent += s.nextLine();
        }
        logger.log(Level.INFO, "End of file loading");
        s.close();
        return fileContent;
    }
    private static String getFilePath(boolean isUniStorage) {
        String userStorageFilePath;
        if (isUniStorage) {
            userStorageFilePath = UNI_STORAGE_FILE_PATH;
        } else {
            userStorageFilePath = TIMETABLE_STORAGE_FILE_PATH;
        }
        return userStorageFilePath;
    }
}
