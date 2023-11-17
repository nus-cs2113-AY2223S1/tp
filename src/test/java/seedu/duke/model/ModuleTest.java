package seedu.duke.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void moduleLoading_loadModules_numberLoaded() {
        assertEquals(13967, Module.getAll().size());
    }

    @Test
    public void moduleLoading_cs1010s_correctDetails() {
        Module mod = Module.get("CS1010S");
        assertEquals("CS1010S", mod.moduleCode);
        assertEquals("Programming Methodology", mod.title);
        assertEquals(List.of(2, 1, 1, 3, 3), mod.workload);
        assertEquals(2, mod.semesterData.size());
        assertEquals("Computer Science", mod.department);
        assertEquals("Computing", mod.faculty);
    }

    @Test
    public void moduleLoading_cs1010s_correctSemesterDetails() {
        Module mod = Module.get("CS1010S");
        assertNotNull(mod.getSemesterData(1));
        assertNotNull(mod.getSemesterData(2));
        SemesterData s1 = mod.getSemesterData(1);
        List<RawLesson> timetable = s1.timetable;
        List<RawLesson> lectures = timetable.stream().filter(s -> s.lessonType == LessonType.LECTURE)
                .collect(Collectors.toList());
        assertEquals(2, lectures.size());
        assertEquals(Day.WEDNESDAY, lectures.get(0).day);
        assertEquals("1000", lectures.get(0).startTime);
        assertEquals("1200", lectures.get(0).endTime);
        List<RawLesson> tutorials = timetable.stream().filter(s -> s.lessonType == LessonType.TUTORIAL)
                .collect(Collectors.toList());
        assertEquals(60, tutorials.size());
        List<RawLesson> recitations = timetable.stream().filter(s -> s.lessonType == LessonType.RECITATION)
                .collect(Collectors.toList());
        assertEquals(12, recitations.size());
    }

    @Test
    public void module_offeredInSemester_isCorrect() {
        Module module = Module.get("CS2113");
        assertTrue(module.isOfferedInSemester(1));
        assertTrue(module.isOfferedInSemester(2));
        assertFalse(module.isOfferedInSemester(3));
        assertFalse(module.isOfferedInSemester(4));
    }

    @Test
    public void module_getLevel_computedCorrectly() {
        assertEquals(2, Module.get("CS2113").getLevel());
        assertEquals(3, Module.get("YSC3209").getLevel());
    }

}
