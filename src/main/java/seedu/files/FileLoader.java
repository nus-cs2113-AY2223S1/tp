package seedu.files;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import seedu.exception.NoFileFoundException;
import seedu.common.CommonFiles;
import seedu.data.Carpark;
import seedu.files.parsing.LtaJsonWrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileLoader {

    /**
     * Loads .json file as an ArrayList of {@link Carpark} class
     *
     * @return list of all Carparks found
     * @throws NoFileFoundException if a file was not found
     */
    public static List<Carpark> loadLtaJson() throws NoFileFoundException {
        Path filepath = Paths.get(CommonFiles.API_JSON_DIRECTORY, CommonFiles.LTA_JSON_FILE);
        Path filepathBackup = Paths.get(CommonFiles.API_JSON_DIRECTORY, "ltaResponseSample.json");
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

    private static List<Carpark> getCarparks(Path filepath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.reader(LtaJsonWrapper.class);
        byte[] jsonData = Files.readAllBytes(filepath);
        LtaJsonWrapper wrapperObject = objectReader.readValue(jsonData);
        return wrapperObject.getValue();
    }

}
