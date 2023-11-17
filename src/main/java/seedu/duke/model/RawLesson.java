package seedu.duke.model;

import java.util.List;

/**
 * A RawLesson is a single block in a timetable representing one lesson slot. 
 * Note that a classNo may correspond to more than one RawLesson objects.
 * Properties are all "public final" as they are not meant to be freely accessed but not modified.
 * Based off https://github.com/nusmodifications/nusmods/blob/master/scrapers/nus-v2/src/types/modules.ts
 */
public class RawLesson {
    /**
     * Class number.
     */
    public final String classNo;
    /**
     * Day the class is on.
     */
    public final Day day;
    /**
     * End time, formatted as a 4-digit 24h timestamp e.g. 1200.
     */
    public final String endTime;
    /**
     * Start time, formatted as a 4-digit 24h timestamp e.g. 1200.
     */
    public final String startTime;
    /**
     * Lesson type.
     */
    public final LessonType lessonType;
    /**
     * Lesson venue.
     */
    public final String venue;
    /**
     * List of weeks the lesson is on.
     */
    public final List<Integer> weeks;
    /**
     * Class size.
     */
    public final int size;

    /**
     * Constructs a RawLesson. Should be only used by ModuleLoader.
     * @param classNo Class number
     * @param day Day
     * @param endTime End time
     * @param startTime Start time
     * @param lessonType Lesson type
     * @param venue Venue
     * @param weeks Weeks
     * @param size Class size
     */
    RawLesson(String classNo, Day day, String endTime, String startTime, LessonType lessonType, String venue,
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
