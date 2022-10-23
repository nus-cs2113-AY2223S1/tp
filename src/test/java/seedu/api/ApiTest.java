package seedu.api;

import static seedu.common.CommonData.API_KEY_DEFAULT;

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
        try {
            api.loadApiKey(testEmptyApiKeyFile, testJsonFileDirectory, true);
        } catch (NoFileFoundException e) {
            assert false;
        }
        Assertions.assertEquals(API_KEY_DEFAULT, api.getApiKey());
    }

    @Test
    public void getApiKeyValid() throws EmptySecretFileException, NoFileFoundException {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        api.loadApiKey(testApiKeyFileInvalid, testJsonFileDirectory, true);
        Assertions.assertEquals("abc123f-exampleinvalid-54321", api.getApiKey());
    }

    @Test
    public void fetchDataUnauthorizedAccess() throws ParkingException {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        api.loadApiKey(testApiKeyFileInvalid, testJsonFileDirectory, true);
        api.asyncExecuteRequest(0, 0);
        Assertions.assertThrows(UnauthorisedAccessApiException.class, () -> api.fetchData(0));
    }

}
