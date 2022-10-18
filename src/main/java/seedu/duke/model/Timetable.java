package seedu.duke.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.tuple.Pair;

import seedu.duke.utils.Color;
import seedu.duke.utils.ColorScheme;
import seedu.duke.utils.ConsoleBorder;

/**
 * Formats a timetable nicely.
 */
public class Timetable {

    private static final int COLUMN_WIDTH = 10;
    private static final int ROWS_PER_TIME = 1;
    private static final int HEADER_ROWS = 3;
    private static final int LEFT_PADDING = 3;
    private static final int TOP_PADDING = 1;
    private static final int BOTTOM_PADDING = 1;
    private static final int RIGHT_PADDING = 2;

    public static final String SUBSYSTEM_NAME = "timetable";

    // private boolean isStyleSimple;
    private boolean withColor;
    private int firstHour;
    private int lastHour;
    private int timeslots;
    private String[][] buffer;
    private int height;
    private int width;
    private List<Day> days;
    private List<Integer> columns;
    private List<Integer> indents;
    private List<Module> modules;
    private List<Pair<Module, RawLesson>> sortedLessons;
    private ConsoleBorder consoleBorder;
    private Logger logger;

    /**
     * Creates a timetable with default settings. Default for windows is simple style and no colour. Default
     * for other OSes is fancy style with colour.
     * @param lessons List of (Module, RawLesson) pairs.
     */
    public Timetable(List<Pair<Module, RawLesson>> lessons) {
        this(lessons, SystemUtils.IS_OS_WINDOWS ? false : true, SystemUtils.IS_OS_WINDOWS ? true : false);
    }

    /**
     * Creates a timetable with specified settings.
     * @param lessons List of (Module, RawLesson) pairs.
     * @param withColor Whether to use coloured output.
     * @param isStyleSimple Whether to use simple output.
     */
    public Timetable(List<Pair<Module, RawLesson>> lessons, boolean withColor, boolean isStyleSimple) {
        assert lessons != null : "List of lessons should not be null";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.FINE, "Creating a timetable with " + lessons.size() + " lessons");
        this.withColor = withColor;
        this.consoleBorder = ConsoleBorder.getInstance(isStyleSimple);
        this.sortedLessons = sortLessons(lessons);
        // collect a sorted list of modules
        this.modules = new ArrayList<>(lessons.stream().map(s -> s.getLeft()).collect(Collectors.toSet()));
        this.modules.sort((a, b) -> a.moduleCode.compareTo(b.moduleCode));
        // find the earliest and latest time in the schedule
        String earliest = lessons.stream().map(s -> s.getRight().startTime).min(String::compareTo).orElseThrow();
        this.firstHour = Integer.parseInt(earliest.substring(0, 2)); // round down to the hour
        String latest = lessons.stream().map(s -> s.getRight().endTime).max(String::compareTo).orElseThrow();
        this.lastHour = Integer.parseInt(latest.substring(0, 2)) + 1; // round up to next hour
        this.timeslots = (this.lastHour - this.firstHour) * 2 + 1; // every half an hour
        this.days = List.of(Day.values()).subList(0, 5); // monday to friday
        // check whether any classes need to be indented
        // classes need to be indented if their timeslots overlap
        Pair<List<Integer>, List<Integer>> res = computeIndentation(days, sortedLessons); 
        this.columns = res.getLeft();
        this.indents = res.getRight();
        int columnTotal = 1; // time column
        for (int c : columns) {
            columnTotal += c;
        }
        this.width = columnTotal * (COLUMN_WIDTH + 1) + RIGHT_PADDING;
        this.height = HEADER_ROWS + timeslots * ROWS_PER_TIME + BOTTOM_PADDING;
        this.buffer = new String[height][width];
        // write data into the buffer
        initialiseBuffer();
        writeHeader();
        writeLessons(sortedLessons, indents);
    }

    private void initialiseBuffer() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                buffer[i][j] = " ";
            }
        }
    }

    private void writeHeader() {
        // draw border below days
        for (int i = 0; i < width; i++) {
            buffer[2][i] = "=";
        }
        // left time column
        for (int i = 0; i < timeslots; i++) {
            write(indexToTime(i), HEADER_ROWS + i * ROWS_PER_TIME, LEFT_PADDING);
        }
        // write day headers
        for (int i = 0; i < days.size(); i++) {
            write(ConsoleBorder.DOTTED_CHAR + " " + dayToShortString(days.get(i)), TOP_PADDING,
                    getColumnOfDay(days.get(i)) - 1);
        }
        // draw border between day columns
        for (int i = 0; i < days.size(); i++) {
            for (int j = 0; j < timeslots * ROWS_PER_TIME; j++) {
                write("" + ConsoleBorder.DOTTED_CHAR, j + HEADER_ROWS, getColumnOfDay(days.get(i)) - 1);
            }
        }
    }

    private void writeLessons(List<Pair<Module, RawLesson>> sortedLessons, List<Integer> indents) {
        for (int i = 0; i < sortedLessons.size(); i++) {
            Pair<Module, RawLesson> lesson = sortedLessons.get(i);
            addSingleLesson(lesson.getLeft(), lesson.getRight(), indents.get(i));
        }
    }

    /**
     * Sorts lessons by earliest start time, then latest end time, then module code.
     * @param lessons List of (Module, RawLesson) pairs.
     * @return The sorted list.
     */
    private List<Pair<Module, RawLesson>> sortLessons(List<Pair<Module, RawLesson>> lessons) {
        List<Pair<Module, RawLesson>> sortedLessons = new ArrayList<>(lessons);
        sortedLessons.sort((a, b) -> {
            RawLesson la = a.getRight();
            RawLesson lb = b.getRight();
            if (!la.startTime.equals(lb.startTime)) {
                return la.startTime.compareTo(lb.startTime);
            }
            if (!la.endTime.equals(lb.endTime)) {
                return -la.endTime.compareTo(lb.endTime);
            }
            if (!a.getLeft().moduleCode.equals(b.getLeft().moduleCode)) {
                return a.getLeft().moduleCode.compareTo(b.getLeft().moduleCode);
            }
            return la.classNo.compareTo(lb.classNo);
        });
        return sortedLessons;
    }

    /**
     * Computes whether any indentation of lessons is required.
     * @param days The days.
     * @param sortedLessons The lessons, sorted.
     * @return A pair of lists. The first list has one element for each day and represents the column width 
     *         of that day. The second list has one element for each lesson and contains the indentation level 
     *         for that lesson within the day.
     */
    private Pair<List<Integer>, List<Integer>> computeIndentation(List<Day> days,
            List<Pair<Module, RawLesson>> sortedLessons) {
        List<List<List<Pair<Module, RawLesson>>>> lessonStack = new ArrayList<>();
        for (int i = 0; i < days.size(); i++) {
            lessonStack.add(new ArrayList<>());
            for (int j = 0; j < timeslots; j++) {
                lessonStack.get(i).add(new ArrayList<>());
            }
        }
        List<Integer> indents = new ArrayList<>();
        for (int i = 0; i < sortedLessons.size(); i++) {
            Pair<Module, RawLesson> lesson = sortedLessons.get(i);
            int dayIndex = days.indexOf(lesson.getRight().day);
            int startRow = timeToIndex(lesson.getRight().startTime);
            int indent = lessonStack.get(dayIndex).get(startRow).size();
            indents.add(indent);
            for (int j = startRow; j < timeToIndex(lesson.getRight().endTime); j++) {
                while (lessonStack.get(dayIndex).get(j).size() < indent) {
                    lessonStack.get(dayIndex).get(j).add(null); // pad
                }
                lessonStack.get(dayIndex).get(j).add(lesson);
            }
        }
        List<Integer> columns = new ArrayList<>();
        for (int i = 0; i < days.size(); i++) {
            columns.add(1);
            for (int j = 0; j < lessonStack.get(i).size(); j++) {
                columns.set(i, Math.max(columns.get(i), lessonStack.get(i).get(j).size()));
            }
        }
        return Pair.of(columns, indents);
    }

    private int getColumnOfDay(Day day) {
        int index = days.indexOf(day);
        int cols = 1;
        for (int i = 0; i < index; i++) {
            cols += columns.get(i);
        }
        return cols * (COLUMN_WIDTH + 1);
    }

    private void write(String text, int row, int column, String color) {
        buffer[row][column] = (withColor ? color : "") + text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            buffer[row][column + i] = "" + text.charAt(i);
        }
        if (withColor) {
            buffer[row][column + text.length() - 1] += Color.ANSI_RESET + Color.BACKGROUND_BLACK;
        }
    }

    private void write(String text, int row, int column) {
        write(text, row, column, Color.NOTHING);
    }

    private void addSingleLesson(Module module, RawLesson lesson, int indent) {
        Day day = lesson.day;
        // int dayIndex = days.indexOf(day);
        int startColumn = getColumnOfDay(day) - 1 + indent * (COLUMN_WIDTH + 1);
        int endColumn = startColumn + COLUMN_WIDTH + 1;
        int startRow = timeToIndex(lesson.startTime) * ROWS_PER_TIME + HEADER_ROWS;
        int endRow = timeToIndex(lesson.endTime) * ROWS_PER_TIME + HEADER_ROWS;
        buffer[startRow][startColumn] = ""
                + consoleBorder.mergeBorder(buffer[startRow][startColumn].charAt(0), consoleBorder.get(1, 1));
        buffer[startRow][endColumn] = ""
                + consoleBorder.mergeBorder(buffer[startRow][endColumn].charAt(0), consoleBorder.get(1, -1));
        buffer[endRow][startColumn] = ""
                + consoleBorder.mergeBorder(buffer[endRow][startColumn].charAt(0), consoleBorder.get(-1, 1));
        buffer[endRow][endColumn] = ""
                + consoleBorder.mergeBorder(buffer[endRow][endColumn].charAt(0), consoleBorder.get(-1, -1));
        for (int i = startRow + 1; i < endRow; i++) {
            buffer[i][startColumn] = ""
                    + consoleBorder.mergeBorder(buffer[i][startColumn].charAt(0), consoleBorder.getSide());
            buffer[i][endColumn] = ""
                    + consoleBorder.mergeBorder(buffer[i][endColumn].charAt(0), consoleBorder.getSide());
        }
        for (int i = 0; i < COLUMN_WIDTH; i++) {
            if (buffer[startRow][startColumn + i + 1].equals(" ")) {
                buffer[startRow][startColumn + i + 1] = "" + consoleBorder.getAcross();
            }
            if (buffer[endRow][startColumn + i + 1].equals(" ")) {
                buffer[endRow][startColumn + i + 1] = "" + consoleBorder.getAcross();
            }
        }
        write(module.moduleCode, startRow + 1, startColumn + 1, ColorScheme.get(modules.indexOf(module)));
        write(lessonTypeToShortString(lesson.lessonType) + "[" + lesson.classNo + "]", startRow + 2, startColumn + 2,
                ColorScheme.get(modules.indexOf(module)));
    }

    private int timeToIndex(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int mins = Integer.parseInt(time.substring(2));
        return (hour - firstHour) * 2 + (mins == 30 ? 1 : 0);
    }

    private String indexToTime(int index) {
        boolean half = index % 2 == 1;
        int hours = index / 2 + firstHour;
        return String.format("%02d", hours) + (half ? "30" : "00");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            if (withColor) {
                builder.append(Color.BACKGROUND_BLACK);
            }
            builder.append(String.join("", buffer[i]));
            if (withColor) {
                builder.append(Color.ANSI_RESET);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static String lessonTypeToShortString(LessonType lessonType) {
        Map<LessonType, String> map = new HashMap<>();
        map.put(LessonType.TUTORIAL, "TUT");
        map.put(LessonType.LECTURE, "LEC");
        map.put(LessonType.RECITATION, "REC");
        map.put(LessonType.DESIGN_LECTURE, "DLEC");
        map.put(LessonType.PACKAGED_LECTURE, "PLEC");
        map.put(LessonType.PACKAGED_TUTORIAL, "PTUT");
        map.put(LessonType.SECTIONAL_TEACHING, "SEC");
        map.put(LessonType.WORKSHOP, "WKSH");
        map.put(LessonType.LABORATORY, "LAB");
        map.put(LessonType.MINI_PROJECT, "PROJ");
        map.put(LessonType.SEMINAR_STYLE_MODULE_CLASS, "SEM");
        map.put(LessonType.TUTORIAL_TYPE_2, "TUT2");
        return Optional.ofNullable(map.get(lessonType)).orElse("<INVALID>");
    }

    public static String dayToShortString(Day day) {
        Map<Day, String> map = new HashMap<>();
        map.put(Day.Monday, "Mon");
        map.put(Day.Tuesday, "Tues");
        map.put(Day.Wednesday, "Wed");
        map.put(Day.Thursday, "Thur");
        map.put(Day.Friday, "Fri");
        map.put(Day.Saturday, "Sat");
        map.put(Day.Sunday, "Sun");
        return Optional.ofNullable(map.get(day)).orElse("<Invalid>");
    }

}
