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

import seedu.duke.parser.DayParser;
import seedu.duke.parser.LessonTypeParser;

/**
 * Loads module data from the resource file.
 */
class ModuleLoader {

    /**
     * Helper function to convert a JsonNode object field to a string with a default value of "".
     * @param node The JsonNode object
     * @param key The key of the string data to be retrieved
     * @return Returns the value indexed by the provided key or an empty string is the key is not present
     */
    private static String getStringOrEmpty(JsonNode node, String key) {
        return Optional.<JsonNode>ofNullable(node.get(key)).<String>map(JsonNode::asText).orElse("");
    }

    /**
     * Helper function to convert a JsonNode object field to a string with a default value of 0.
     * @param node The JsonNode object
     * @param key The key of the int data to be retrieved
     * @return Returns the value indexed by the provided key or 0 is the key is not present
     */
    private static int getIntOrEmpty(JsonNode node, String key) {
        return Optional.<JsonNode>ofNullable(node.get(key)).<Integer>map(JsonNode::asInt).orElse(0);
    }

    /**
     * Helper function to convert an iterator to a stream.
     * @param <T> The type contained in the iterator
     * @param iterator The iterator
     * @return A stream containing the values being iterated over
     */
    private static <T> Stream<T> toStream(Iterator<T> iterator) {
        return StreamSupport.stream(((Iterable<T>) () -> iterator).spliterator(), false);
    }

    /**
     * Helper function to convert an integer array in a JsonNode to an integer list in java.
     * @param array The JsonNode representing the array
     * @return The data as a list of integers
     */
    private static List<Integer> jsonNodeAsIntList(JsonNode array) {
        if (array == null || !array.isArray()) {
            return List.of();
        }
        return toStream(((ArrayNode) array).elements())
                .map(JsonNode::asInt)
                .collect(Collectors.toList());
    }

    /**
     * Parses a RawLesson object from a JsonNode.
     * @param node The JsonNode object
     * @return The data as a RawLesson object
     */
    private static RawLesson jsonNodeToRawLesson(JsonNode node) {
        return new RawLesson(node.get("classNo").asText(),
                DayParser.parse(node.get("day").asText()),
                node.get("endTime").asText(),
                node.get("startTime").asText(),
                LessonTypeParser.parse(node.get("lessonType").asText()),
                node.get("venue").asText(),
                jsonNodeAsIntList(node.get("weeks")),
                node.get("size").asInt());
    }

    /**
     * Parses a SemesterData object from a JsonNode.
     * @param node The JsonNode object
     * @return The data as a SemesterData object
     */
    private static SemesterData jsonNodeToSemesterData(JsonNode node) {
        return new SemesterData(
                node.get("semester").asInt(),
                toStream(((ArrayNode) node.get("timetable")).elements())
                        .map(ModuleLoader::jsonNodeToRawLesson)
                        .collect(Collectors.toList()),
                getStringOrEmpty(node, "examDate"),
                getIntOrEmpty(node, "examDuration"));
    }

    /**
     * Parses a Module object from a JsonNode.
     * @param node The JsonNode object
     * @return The data as a Module object
     */
    private static Module jsonNodeToModule(JsonNode node) {
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

    /**
     * Loads a list of modules from the resource file.
     * @return A list of modules sorted by module code
     */
    static List<Module> loadModules() {
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
