package seedu.duke.module.lessons;

import java.util.List;
import java.util.ArrayList;

public class Lesson {
    private String day;
    private String startTime;
    private String endTime;
    private String lessonType;
    private String classNumber;
    private String moduleCode;

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

    public List<String> getInfo() {
        List<String> info = new ArrayList<String>();
        info.add(day);
        info.add(startTime);
        info.add(endTime);
        info.add(lessonType);
        info.add(moduleCode);
        return info;
    }

    public Lesson(String day, String startTime, String endTime, String lessonType,
                 String classNumber, String moduleCode) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lessonType = lessonType;
        this.classNumber = classNumber;
        this.moduleCode = moduleCode;
    }
}
