package seedu.duke.module.lessons;

public abstract class Lesson {
    private String day;
    private String startTime;
    private String endTime;
    private String lessonType;

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

    public Lesson(String day, String startTime, String endTime, String lessonType) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lessonType = lessonType;
    }
}
