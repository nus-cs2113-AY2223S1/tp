package seedu.duke.module;

import seedu.duke.Timetable;
import seedu.duke.TimetableDict;
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
    private List<Lesson> lessons;
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> allLessons;
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> allAttending;
    private List<Lesson> attending;
    private LinkedHashMap<String, ArrayList<Lesson>> classifiedLessons;


    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public List<Lesson> getAttending() {
        return attending;
    }

    public List<Lesson> getAllAvailableLessons() {
        List<Lesson> allAttending = new ArrayList<Lesson>();
        for (LinkedHashMap<String, ArrayList<Lesson>> lessonTypes : this.allAttending.values()) {
            for (ArrayList<Lesson> allClasses : lessonTypes.values()) {
                for (Lesson lesson : allClasses) {
                    allAttending.add(lesson);
                }
            }
        }
        return allAttending;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }


    public Module(String moduleCode, String moduleName, List<Lesson> lessons) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.lessons = lessons;
        this.classifiedLessons = classifyLessons(lessons);
        this.allLessons = populateData(); //this is the new classifiedLessons
        this.allAttending = populateAttending(); //this is the new attending
        this.attending = getAllAvailableLessons();
    }

    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> populateAttending() {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> temp
                = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>>();
        for (String lessonType : allLessons.keySet()) { //initialises the types of lessons
            if (!temp.containsKey(lessonType)) {
                temp.put(lessonType, new LinkedHashMap<String, ArrayList<Lesson>>());
            }
            if (allLessons.get(lessonType).size() == 1) {
                temp.replace(lessonType, allLessons.get(lessonType));
            } else {
                int numOfClasses = 0;
                for (ArrayList<Lesson> numberedClass : allLessons.get(lessonType).values()) {
                    numOfClasses = numberedClass.size();
                    break;
                }
                addUnknownToAttendingList(temp.get(lessonType), lessonType, numOfClasses);
            }
        }
        return temp;
    }

    private List<Lesson> matchLessonTypes(LinkedHashMap<String, ArrayList<Lesson>> classifiedLessons) {
        List<Lesson> temp = new ArrayList<>();
        for (ArrayList<Lesson> list : classifiedLessons.values()) {
            for (int i = 0; i < checkDuplicateLessonNumbers(list); i++) {
                String tempLessonType = list.get(0).getLessonType();
                addToAttendingList(temp, tempLessonType);
            }
        }
        return temp;
    }

    private int checkDuplicateLessonNumbers(ArrayList<Lesson> list) {
        LinkedHashMap<String, Integer> checker = new LinkedHashMap<>();

        for (Lesson lesson : list) {
            String classNum = lesson.getClassNumber();
            if (!checker.containsKey(classNum)) {
                checker.put(classNum, 1);
            } else {
                int newCount = checker.get(classNum) + 1;
                checker.remove(classNum);
                checker.put(classNum, newCount);
            }
        }
        return getHighestCount(checker);
    }

    private int getHighestCount(LinkedHashMap<String, Integer> checker) {
        int highestCount = 0;
        for (Integer count : checker.values()) {
            if (count > highestCount) {
                highestCount = count;
            }
        }
        return highestCount;
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

    private void addToAttendingList(List<Lesson> temp, String lessonType) {
        String day = "Undetermined Day";
        String startTime = "Undetermined";
        String endTime = "Undetermined";
        String classNumber = "NA";
        Lesson tempLesson = new Lesson(day, startTime, endTime, lessonType, classNumber, moduleCode);
        temp.add(tempLesson);
    }

    public String getModuleDetails() {
        StringBuilder details = new StringBuilder(this.getModuleCode() + ": " + this.getModuleName() + "\n");

        HashMap<String, Integer> lessonDupe = new HashMap<>();
        String counter;
        for (Lesson lesson : attending) {
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
        for (Lesson tempLesson : attending) {
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

    public String getLessonTypes() {
        StringBuilder list = new StringBuilder();
        int index = 1;
        for (String lessonType : allLessons.keySet()) {
            if (allLessons.get(lessonType).size() != 1) {
                list.append(index).append(". ").append(lessonType).append("\n");
                index++;
            }
        }
        return list.toString();
    }

    /**
     * Gets the number of lesson types whose lessons are adjustable.
     *
     * @return Number of lesson types
     */
    public int getLessonTypeLength() {
        int length = 0;
        for (LinkedHashMap<String, ArrayList<Lesson>> entry : allLessons.values()) {
            if (entry.size() != 1) {
                length++;
            }
        }
        return length;
        //return getAllAttending().size();
    }

    @Override
    public String toString() {
        return moduleCode + " : " + moduleName;
    }

    public String getTypeOfLessonFromIndex(int index) {
        assert index >= 0 : "index should be within range";
        int indexInMap = 0;
        for (String lessonType : allLessons.keySet()) {
            if (allLessons.get(lessonType).size() != 1) {
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
        for (ArrayList<Lesson> oneClass : allLessons.get(targetType).values()) {
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
        return allLessons.get(targetType).size();
    }

    public ArrayList<Lesson> getReplacement(String targetType, int index) {
        assert index >= 0 : "index should be within range";
        assert index < lessons.size() : "index should be within range";

        int counter = 0;
        for (ArrayList<Lesson> oneClass : allLessons.get(targetType).values()) {
            counter++;
            if (counter == index) {
                return oneClass;
            }
        }

        lgr.severe("lesson replacement fail!");
        assert false : "lesson index should be found and returned before this";
        return null;
    }


    public void replaceNewAttending(LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> loadedLessons) {
        this.allAttending = loadedLessons;
        this.attending = getAllAvailableLessons();

    }


    /**
     * Replace the current list of attending lessons.
     * This method is used in the set command.
     *
     * @param timetableDict
     * @param newLessons Array of lessons to be added
     * @param indexForLesson
     */
    public void replaceAttending(TimetableDict timetableDict, ArrayList<Lesson> newLessons, String moduleType) {
        ArrayList<Lesson> oldLesson = getOldLessons(moduleType);
        String classNum = newLessons.get(0).getClassNumber();
        allAttending.get(moduleType).clear();
        allAttending.get(moduleType).put(classNum, newLessons);
        attending = getAllAvailableLessons();
        for (Lesson lesson : oldLesson) {
            timetableDict.deleteLesson(lesson);
        }
        for (Lesson lesson : newLessons) {
            timetableDict.addLesson(lesson, moduleCode);
        }
//        Lesson oldLesson = attending.get(indexForLesson);
//        timetableDict.deleteLesson(oldLesson);
//        attending.set(indexForLesson, newLessons);
//        timetableDict.addLesson(newLessons, moduleCode);
    }

    public void replaceAttending(Lesson newLesson, Integer indexForLesson) {
        Lesson oldLesson = attending.get(indexForLesson);
        Timetable.timetableDict.deleteLesson(oldLesson);
        attending.set(indexForLesson, newLesson);
        Timetable.timetableDict.addLesson(newLesson, moduleCode);
    }

    private ArrayList<Lesson> getOldLessons(String moduleCode) {
            return allAttending.get(moduleCode).values().iterator().next();
    }

    public void replaceAttending(Lesson newLesson) {
        int indexToSet = 0;
        for (Lesson lesson : attending) {
            if (lesson.getLessonType().equals(newLesson.getLessonType())) {
                break;
            }
            indexToSet += 1;
        }
        if (indexToSet >= attending.size()) {
            return;
        }

        //delete old lesson from timetableDict
        Lesson oldLesson = attending.get(indexToSet);
        Timetable.timetableDict.deleteLesson(oldLesson);

        //Setting attending for this module
        attending.set(indexToSet, newLesson);

        //Adding to timetableDict
        Timetable.timetableDict.addLesson(newLesson, moduleCode);
    }



    /**
     * Stores all lessons of the module gathered from the NUSMods API.
     *
     * @return A "2-key" hashmap of arrays. Representing lessonType : classNo : {class1, class2}
     */
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> populateData() {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> data
                = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>>();
        for (Lesson lesson : lessons) {
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
}
