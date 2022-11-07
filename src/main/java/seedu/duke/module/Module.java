package seedu.duke.module;

import seedu.duke.UI;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.timetable.Timetable;
import seedu.duke.timetable.TimetableDict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.Objects;

/**
 * The class that represents a module object.
 */
public class Module {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String moduleName;
    private String moduleCode;
    private List<Lesson> lessonList;
    private final LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> lessonMap;
    private final LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> uniqueLessonMap;
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> attendingMap;
    private List<Lesson> attendingList;


    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public List<Lesson> getAttendingList() {
        return attendingList;
    }


    public LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> getUniqueLessonMap() {
        return uniqueLessonMap;
    }

    /**
     * Constructor for a module object.
     * @param moduleCode A valid module code from NUSMods.
     * @param moduleName The name of the module.
     * @param lessons The array of lessons from the module.
     */
    public Module(String moduleCode, String moduleName, List<Lesson> lessons) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.lessonList = lessons;
        this.lessonMap = populateData(); //this is the new classifiedLessons
        this.uniqueLessonMap = populateUniqueData(); //lessonMap with classes occupying the same timing removed
        this.attendingMap = populateAttending(); //this is the new attending
        this.attendingList = getAttendingInListForm();
    }

    /**
     * Obtains the information about the lessons that share a single class number.
     *
     * @param currClass A list of lessons that share the same class number.
     * @return A list information of the list of lessons.
     */
    private List<List<String>> getClassInfo(List<Lesson> currClass) {
        List<List<String>> lessonsInfoList = new ArrayList<>();
        for (Lesson lesson : currClass) {
            lessonsInfoList.add(lesson.getInfo());
        } 
        return lessonsInfoList;
    }

    /**
     * Obtains the list of lessons that the user is attending via the attendingMap.
     *
     * @param lessonType The type of lesson to be obtained.
     * @return A list of attending lessons that share the same class number.
     */
    public List<Lesson> getClassFromAttendingMap(String lessonType) {
        String classNumber = attendingMap.get(lessonType).keySet().iterator().next();
        return attendingMap.get(lessonType).get(classNumber);
    }

    /**
     * Reduces the attendingMap into a list, where all attending lessons are stored.
     *
     * @return The list of attending lessons.
     */
    public List<Lesson> getAttendingInListForm() {
        List<Lesson> allAttending = new ArrayList<Lesson>();
        for (LinkedHashMap<String, ArrayList<Lesson>> lessonTypes : this.attendingMap.values()) {
            for (ArrayList<Lesson> allClasses : lessonTypes.values()) {
                for (Lesson lesson : allClasses) {
                    allAttending.add(lesson);
                }
            }
        }
        return allAttending;
    }

    /**
     * Getter for lessonList.
     *
     * @return List of all lessons.
     */
    public List<Lesson> getLessonList() {
        return lessonList;
    }


    /**
     * This method fills up the attendingMap with information about each lesson that the user is attending.
     * If the lesson does not have a specific attending timing, a placeholder timing will be added.
     *
     * @return The data structure that holds all information about the lessons the user are attending.
     */
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> populateAttending() {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> entry
                = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>>();
        for (String lessonType : lessonMap.keySet()) { //initialises the types of lessons
            if (!entry.containsKey(lessonType)) {
                entry.put(lessonType, new LinkedHashMap<String, ArrayList<Lesson>>());
            }
            if (lessonMap.get(lessonType).size() == 1) { //only one class
                String classNumber = "";
                for (String currClassNumber : lessonMap.get(lessonType).keySet()) {
                    classNumber = currClassNumber;
                }
                entry.replace(lessonType, new LinkedHashMap<>(lessonMap.get(lessonType)));
                Timetable.timetableDict.addClass(lessonMap.get(lessonType).get(classNumber));
            } else {
                int numOfClasses = 0;
                for (ArrayList<Lesson> numberedClass : new ArrayList<>(lessonMap.get(lessonType).values())) {
                    numOfClasses = numberedClass.size();
                    break;
                }
                addUnknownToAttendingList(entry.get(lessonType), lessonType, numOfClasses);
            }
        }
        return entry;
    }

    /**
     * This method helps to fill up attendingMap by adding placeholder lesson information.
     *
     * @param unknownClass The particular class that has undetermined lessons.
     * @param lessonType The type of the lesson attended.
     * @param size Number of lessons that share the same class number.
     */
    private void addUnknownToAttendingList(HashMap<String, ArrayList<Lesson>> unknownClass,
                                           String lessonType, int size) {
        ArrayList<Lesson> classes = new ArrayList<Lesson>();
        String day = "Undetermined Day";
        String startTime = "Undetermined";
        String endTime = "Undetermined";
        String classNumber = "NA";
        String weeks = "Weeks: NA";
        for (int i = 0; i < size; i++) {
            classes.add(new Lesson(day, startTime, endTime, lessonType, classNumber, weeks, moduleCode));
        }
        unknownClass.put(classNumber, classes);
    }

    /**
     * Generates the text to be displayed to the user when they list the modules.
     * @return The formatted text to be displayed.
     */
    public String getModuleDetails() {
        StringBuilder details = new StringBuilder(this.getModuleCode() + ": " + this.getModuleName() + "\n");

        HashMap<String, Integer> lessonDupe = new HashMap<>();
        String counter;
        for (Lesson lesson : attendingList) {
            counter = getCount(lessonDupe, lesson);
            details.append("     [").append(lesson.getLessonType()).append(counter).append("] ")
                    .append(lesson.getDay()).append("   ")
                    .append(convertTime(lesson.getStartTime())).append(" - ")
                    .append(convertTime(lesson.getEndTime())).append("   ").append(lesson.getWeeks()).append("\n");
        }
        return details.toString();
    }

    /**
     * Obtains the number of lessons a certain lesson type has, in order to format
     * the list to be displayed to user.
     *
     * @param lessonDupe The hashmap to track the number of each lesson type.
     * @param lesson The lesson to be checked.
     * @return The index tagged to the lesson type that is displayed to the user.
     */
    private String getCount(HashMap<String, Integer> lessonDupe, Lesson lesson) {
        String type = lesson.getLessonType();
        if (lessonDupe.containsKey(type)) {
            Integer newCount = lessonDupe.get(type) + 1;
            lessonDupe.remove(type);
            lessonDupe.put(type, newCount);
            return " " + newCount;
        } else {
            lessonDupe.put(type, 1);
            return checkForMoreDupes(lesson);
        }
    }

    /**
     * Checks if a certain lesson type has dupes when looping through all the attending lessons.
     * If there are no lesson that share the same lesson type, no index is appended to the list
     * the user sees.
     *
     * @param lesson The lesson to be checked.
     * @return The index tagged to the lesson type that is displayed to the user.
     */
    private String checkForMoreDupes(Lesson lesson) {
        int count = 0;
        for (Lesson tempLesson : attendingList) {
            if (Objects.equals(tempLesson.getLessonType(), lesson.getLessonType())) {
                count += 1;
            }
        }
        if (count > 1) {
            return " 1";
        }
        return "";
    }

    /**
     * Adds a ':' to separate the hours and the minutes of the time.
     *
     * @param time The time without a colon in 24hr format.
     * @return An array that represents the new time format.
     */
    private String convertTime(String time) {
        char[] arr = time.toCharArray();
        if (arr.length != 4) {
            return "Undetermined Time";
        }
        char[] newArr = {arr[0], arr[1], ':', arr[2], arr[3]};
        return new String(newArr);
    }

    /**
     * Gets the lesson types that the user can choose when
     * they are setting the lessons for the current module.
     *
     * @return The formatted list of options the user will see.
     */
    public String getLessonTypes() {
        StringBuilder userView = new StringBuilder();
        int index = 1;
        for (String lessonType : lessonMap.keySet()) {
            if (lessonMap.get(lessonType).size() != 1) {
                UI.displayLessonTypeToSet(index, lessonType, userView);
                index++;
            }
        }
        return userView.toString();
    }

    /**
     * Gets the number of lesson types whose lessons are adjustable.
     *
     * @return Number of lesson types.
     */
    public int getNumLessonTypes() {
        int length = 0;
        for (LinkedHashMap<String, ArrayList<Lesson>> entry : lessonMap.values()) {
            if (entry.size() != 1) {
                length++;
            }
        }
        return length;
    }


    /**
     * Returns the type of lesson based on what the index the user inputs during the set command.
     *
     * @param index The user's choice of lesson based on what the options displayed.
     * @return The type of lesson the user wishes to set.
     */
    public String getTypeOfLessonFromIndex(int index) {
        assert index >= 0 : "index should be within range";
        int indexInMap = 0;
        for (String lessonType : lessonMap.keySet()) {
            if (lessonMap.get(lessonType).size() != 1) {
                if (indexInMap != index) {
                    indexInMap++;
                } else {
                    return lessonType;
                }
            }
        }
        return null;
    }

    /**
     * Gets all the possible classes that the user can choose to set and the information of
     * the lessons in the class. This is then formatted and displayed to the user.
     *
     * @param targetType Type of lesson to be replaced.
     * @return The formatted list of replacement that the user sees.
     */
    public String getListOfLessonReplacements(String targetType) {
        StringBuilder details = new StringBuilder();
        int index = 1;
        for (ArrayList<Lesson> oneClass : lessonMap.get(targetType).values()) {
            details.append(index).append(":\n");
            for (Lesson lesson : oneClass) {
                details.append(lesson.getDay()).append("   ")
                        .append(convertTime(lesson.getStartTime())).append(" - ")
                        .append(convertTime(lesson.getEndTime())).append("   ").append(lesson.getWeeks()).append("\n");
            }
            index++;
        }
        return details.toString();
    }

    public int getNumberOfReplacements(String targetType) {
        return lessonMap.get(targetType).size();
    }

    /**
     * Obtains the replacement class that the user chooses to set.
     *
     * @param targetType Type of lesson to be replaced.
     * @param index Index of the class the user chose.
     * @return The array representing the class that the user chose.
     */
    public ArrayList<Lesson> getReplacement(String targetType, int index) {
        assert index >= 0 : "index should be within range";
        assert index <= lessonMap.get(targetType).size() : "index should be within range";

        int counter = 0;
        for (ArrayList<Lesson> oneClass : lessonMap.get(targetType).values()) {
            counter++;
            if (counter == index) {
                return oneClass;
            }
        }

        lgr.severe("lesson replacement fail!");
        assert false : "lesson index should be found and returned before this";
        return null;
    }

    /**
     * Replaces attendingMap with the information generated from local txt file.
     *
     * @param loadedLessons The data structure representing the lessons from the local txt file.
     */
    public void replaceAllAttending(LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> loadedLessons) {
        this.attendingMap = loadedLessons;
        for (String lessonType : loadedLessons.keySet()) {
            LinkedHashMap<String, ArrayList<Lesson>> classes = loadedLessons.get(lessonType);
            for (String classNumber : classes.keySet()) {
                List<Lesson> currClass = loadedLessons.get(lessonType).get(classNumber);
                Timetable.timetableDict.addClass(currClass);
            }
        }
        this.attendingList = getAttendingInListForm();
    }


    /**
     * Replace the current list and map of attending lessons.
     * This method is used in the set command.
     *
     * @param timetableDict Object that handles allocate command.
     * @param newLessons    Array of lessons to be added.
     * @param lessonType    The type of the lesson to be added.
     */
    public void replaceAttending(TimetableDict timetableDict, ArrayList<Lesson> newLessons, String lessonType) {
        String classNum = newLessons.get(0).getClassNumber();
        attendingMap.get(lessonType).clear();
        attendingMap.get(lessonType).put(classNum, newLessons);
        attendingList = getAttendingInListForm();
        ArrayList<Lesson> oldLesson = getOldLessons(lessonType);
        for (Lesson lesson : oldLesson) {
            timetableDict.deleteLesson(lesson);
        }
        for (Lesson lesson : newLessons) {
            timetableDict.addLesson(lesson);
        }
    }

    /**
     * Replace the current list and map of attending lessons.
     * This method is used in the allocate command.
     *
     * @param newLesson The new lesson to be allocated.
     */
    public void replaceAttending(Lesson newLesson) {
        String newLessonType = newLesson.getLessonType();
        ArrayList<Lesson> oldLessonGroup = attendingMap.get(newLessonType)
                .values()
                .iterator()
                .next(); //gets the array of lessons belonging to the same classNum
        for (Lesson oldLesson : oldLessonGroup) {
            Timetable.timetableDict.deleteLesson(oldLesson); //remove the lessons from timetable dict
        }
        attendingMap.get(newLessonType).clear(); //clears the lessons attended for this lesson type


        String newClassNum = newLesson.getClassNumber();
        //makes a copy of the new lesson group
        ArrayList<Lesson> newLessonGroup = new ArrayList<Lesson>(lessonMap.get(newLessonType).get(newClassNum));
        attendingMap.get(newLessonType).put(newClassNum, newLessonGroup); //puts the new lesson into attendingMap
        attendingList = getAttendingInListForm(); //updates the attendingList based on the new attendingMap

        //Adding to timetableDict
        for (Lesson lesson : newLessonGroup) {
            Timetable.timetableDict.addLesson(lesson);
        }
    }

    /**
     * Gets the old lessons present in attendingMap that is to be replaced.
     * @param lessonType The lesson type of the lesson to be replaced.
     * @return The first (and only) list of lessons with the specific lesson type.
     */
    private ArrayList<Lesson> getOldLessons(String lessonType) {
        return attendingMap.get(lessonType).values().iterator().next();
    }


    /**
     * Stores all lessons of the module gathered from the NUSMods API.
     *
     * @return A "2-key" hashmap of arrays. Representing lessonType : classNo : {class1, class2}
     */
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> populateData() {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> data
                = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>>();
        for (Lesson lesson : lessonList) {
            String lessonType = lesson.getLessonType();
            String classNum = lesson.getClassNumber();
            if (!data.containsKey(lessonType)) {
                data.put(lessonType, new LinkedHashMap<String, ArrayList<Lesson>>());
                data.get(lessonType).put(classNum, new ArrayList<Lesson>());
                data.get(lessonType).get(classNum).add(lesson);
            } else {
                if (!data.get(lessonType).containsKey(classNum)) {
                    data.get(lessonType).put(classNum, new ArrayList<Lesson>());
                    data.get(lessonType).get(classNum).add(lesson);
                } else {
                    data.get(lessonType).get(classNum).add(lesson);
                }
            }
        }
        return data;
    }

    /**
     * Creates a data structure that holds data about lessons that have unique information.
     *
     * @return The data structure created.
     */
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> populateUniqueData() {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> uniqueData
                = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>>();
        List<List<List<String>>> classInfoList = new ArrayList<>();
        for (String lessonType : lessonMap.keySet()) {
            uniqueData.put(lessonType, new LinkedHashMap<String, ArrayList<Lesson>>());
            for (String classNumber : lessonMap.get(lessonType).keySet()) {
                ArrayList<Lesson> currClass = lessonMap.get(lessonType).get(classNumber);
                List<List<String>> currClassInfo = getClassInfo(currClass);
                if (classInfoList.contains(currClassInfo)) {
                    continue;
                } else {
                    classInfoList.add(currClassInfo);
                    uniqueData.get(lessonType).put(classNumber, currClass);
                }
            }
        }
        return uniqueData;
    }

    @Override
    public String toString() {
        return moduleCode + " : " + moduleName;
    }

    /**
     * Checks if a certain lesson type has already been set.
     *
     * @param lessonType The lesson type to be checked.
     * @return Whether the lesson is set or not.
     */
    public boolean isLessonTypeAttended(String lessonType) {
        boolean isLessonTypeAttended = false;
        for (Lesson attendingLesson : attendingList) {
            String lessonDay = attendingLesson.getDay();
            String attendingLessonType = attendingLesson.getLessonType();
            if (attendingLessonType.equals(lessonType)) {
                if (!lessonDay.equals("Undetermined Day")) {
                    isLessonTypeAttended = true;
                } else {
                    isLessonTypeAttended = false;
                }
            }
        }
        return isLessonTypeAttended;
    }

    /**
     * Finds the number of unique lesson types for the module.
     *
     * @return A list of all lesson types.
     */
    public List<String> getAttendingLessonTypes() {
        List<String> attendingLessonTypes = new ArrayList<String>();
        for (String lessonType : attendingMap.keySet()) {
            attendingLessonTypes.add(lessonType);
        }
        return attendingLessonTypes;
    }

    /**
     * Checks whether a module has lessons to swap. It checks whether the module
     * has any lessons. If it does, it will check if it has more than 1 unique class.
     *
     * @return Whether a module has lessons to swap.
     */
    public boolean hasAvailableLessonsToSwap() {
        if (lessonMap.size() == 0) {
            return false;
        }
        for (LinkedHashMap<String, ArrayList<Lesson>> allClasses : lessonMap.values()) {
            if (allClasses.size() > 1) {
                return true;
            }
        }
        return false;
    }
}
