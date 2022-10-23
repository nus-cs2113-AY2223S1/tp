package seedu.duke.module;

import seedu.duke.module.lessons.Lesson;
import seedu.duke.data.AttendingManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


public class Module {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String moduleName;
    private String moduleCode;
    private String moduleDescription;
    private List<Lesson> lessons;
    private List<Lesson> attending;
    private HashMap<String, ArrayList<Lesson>> classifiedLessons;

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public List<Lesson> getAttending() {
        return attending;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Module(String moduleCode, String moduleName, String moduleDescription, List<Lesson> lessons) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.lessons = lessons;
        this.classifiedLessons = classifyLessons(lessons);
        this.attending = matchLessonTypes(classifiedLessons);
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

    private void addToAttendingList(List<Lesson> temp, String lessonType) {
        String day = "Undetermined Day";
        String startTime = "Undetermined";
        String endTime = "Undetermined";
        String classNumber = "NA";
        Lesson tempLesson = new Lesson(day, startTime, endTime, lessonType, classNumber);
        temp.add(tempLesson);
        if (!AttendingManager.attendingExists(tempLesson, moduleCode)) {
            AttendingManager.addAttending(tempLesson, this);
        }
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

        for (Lesson lesson : attending) {
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
        for (Lesson lesson : attending) {
            list.append(index).append(". ").append(lesson.getLessonType()).append("     ");
            index += 1;
        }
        return list.toString();
    }

    public int getLessonTypeLength() {
        return attending.size();
    }

    @Override
    public String toString() {
        return moduleCode + " : " + moduleName;
    }

    public String getTypeOfLessonFromIndex(int index) {
        assert index >= 0 : "index should be within range";
        assert index < attending.size() : "index should be within range";

        return attending.get(index).getLessonType();
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

    public void replaceAttending(Lesson newLesson, Integer indexForLesson) {
        attending.set(indexForLesson, newLesson);
        AttendingManager.setAttending(newLesson, moduleCode);
    }

    private HashMap<String, ArrayList<Lesson>> classifyLessons(List<Lesson> lessons) {
        HashMap<String, ArrayList<Lesson>> classifiedLessons = new HashMap<>();
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
}
