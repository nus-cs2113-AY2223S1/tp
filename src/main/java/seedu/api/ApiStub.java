package seedu.api;

import seedu.exception.FileWriteException;
import seedu.files.FileStorage;

/**
 * Stub class to test update command
 */
public class ApiStub implements ApiInterface {

    private final FileStorage storage;

    public ApiStub(String file, String directory) {
        this.storage = new FileStorage(directory, file);
    }

    /**
     * Stub function to test update command.
     *
     * @throws FileWriteException if there is a writing error.
     */
    public void syncFetchData()
            throws FileWriteException {
        String sampleResult = "{\"odata.metadata\":\"http://datamall2.mytransport.sg/ltaodataservice/$metadata#"
                + "CarParkAvailability\",\"value\":[{\"CarParkID\":\"1\",\"Area\":\"Marina\",\"Development\":\"Suntec "
                + "City\",\"Location\":\"1.29375 103.85718\",\"AvailableLots\":1000,\"LotType\":\"C\",\"Agency\":"
                + "\"LTA\"}]}";
        storage.writeDataToFile(sampleResult);
    }
}
