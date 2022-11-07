package seedu.duke.module.lessons;

import java.util.List;
import java.util.ArrayList;

/**
 * Class to represent a lesson object.
 */
public class Lesson {
    private String day;
    private String startTime;
    private String endTime;
    private String lessonType;
    private String classNumber;
    private String moduleCode;
    private String weeks;

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLessonType() {
        return lessonType;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getWeeks() {
        return weeks;
    }

    /**
     * Gets a list of information about a certain lesson.
     *
     * @return The list of information.
     */
    public List<String> getInfo() {
        List<String> info = new ArrayList<String>();
        info.add(day);
        info.add(startTime);
        info.add(endTime);
        info.add(lessonType);
        info.add(moduleCode);
        return info;
    }

    /**
     * Constructor for a lesson object.
     *
     * @param day The day the lesson is conducted.
     * @param startTime Start time of the lesson.
     * @param endTime End time of the lesson.
     * @param lessonType The type of lesson that this lesson belongs in.
     * @param classNumber The class number the lesson is grouped under.
     * @param weeks The weeks that the lesson is conducted in.
     * @param moduleCode The module code that this lesson originates from.
     */
    public Lesson(String day, String startTime, String endTime, String lessonType,
                 String classNumber, String weeks, String moduleCode) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lessonType = lessonType;
        this.classNumber = classNumber;
        this.weeks = weeks;
        this.moduleCode = moduleCode;
    }
}
