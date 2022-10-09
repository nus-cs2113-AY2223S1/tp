package seedu.duke.model;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    public Set<LessonType> getLessonTypes() {
        Set<LessonType> set = new TreeSet<>();
        for (RawLesson lesson : timetable) {
            set.add(lesson.lessonType);
        }
        return set;
    }

    public List<RawLesson> getLessonsByTypeAndNo(LessonType type, String no) {
        return timetable.stream().filter(lesson -> lesson.lessonType == type && lesson.classNo.equals(no))
                .collect(Collectors.toList());
    }

    public List<RawLesson> getLessonsByNo(String no) {
        return timetable.stream().filter(lesson -> lesson.classNo.equals(no)).collect(Collectors.toList());
    }

    public Set<String> getClassNosByType(LessonType type) {
        return timetable.stream().filter(lesson -> lesson.lessonType == type).map(s -> s.classNo)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public List<RawLesson> getLessonsByType(LessonType type) {
        return timetable.stream().filter(lesson -> lesson.lessonType == type).collect(Collectors.toList());
    }
}
