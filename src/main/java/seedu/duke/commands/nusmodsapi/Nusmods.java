package seedu.duke.commands.nusmodsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import seedu.duke.Exceptions;
import seedu.duke.module.lessons.Lesson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Nusmods {

    private final String baseUri = "https://api.nusmods.com/v2/2022-2023/modules/";
    private final int moduleCode = 0;
    private final int moduleName = 1;
    private final int moduleDescription = 2;
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String setUri(String moduleCode) {
        boolean validUri = false;
        String mod = null;
        while (!validUri) {
            try {
                mod = moduleCode.toUpperCase().trim();
                new URL(baseUri + mod + ".json").toURI();
                validUri = true;
            } catch (MalformedURLException | URISyntaxException e) {
                System.out.println("Module not found, please try again.");;
            }
        }
        assert mod != null : "URI creation failed, module code is invalid!";
        return baseUri + mod + ".json";
    }

    private HttpResponse<String> getResponse(String moduleCode) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(setUri(moduleCode)))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String[] getModuleInfo(String moduleCode) throws IOException, InterruptedException {
        while (true) {
            HttpResponse<String> response = getResponse(moduleCode);
            if (response.statusCode() != 200) {
                System.out.println("Module not found, please try again.");
            } else {
                lgr.info("api call successful, module exists");
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

    public List<Lesson> addModuleInfo(String currentSemester, String[] info, String moduleCode)
            throws IOException, InterruptedException, Exceptions.InvalidSemException,
            Exceptions.InvalidModuleCodeException {
        while (true) {
            HttpResponse<String> response = getResponse(moduleCode);
            if (response.statusCode() != 200) {
                throw new Exceptions.InvalidModuleCodeException();
            } else {
                lgr.info("api call successful, module exists");
                return addModuleComponents(response.body(), currentSemester, info);
            }
        }

    }

    private List<Lesson> addModuleComponents(String response, String currentSemester, String[] info)
            throws JsonProcessingException, Exceptions.InvalidSemException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response);

        info[moduleCode] = node.get("moduleCode").asText();
        info[moduleName] = node.get("title").asText();
        info[moduleDescription] = node.get("description").asText();

        JsonNode semData = node.get("semesterData");
        List<Lesson> lessons;
        lessons = checkAllLessons(currentSemester, (ArrayNode) semData, info[moduleCode]);

        assert info[0] != null : "Module code must be filled and cannot be null";
        assert info[1] != null : "Module name must be filled and cannot be null";
        assert info[2] != null : "Module details must be filled and cannot be null";

        return lessons;
    }

    private static List<Lesson> checkAllLessons(String currentSemester, ArrayNode semData, String moduleCode)
            throws Exceptions.InvalidSemException {
        lgr.fine("attempting to add lessons data to module object");

        int arrayIndex = 0;
        List<Lesson> lessons = new ArrayList<>();
        boolean isValidSemester = false;

        while (semData.get(arrayIndex) != null) {
            isValidSemester = semData.get(arrayIndex).get("semester").toString().equals(currentSemester);
            if (isValidSemester) {
                lgr.fine("module exists in selected semester");
                lessons = findIndividualLessons(semData.get(arrayIndex).get("timetable"), moduleCode);
                break;
            }
            arrayIndex += 1;
        }
        if (!isValidSemester) {
            throw new Exceptions.InvalidSemException();
        }
        return lessons;
    }

    private static List<Lesson> findIndividualLessons(JsonNode currentNode, String moduleCode) {
        int arrayIndex = 0;
        List<Lesson> lessons = new ArrayList<>();
        while (currentNode.get(arrayIndex) != null) {
            JsonNode lessonNode = currentNode.get(arrayIndex);
            String day = lessonNode.get("day").asText();
            String startTime = lessonNode.get("startTime").asText();
            String endTime = lessonNode.get("endTime").asText();
            String lessonType = removeQuotes(lessonNode.get("lessonType").toString());
            String classNumber = lessonNode.get("classNo").toString();

            lessons.add(new Lesson(day, startTime, endTime, lessonType, classNumber, moduleCode));
            arrayIndex += 1;
        }
        lgr.fine("lessons are added, returning list of lesson data");
        return lessons;
    }

    private static String removeQuotes(String lessonType) {
        return lessonType.substring(1, lessonType.length() - 1);
    }
}
