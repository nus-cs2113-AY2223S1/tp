package seedu.duke.timetable;

import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    public static List<Module> listOfModules = new ArrayList<Module>();
    public static TimetableDict timetableDict = new TimetableDict();
    public static List<Module> listOfChangeableModules = new ArrayList<Module>();

    public static String allocateModules() {
        return timetableDict.allocateModules();
    }

    public static void addNewModule(String code, String name, List<Lesson> lessons) {
        Module newModule = new Module(code, name, lessons);
        listOfModules.add(newModule);
        if (newModule.hasAvailableLessonsToSwap()) {
            listOfChangeableModules.add(newModule);
        }
    }

    public static void addNewModuleFromFile(String code, String name, List<Lesson> lessons) {
        Module newModule = new Module(code, name, lessons);
        listOfModules.add(newModule);
    }

    public static List<Module> getListOfModules() {
        return listOfModules;
    }

    public static Module getModuleByCode(String code) {
        for (int i = 0; i < listOfModules.size(); i++) {
            if (listOfModules.get(i).getModuleCode().equals(code)) {
                return listOfModules.get(i);
            }
        }
        return null;
    }

    public static String listModules() {
        //list to print
        StringBuilder list;
        int counter = 1;


        if (listOfModules.size() == 0) {
            return "You have no modules at the moment!";
        } else {
            list = new StringBuilder("Here are your modules:\n");
        }
        for (Module modules : listOfModules) {
            list.append(counter).append(". ").append(modules.getModuleDetails()).append("\n");
            counter += 1;
        }
        return list.toString();
    }

    public static void deleteModule(int index) {
        Module module = listOfModules.get(index - 1);
        timetableDict.deleteModule(module);
        listOfModules.remove(index - 1);
        listOfChangeableModules.remove(module);
    }    // the nth module in list has index n-1

    public static int getListLength() {
        return listOfModules.size();
    }

    public static List<Module> getListOfChangeableModules() {
        return listOfChangeableModules;
    }

    public static int getNumberOfSettableLessons() {
        return listOfChangeableModules.size();
    }

    /**
     * Displays the list of modules that are selectable during set operation.
     *
     * @return The formatted list the user will see.
     */
    public static String getShortenedList() {
        StringBuilder list = new StringBuilder();
        int index = 1;
        for (Module module : listOfChangeableModules) {
            list.append(index).append(". ").append(module.toString()).append("\n");
            index += 1;
        }
        return list.toString();
    }

    /**
     * Gets the number of different lesson types whose lessons are adjustable for a particular module.
     *
     * @param index The index of the target module
     * @return Number of lessons types with adjustable lessons
     */
    public static int getSettableLessonTypeLength(int index) {
        return listOfChangeableModules.get(index).getNumLessonTypes();
    }

    public static String getSettableLessonTypes(int index) {
        return listOfChangeableModules.get(index).getLessonTypes();
    }

    public static String getSettableLessonTypeFromIndex(int indexForModule, int lessonIndex) {
        assert indexForModule >= 0 : "index should be within range";
        assert lessonIndex >= 0 : "index should be within range";

        return listOfChangeableModules.get(indexForModule).getTypeOfLessonFromIndex(lessonIndex);
    }

    public static String listAllSettableLessonReplacements(int indexForModule, String targetLessonType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfChangeableModules.get(indexForModule).getListOfLessonReplacements(targetLessonType);
    }

    public static int getSettableNumberOfPossibleReplacements(int indexForModule, String targetLessonType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfChangeableModules.get(indexForModule).getNumberOfReplacements(targetLessonType);
    }

    public static ArrayList<Lesson> getSettableLessonReplacement(int indexForModule, int indexForTarget,
                                                                 String targetType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfChangeableModules.get(indexForModule).getReplacement(targetType, indexForTarget);
    }

    public static void replaceSettableLesson(ArrayList<Lesson> newLessons, int indexForModule, String moduleType) {
        assert indexForModule >= 0 : "index should be within range";

        listOfChangeableModules.get(indexForModule).replaceAttending(timetableDict, newLessons, moduleType);
    }

    public static void clearData() {
        listOfModules.clear();
    }
}
