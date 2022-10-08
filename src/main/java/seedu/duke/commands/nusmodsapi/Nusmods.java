package seedu.duke.commands.nusmodsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    private final String BASE_URI = "https://api.nusmods.com/v2/2021-2022/modules/";
    private final int MODULE_CODE = 0;
    private final int MODULE_NAME = 1;
    private final int MODULE_DESCRIPTION = 2;

    private String setURI() {
        boolean validURI = false;
        Scanner sc = new Scanner(System.in);
        String mod = new String();
        while (!validURI) {
            try {
                mod = sc.nextLine().toUpperCase().trim();
                new URL(BASE_URI + mod + ".json").toURI();
                validURI = true;
            } catch (MalformedURLException | URISyntaxException e) {
                System.out.println("Module not found, please try again.");;
            }
        }

        return BASE_URI + mod + ".json";
    }

    public String[] getModuleInfo() throws IOException, InterruptedException {
        while (true) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(setURI()))
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
        info[MODULE_CODE] = node.get("moduleCode").asText();
        info[MODULE_NAME] = node.get("title").asText();
        info[MODULE_DESCRIPTION] = node.get("description").asText();
        return info;
    }
}
