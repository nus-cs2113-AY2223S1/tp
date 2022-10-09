package seedu.duke.model;

import java.util.*;
import java.util.stream.Collectors;

public class SemesterData {
    public final int semester;
    public final List<RawLesson> timetable;
    public final String examDate;
    public final int examDuration;
    public final Set<LessonType> lessonTypes;
    public final Map<LessonType, List<RawLesson>> availableSlots;

    SemesterData(int semester, List<RawLesson> timetable, String examDate, int examDuration) {
        this.semester = semester;
        this.timetable = timetable;
        this.examDate = examDate;
        this.examDuration = examDuration;
        this.lessonTypes = getLessonTypes();
        this.availableSlots = getAvailableSlots();
    }

    public Set<LessonType> getLessonTypes() {
        Set<LessonType> set = new TreeSet<>();
        for (RawLesson lesson : timetable) {
            set.add(lesson.lessonType);
        }
        return set;
    }

    public Map<LessonType, List<RawLesson>> getAvailableSlots(){
        Map<LessonType, List<RawLesson>> map = new HashMap<>();
        for(LessonType lessonType: lessonTypes) {
            map.put(lessonType, getLessonsByType(lessonType));
        }
        return map;
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
