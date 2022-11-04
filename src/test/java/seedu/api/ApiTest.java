package seedu.api;

import static seedu.common.CommonData.API_KEY_DEFAULT;
import static seedu.common.CommonData.LTA_BASE_URL;

import java.net.URI;
import java.net.http.HttpRequest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.exception.EmptySecretFileException;
import seedu.exception.NoFileFoundException;
import seedu.exception.ParkingException;
import seedu.exception.ServerNotReadyApiException;
import seedu.exception.UnauthorisedAccessApiException;
import seedu.exception.UnknownResponseApiException;

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

    @Test
    public void generateHttpRequestTest() {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        api.generateHttpRequestCarpark(0);
        HttpRequest expectedReq = HttpRequest.newBuilder(
                        URI.create(LTA_BASE_URL + "?$skip=0"))
                    .header("AccountKey", "")
                    .build();
        Assertions.assertEquals(expectedReq, api.getRequest());
    }

    @Test
    public void processDataTest() {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        String data = "this is a sample one \"value\":[CS2113 testing in progress]}";
        String expectedResult = "CS2113 testing in progress";
        String result = api.processData(data);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void countDataTest() {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        String data = "{item_1},{item_2},{item_3},{item_4},{item_5}same_item";
        int expectedCount = 5;
        int result = api.countData(data);

        Assertions.assertEquals(expectedCount, result);
    }

    @Test
    public void isValidResponseTest()
            throws ServerNotReadyApiException, UnknownResponseApiException,
            UnauthorisedAccessApiException, NoFileFoundException {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        api.loadApiKey(testApiKeyFileInvalid, testJsonFileDirectory, true);

        Assertions.assertTrue(api.isValidResponse(200));
        Assertions.assertEquals(AuthenticationStatus.SUCCESS, api.getAuthStatus());

        Assertions.assertThrows(UnauthorisedAccessApiException.class, () -> api.isValidResponse(401));
        Assertions.assertEquals(AuthenticationStatus.FAIL, api.getAuthStatus());

        api.loadDefaultApiKey();
        Assertions.assertTrue(api.isValidResponse(200));
        Assertions.assertEquals(AuthenticationStatus.DEFAULT, api.getAuthStatus());

        Assertions.assertThrows(UnauthorisedAccessApiException.class, () -> api.isValidResponse(401));
        Assertions.assertEquals(AuthenticationStatus.DEFAULT, api.getAuthStatus());

        Assertions.assertThrows(ServerNotReadyApiException.class, () -> api.isValidResponse(503));

        Assertions.assertThrows(UnknownResponseApiException.class, () -> api.isValidResponse(0));
    }

    @Test
    public void isApiValidTest() {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        String apiKeyInvalid = "thisisinvalidapikey";
        boolean isSuccess = api.isApiValid(apiKeyInvalid);
        Assertions.assertFalse(isSuccess);
        Assertions.assertEquals("", api.getApiKey());
    }

    @Test
    public void getApiAuthStatusStringTest() {
        Api api = new Api(testJsonFile, testJsonFileDirectory);
        String messageFail = "You have not authenticated your API key. API key stored in the local file is "
                + "\nUse command `auth APIKEY` to re-authenticate or change your key";
        String messageSuccess = "You have authenticated your API key successfully. "
                + "API key stored in the local file is ";
        String messageChanged = "You have loaded your API key ("
                + ")but have not authenticated it! Use `update` command to authenticate.";
        String messageDefault = "You have not authenticated your personal API key. Currently you have access to the API"
                + " but you are using our default key!";

        api.setAuthStatus(AuthenticationStatus.FAIL);
        Assertions.assertEquals(messageFail, api.getApiAuthStatusString());
        api.setAuthStatus(AuthenticationStatus.API_CHANGED);
        Assertions.assertEquals(messageChanged, api.getApiAuthStatusString());
        api.setAuthStatus(AuthenticationStatus.DEFAULT);
        Assertions.assertEquals(messageDefault, api.getApiAuthStatusString());
        api.setAuthStatus(AuthenticationStatus.SUCCESS);
        Assertions.assertEquals(messageSuccess, api.getApiAuthStatusString());
    }

}
