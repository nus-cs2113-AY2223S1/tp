package seedu.api;

import seedu.exception.EmptyResponseException;
import seedu.ui.Ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static seedu.common.CommonFiles.LTA_BASE_URL;
import static seedu.common.CommonFiles.API_JSON_DIRECTORY;
import static seedu.common.CommonFiles.LTA_JSON_FILE;


/**
 * Class to fetch data from LTA API.
 */
public class Api {
    private final String API_KEY = "1B+7tBxzRNOtFbTxGcCiYA==";
    private final String AUTH_HEADER_NAME = "AccountKey";
    private final int MAX_FETCH_TRIES = 5;
    private HttpClient client;
    private HttpRequest request;
    private CompletableFuture<HttpResponse<String>> responseFuture;
    private Storage storage;
    private final Ui ui;

    /**
     * Constructor to create a new client and the correct HTTP request.
     * Initializes the storage class for file writing purposes.
     */
    public Api() {
        this.client = HttpClient.newHttpClient();
        generateHttpRequestCarpark();
        this.storage = new Storage(API_JSON_DIRECTORY, LTA_JSON_FILE);
        this.ui = new Ui();
    }


    /**
     * Builds the API HTTP GET request header and body.
     */
    private void generateHttpRequestCarpark() {
        request = HttpRequest.newBuilder(
                URI.create(LTA_BASE_URL))
                .header(AUTH_HEADER_NAME, API_KEY)
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
    public String asyncGetResponse() {
        String result = "";
        try {
            HttpResponse<String> response = responseFuture.get(1000, TimeUnit.MILLISECONDS);
            if (!response.body().trim().isEmpty()) {
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
     * @throws EmptyResponseException if empty/invalid response received.
     * @throws IOException if data writing fails.
     */
    public void fetchData() throws EmptyResponseException, IOException {
        String result = asyncGetResponse();
        int fetchTries = MAX_FETCH_TRIES;
        while (result.isEmpty() && fetchTries > 0) {
            asyncExecuteRequest();
            result = asyncGetResponse();
            fetchTries--;
        }
        if (fetchTries == 0 && result.isEmpty()) {
            throw new EmptyResponseException("No response was received.");
        }
        storage.writeDataToFile(result);
    }
}
