package seedu.duke.model;

import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Semester data contains all the module information pertaining to a single semester.
 * Properties are all "public final" as they are not meant to be freely accessed but not modified.
 * Based off https://github.com/nusmodifications/nusmods/blob/master/scrapers/nus-v2/src/types/modules.ts
 */
public class SemesterData {
    public final int semester;
    public final List<RawLesson> timetable;
    public final String examDate;
    public final int examDuration;
    public final Set<LessonType> lessonTypes;

    /**
     * Constructor for SemesterData. Should only be used by ModuleLoader.
     * @param semester Semester
     * @param timetable Timetable
     * @param examDate Examination date
     * @param examDuration Examination duration
     */
    SemesterData(int semester, List<RawLesson> timetable, String examDate, int examDuration) {
        this.semester = semester;
        this.timetable = timetable;
        this.examDate = examDate;
        this.examDuration = examDuration;
        this.lessonTypes = getLessonTypes();
    }

    /**
     * Gets all lesson types applicable for this offering.
     * @return A set of lesson types
     */
    public Set<LessonType> getLessonTypes() {
        Set<LessonType> set = new TreeSet<>();
        for (RawLesson lesson : timetable) {
            set.add(lesson.lessonType);
        }
        return set;
    }

    /**
     * Gets all lessons corresponding to a given lesson type and class number.
     * @param type Lesson type
     * @param no Class number
     * @return A list of RawLesson objects
     */
    public List<RawLesson> getLessonsByTypeAndNo(LessonType type, String no) {
        return timetable.stream().filter(lesson -> lesson.lessonType == type && lesson.classNo.equals(no))
                .collect(Collectors.toList());
    }
    
    /**
     * Gets all class numbers corresponding to a given lesson type. This class number is used to select lesson slots.
     * @param type Lesson type
     * @return A set of class numbers
     */
    public Set<String> getClassNosByType(LessonType type) {
        return timetable.stream().filter(lesson -> lesson.lessonType == type).map(s -> s.classNo)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
