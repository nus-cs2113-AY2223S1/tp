package seedu.api;


import static seedu.common.CommonFiles.API_JSON_DIRECTORY;
import static seedu.common.CommonFiles.LTA_BASE_URL;
import static seedu.common.CommonFiles.LTA_JSON_FILE;

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
import seedu.exception.ServerNotReadyApiException;
import seedu.exception.UnauthorisedAccessApiException;
import seedu.exception.UnknownResponseApiException;
import seedu.files.FileStorage;
import seedu.ui.Ui;

/**
 * Class to fetch .json data from APIs and save that locally.
 */
public class Api {
    private final HttpClient client;
    private HttpRequest request;
    private CompletableFuture<HttpResponse<String>> responseFuture;
    private final FileStorage storage;
    private final Ui ui;

    /**
     * Constructor to create a new client and the correct HTTP request.
     * Initializes the storage class for file writing purposes.
     */
    public Api() {
        this.client = HttpClient.newHttpClient();
        generateHttpRequestCarpark();
        this.storage = new FileStorage(API_JSON_DIRECTORY, LTA_JSON_FILE);
        this.ui = new Ui();
    }


    /**
     * Builds the API HTTP GET request header and body.
     */
    private void generateHttpRequestCarpark() {
        String apiKey = "1B+7tBxzRNOtFbTxGcCiYA==";
        String authHeaderName = "AccountKey";
        request = HttpRequest.newBuilder(
                URI.create(LTA_BASE_URL))
            .header(authHeaderName, apiKey)
            .build();
    }

    /**
     * Sends the HTTP GET request to the API endpoint asynchronously.
     */
    public void asyncExecuteRequest() {
        responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Wait for the response from the API endpoint. It is a blocking code.
     * Timeout set at 1000ms and will return timeout exception.
     *
     * @return JSON string response from the API.
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

    private boolean isValidResponse(int responseCode)
            throws UnauthorisedAccessApiException, ServerNotReadyApiException, UnknownResponseApiException {
        switch (responseCode) {
        case 200:
            return true;
        case 401:
            throw new UnauthorisedAccessApiException();
        case 503:
            throw new ServerNotReadyApiException("Too many request. Trying again...");
        default:
            throw new UnknownResponseApiException("Response Code: " + responseCode
                    + "\nIf problem persist contact developer. Trying again...");
        }
    }

    /**
     * Execute the data fetching subroutine. Subroutine will repeat for a certain number of time
     * and throws an exception if no response is received.
     *
     * @throws EmptyResponseException if empty/invalid response received.
     * @throws IOException if data writing fails.
     */
    public void fetchData() throws EmptyResponseException, IOException, UnauthorisedAccessApiException {
        String result = "";
        int fetchTries = 5;
        do {
            try {
                result = asyncGetResponse().trim();
            } catch (ServerNotReadyApiException | UnknownResponseApiException e) {
                System.out.println(e.getMessage());
            } finally {
                fetchTries--;
            }
            if (fetchTries > 0 && result.isEmpty()) {
                asyncExecuteRequest();
            }
        } while (fetchTries > 0 && result.isEmpty());

        if (fetchTries == 0 && result.isEmpty()) {
            throw new EmptyResponseException("No response was received. Check your internet connection.");
        }
        storage.writeDataToFile(result);
    }
}
