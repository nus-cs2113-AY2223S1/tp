package seedu.duke.commands.nusmodsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import seedu.duke.Duke;
import seedu.duke.Exceptions;
import seedu.duke.module.lessons.Lecture;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.module.lessons.Others;
import seedu.duke.module.lessons.Tutorial;

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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public List<Lesson> addModuleInfo(String currentSemester, String[] info)
            throws IOException, InterruptedException, Exceptions.InvalidSemException, Exceptions.InvalidModuleCode {
        Logger logger = Logger.getLogger(Nusmods.class.getName());

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
                logger.log(Level.INFO, "successful call to api");
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
        int arrayIndex = 0;
        List<Lesson> lessons = new ArrayList<>();
        boolean isValidSemester = false;

        while (semData.get(arrayIndex) != null) {
            isValidSemester = semData.get(arrayIndex).get("semester").toString().equals(currentSemester);
            if (isValidSemester) {
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
        return lessons;
    }

    private static void allocateLessonType(List<Lesson> lessons, String day, String startTime,
                                           String endTime, String lessonType) {
        Logger logger = Logger.getLogger(Nusmods.class.getName());
        switch (lessonType) {
        case "\"Lecture\"":
            lessons.add(new Lecture(day, startTime, endTime));
            break;
        case "\"Tutorial\"":
            lessons.add(new Tutorial(day, startTime, endTime));
            break;
        default:
            logger.log(Level.WARNING, "lecture and tutorial not identified");
            assert !lessonType.equals("Lecture") : "Not a lecture";
            assert !lessonType.equals("Tutorial") : "Not a tutorial";
            lessons.add(new Others(day, startTime, endTime));
        }
    }
}
