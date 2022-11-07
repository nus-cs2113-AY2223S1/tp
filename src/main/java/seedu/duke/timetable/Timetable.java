package seedu.duke.timetable;

import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the timetable, which holds all the modules that has been added.
 */
public class Timetable {
    public static List<Module> listOfModules = new ArrayList<Module>();
    public static TimetableDict timetableDict = new TimetableDict();
    private static List<Module> listOfChangeableModules = new ArrayList<Module>();

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

    /**
     * Gets the module object based on its module code.
     *
     * @param code The module code of the module.
     * @return The module object with the module code, null if the module is not found.
     */
    public static Module getModuleByCode(String code) {
        for (int i = 0; i < listOfModules.size(); i++) {
            if (listOfModules.get(i).getModuleCode().equals(code)) {
                return listOfModules.get(i);
            }
        }
        return null;
    }

    /**
     * List all modules that the user has added with their respective lessons.
     * The list is formatted the user to have a better view.
     *
     * @return The formatted list.
     */
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

    /**
     * Deletes a module from the timetable.
     *
     * @param index Index of module to be deleted.
     */
    //@@author HT-T
    public static void deleteModule(int index) {
        Module module = listOfModules.get(index - 1);
        timetableDict.deleteModule(module);
        listOfModules.remove(index - 1);
        listOfChangeableModules.remove(module);
    }    // the nth module in list has index n-1
    //@@author HT-T

    /**
     * Gets the number of modules that are currently in the list.
     *
     * @return Number of modules.
     */
    public static int getListLength() {
        return listOfModules.size();
    }

    public static List<Module> getListOfChangeableModules() {
        return listOfChangeableModules;
    }

    /**
     * Gets the number of modules whose lessons can be changed during setting.
     *
     * @return Number of settable modules.
     */
    public static int getNumberOfSettableModules() {
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
     * @return Number of lessons types with settable lessons.
     */
    public static int getSettableLessonTypeLength(int index) {
        return listOfChangeableModules.get(index).getNumLessonTypes();
    }

    /**
     * Gets the type of lessons that can be altered during setting for a certain module.
     * It is formatted for the user to have a better view.
     *
     * @param index The index of the module of concern.
     * @return The formatted lesson type that can be set.
     */
    public static String getSettableLessonTypes(int index) {
        return listOfChangeableModules.get(index).getLessonTypes();
    }

    /**
     * Gets the lesson type that the user has chosen from the list.
     *
     * @param indexForModule Index for the module the user has chosen.
     * @param lessonIndex Index of the lesson chosen.
     * @return The type of lesson that obtained.
     */
    public static String getSettableLessonTypeFromIndex(int indexForModule, int lessonIndex) {
        assert indexForModule >= 0 : "index should be within range";
        assert lessonIndex >= 0 : "index should be within range";

        return listOfChangeableModules.get(indexForModule).getTypeOfLessonFromIndex(lessonIndex);
    }

    /**
     * List all possible classes that the user can set, together with their lessons.
     * It is formatted for the user to have a better view.
     *
     * @param indexForModule Index for the module the user has chosen.
     * @param targetLessonType Type of lesson the user wants to set.
     * @return The formatted list that the user sees.
     */
    public static String listAllSettableLessonReplacements(int indexForModule, String targetLessonType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfChangeableModules.get(indexForModule).getListOfLessonReplacements(targetLessonType);
    }

    /**
     * Gets the number of classes that a certain module has.
     *
     * @param indexForModule Index for the module the user has chosen.
     * @param targetLessonType Type of lesson the user wants to set.
     * @return The number of classes.
     */
    public static int getSettableNumberOfPossibleReplacements(int indexForModule, String targetLessonType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfChangeableModules.get(indexForModule).getNumberOfReplacements(targetLessonType);
    }

    /**
     * Gets the class that the user has chosen to replace for the lesson type of
     * a certain module.
     *
     * @param indexForModule Index for the module the user has chosen.
     * @param indexForTarget The index for the class that the user has chosen.
     * @param targetType Type of lesson the user wants to set.
     * @return The list of lessons belonging to that class.
     */
    public static ArrayList<Lesson> getSettableLessonReplacement(int indexForModule, int indexForTarget,
                                                                 String targetType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfChangeableModules.get(indexForModule).getReplacement(targetType, indexForTarget);
    }

    /**
     * Replaces the lessons of a certain lesson type of a certain module.
     *
     * @param newLessons The list of lessons in a certain class.
     * @param indexForModule Type of lesson the user wants to set.
     * @param lessonType Type of lesson the user wants to set.
     */
    public static void replaceSettableLesson(ArrayList<Lesson> newLessons, int indexForModule, String lessonType) {
        assert indexForModule >= 0 : "index should be within range";

        listOfChangeableModules.get(indexForModule).replaceAttending(timetableDict, newLessons, lessonType);
    }

    /**
     * Clears all the modules stored in the lists.
     */
    public static void clearData() {
        listOfModules.clear();
        listOfChangeableModules.clear();
    }
}
