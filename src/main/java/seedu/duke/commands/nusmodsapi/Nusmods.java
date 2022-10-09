package seedu.duke.commands.nusmodsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import seedu.duke.Duke;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Nusmods {

    private final String baseUri = "https://api.nusmods.com/v2/2021-2022/modules/";
    private final int moduleCode = 0;
    private final int moduleName = 1;
    private final int moduleDescription = 2;

    private String setUri() {
        boolean validUri = false;
        String mod = new String();
        while (!validUri) {
            try {
                if (Duke.sc.hasNextLine()) {
                    mod = Duke.sc.nextLine().toUpperCase().trim();
                }
                new URL(baseUri + mod + ".json").toURI();
                validUri = true;
            } catch (MalformedURLException | URISyntaxException e) {
                System.out.println("Module not found, please try again.");;
            }
        }

        return baseUri + mod + ".json";
    }

    public String[] getModuleInfo() throws IOException, InterruptedException {
        while (true) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(setUri()))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Module not found, please try again.");
            } else {
                return retrieveBasicInfo(response.body());
            }
        }

    }

    private String[] retrieveBasicInfo(String response) throws JsonProcessingException {
        String[] info = new String[3];
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response);
        info[moduleCode] = node.get("moduleCode").asText();
        info[moduleName] = node.get("title").asText();
        info[moduleDescription] = node.get("description").asText();
        return info;
    }
}
