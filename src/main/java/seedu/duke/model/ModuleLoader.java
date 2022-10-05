package seedu.duke.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipInputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ModuleLoader {

    public static List<Integer> jsonListToList(ArrayNode array) {
        return Stream.of(array)
                .map(JsonNode::asInt)
                .collect(Collectors.toList());
    }

    public static RawLesson jsonNodeToRawLesson(JsonNode node) {
        return new RawLesson(node.get("classNo").asText(),
                Day.valueOf(node.get("day").asText()),
                node.get("endTime").asText(),
                node.get("startTime").asText(),
                node.get("lessonType").asText(),
                node.get("venue").asText(),
                jsonListToList((ArrayNode) node.get("weeks")),
                node.get("size").asInt());
    }

    public static SemesterData jsonNodeToSemesterData(JsonNode node) {
        return new SemesterData(
                node.get("semester").asInt(),
                Stream.of((ArrayNode) node.get("timetable"))
                        .map(ModuleLoader::jsonNodeToRawLesson)
                        .collect(Collectors.toList()),
                node.get("examDate").asText(""),
                node.get("examDuration").asInt(0));
    }

    public static Module jsonNodeToModule(JsonNode node) {
        return new Module(
                node.get("acadYear").asText(),
                node.get("moduleCode").asText(),
                node.get("title").asText(),
                node.get("description").asText(),
                node.get("moduleCredit").asInt(),
                node.get("department").asText(),
                node.get("faculty").asText(),
                jsonListToList((ArrayNode) node.get("workload")),
                Stream.of((ArrayNode) node.get("semesterData")).map(ModuleLoader::jsonNodeToSemesterData)
                        .collect(Collectors.toList()),
                node.get("prerequisite").asText(""),
                node.get("corequisite").asText(""),
                node.get("preclusion").asText(""));
    }

    public static List<Module> loadModules() {
        ArrayList<Module> modules = new ArrayList<>();
        try {
            InputStream fullData = ModuleLoader.class.getClassLoader()
                    .getResourceAsStream("seedu/duke/assets/moduleFull.zip");
            ZipInputStream zis = new ZipInputStream(fullData);
            zis.getNextEntry();
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode root = (ArrayNode) mapper.readTree(zis);
            for (JsonNode node : root) {
                modules.add(jsonNodeToModule(node));
                System.out.println(modules.get(modules.size() - 1).moduleCode);
            }
            fullData.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(modules);
    }
}
