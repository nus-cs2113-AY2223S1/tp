package seedu.api;

import static seedu.common.CommonData.API_KEY_DEFAULT;
import static seedu.common.CommonFiles.LTA_BASE_URL;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import seedu.exception.EmptyResponseException;
import seedu.exception.EmptySecretFileException;
import seedu.exception.FileWriteException;
import seedu.exception.NoFileFoundException;
import seedu.exception.ServerNotReadyApiException;
import seedu.exception.UnauthorisedAccessApiException;
import seedu.exception.UnknownResponseApiException;
import seedu.files.FileReader;
import seedu.files.FileStorage;
import seedu.ui.Ui;

/**
 * Class to fetch .json data from APIs and save that locally.
 */
public class Api {
    private static final int FETCH_TRIES = 5;
    private final HttpClient client;
    private final FileStorage storage;
    private final Ui ui;
    private HttpRequest request;
    private CompletableFuture<HttpResponse<String>> responseFuture;
    private String apiKey = "";
    private AuthenticationStatus authStatus = AuthenticationStatus.FAIL;

    /**
     * Constructor to create a new client.
     * Initializes the storage class for file writing purposes.
     */
    public Api(String file, String directory) {
        this.client = HttpClient.newHttpClient();
        this.storage = new FileStorage(directory, file);
        this.ui = new Ui();
    }

    /**
     * Builds the API HTTP GET request header and body.
     */
    private void generateHttpRequestCarpark(int skip) {
        String authHeaderName = "AccountKey";
        request = HttpRequest.newBuilder(
                URI.create(LTA_BASE_URL + "?skip=" + skip))
            .header(authHeaderName, apiKey)
            .build();
    }

    /**
     * Sends the HTTP GET request to the API endpoint asynchronously.
     */
    public void asyncExecuteRequest(int skip) {
        generateHttpRequestCarpark(skip);
        responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Waits (for at most 1s) and receive response from API endpoint. It breaks the asynchronous part of the code.
     *
     * @return JSON string response from the API.
     * @throws UnauthorisedAccessApiException if API key is invalid.
     * @throws ServerNotReadyApiException if Server request timeout.
     * @throws UnknownResponseApiException if response code is not handled properly.
     */
    private String asyncGetResponse()
            throws UnauthorisedAccessApiException, ServerNotReadyApiException, UnknownResponseApiException {
        String result = "";
        try {
            HttpResponse<String> response = responseFuture.get(1000, TimeUnit.MILLISECONDS);
            if (isValidResponse(response.statusCode())) {
                result = response.body();
            }
        } catch (ExecutionException | InterruptedException e) {
            ui.showFetchError();
        } catch (TimeoutException e) {
            ui.showFetchTimeout();
        }
        return result;
    }

    /**
     * Execute the data fetching subroutine. Subroutine will repeat for a certain number of time
     * and throws an exception if no response is received.
     *
     * @throws FileWriteException if data fails to write.
     * @throws EmptyResponseException if empty/invalid response received.
     * @throws UnauthorisedAccessApiException if access not granted.
     */
    public String fetchData() throws EmptyResponseException, UnauthorisedAccessApiException, FileWriteException {
        String result = "";
        int fetchTries = FETCH_TRIES;
        do {
            try {
                result = asyncGetResponse().trim();
            } catch (ServerNotReadyApiException | UnknownResponseApiException e) {
                System.out.println(e.getMessage());
            } finally {
                fetchTries--;
            }
            if (fetchTries > 0 && result.isEmpty()) {
                asyncExecuteRequest(0);
            }
        } while (fetchTries > 0 && result.isEmpty());

        if (fetchTries == 0 && result.isEmpty()) {
            throw new EmptyResponseException();
        }
        return result;
    }

    /**
     * Synchronous version of data fetching from the API.
     *
     * @throws FileWriteException if data fails to write.
     * @throws EmptyResponseException if empty/invalid response received.
     * @throws UnauthorisedAccessApiException if access not granted.
     */
    public void syncFetchData() throws FileWriteException, EmptyResponseException, UnauthorisedAccessApiException {
        int i = 0;
        String result = "";
        while (i <= 3000) {
            asyncExecuteRequest(i);
            String dataReceived = fetchData();
            int dataCount = countDataSet(dataReceived);
        }
        storage.writeDataToFile(result);
    }

    public int countDataSet(String data) {
        String[] dataSplit = data.split("\"value\":\\[",2);
        int counter = dataSplit[1].;
        return dataSplit.length;
    }

    /**
     * Check whether response code from API response is 200 OK, otherwise throw exception.
     *
     * @param responseCode Response code from API HTTP response header.
     * @return true if response code is 200 OK.
     * @throws UnauthorisedAccessApiException API key is invalid
     * @throws ServerNotReadyApiException if server request timeout.
     * @throws UnknownResponseApiException if received response code besides 200, 401 or 503.
     */
    private boolean isValidResponse(int responseCode)
            throws UnauthorisedAccessApiException, ServerNotReadyApiException, UnknownResponseApiException {
        switch (responseCode) {
        case 200:
            authStatus = (authStatus == AuthenticationStatus.DEFAULT) ? authStatus : AuthenticationStatus.SUCCESS;
            return true;
        case 401:
            authStatus = (authStatus == AuthenticationStatus.DEFAULT) ? authStatus : AuthenticationStatus.FAIL;
            throw new UnauthorisedAccessApiException();
        case 503:
            throw new ServerNotReadyApiException();
        default:
            throw new UnknownResponseApiException(responseCode);
        }
    }

    /**
     * Authenticate API key and update data if it is valid. If it is invalid, the previous API key will be kept.
     * No data loading required afterwards.
     *
     * @param apiKeyInput API key to validate.
     * @return true if authentication is successful.
     */
    public boolean isApiValid(String apiKeyInput) {
        String originalApiKey = apiKey;
        apiKey = apiKeyInput;
        boolean isDifferent = true;
        if (originalApiKey.equals(apiKeyInput)) {
            isDifferent = false;
        }
        boolean isSuccess = false;
        asyncExecuteRequest(0);
        try {
            fetchData();
            isSuccess = true;
            authStatus = (isDifferent) ? AuthenticationStatus.SUCCESS : authStatus;
        } catch (EmptyResponseException | UnauthorisedAccessApiException | FileWriteException e) {
            System.out.println(e.getMessage());
            apiKey = originalApiKey;
        }
        return isSuccess;
    }

    /**
     * Load default api key.
     */
    public void loadDefaultApiKey() {
        apiKey = API_KEY_DEFAULT;
        authStatus = AuthenticationStatus.DEFAULT;
    }

    /**
     * Reads API key from secret.txt file and loads it to the object.
     * If secret.txt does not exist, create it and use the default key for now.
     *
     * @param file file name of where the api key is stored.
     * @param directory directory where the file is stored.
     * @param toloadDefault to load default api key or not.
     * @throws NoFileFoundException If directory / file is not found.
     * @throws EmptySecretFileException If the file is empty.
     */
    public void loadApiKey(String file, String directory, boolean toloadDefault)
            throws NoFileFoundException {
        try {
            String key = FileReader.readStringFromTxt(file, directory, true).trim();
            if (key.isEmpty()) {
                if (toloadDefault) {
                    loadDefaultApiKey();
                }
                throw new EmptySecretFileException(directory);
            }
            apiKey = key;
            authStatus = AuthenticationStatus.API_CHANGED;
        } catch (IOException e) {
            throw new NoFileFoundException("API key file is missing! Please check " + file + ".");
        } catch (EmptySecretFileException e) {
            ui.print(e.getMessage());
        }
    }

    public String getApiKey() {
        return apiKey;
    }

    /**
     * Returns the message corresponding to the authentication status.
     *
     * @return formatted message for authentication status.
     */
    public String getApiAuthStatus() {
        String message;
        switch(authStatus) {
        case FAIL:
            message = "You have not authenticated your API key. Your API key is " + apiKey;
            break;
        case SUCCESS:
            message = "You have authenticated your API key successfully. Your API key is " + apiKey;
            break;
        case API_CHANGED:
            message = "You have loaded your API key (" + apiKey
                    + ")but have not authenticated it! Use `update` command to authenticate.";
            break;
        case DEFAULT:
            message = "You have not authenticated your personal API key. Currently you have access to the API"
                    + " but you are using our default key!";
            break;
        default:
            message = "";
            break;
        }
        return message;
    }
}
