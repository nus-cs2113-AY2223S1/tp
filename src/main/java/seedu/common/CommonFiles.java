package seedu.common;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class to contain data used by multiple classes.
 */
public class CommonFiles {
    public static final String API_JSON_DIRECTORY = "./resources/api";
    public static final String LTA_JSON_FILE = "ltaResponse.json";
    public static final String CARPARK_LIST_DIRECTORY = "./resources";
    public static final String CARPARK_LIST_FILE = "carparkList.txt";
    public static final Path LTA_FILE_PATH = Paths.get(CommonFiles.API_JSON_DIRECTORY, CommonFiles.LTA_JSON_FILE);
    public static final Path LTA_BACKUP_FILE_PATH = Paths.get(CommonFiles.API_JSON_DIRECTORY,
        "ltaResponseSample.json");

    public static final String API_KEY_FILE = "secret.txt";
    public static final Path API_KEY_FILE_PATH = Paths.get(CommonFiles.API_JSON_DIRECTORY, "secret.txt");
    public static final String FAVOURITE_DIRECTORY = "./resources/favourite";
    public static final String FAVOURITE_FILE = "favourite.txt";
    public static final Path FAVOURITE_PATH = Paths.get(CommonFiles.FAVOURITE_DIRECTORY, CommonFiles.FAVOURITE_FILE);
}
