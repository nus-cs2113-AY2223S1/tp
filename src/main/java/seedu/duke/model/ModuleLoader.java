package seedu.duke.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.ZipInputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ModuleLoader {

    public static String getStringOrEmpty(JsonNode node, String key) {
        return Optional.<JsonNode>ofNullable(node.get(key)).<String>map(JsonNode::asText).orElse("");
    }

    public static int getIntOrEmpty(JsonNode node, String key) {
        return Optional.<JsonNode>ofNullable(node.get(key)).<Integer>map(JsonNode::asInt).orElse(0);
    }

    public static <T> Stream<T> toStream(Iterator<T> iterator) {
        return StreamSupport.stream(((Iterable<T>) () -> iterator).spliterator(), false);
    }

    public static List<Integer> jsonNodeAsIntList(JsonNode array) {
        if (array == null || !array.isArray()) {
            return List.of();
        }
        return toStream(((ArrayNode) array).elements())
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
                jsonNodeAsIntList(node.get("weeks")),
                node.get("size").asInt());
    }

    public static SemesterData jsonNodeToSemesterData(JsonNode node) {
        return new SemesterData(
                node.get("semester").asInt(),
                toStream(((ArrayNode) node.get("timetable")).elements())
                        .map(ModuleLoader::jsonNodeToRawLesson)
                        .collect(Collectors.toList()),
                getStringOrEmpty(node, "examDate"),
                getIntOrEmpty(node, "examDuration"));
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
                jsonNodeAsIntList(node.get("workload")),
                toStream(((ArrayNode) node.withArray("semesterData")).elements())
                        .map(ModuleLoader::jsonNodeToSemesterData)
                        .collect(Collectors.toList()),
                getStringOrEmpty(node, "prerequisite"),
                getStringOrEmpty(node, "corequisite"),
                getStringOrEmpty(node, "preclusion"));
    }

    public static List<Module> loadModules() {
        ArrayList<Module> modules = new ArrayList<>();
        try {
            InputStream fullData = ModuleLoader.class.getClassLoader()
                    .getResourceAsStream("moduleFull.zip");
            ZipInputStream zis = new ZipInputStream(fullData);
            zis.getNextEntry();
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode root = (ArrayNode) mapper.readTree(zis);
            for (JsonNode node : root) {
                modules.add(jsonNodeToModule(node));
            }
            fullData.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        modules.sort((a, b) -> a.moduleCode.compareTo(b.moduleCode));
        return Collections.unmodifiableList(modules);
    }
}
