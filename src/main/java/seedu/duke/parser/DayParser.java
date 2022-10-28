package seedu.duke.parser;

import seedu.duke.model.Day;

public class DayParser {
    public static Day parse(String day) {
        day = day.toLowerCase();
        if (day.startsWith("mon")) {
            return Day.MONDAY;
        }
        if (day.startsWith("tues")) {
            return Day.TUESDAY;
        }
        if (day.startsWith("wed")) {
            return Day.WEDNESDAY;
        }
        if (day.startsWith("thur")) {
            return Day.THURSDAY;
        }
        if (day.startsWith("fri")) {
            return Day.FRIDAY;
        }
        if (day.startsWith("sat")) {
            return Day.SATURDAY;
        }
        if (day.startsWith("sun")) {
            return Day.SUNDAY;
        }
        throw new RuntimeException("Day format invalid");
    }
}
