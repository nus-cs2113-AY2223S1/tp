package seedu.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;

import seedu.common.CommonFiles;
import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.NoFileFoundException;
import seedu.exception.ParkingException;
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
    public static List<Carpark> loadLtaJson(Path filepath, Path filepathBackup)
            throws NoFileFoundException, FileWriteException {
        try {
            return getCarparks(filepath);
        } catch (IOException e) {
            System.out.println("No file was found, or invalid format at " + filepath + ". Trying the backup:");
            try {
                List<Carpark> carparks = getCarparks(filepathBackup);
                System.out.println("Backup load successful!");
                // Backup file timestamp
                for (Carpark carpark : carparks) {
                    carpark.setLastUpdated("24-10-2022 16:06:09");
                }
                return carparks;
            } catch (IOException backupException) {
                System.out.println("Both the main and backup file failed to load. Loading from internal backup: ");
                FileStorage.ensureBackup();
                try {
                    return getCarparks(filepathBackup);
                } catch (IOException ex) {
                    throw new NoFileFoundException("A critical error has occured. Please contact the developer!");
                }
            }
        }
    }

    /**
     * get carparks from Json file
     * @param filepath file path of Json file
     * @return List of carparks
     * @throws IOException if an I/O error occurs reading from the stream
     */
    private static List<Carpark> getCarparks(Path filepath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.reader(LtaJsonWrapper.class);
        byte[] jsonData = Files.readAllBytes(filepath);
        LtaJsonWrapper wrapperObject = objectReader.readValue(jsonData);
        return wrapperObject.getValue();
    }

    /**
     * @param filePath File path.
     * @param directoryPath Directory path.
     * @param createDirectory if true and file does not exist, it will create.
     * @return Data string in file.
     * @throws IOException if file not found.
     */
    public static String readStringFromTxt(String filePath, String directoryPath, boolean createDirectory)
            throws NoFileFoundException, FileWriteException {
        String filepath = Paths.get(directoryPath, filePath).toString();
        File file = new File(filepath);
        if (!file.exists() && createDirectory) {
            System.out.println("File does not exist. Creating one at ./resources/api/secret.txt.");
            File directory = new File(directoryPath);
            directory.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new NoFileFoundException(filepath + ": file not found");
            }
        } else if (!file.exists()) {
            throw new NoFileFoundException(filePath + ": file not found");
        }
        StringBuilder content = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new NoFileFoundException(filepath + ": file not found");
        }
        while (scanner.hasNext()) {
            content.append(scanner.nextLine().trim()).append("\n");
        }
        return content.toString();
    }

    /**
     * Loads a {@link CarparkList} object from a given file and directory path.
     * @param filePath File path.
     * @param directoryPath Directory path.
     * @return A properly generated CarparkList object.
     * @throws FileWriteException If file cannot be written to.
     * @throws NoFileFoundException If the directory cannot be found.
     */
    public static CarparkList loadCarparkListFromTxt(String filePath, String directoryPath)
            throws FileWriteException, NoFileFoundException {
        try {
            return new CarparkList(readStringFromTxt(filePath, directoryPath, true));
        } catch (ParkingException | ArrayIndexOutOfBoundsException e) {
            return new CarparkList(CommonFiles.LTA_BACKUP_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
        }
    }
}
