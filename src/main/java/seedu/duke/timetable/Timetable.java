package seedu.duke.timetable;

import seedu.duke.exceptions.InvalidLessonDayException;
import seedu.duke.exceptions.InvalidTimeFormatException;
import seedu.duke.exceptions.InvalidTimingException;
import seedu.duke.exceptions.LessonNotFoundException;
import seedu.duke.exceptions.TimetableClashException;
import seedu.duke.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Timetable {
    private HashMap<String, ArrayList<Lesson>> userTimetable;

    public Timetable() {
        userTimetable = new HashMap<String, ArrayList<Lesson>>();
        ArrayList<Lesson> mondayList = new ArrayList<Lesson>();
        userTimetable.put("monday", mondayList);
        ArrayList<Lesson> tuesdayList = new ArrayList<Lesson>();
        userTimetable.put("tuesday", tuesdayList);
        ArrayList<Lesson> wednesdayList = new ArrayList<Lesson>();
        userTimetable.put("wednesday", wednesdayList);
        ArrayList<Lesson> thursdayList = new ArrayList<Lesson>();
        userTimetable.put("thursday", thursdayList);
        ArrayList<Lesson> fridayList = new ArrayList<Lesson>();
        userTimetable.put("friday", fridayList);
        ArrayList<Lesson> saturdayList = new ArrayList<Lesson>();
        userTimetable.put("saturday", saturdayList);
        ArrayList<Lesson> sundayList = new ArrayList<Lesson>();
        userTimetable.put("sunday", sundayList);
    }

    public HashMap<String, ArrayList<Lesson>> getTimetable() {
        return userTimetable;
    }

    /**
     * Checks if the day for a lesson is valid.
     *
     * @param lesson The lesson to be validated.
     * @return True if the day for a lesson is a valid day of the week.
     * @throws InvalidLessonDayException if the input day for a lesson is invalid.
     */
    public static boolean isValidDay(Lesson lesson) throws InvalidLessonDayException {
        String day = lesson.getDay();
        if (day.equals("monday") || day.equals("tuesday") || day.equals("wednesday")
                || day.equals("thursday") || day.equals("friday") || day.equals("saturday") || day.equals("sunday")) {
            return true;
        } else {
            throw new InvalidLessonDayException("Please enter a day from Monday to Sunday.");
        }
    }

    /**
     * Checks if the input end time for a lesson is in valid 24-hour format.
     * e.g. 13:05 is valid but 10:60 and 24:00 is invalid
     *
     * @param lesson The lesson to be validated.
     * @return True if the input start time for a lesson is valid.
     * @throws InvalidTimeFormatException if the input start time for a lesson is invalid.
     */
    public static boolean isValidStartTime(Lesson lesson) throws InvalidTimeFormatException {
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(lesson.getStartTime());
        if (m.matches()) {
            return true;
        } else {
            throw new InvalidTimeFormatException("Invalid time format entered!");
        }
    }

    /**
     * Checks if the input end time for a lesson is in valid 24-hour format.
     * e.g. 13:05 is valid but 10:60 and 24:00 is invalid
     *
     * @param lesson The lesson to be validated.
     * @return True if the input end time for a lesson is valid.
     * @throws InvalidTimeFormatException if the input end time for a lesson is invalid.
     */
    public static boolean isValidEndTime(Lesson lesson) throws InvalidTimeFormatException {
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(lesson.getEndTime());
        if (m.matches()) {
            return true;
        } else {
            throw new InvalidTimeFormatException("Invalid time format entered!");
        }
    }

    /**
     * Checks if the time for a lesson is valid in terms of chronology.
     * e.g. 09:00 to 10:00 is valid but 09:00 to 09:00 and 10:00 to 09:00 is invalid
     *
     * @param lesson The lesson to be validated.
     * @return True if the input time for a lesson is valid.
     * @throws InvalidTimingException if the input time for a lesson is invalid.
     */
    public static boolean isValidTiming(Lesson lesson) throws InvalidTimingException, ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date startTime = parser.parse(lesson.getStartTime());
        Date endTime = parser.parse(lesson.getEndTime());
        if (startTime.before(endTime)) {
            return true;
        } else {
            throw new InvalidTimingException("Invalid timing entered!");
        }
    }

    /**
     * Checks if attempting to add a lesson results in a timetable clash.
     *
     * @param lesson The lesson that the user is attempting to add to a timetable.
     * @return False if there is no timetable clash
     * @throws TimetableClashException if there is a timetable clash
     * @throws ParseException if there is an invalid time format parsed
     */
    public boolean hasNoTimetableClash(Lesson lesson) throws TimetableClashException, ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String day = lesson.getDay();
        Date newStartTime = parser.parse(lesson.getStartTime());
        Date newEndTime = parser.parse(lesson.getEndTime());
        for (Lesson currentLesson : userTimetable.get(day)) {
            Date currentStartTime = parser.parse(currentLesson.getStartTime());
            Date currentEndTime = parser.parse(currentLesson.getEndTime());
            boolean hasNoClash =
                    (newStartTime.compareTo(currentStartTime) <= 0 && newEndTime.compareTo(currentStartTime) <= 0)
                    || newStartTime.compareTo(currentEndTime) >= 0 && newEndTime.compareTo(currentEndTime) >= 0;
            if (!hasNoClash) {
                throw new TimetableClashException("Error: There is a timetable clash!");
            }
        }
        return true;
    }

    /**
     * Adds a lesson to the timetable based on day.
     *
     * @param newLesson The new lesson to be added to the timetable.
     * @param isLoadFromFile Boolean variable to indicate if lesson is being loaded from user's saved file.
     */
    public void addLesson(Lesson newLesson, boolean isLoadFromFile) {
        try {
            if (isValidDay(newLesson) && isValidStartTime(newLesson) && isValidEndTime(newLesson)
                    && isValidTiming(newLesson) && hasNoTimetableClash(newLesson)) {
                userTimetable.get(newLesson.getDay()).add(newLesson);
                if (!isLoadFromFile) {
                    System.out.print(Ui.printLessonAddedAcknowledgement(newLesson));
                }
                Collections.sort(userTimetable.get(newLesson.getDay()), (l1, l2) -> {
                    try {
                        return new SimpleDateFormat("HH:mm").parse(l1.getStartTime())
                                .compareTo(new SimpleDateFormat("HH:mm").parse(l2.getStartTime()));
                    } catch (ParseException e) {
                        return 0;
                    }
                });
            }
        } catch (InvalidLessonDayException | InvalidTimeFormatException | InvalidTimingException
                 | TimetableClashException | ParseException e) {
            Ui.printExceptionMessage(e);
        }
    }

    /**
     * Deletes a lesson from the timetable based on day.
     *
     * @param oldLesson The old lesson to be deleted from the timetable.
     * @throws LessonNotFoundException if the lesson to be deleted is not stored in the timetable.
     */
    public void deleteLesson(Lesson oldLesson) throws LessonNotFoundException {
        if (!(userTimetable.get(oldLesson.getDay()).contains(oldLesson))) {
            throw new LessonNotFoundException("Lesson not found in timetable.");
        }
        userTimetable.get(oldLesson.getDay()).remove(oldLesson);
        System.out.print(Ui.printLessonDeletedAcknowledgement(oldLesson));
    }

    /**
     * Deletes a lesson from the timetable based on the module code.
     *
     * @param moduleCode The code of the module to be deleted.
     */
    public void deleteLessonByCode(String moduleCode) {
        HashMap<String, ArrayList<Lesson>> updatedUserTimetable = new HashMap<>();
        for (HashMap.Entry<String, ArrayList<Lesson>> dayAndLessons : userTimetable.entrySet()) {
            ArrayList<Lesson> updatedLessons = new ArrayList<>();
            for (Lesson lesson : dayAndLessons.getValue()) {
                if (!lesson.getCode().equals(moduleCode)) {
                    updatedLessons.add(lesson);
                }
            }
            updatedUserTimetable.put(dayAndLessons.getKey(),updatedLessons);
        }
        userTimetable = updatedUserTimetable;
    }
}
