package seedu.duke.userstorage;

import seedu.duke.command.Database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**.
 * Deals with loading and saving information into the specified file
 * Each file holds information about a specific partner university, and is named after the university
 * Information includes module codes that user is interested in, comments for each module (if any),
 * and lesson timings
 */
public class UserStorage {
    private static Logger logger = Logger.getLogger("UserStorage");
    private static HashMap<String, String> filePaths = new HashMap<>();
    private static final String directory = "data/";

    public static HashMap<String, String> getFilePaths() {
        return filePaths;
    }

    /**.
     * Method to save partner university information into the corresponding text file
     * @param uniName Partner university name
     * @param textToAdd String information to add to text file
     * @throws IOException If input/output operations fail or are interrupted
     */
    public static void saveFile(String uniName, String textToAdd) throws IOException {
        logger.log(Level.INFO, "Going to start file saving");
        String filePath;
        if (filePaths.get(uniName) == null) {
            filePath = directory + uniName + ".txt";
            filePaths.put(uniName, filePath);
        } else {
            filePath = filePaths.get(uniName);
        }
        FileWriter fw = new FileWriter(filePath);
        logger.log(Level.INFO, "Going to add text into file");
        fw.write(textToAdd);
        fw.close();
        logger.log(Level.INFO, "End of file saving");
    }

    /**.
     * Method to delete text file
     * @param uniName Partner university name
     */
    public static void deleteFile(String uniName, boolean fromStartUp) {
        String filePath = filePaths.get(uniName);
        File file = new File(filePath);
        file.delete();
        if (!fromStartUp) {
            filePaths.remove(uniName);
        }
    }

    /**.
     * Method to load information from a specified university's text file
     * @param uniName Partner university name
     * @return String containing all saved information corresponding to the university
     * @throws IOException If input/output operations fail or are interrupted
     */
    public static String loadFile(String uniName) throws IOException {
        logger.log(Level.INFO, "Going to start loading file");
        File dir = new File(directory);
        if (!dir.isDirectory()) {   //directory "data/" does not exist yet
            dir.mkdir();
        }

        String uniListFilePath = filePaths.get(uniName);
        File f = new File(uniListFilePath);
        Scanner s = new Scanner(f);
        String fileContent = "";
        while (s.hasNext()) {
            fileContent += s.nextLine();
        }
        logger.log(Level.INFO, "End of file loading");
        s.close();
        return fileContent;
    }

    /**.
     * Method to set HashMap filePaths on start of program
     * Iterates through all text files stored in the data directory and checks if the file name is equals to
     * a university that exists in the database
     * If true, insert university and filepath into HashMap
     * Otherwise, delete text file and output error message
     */
    public static void setFilePathsAtStartUp() {
        File dir = new File(directory);
        for (File file : dir.listFiles()) {
            String filePath = file.getPath();
            String uniName = filePath.substring(5, filePath.length() - 4);
            if (!Database.hasUniversityInDatabase(uniName) && !filePath.equals("data" + File.separator + "data.csv")) {
                file.delete();
                System.out.println("Invalid file name, deleting " + filePath);
            } else if (!filePath.equals("data" + File.separator + "data.csv")) {
                filePaths.put(uniName, filePath);
            }
        }
    }

    public static void setFilePaths(HashMap<String, String> newFilePaths) {
        filePaths = newFilePaths;
    }
}
