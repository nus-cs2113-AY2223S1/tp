package seedu.api;

import static seedu.common.CommonData.API_KEY_DEFAULT;
import static seedu.common.CommonData.API_RESPONSE_HEADER;
import static seedu.common.CommonData.API_RESPONSE_TAIL;
import static seedu.common.CommonData.LTA_BASE_URL;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
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
    private ArrayList<CompletableFuture<HttpResponse<String>>> responseFutureList = new ArrayList<>(5);
    private String apiKey = "";
    private AuthenticationStatus authStatus = AuthenticationStatus.FAIL;

    /**
     * Constructor to create a new client.
     * Initializes the storage class for file writing purposes.
     *
     * @param file The file name where the storage file is stored.
     * @param directory The directory path where the storage file is stored.
     */
    public Api(String file, String directory) {
        this.client = HttpClient.newHttpClient();
        this.storage = new FileStorage(directory, file);
        this.ui = new Ui();
    }

    /**
     * Builds the API HTTP GET request header and body.
     *
     * @param skip The number of data sets to skip.
     */
    private void generateHttpRequestCarpark(int skip) {
        String authHeaderName = "AccountKey";
        request = HttpRequest.newBuilder(
                URI.create(LTA_BASE_URL + "?$skip=" + skip))
            .header(authHeaderName, apiKey)
            .build();
    }

    /**
     * Sends the HTTP GET request to the API endpoint asynchronously.
     *
     * @param skip The number of data sets to skip.
     * @param index The index to insert the responseFuture call.
     */
    public void asyncExecuteRequest(int skip, int index) {
        generateHttpRequestCarpark(skip);
        responseFutureList.add(index, client.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
    }

    /**
     * Waits (for at most 1s) and receive response from API endpoint. It breaks the asynchronous part of the code.
     *
     * @param index The index to get the response from the responseFutureList
     * @return JSON string response from the API.
     * @throws UnauthorisedAccessApiException if API key is invalid.
     * @throws ServerNotReadyApiException if Server request timeout.
     * @throws UnknownResponseApiException if response code is not handled properly.
     */
    private String asyncGetResponse(int index)
            throws UnauthorisedAccessApiException, ServerNotReadyApiException, UnknownResponseApiException {
        String result = "";
        try {
            HttpResponse<String> response = responseFutureList.get(index).get(1000, TimeUnit.MILLISECONDS);
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
     * Execute the data fetching subroutine for a specific index from the asynchronous call.
     * Subroutine will repeat for a certain number of time and throws an exception if no response is received.
     *
     * @param index The index to get the response from.
     * @return String The raw data from the API.
     * @throws FileWriteException if data fails to write.
     * @throws EmptyResponseException if empty/invalid response received.
     * @throws UnauthorisedAccessApiException if access not granted.
     */
    public String fetchData(int index)
            throws EmptyResponseException, UnauthorisedAccessApiException, FileWriteException {
        String result = "";
        int fetchTries = FETCH_TRIES;
        do {
            try {
                result = asyncGetResponse(index).trim();
            } catch (ServerNotReadyApiException | UnknownResponseApiException e) {
                e.setTryNumber(fetchTries);
                ui.printError(e);
            } finally {
                fetchTries--;
            }
            if (fetchTries > 0 && result.isEmpty()) {
                asyncExecuteRequest(index * 500, index);
            }
        } while (fetchTries > 0 && result.isEmpty());

        if (fetchTries == 0 && result.isEmpty()) {
            throw new EmptyResponseException();
        }
        return result;
    }

    /**
     * Synchronous version of multiple data fetching from the API. If the result is fetched successfully, it will be
     * stored locally.
     *
     * @throws FileWriteException if data fails to write.
     * @throws EmptyResponseException if empty/invalid response received.
     * @throws UnauthorisedAccessApiException if access not granted.
     */
    public void syncFetchData() throws FileWriteException, EmptyResponseException, UnauthorisedAccessApiException {
        String result = API_RESPONSE_HEADER;
        int totalDataCount = 0;
        for (int i = 0; i < 5; i++) {
            asyncExecuteRequest(i * 500, i);
        }
        boolean isWritten = false;
        for (int i = 0; i < 5; i++) {
            String partialResult = fetchData(i);
            String processedResult = processData(partialResult);
            totalDataCount += countData(processedResult);

            if (i != 0 && !processedResult.isEmpty() && isWritten) {
                result += "," + processedResult;
            } else if (!processedResult.isEmpty()) {
                isWritten = true;
                result += processedResult;
            }
        }

        result += API_RESPONSE_TAIL;

        ui.println(totalDataCount + " Parking Lot data received from LTA!");

        storage.writeDataToFile(result);
    }

    /**
     * Process the data from the API to adhere to concatenating format.
     *
     * @param data raw data from the API.
     * @return The processed data.
     */
    public String processData(String data) {
        String[] dataSplit = data.split("\"value\":\\[", 2);
        dataSplit = dataSplit[1].split("]}", 2);
        return dataSplit[0];
    }

    /**
     * Count the number of parking lot data received from LTA.
     *
     * @param data processed data set to count.
     * @return number of parking lots.
     */
    public int countData(String data) {
        String[] individualData = data.trim().split("},\\{");
        return individualData.length;
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
        asyncExecuteRequest(0, 0);
        try {
            fetchData(0);
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
        } catch (NoFileFoundException e) {
            throw new NoFileFoundException("API key file is missing! Please check " + file + ".");
        } catch (EmptySecretFileException | FileWriteException e) {
            ui.printError(e);
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
            message = "You have not authenticated your API key. API key stored in the local file is " + apiKey
                    + "\nUse command `auth APIKEY` to re-authenticate or change your key";
            break;
        case SUCCESS:
            message = "You have authenticated your API key successfully. API key stored in the local file is " + apiKey;
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
