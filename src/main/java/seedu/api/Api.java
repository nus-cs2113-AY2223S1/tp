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
import seedu.files.FileStorage;
import seedu.ui.Ui;

/**
 * Class to fetch .json data from APIs and save that locally.
 */
public class Api {
    private static final String API_KEY = "1B+7tBxzRNOtFbTxGcCiYA==";
    private final Ui ui;
    private final String authHeaderName = "AccountKey";
    private final HttpClient client;
    private final FileStorage storage;
    private HttpRequest request;
    private CompletableFuture<HttpResponse<String>> responseFuture;

    /**
     * Constructor for the {@link Api} class.
     */
    public Api() {
        this.client = HttpClient.newHttpClient();
        generateHttpRequestCarpark();
        this.storage = new FileStorage(API_JSON_DIRECTORY, LTA_JSON_FILE);
        this.ui = new Ui();
    }

    private void generateHttpRequestCarpark() {
        request = HttpRequest.newBuilder(
                URI.create(LTA_BASE_URL))
            .header(authHeaderName, API_KEY)
            .build();
    }

    /**
     * TODO: Javadoc comment.
     */
    public void asyncExecuteRequest() {
        responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * TODO: Javadoc comment.
     * @return
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
     * TODO: Javadoc comment.
     * @throws EmptyResponseException
     * @throws IOException
     */
    public void fetchData() throws EmptyResponseException, IOException {
        String result = asyncGetResponse();
        int fetchTries = 5;
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
