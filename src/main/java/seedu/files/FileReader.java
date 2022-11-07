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
import seedu.exception.InvalidFormatException;
import seedu.exception.NoFileFoundException;
import seedu.exception.ParkingException;
import seedu.files.wrapper.LtaJsonWrapper;
import seedu.ui.Ui;

/**
 * Deals with reading from files.
 */

public abstract class FileReader {
    /**
     * Loads .json file as an ArrayList of {@link Carpark} class.
     *
     * @param filepath path to file.
     * @param filepathBackup path to backup file.
     * @return List of all Carparks found.
     * @throws NoFileFoundException If a file was not found.
     * @throws FileWriteException If unable to write to file.
     */
    public static List<Carpark> loadLtaJson(Path filepath, Path filepathBackup)
            throws NoFileFoundException, FileWriteException {
        try {
            List<Carpark> carparks = getCarparks(filepath);
            if (filepath == CommonFiles.LTA_BACKUP_FILE_PATH && filepathBackup == CommonFiles.LTA_BACKUP_FILE_PATH) {
                // Backup file timestamp
                for (Carpark carpark : carparks) {
                    try {
                        carpark.setLastUpdated("24-10-2022 16:06:09");
                    } catch (InvalidFormatException ignored) {
                        // Will always be valid (backup timestamp guaranteed)
                    }
                }
            }
            return carparks;
        } catch (IOException e) {
            Ui.println("No file was found, or invalid format at " + filepath + ". Trying the backup:");
            try {
                List<Carpark> carparks = getCarparks(filepathBackup);
                Ui.println("Backup load successful!");
                // Backup file timestamp
                for (Carpark carpark : carparks) {
                    try {
                        carpark.setLastUpdated("24-10-2022 16:06:09");
                    } catch (InvalidFormatException ignored) {
                        // Will always be valid (backup timestamp guaranteed)
                    }
                }
                return carparks;
            } catch (IOException backupException) {
                Ui.println("Both the main and backup file failed to load. Loading from internal backup: ");
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
     * Get carparks from .json file.
     *
     * @param filepath path to file.
     * @return List of carparks.
     * @throws IOException If an I/O error occurs reading from the stream.
     */
    private static List<Carpark> getCarparks(Path filepath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.reader(LtaJsonWrapper.class);
        byte[] jsonData = Files.readAllBytes(filepath);
        LtaJsonWrapper wrapperObject = objectReader.readValue(jsonData);
        return wrapperObject.getValue();
    }

    /**
     * Read contents of file.
     *
     * @param filePath path to file.
     * @param directoryPath path to directory.
     * @param createDirectory create the directory if file does not exist.
     * @return Data string in file.
     * @throws NoFileFoundException If a file was not found.
     * @throws FileWriteException If unable to write to file.
     */
    public static String readStringFromTxt(String filePath, String directoryPath, boolean createDirectory)
            throws NoFileFoundException, FileWriteException {
        String filepath = Paths.get(directoryPath, filePath).toString();
        File file = new File(filepath);
        if (!file.exists() && createDirectory) {
            System.out.println(filePath + " file does not exist. Creating one at " + directoryPath);
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
     *
     * @param filePath path to file.
     * @param directoryPath path to directory.
     * @return A properly generated CarparkList object.
     * @throws NoFileFoundException If a file was not found.
     * @throws FileWriteException If unable to write to file.
     */
    public static CarparkList loadCarparkListFromTxt(String filePath, String directoryPath)
            throws FileWriteException, NoFileFoundException {
        try {
            return new CarparkList(readStringFromTxt(filePath, directoryPath, true));
        } catch (ParkingException e) {
            Ui.printError(e);
            return new CarparkList(CommonFiles.LTA_BACKUP_FILE_PATH, CommonFiles.LTA_BACKUP_FILE_PATH);
        }
    }
}
