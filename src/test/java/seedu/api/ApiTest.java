package seedu.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.exception.EmptySecretFileException;
import seedu.exception.NoFileFoundException;
import seedu.exception.ParkingException;
import seedu.exception.UnauthorisedAccessApiException;

public class ApiTest {
    private final String testJsonFileDirectory = "./src/test/java/seedu/testfiles";
    private final String testJsonFile = "ltaResponse.json";
    private final String testEmptyApiKeyFile = "empty.txt";
    private final String testApiKeyFileInvalid = "invalidSecret.txt";

    @Test
    public void loadApiKeyFileEmpty() {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        Assertions.assertThrows(EmptySecretFileException.class, () -> api.loadApiKey(testEmptyApiKeyFile,
                testJsonFileDirectory));
    }

    @Test
    public void getApiKeyValid() throws EmptySecretFileException, NoFileFoundException {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        api.loadApiKey(testApiKeyFileInvalid, testJsonFileDirectory);
        Assertions.assertEquals(api.getApiKey(), "abc123f-exampleinvalid-54321");
    }

    @Test
    public void fetchDataUnauthorizedAccess() throws ParkingException {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        api.loadApiKey(testApiKeyFileInvalid, testJsonFileDirectory);
        api.asyncExecuteRequest();
        Assertions.assertThrows(UnauthorisedAccessApiException.class, () -> api.fetchData());
    }

}
