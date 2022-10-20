package seedu.duke.timetable;

import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidTimeFormatException;
import seedu.duke.module.Module;
import seedu.duke.university.University;

public class Lesson extends Module {
    private String day;
    private String startTime;
    private String endTime;

    public Lesson(String code, String title, String credit, University university, String day, String startTime,
                  String endTime) throws InvalidModuleException {
        super(code, title, credit, university);
        this.day = day.toLowerCase();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    boolean isValidDay() {
        return day.equals("monday") || day.equals("tuesday") || day.equals("wednesday")
                || day.equals("thursday") || day.equals("friday");
    }

    boolean isValidStartTime() throws InvalidTimeFormatException {
        try {
            int startTimeAsInt = Integer.parseInt(startTime);
            boolean withinTimeRange = startTimeAsInt >= 0 && startTimeAsInt <= 2359;
            if (withinTimeRange) {
                return true;
            }
        } catch (NumberFormatException e) {
            throw new InvalidTimeFormatException("Invalid time format entered!");
        }
        return false;
    }

    boolean isValidEndTime() throws InvalidTimeFormatException {
        try {
            int endTimeAsInt = Integer.parseInt(endTime);
            boolean withinTimeRange = endTimeAsInt >= 0 && endTimeAsInt <= 2359;
            if (withinTimeRange) {
                return true;
            }
        } catch (NumberFormatException e) {
            throw new InvalidTimeFormatException("Invalid time format entered!");
        }
        return false;
    }
}
