package seedu.duke.module.lessons;

public class Lecture extends Lesson {
    private String isWebLecture = "Undetermined";

    public void setIsWebLecture(String isWebLecture) {
        this.isWebLecture = isWebLecture;
    }

    public String getIsWebLecture() {
        return isWebLecture;
    }

    public Lecture(String day, String startTime, String endTime, String lessonType) {
        super(day, startTime, endTime, lessonType);
    }
}
