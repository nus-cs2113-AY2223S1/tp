package seedu.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;

import seedu.data.Carpark;
import seedu.exception.EmptySecretFileException;
import seedu.exception.NoFileFoundException;
import seedu.files.parsing.LtaJsonWrapper;

/**
 * Deals with reading from files
 */
public class FileReader {

    /**
     * Loads .json file as an ArrayList of {@link Carpark} class
     *
     * @return list of all Carparks found
     * @throws NoFileFoundException if a file was not found
     */
    public static List<Carpark> loadLtaJson(Path filepath, Path filepathBackup) throws NoFileFoundException {
        try {
            return getCarparks(filepath);
        } catch (IOException e) {
            System.out.println("No file was found, or invalid format at " + filepath + ". Trying the backup:");
            try {
                List<Carpark> carparks = getCarparks(filepathBackup);
                System.out.println("Backup load successful!");
                return carparks;
            } catch (IOException backupException) {
                throw new NoFileFoundException("Both the main file and backup file did not load correctly."
                    + "Please check your directory at " + filepath + " and try again, or"
                    + "download a sample .json file and place it in " + filepathBackup + ".");
            }
        }
    }

    /**
     * TODO: Javadoc
     * @param filepath
     * @return
     * @throws IOException
     */
    private static List<Carpark> getCarparks(Path filepath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.reader(LtaJsonWrapper.class);
        byte[] jsonData = Files.readAllBytes(filepath);
        LtaJsonWrapper wrapperObject = objectReader.readValue(jsonData);
        return wrapperObject.getValue();
    }

    /**
     * Returns data in string from file.

     * @param filepath File path.
     * @return Data string in file.
     * @throws IOException if file not found.
     */
    public static String readStringFromTxt(Path filepath) throws IOException {
        File file = new File(filepath.toString());
        Scanner scanner = new Scanner(file);
        return scanner.nextLine().trim();
    }
    /**
     * @param filePath File path.
     * @param directoryPath Directory path.
     * @param createDirectory if true and file does not exist, it will create.
     * @return Data string in file.
     * @throws IOException if file not found.
     */
    public static String readStringFromTxt(String filePath, String directoryPath, boolean createDirectory)
            throws IOException {
        String filepath = Paths.get(directoryPath, filePath).toString();
        File file = new File(filepath);
        if (!file.exists() && createDirectory) {
            System.out.println("File does not exist. Creating one at ./resources/api/secret.txt.");
            File directory = new File(directoryPath);
            directory.mkdirs();
            file.createNewFile();
        }
        String content = "";
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            content = scanner.nextLine().trim();
        }
        return content;
    }
}
