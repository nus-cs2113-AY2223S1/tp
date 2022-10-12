package seedu.api;

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
    private final HttpClient client;
    private HttpRequest request;
    private CompletableFuture<HttpResponse<String>> responseFuture;
    private final FileStorage storage;
    private final Ui ui;
    private String apiKey = "";
    private static final int FETCH_TRIES = 5;

    /**
     * Constructor to create a new client and the correct HTTP request.
     * Initializes the storage class for file writing purposes.
     * Loads the API key.
     */
    public Api(String file, String directory) {
        this.client = HttpClient.newHttpClient();
        this.storage = new FileStorage(directory, file);
        this.ui = new Ui();
    }

    /**
     * TODO: Check API last authenticated successfully / empty
     * Builds the API HTTP GET request header and body.
     */
    private void generateHttpRequestCarpark() {
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
        generateHttpRequestCarpark();
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

    /**
     * Check whether response code from API response is 200 OK, otherwise handle it gracefully.
     * @param responseCode Response code from API HTTP response header.
     * @return true if response code is 200.
     * @throws UnauthorisedAccessApiException API key is wrong.
     * @throws ServerNotReadyApiException Too many request.
     * @throws UnknownResponseApiException Response code besides 200, 401 or 503.
     */
    private boolean isValidResponse(int responseCode)
            throws UnauthorisedAccessApiException, ServerNotReadyApiException, UnknownResponseApiException {
        switch (responseCode) {
        case 200:
            return true;
        case 401:
            throw new UnauthorisedAccessApiException();
        case 503:
            throw new ServerNotReadyApiException("Too many requests. Trying again...");
        default:
            throw new UnknownResponseApiException("Response Code: " + responseCode
                    + "\nIf the problem persists please contact the developer. Trying again...");
        }
    }

    /**
     * Execute the data fetching subroutine. Subroutine will repeat for a certain number of time
     * and throws an exception if no response is received.
     * todo: handle bad request
     *
     * @throws EmptyResponseException if empty/invalid response received.
     * @throws IOException if data writing fails.
     */
    public void fetchData() throws EmptyResponseException, IOException, UnauthorisedAccessApiException {
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
                asyncExecuteRequest();
            }
        } while (fetchTries > 0 && result.isEmpty());

        if (fetchTries == 0 && result.isEmpty()) {
            throw new EmptyResponseException("No response was received. Check your internet connection.");
        }
        storage.writeDataToFile(result);
    }

    /**
     * Reads API key from secret.txt file and loads it to the object.
     * If secret.txt does not exist, create it.
     *
     * @throws NoFileFoundException If directory / file is not found.
     * @throws EmptySecretFileException If the file is empty.
     */
    public void loadApiKey(String file, String directory) throws NoFileFoundException, EmptySecretFileException {
        try {
            String key = FileReader.readStringFromTxt(file, directory, true);
            if (key.isEmpty()) {
                throw new EmptySecretFileException();
            }
            apiKey = key;
        } catch (IOException e) {
            throw new NoFileFoundException("API key file is missing!");
        }
    }

    public String getApiKey() {
        return apiKey;
    }
}
