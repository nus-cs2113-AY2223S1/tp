package seedu.duke;

import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.data.DataManager;
import seedu.duke.data.LessonManager;
import seedu.duke.data.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    public static List<Module> listOfModules = new ArrayList<>();

    public static void addNewModule(String code, String name, String description, List<Lesson> lessons) {
        Module newModule = new Module(code, name, description, lessons); 
        LessonManager.addLesson(newModule);
        ModuleManager.addModule(code, name, description);
        listOfModules.add(newModule);
    }

    public static void addNewModuleFromFile(String code, String name, String description, List<Lesson> lessons) {
        Module newModule = new Module(code, name, description, lessons); 
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
        DataManager.deleteModule(module);
        listOfModules.remove(index - 1);
    }    // the nth module in list has index n-1

    public static int getListLength() {
        return listOfModules.size();
    }

    public static String getShortenedList() {
        StringBuilder list = new StringBuilder();
        int index = 1;
        for (Module module : listOfModules) {
            list.append(index).append(". ").append(module.toString()).append("\n");
            index += 1;
        }
        return list.toString();
    }

    public static int getLessonTypeLength(int index) {
        return listOfModules.get(index).getLessonTypeLength();
    }

    public static String getLessonTypes(int index) {
        return listOfModules.get(index).getLessonTypes();
    }

    public static String getLessonTypeFromIndex(int indexForModule, int lessonIndex) {
        assert indexForModule >= 0 : "index should be within range";
        assert lessonIndex >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getTypeOfLessonFromIndex(lessonIndex);
    }

    public static String listAllPossibleLessonReplacements(int indexForModule, String targetLessonType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getListOfLessonReplacements(targetLessonType);
    }

    public static int getNumberOfPossibleReplacements(int indexForModule, String targetLessonType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getNumberOfReplacements(targetLessonType);
    }

    public static Lesson getLessonReplacement(int indexForModule, int indexForTarget, String targetType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getReplacement(targetType, indexForTarget);
    }

    public static void replaceLesson(Lesson newLesson, int indexForModule, Integer indexForLesson) {
        assert indexForModule >= 0 : "index should be within range";

        listOfModules.get(indexForModule).replaceAttending(newLesson, indexForLesson);
    }
}
