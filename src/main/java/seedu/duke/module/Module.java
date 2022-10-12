package seedu.duke.module;

import seedu.duke.module.lessons.Lesson;
import seedu.duke.module.lessons.Lecture;
import seedu.duke.module.lessons.Tutorial;
import seedu.duke.data.DataManager;
import seedu.duke.module.lessons.Laboratory;
import seedu.duke.module.lessons.Others;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Module {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String moduleName;
    private String moduleCode;
    private String moduleDescription;
    private List<Lesson> lessons;
    private List<Lesson> attending;

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public List<Lesson> getAttending(){
        return attending;
    }

    public List<Lesson> getLessons(){
        return lessons;
    }

    public Module(String moduleCode, String moduleName, String moduleDescription, List<Lesson> lessons) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.lessons = lessons;
        this.attending = matchLessonTypes(lessons);
    }

    private List<Lesson> matchLessonTypes(List<Lesson> lessons) {
        List<Lesson> temp = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (!checkExist(temp, lesson)) {
                addToAttendingList(temp, lesson);
            }
        }
        return temp;
    }

    private void addToAttendingList(List<Lesson> temp, Lesson lesson) {
        String day = "Undetermined Day";
        String startTime = "Undetermined";
        String endTime = "Undetermined";
        switch (lesson.getLessonType()) {
        case "Lecture":
            Lecture tempLecture = new Lecture(day, startTime, endTime, "Lecture");
            temp.add(tempLecture);
            if (!DataManager.attendingExists(tempLecture, moduleCode)) {
                DataManager.addAttending(tempLecture, moduleCode);
            }
            break;
        case "Tutorial":
            Tutorial tempTutorial = new Tutorial(day, startTime, endTime, "Tutorial");
            temp.add(tempTutorial);
            if (!DataManager.attendingExists(tempTutorial, moduleCode)) {
                DataManager.addAttending(tempTutorial, moduleCode);
            }
            break;
        case "Laboratory":
            Laboratory tempLaboratory = new Laboratory(day, startTime, endTime, "Laboratory");
            temp.add(tempLaboratory);
            if (!DataManager.attendingExists(tempLaboratory, moduleCode)) {
                DataManager.addAttending(tempLaboratory, moduleCode);
            }
            break;
        default:
            Others tempOthers = new Others(day, startTime, endTime, "Others");
            temp.add(tempOthers);
            if (!DataManager.attendingExists(tempOthers, moduleCode)) {
                DataManager.addAttending(tempOthers, moduleCode);
            }
            break;
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

    public void replaceAttending(Lesson newLesson) {
        int indexToRemove = 0;
        for (Lesson lesson : attending) {
            if (lesson.getLessonType().equals(newLesson.getLessonType())) {
                break;
            }
            indexToRemove += 1;
        }
        attending.remove(indexToRemove);
        attending.add(newLesson);
        DataManager.setAttending(newLesson, moduleCode);
    }
}
