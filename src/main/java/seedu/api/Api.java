package seedu.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Api {
    private final String LTA_BASE_URL = "http://datamall2.mytransport.sg/ltaodataservice/CarParkAvailabilityv2";
    private final String URA_BASE_URL = "";
    private final String LTA_API_KEY = "1B+7tBxzRNOtFbTxGcCiYA==";
    private final String URA_API_KEY = "";

    public void asyncGetRequest() throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(
                URI.create(LTA_BASE_URL))
                .header("AccountKey", LTA_API_KEY)
                .build();

        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> response = responseFuture.get();

        System.out.println(response.body());
    }
}
