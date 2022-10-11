package seedu.common;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class to contain data used by multiple classes.
 */
public class CommonFiles {
    public static final String API_JSON_DIRECTORY = "./resources/api";
    public static final String URA_JSON_FILE = "uraResponse.json";
    public static final String LTA_JSON_FILE = "ltaResponse.json";
    public static final String LTA_BASE_URL = "http://datamall2.mytransport.sg/ltaodataservice/CarParkAvailabilityv2";
    public static final String URA_BASE_URL = "";
    public static final Path LTA_FILE_PATH = Paths.get(CommonFiles.API_JSON_DIRECTORY, CommonFiles.LTA_JSON_FILE);
    public static final Path LTA_BACKUP_FILE_PATH = Paths.get(CommonFiles.API_JSON_DIRECTORY,
        "ltaResponseSample.json");
    public static final Path API_KEY_FILE_PATH = Paths.get(CommonFiles.API_JSON_DIRECTORY, "secret.txt");
}
