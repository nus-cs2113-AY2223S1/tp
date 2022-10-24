package seedu.duke.timetable;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.InvalidLessonDayException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidTimeFormatException;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.exceptions.TimetableClashException;
import seedu.duke.exceptions.LessonNotFoundException;
import seedu.duke.ui.Ui;
import seedu.duke.university.University;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimetableTest {
    @Test
    void isValidDay_inputWeekend_expectException() throws InvalidUniversityException, InvalidModuleException {
        University university = new University("Stanford University", "USA");
        Lesson dummyLesson = new Lesson("CS229", "Machine Learning", "5", university,"Sunday", "11:00", "13:00");
        assertThrows(InvalidLessonDayException.class, () -> Timetable.isValidDay(dummyLesson));
    }

    @Test
    void isValidDay_inputInvalidDay_expectException() throws InvalidUniversityException, InvalidModuleException {
        University university = new University("Stanford University", "USA");
        Lesson dummyLesson = new Lesson("CS229", "Machine Learning", "5", university,"tuesday1234", "11:00", "13:00");
        assertThrows(InvalidLessonDayException.class, () -> Timetable.isValidDay(dummyLesson));
    }

    @Test
    void isValidStartTime_inputInvalidMinutes_expectException() throws InvalidUniversityException,
            InvalidModuleException {
        University university = new University("Stanford University", "USA");
        Lesson dummyLesson = new Lesson("CS229", "Machine Learning", "5", university,"Sunday", "11:60", "13:00");
        assertThrows(InvalidTimeFormatException.class, () -> Timetable.isValidStartTime(dummyLesson));
    }

    @Test
    void isValidEndTime_inputInvalidHour_expectException() throws InvalidUniversityException, InvalidModuleException {
        University university = new University("Stanford University", "USA");
        Lesson dummyLesson = new Lesson("CS229", "Machine Learning", "5", university,"Sunday", "11:00", "25:00");
        assertThrows(InvalidTimeFormatException.class, () -> Timetable.isValidEndTime(dummyLesson));
    }

    @Test
    void isValidTiming_startTimeEqualsEndTime_expectException() throws InvalidUniversityException,
            InvalidModuleException {
        University university = new University("Stanford University", "USA");
        Lesson dummyLesson = new Lesson("CS229", "Machine Learning", "5", university,"Sunday", "11:00", "11:00");
        assertThrows(InvalidTimeFormatException.class, () -> Timetable.isValidTiming(dummyLesson));
    }

    @Test
    void isValidTiming_startTimeAfterEndTime_expectException() throws InvalidUniversityException,
            InvalidModuleException {
        University university = new University("Stanford University", "USA");
        Lesson dummyLesson = new Lesson("CS229", "Machine Learning", "5", university,"Sunday", "11:00", "10:00");
        assertThrows(InvalidTimeFormatException.class, () -> Timetable.isValidTiming(dummyLesson));
    }

    @Test
    void hasNoTimetableClash_startsBeforeCurrentLessonEnds_expectException() throws InvalidUniversityException,
            InvalidModuleException {
        Timetable timetable = new Timetable();
        University university = new University("Stanford University", "USA");
        Lesson existingLesson = new Lesson("CS229", "Machine Learning", "5", university,"Tuesday", "09:00", "11:00");
        timetable.addLesson(existingLesson, false);
        Lesson newLesson = new Lesson("CS161", "Algorithms", "5", university,"Tuesday", "10:00", "12:00");
        assertThrows(TimetableClashException.class, () -> timetable.hasNoTimetableClash(newLesson));
    }

    @Test
    void hasNoTimetableClash_endsAfterCurrentLessonStarts_expectException() throws InvalidUniversityException,
            InvalidModuleException {
        Timetable timetable = new Timetable();
        University university = new University("Stanford University", "USA");
        Lesson existingLesson = new Lesson("CS229", "Machine Learning", "5", university,"Tuesday", "11:00", "13:00");
        timetable.addLesson(existingLesson, false);
        Lesson newLesson = new Lesson("CS161", "Algorithms", "5", university,"Tuesday", "10:00", "12:00");
        assertThrows(TimetableClashException.class, () -> timetable.hasNoTimetableClash(newLesson));
    }

    @Test
    void addLesson_successfulAddition_listInSortedOrder() throws InvalidUniversityException, InvalidModuleException {
        Timetable timetable = new Timetable();
        University university = new University("Stanford University", "USA");
        Lesson existingLesson = new Lesson("CS229", "Machine Learning", "5", university,"Tuesday", "11:00", "13:00");
        timetable.addLesson(existingLesson, false);
        Lesson newLesson = new Lesson("CS161", "Algorithms", "5", university,"Tuesday", "09:00", "10:00");
        timetable.addLesson(newLesson, false);
        Lesson newLessonTwo = new Lesson("CS162", "Algorithms 2", "5", university,"Tuesday", "07:00", "09:00");
        timetable.addLesson(newLessonTwo, false);
        System.out.println(Ui.printLessonsByDayInTimetable(timetable.getTimetable().get("tuesday")));
    }

    @Test
    void deleteLesson_deleteNonExistentLesson_expectException() throws InvalidUniversityException,
            InvalidModuleException {
        Timetable timetable = new Timetable();
        University university = new University("Stanford University", "USA");
        Lesson dummyLesson = new Lesson("CS229", "Machine Learning", "5", university,"Tuesday", "11:00", "13:00");
        assertThrows(LessonNotFoundException.class, () -> timetable.deleteLesson(dummyLesson));
    }
}
