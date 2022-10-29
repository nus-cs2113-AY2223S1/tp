package seedu.duke.module.lessons;

public class Lesson {
    private String day;
    private String startTime;
    private String endTime;
    private String lessonType;
    private String classNumber;
    private String duplicateIndex;

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

    public Lesson(String day, String startTime, String endTime, String lessonType, String classNumber) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lessonType = lessonType;
        this.classNumber = classNumber;
    }
}
