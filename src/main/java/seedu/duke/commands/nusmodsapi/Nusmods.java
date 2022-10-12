package seedu.duke.commands.nusmodsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import seedu.duke.Duke;
import seedu.duke.Exceptions;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.module.lessons.Lecture;
import seedu.duke.module.lessons.Tutorial;
import seedu.duke.module.lessons.Laboratory;
import seedu.duke.module.lessons.Others;

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

    public List<Lesson> addModuleInfo(String currentSemester, String[] info)
            throws IOException, InterruptedException, Exceptions.InvalidSemException, Exceptions.InvalidModuleCode {
        while (true) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(setUri()))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new Exceptions.InvalidModuleCode();
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
        lessons = checkAllLessons(currentSemester, (ArrayNode) semData);

        assert info[0] != null : "Module code must be filled and cannot be null";
        assert info[1] != null : "Module name must be filled and cannot be null";
        assert info[2] != null : "Module details must be filled and cannot be null";

        return lessons;
    }

    private static List<Lesson> checkAllLessons(String currentSemester, ArrayNode semData)
            throws Exceptions.InvalidSemException {
        lgr.fine("attempting to add lessons data to module object");

        int arrayIndex = 0;
        List<Lesson> lessons = new ArrayList<>();
        boolean isValidSemester = false;

        while (semData.get(arrayIndex) != null) {
            isValidSemester = semData.get(arrayIndex).get("semester").toString().equals(currentSemester);
            if (isValidSemester) {
                lgr.fine("module exists in selected semester");
                lessons = findIndividualLessons(semData.get(arrayIndex).get("timetable"));
                break;
            }
            arrayIndex += 1;
        }
        if (!isValidSemester) {
            throw new Exceptions.InvalidSemException();
        }
        return lessons;
    }

    private static List<Lesson> findIndividualLessons(JsonNode currentNode) {
        int arrayIndex = 0;
        List<Lesson> lessons = new ArrayList<>();
        while (currentNode.get(arrayIndex) != null) {
            String day = currentNode.get(arrayIndex).get("day").asText();
            String startTime = currentNode.get(arrayIndex).get("startTime").asText();
            String endTime = currentNode.get(arrayIndex).get("endTime").asText();
            String lessonType = currentNode.get(arrayIndex).get("lessonType").toString();

            allocateLessonType(lessons, day, startTime, endTime, lessonType);
            arrayIndex += 1;
        }
        lgr.fine("lessons are added, returning list of lesson data");
        return lessons;
    }

    private static void allocateLessonType(List<Lesson> lessons, String day, String startTime,
                                           String endTime, String lessonType) {
        switch (lessonType) {
        case "\"Lecture\"":
            lessons.add(new Lecture(day, startTime, endTime, "Lecture"));
            break;
        case "\"Tutorial\"":
            lessons.add(new Tutorial(day, startTime, endTime, "Tutorial"));
            break;
        case "\"Laboratory\"":
            lessons.add(new Laboratory(day, startTime, endTime, "Laboratory"));
            break;
        default:
            lgr.warning("lesson does not fall in existing categories");
            lessons.add(new Others(day, startTime, endTime, "Others"));
        }
    }
}
