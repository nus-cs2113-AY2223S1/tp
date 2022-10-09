package seedu.duke.module.lessons;

public class Lesson {
    private String day;
    private String startTime;
    private String endTime;
    private Boolean isAttending = false;

    public Boolean getAttending() {
        return isAttending;
    }

    public void setAttending(Boolean attending) {
        isAttending = attending;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Lesson(String day, String startTime, String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
