package seedu.duke.model;

import java.util.List;

public class RawLesson {
    public final String classNo;
    public final Day day;
    public final String endTime;
    public final String startTime;
    public final String lessonType;
    public final String venue;
    public final List<Integer> weeks;
    public final int size;

    RawLesson(String classNo, Day day, String endTime, String startTime, String lessonType, String venue,
            List<Integer> weeks, int size) {
        this.classNo = classNo;
        this.day = day;
        this.endTime = endTime;
        this.startTime = startTime;
        this.lessonType = lessonType;
        this.venue = venue;
        this.weeks = weeks;
        this.size = size;

    }

}
