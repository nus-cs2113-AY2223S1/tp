package seedu.api;

import seedu.api.exception.EmptyResponseException;
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


public class Api {
    private final String API_KEY = "1B+7tBxzRNOtFbTxGcCiYA==";
    private String authHeaderName = "AccountKey";
    private HttpClient client;
    private HttpRequest request;
    private CompletableFuture<HttpResponse<String>> responseFuture;
    private Storage storage;
    private final Ui ui;

    public Api() {
        this.client = HttpClient.newHttpClient();
        generateHttpRequestCarpark();
        this.storage = new Storage(API_JSON_DIRECTORY, LTA_JSON_FILE);
        this.ui = new Ui();
    }

    private void generateHttpRequestCarpark() {
        request = HttpRequest.newBuilder(
                URI.create(LTA_BASE_URL))
                .header(authHeaderName, API_KEY)
                .build();
    }

    public void asyncExecuteRequest() {
        responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

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

    public void fetchData() throws EmptyResponseException, IOException {
        String result = asyncGetResponse();
        int fetchTries = 5;
        while (result.isEmpty() && fetchTries > 0) {
            asyncExecuteRequest();
            result = asyncGetResponse();
            fetchTries--;
        }
        if (fetchTries == 0 && result.isEmpty()) {
            throw new EmptyResponseException();
        }
        storage.writeDataToFile(result);
    }
}
