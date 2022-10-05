package seedu.duke.model;

import java.util.List;

public class SemesterData {
    public final int semester;
    public final List<RawLesson> timetable;
    public final String examDate;
    public final int examDuration;

    SemesterData(int semester, List<RawLesson> timetable, String examDate, int examDuration) {
        this.semester = semester;
        this.timetable = timetable;
        this.examDate = examDate;
        this.examDuration = examDuration;
    }
}
