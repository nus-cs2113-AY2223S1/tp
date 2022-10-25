package seedu.duke.module;

import seedu.duke.module.lessons.Lesson;

import java.util.*;
import java.util.logging.Logger;


public class Module {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String moduleName;
    private String moduleCode;
    //private String moduleDescription;
    private List<Lesson> lessons;
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> allLessons;
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> allAttending;

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public List<Lesson> getAllAttending() {
        List<Lesson> allAttending = new ArrayList<Lesson>();
        for (HashMap<String, ArrayList<Lesson>> lessonTypes : this.allAttending.values()) {
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
        this.allLessons = populateData();
        this.allAttending = populateAttending();
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

    private List<Lesson> matchLessonTypes(HashMap<String, ArrayList<Lesson>> classifiedLessons) {
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
        HashMap<String, Integer> checker = new HashMap<>();

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

    private int getHighestCount(HashMap<String, Integer> checker) {
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
            classes.add(new Lesson(day, startTime, endTime, lessonType, classNumber));
        }
        temp.put(classNumber, classes);
    }

    private void addToAttendingList(List<Lesson> temp, String lessonType) {
        String day = "Undetermined Day";
        String startTime = "Undetermined";
        String endTime = "Undetermined";
        String classNumber = "NA";
        Lesson tempLesson = new Lesson(day, startTime, endTime, lessonType, classNumber);
        temp.add(tempLesson);
    }

    private boolean checkExist(List<Lesson> temp, Lesson lessonToCheck) {
        for (Lesson lesson : temp) {
            if (lesson.getLessonType().equals(lessonToCheck.getLessonType())) {
                return true;
            }
        }
        return false;
    }

    public String getModuleDetails() {
        StringBuilder details = new StringBuilder(this.getModuleCode() + ": " + this.getModuleName() + "\n");

        for (Lesson lesson : getAllAttending()) {
            details.append("     [").append(lesson.getLessonType()).append("] ").append(lesson.getDay()).append("   ")
                    .append(convertTime(lesson.getStartTime())).append(" - ")
                    .append(convertTime(lesson.getEndTime())).append("\n");
        }
        return details.toString();
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
        for (Lesson lesson : getAllAttending()) {
            list.append(index).append(". ").append(lesson.getLessonType()).append("     ");
            index += 1;
        }
        return list.toString();
    }

    public int getLessonTypeLength() {
        return getAllAttending().size();
    }

    @Override
    public String toString() {
        return moduleCode + " : " + moduleName;
    }

    public String getTypeOfLessonFromIndex(int index) {
        assert index >= 0 : "index should be within range";
        assert index < getAllAttending().size() : "index should be within range";

        return getAllAttending().get(index).getLessonType();
    }

    public String getListOfLessonReplacements(String targetType) {
        StringBuilder details = new StringBuilder();
        int index = 1;
        for (Lesson lesson : lessons) {
            if (lesson.getLessonType().equals(targetType)) {
                details.append(index).append(". [").append(lesson.getLessonType()).append("] ")
                        .append(lesson.getDay()).append("   ")
                        .append(convertTime(lesson.getStartTime())).append(" - ")
                        .append(convertTime(lesson.getEndTime())).append("\n");
                index += 1;
            }
        }
        return details.toString();
    }

    public int getNumberOfReplacements(String targetType) {
        int counter = 0;
        for (Lesson lesson : lessons) {
            if (lesson.getLessonType().equals(targetType)) {
                counter += 1;
            }
        }
        return counter;
    }

    public Lesson getReplacement(String targetType, int index) {
        assert index >= 0 : "index should be within range";
        assert index < lessons.size() : "index should be within range";

        int counter = 0;
        for (Lesson lesson : lessons) {
            if (lesson.getLessonType().equals(targetType)) {
                counter += 1;
            }
            if (counter == index) {
                return lesson;
            }
        }

        lgr.severe("lesson replacement fail!");
        assert false : "lesson index should be found and returned before this";
        return null;
    }

    public void replaceNewAttending(LinkedHashMap<String, LinkedHashMap<String, ArrayList<Lesson>>> loadedLessons) {
        this.allAttending = loadedLessons;
    }

    public void replaceAttending(Lesson newLesson, Integer indexForLesson) {
        getAllAttending().set(indexForLesson, newLesson);
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
}
