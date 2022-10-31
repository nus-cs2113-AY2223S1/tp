package seedu.duke.module;

import seedu.duke.Timetable;
import seedu.duke.TimetableDict;
import seedu.duke.UI;
import seedu.duke.module.lessons.Lesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.Objects;


public class Module {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String moduleName;
    private String moduleCode;
    private List<Lesson> lessonList;
    private final LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> lessonMap;
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> attendingMap;
    private List<Lesson> attendingList;
    private LinkedHashMap<String, ArrayList<Lesson>> classifiedLessons;


    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public List<Lesson> getAttendingList() {
        return attendingList;
    }

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

    public List<Lesson> getLessonList() {
        return lessonList;
    }


    public Module(String moduleCode, String moduleName, List<Lesson> lessons) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.lessonList = lessons;
        this.classifiedLessons = classifyLessons(lessons);
        this.lessonMap = populateData(); //this is the new classifiedLessons
        this.attendingMap = populateAttending(); //this is the new attending
        this.attendingList = getAttendingInListForm();
    }

    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> populateAttending() {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> entry
                = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>>();
        for (String lessonType : lessonMap.keySet()) { //initialises the types of lessons
            if (!entry.containsKey(lessonType)) {
                entry.put(lessonType, new LinkedHashMap<String, ArrayList<Lesson>>());
            }
            if (lessonMap.get(lessonType).size() == 1) {
                entry.replace(lessonType, new LinkedHashMap<>(lessonMap.get(lessonType)));
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

    private void addUnknownToAttendingList(HashMap<String, ArrayList<Lesson>> temp, String lessonType, int size) {
        ArrayList<Lesson> classes = new ArrayList<Lesson>();
        String day = "Undetermined Day";
        String startTime = "Undetermined";
        String endTime = "Undetermined";
        String classNumber = "NA";
        for (int i = 0; i < size; i++) {
            classes.add(new Lesson(day, startTime, endTime, lessonType, classNumber, moduleCode));
        }
        temp.put(classNumber, classes);
    }

    public String getModuleDetails() {
        StringBuilder details = new StringBuilder(this.getModuleCode() + ": " + this.getModuleName() + "\n");

        HashMap<String, Integer> lessonDupe = new HashMap<>();
        String counter;
        for (Lesson lesson : attendingList) {
            counter = getCount(lessonDupe, lesson);
            details.append("     [").append(lesson.getLessonType()).append(counter).append("] ")
                    .append(lesson.getDay()).append("   ")
                    .append(convertTime(lesson.getStartTime())).append(" - ")
                    .append(convertTime(lesson.getEndTime())).append("\n");
        }
        return details.toString();
    }

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

    private String convertTime(String time) {
        char[] arr = time.toCharArray();
        if (arr.length != 4) {
            return "Undetermined Time";
        }
        char[] newArr = {arr[0], arr[1], ':', arr[2], arr[3]};
        return new String(newArr);
    }

    /**
     * Gets the lessonTypes that the user can choose when
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
     * @return Number of lesson types
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

    public String getListOfLessonReplacements(String targetType) {
        StringBuilder details = new StringBuilder();
        int index = 1;
        for (ArrayList<Lesson> oneClass : lessonMap.get(targetType).values()) {
            details.append(index).append(":\n");
            for (Lesson lesson : oneClass) {
                details.append(lesson.getDay()).append("   ")
                        .append(convertTime(lesson.getStartTime())).append(" - ")
                        .append(convertTime(lesson.getEndTime())).append("\n");
            }
            index++;
        }
        return details.toString();
    }

    public int getNumberOfReplacements(String targetType) {
        return lessonMap.get(targetType).size();
    }

    public ArrayList<Lesson> getReplacement(String targetType, int index) {
        assert index >= 0 : "index should be within range";
        assert index < lessonList.size() : "index should be within range";

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


    public void replaceAllAttending(LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> loadedLessons) {
        this.attendingMap = loadedLessons;
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
            timetableDict.addLesson(lesson, moduleCode);
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
            Timetable.timetableDict.addLesson(lesson, moduleCode);
        }
    }

    private ArrayList<Lesson> getOldLessons(String moduleCode) {
        return attendingMap.get(moduleCode).values().iterator().next();
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

    private LinkedHashMap<String, ArrayList<Lesson>> classifyLessons(List<Lesson> lessons) {
        LinkedHashMap<String, ArrayList<Lesson>> classifiedLessons = new LinkedHashMap<>();
        for (Lesson lesson : lessons) {
            if (!classifiedLessons.containsKey(lesson.getLessonType())) {
                classifiedLessons.put(lesson.getLessonType(), new ArrayList<>());
                classifiedLessons.get(lesson.getLessonType()).add(lesson);
            } else {
                classifiedLessons.get(lesson.getLessonType()).add(lesson);
            }
        }
        return classifiedLessons;
    }

    public LinkedHashMap<String, ArrayList<Lesson>> getClassifiedLessons() {
        return classifiedLessons;
    }

    @Override
    public String toString() {
        return moduleCode + " : " + moduleName;
    }
}
