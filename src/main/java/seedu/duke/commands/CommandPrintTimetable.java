package seedu.duke.commands;

import seedu.duke.Timetable;
import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;

import java.util.List;
import java.util.Objects;

public class CommandPrintTimetable {

    public static final String blankTimeSlot = "______|";
    public static final String clashedTimeSlot = "XXXXXX|";

    public static final String[] daysInWeek = {"MON |", "TUE |", "WED |", "THU |", "FRI |"};

    public static String printTimetable() {
        String[][] timeline = createTimeline();
        populateTimeline(timeline, Timetable.getListOfModules());
        return convertToString(timeline);
    }

    private static void populateTimeline(String[][] timeline, List<Module> listOfModules) {
        for (Module module : listOfModules) {
            List<Lesson> lessons = module.getAttending();
            String name = module.getModuleCode();
            for (Lesson les : lessons) {
                int[] info = convertTimeToIndex(les.getDay(), les.getStartTime(), les.getEndTime());
                if (info[0] == -1) {
                    continue;
                }
                addToTimeline(timeline, name, info);
            }
        }
    }

    private static void addToTimeline(String[][] timeline, String name, int[] info) {
        for (int i = info[1]; i <= info[2]; i++) {
            if (!Objects.equals(timeline[info[0]][i], blankTimeSlot)) {
                timeline[info[0]][i] = clashedTimeSlot;
                continue;
            }
            timeline[info[0]][i] = name.substring(0, Math.min(name.length(), 6)) + "|";
        }
    }

    private static int[] convertTimeToIndex(String day, String startTime, String endTime) {
        int[] info = new int[3];

        if (isInvalidTimings(startTime, endTime)) {
            info[0] = -1;
            return info;
        }

        info[0] = determineDay(day);
        info[1] = ((Integer.parseInt(startTime.replaceFirst("^0+(?!$)", "")) - 770) / 50);
        info[2] = ((Integer.parseInt(endTime.replaceFirst("^0+(?!$)", "")) - 770) / 50) - 1;

        return info;
    }

    private static boolean isInvalidTimings(String startTime, String endTime) {
        return (Objects.equals(startTime, "Undetermined") || Objects.equals(endTime, "Undetermined")
                || Integer.parseInt(startTime) > 2000 || Integer.parseInt(startTime) < 800
                || Integer.parseInt(endTime) > 2000 || Integer.parseInt(endTime) < 800);
    }

    private static int determineDay(String day) {
        switch (day) {
        case "Monday":
            return 0;
        case "Tuesday":
            return 1;
        case "Wednesday":
            return 2;
        case "Thursday":
            return 3;
        case "Friday":
            return 4;
        default:
            return -1;
        }
    }

    private static String convertToString(String[][] timeline) {
        StringBuilder timetable = new StringBuilder();
        timetable.append("    |");

        boolean isHalfHour = true;
        int time = 800;
        while (time <= 1930) {
            timetable.append(getStringFromTime(time));
            if (isHalfHour) {
                time += 30;
                isHalfHour = false;
            } else {
                time += 70;
                isHalfHour = true;
            }
        }
        timetable.append('\n');

        return addRemarks(addModuleInfo(timetable, timeline));
    }

    private static String addRemarks(StringBuilder timetable) {
        timetable.append("\n * Note that timings indicated refers to the start of "
                + "the corresponding 30 minutes timeslot.\n"
                + " * Slots with XXXXXX indicates that there is a clash between two or more lessons.\n"
                + " * Modules, if any, that start or end beyond the 8am to 8pm timings are excluded.\n"
                + " * Timings are approximated to 30 minutes block with valid assumption that "
                + "NUS mods are typically designed in such blocks.\n");
        return timetable.toString();
    }

    private static StringBuilder addModuleInfo(StringBuilder timetable, String[][] timeline) {
        for (int i = 0; i < 5; i++) {
            timetable.append(daysInWeek[i]);
            for (int j = 0; j < 24; j++) {
                timetable.append(timeline[i][j]);
            }
            timetable.append('\n');
        }
        return timetable;
    }

    private static String getStringFromTime(int time) {
        if (time < 1000) {
            return " 0" + time + " |";
        } else {
            return " " + time + " |";
        }
    }

    private static String[][] createTimeline() {
        String[][] timeline = new String[5][24];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 24; j++) {
                timeline[i][j] = blankTimeSlot;
            }
        }
        return timeline;
    }
}
