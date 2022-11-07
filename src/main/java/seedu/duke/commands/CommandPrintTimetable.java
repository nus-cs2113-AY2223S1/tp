package seedu.duke.commands;

import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.timetable.Timetable;
import seedu.duke.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;
import java.util.Stack;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Collections;
import java.util.logging.Logger;

import static seedu.duke.timetable.Timetable.listOfModules;

//@@author HT-T

public class CommandPrintTimetable {

    private static final String[] DAYS_IN_WEEK = {"MON", "TUE", "WED", "THU", "FRI"};
    private static final Integer FIRST_HOUR = 8; // timetable start at 0800
    private static final Integer COLUMN_WIDTH = 15;
    private static final Integer LEFT_PADDING = 3;
    private static final Integer TOP_PADDING = 1;
    private static final Integer COLUMN_PADDING = 1;
    private static final Integer ROW_DIFFERENCE = 3; // difference between api data and timetable here
    private static final Integer DAY_PER_WEEK = 5; // only considering Mon to Fri
    private static final Integer TIMETABLE_WIDTH = 90;
    private static final Integer TIMETABLE_TIME_WIDTH = 15;
    private static final Integer TIMETABLE_HEIGHT = 32;
    private static final Integer END_SLOT_DIFFERENCE = 1;
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static String[][] timeTable;
    private static ArrayList<ArrayList<Object[]>> rawTimetable = new ArrayList<>(5);
    // rawTimetable stores the lesson objects for data handling 
    private static ArrayList<ArrayList<Integer[]>> emptySlotList = new ArrayList<>(DAY_PER_WEEK);
    // array of 5 arraylist of integer pairs
    private static ArrayList<String> clashModCodeList = new ArrayList<>();

    public static String viewTimetable() {
        populateRawTimetable(Timetable.getListOfModules());
        setTable();
        initializeTable();
        writeTableHeader();
        writeTable();

        return printTimetable(timeTable);
    }

    /**
     * Initialize list of lesson data type
     * and list of empty slot list, a pair of integers.
     *
     */
    private static void initializeRawTimeTable() {
        for (int i = 0; i < DAY_PER_WEEK; i++) {
            rawTimetable.add(new ArrayList<>());
            emptySlotList.add(new ArrayList<>());

        }
    }



    /**
     * Reads from user's list of lessons and populates timetable structure
     * and emptySlotList respectively.
     *
     * @param listOfModules User's attending list of lessons and modules.
     */
    private static void populateRawTimetable(List<Module> listOfModules) {
        rawTimetable = new ArrayList<>(DAY_PER_WEEK);
        emptySlotList = new ArrayList<>(DAY_PER_WEEK);
        initializeRawTimeTable();

        for (Module module : listOfModules) {
            List<Lesson> lessons = module.getAttendingList();
            try {
                String code = module.getModuleCode();
                writeRawTimetable(lessons, code);
                lgr.fine("Timetable initialized successfully. ");
            } catch (IllegalArgumentException e) {
                UI.printResponse("File may be corrupted, please delete the file and re-try. ");
                lgr.info("Timetable file corrupted. ");
            }
        }
    }

    /**
     * Read from each lesson data type and write them into lists.
     *
     * @param lessons User's list of attending lessons.
     * @param code The module code of user's attending lesson
     */
    private static void writeRawTimetable(List<Lesson> lessons, String code) {
        for (Lesson les : lessons) {
            int[] info = convertTimeToIndex(les.getDay(), les.getStartTime(), les.getEndTime());
            if (info[0] == -1) {
                continue;
            }
            populateDailyRawTimetable(code, les, info);
        }
    }


    /**
     * Helper function that transforms and writes lesson structure from timetable to
     * lesson structure printTimetable method uses.
     *
     * @param code User's attending module code.
     * @param les User's lesson type of that module.
     * @param info Array of lesson's starting and ending slot.
     */
    private static void populateDailyRawTimetable(String code, Lesson les, int[] info) {
        Object[] rawLesson = new Object[4];
        rawLesson[0] = info[1]; // starting slot
        rawLesson[1] = info[2] + END_SLOT_DIFFERENCE; 
        // api data and timetable data here has a difference in lesson ending time
        rawLesson[2] = code; // lesson code
        String type = les.getLessonType();
        rawLesson[3] = type; // lesson type

        Integer[] rawLessonSlot = new Integer[2];
        rawLessonSlot[0] = info[1];
        rawLessonSlot[1] = info[2] + END_SLOT_DIFFERENCE;


        try {
            rawTimetable.get(info[0]).add(rawLesson); // add each rawLesson into respective day
            emptySlotList.get(info[0]).add(rawLessonSlot);
        } catch (NullPointerException e) {
            UI.printResponse("rawTimetable / emptySlotList not initialized! ");
        }

    }


    /**
     * Convert 24-hour data from nusMODS to integer of slots for use of
     * lesson data structure in printTimetable method.
     *
     * @param day Day of the week.
     * @param startTime Starting time of the lesson.
     * @param endTime Ending time of the lesson.
     * @return Integer array of lesson with day, starting, and ending times.
     */
    private static int[] convertTimeToIndex(String day, String startTime, String endTime) {
        int[] info = new int[3];

        if (isInvalidTimings(startTime, endTime)) {
            info[0] = -1;
            return info;
        }

        info[0] = determineDay(day);
        info[1] = parseTimeToIndex(startTime);
        info[2] = parseTimeToIndex(endTime) - 1;

        return info;
    }


    private static int parseTimeToIndex(String time) {
        String newTime = time.replaceFirst("^0+(?!$)", "");
        return (Integer.parseInt(newTime) - 770) / 50;
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


    private static void sortDailySlots(Integer day, ArrayList<ArrayList<Integer[]>> emptySlotList) {
        emptySlotList.get(day).sort(Comparator.comparingInt(i -> i[0]));
    }


    /**
     * Declares timeTable, 2D array of String to store the output of timetable,
     * initializes ArrayList of clashed module codes.
     *
     */
    private static void setTable() {
        timeTable = new String[TIMETABLE_HEIGHT][TIMETABLE_WIDTH];
        clashModCodeList = new ArrayList<>();
        // initialized here to make sure clashModCodeList updates everytime print command is called
    }


    private static void initializeTable() {
        for (int i = 0; i < TIMETABLE_HEIGHT; i++) {
            for (int j = 0; j < TIMETABLE_WIDTH; j++) {
                timeTable[i][j] = " ";
            }
        }
    }

    /**
     * Write day, time headers, and rows and columns of the timetable.
     *
     */
    private static void writeTableHeader() {
        // draw border below days
        for (int i = 0; i < TIMETABLE_WIDTH; i++) {
            timeTable[2 * TOP_PADDING][i] = UI.TABLE_HEADER;
        }
        drawDaysHeader();
        drawTopSeparator();
        drawColumnSeparator();
    }

    private static void drawDaysHeader() {
        for (int i = LEFT_PADDING; i < TIMETABLE_HEIGHT; i++) {
            write(indexToTime(i), i, LEFT_PADDING);
        }
    }

    private static void drawTopSeparator() {
        for (int i = 0; i < DAY_PER_WEEK; i++) {
            write(UI.DOTTED_CHAR + " " + DAYS_IN_WEEK[i], TOP_PADDING,
                    getDayColumnIndex(i) - COLUMN_PADDING);
        }
    }

    private static void drawColumnSeparator() {
        for (int i = 0; i < TIMETABLE_HEIGHT; i++) {
            for (int j = 0; j < DAY_PER_WEEK; j++) {
                write("" + UI.DOTTED_CHAR, i, getDayColumnIndex(j) - COLUMN_PADDING);
            }
        }
    }

    /**
     * Write desired strings to desired destination in 2D array.
     *
     * @param text String to write into array
     * @param row Row of the to-be-written string
     * @param column Column of the to-be-written string
     */
    private static void write(String text, int row, int column) {
        timeTable[row][column] = "" + text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            timeTable[row][column + i] = "" + text.charAt(i);
        }
    }

    /**
     * Convert slot integer index to actual timings.
     * Differentiate half hours and full hours
     *
     * @param index Integer index of lesson's starting and ending slot
     * @return String of the represented actual time
     */
    private static String indexToTime(int index) {
        boolean isHalf = index % 2 == 0; // even index in timetable is half hours
        int hours = (index - ROW_DIFFERENCE) / 2 + FIRST_HOUR; 
        return String.format("%02d", hours) + (isHalf ? "30" : "00");
    }


    private static int getDayColumnIndex(int day) { // day 0 is Monday
        int columnIndex = 0;
        for (int i = 0; i < day; i++) {
            columnIndex += COLUMN_WIDTH;
        }

        return columnIndex + TIMETABLE_TIME_WIDTH; 
    }

    /**
     * Write the output timetable with lessons and boxes.
     */
    private static void writeTable() {
        for (int i = 0; i < rawTimetable.size(); i++) {
            if (!rawTimetable.get(i).isEmpty()) {
                sortDailySlots(i,emptySlotList);
                ArrayList<Object[]> dayIterator = rawTimetable.get(i);
                // proceed with non-empty days to process
                initializeClashSlotList(i);
                createTimetableString(i, dayIterator);
            }
        }

    }

    /**
     * Read lesson data structure from list and write them into output timetable.
     *
     * @param day Day of the week.
     * @param dayIterator List of lesson data structure of that day.
     */
    private static void createTimetableString(int day, ArrayList<Object[]> dayIterator) {
        for (Object[] rawLesson : dayIterator) {
            int modStartSlot = Integer.parseInt(rawLesson[0].toString());
            int modEndSlot = Integer.parseInt(rawLesson[1].toString());
            assert modStartSlot < modEndSlot;
            String currentModCode = (String) rawLesson[2];
            String currentModType = (String) rawLesson[3];
            StringBuilder upperBoarder = new StringBuilder();
            StringBuilder lowerBoarder = new StringBuilder();

            //buildLowBoarder(modStartSlot, modEndSlot, currentModType, lowerBoarder);
            lowerBoarder.append(UI.HORIZONTAL_BORDER.repeat(COLUMN_WIDTH - 1));
            upperBoarder.append(UI.HORIZONTAL_BORDER.repeat(COLUMN_WIDTH - 1));

            Integer columnIndex = getDayColumnIndex(day);
            Integer thisSlotRowIndex = modStartSlot + ROW_DIFFERENCE;
            boolean nextSlotWritten = checkSlotWritten(thisSlotRowIndex + 1, columnIndex);

            if (!nextSlotWritten) {
                // if no clash is indicated in timetable, write the timetable
                writeLesson(modStartSlot,modEndSlot, currentModCode, currentModType, columnIndex);
                writeBoarder(modStartSlot, modEndSlot, upperBoarder, lowerBoarder, columnIndex);
            }
        }
    }


    /**
     * Write the module and type of lesson into output timetable.
     *
     * @param start Starting slot of the lesson
     * @param end Ending slot of the lesson
     * @param code Module code of the lesson
     * @param type Type of lesson
     * @param col Column of that lesson in the output timetable
     */
    private static void writeLesson(Integer start, Integer end, String code, String type, Integer col) {
        try {
            assert end > start : "End slot index is smaller than start index!";
            Integer lessonHeight = end - start;
            Integer codeRowIndex = start + ROW_DIFFERENCE + 1;
            String lessonType = type.substring(0, 3).toUpperCase();
            if (lessonHeight > 2) { // lesson box is large enough
                write(code, codeRowIndex, col);
                // type is printed with first three letters
                Integer typeRowIndex = start + ROW_DIFFERENCE + 2;
                write(lessonType, typeRowIndex, col + 1);
            } else {
                String tightCode = code + " " + lessonType;
                write(tightCode, codeRowIndex, col);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            UI.printResponse("index out of bounds when writing lesson");
        }
    }

    /**
     * Write the box boarders of the lessons.
     *
     * @param start Starting slot of the lesson
     * @param end Ending slot of the lesson
     * @param upLine Upper boarder of the lesson box
     * @param lowLine Lower boarder of the lesson box
     * @param col Column of that lesson in the output timetable
     */
    private static void writeBoarder(int start, int end, StringBuilder upLine, StringBuilder lowLine, Integer col) {
        Integer endRowIndex = end + ROW_DIFFERENCE;
        try {
            write(lowLine.toString(), endRowIndex, col);
            writeTopBoarder(start, upLine, col);
        } catch (ArrayIndexOutOfBoundsException e) {
            UI.printResponse("index out of bounds when writing boarder");
        }

    }


    private static void writeTopBoarder(int startSlot, StringBuilder upBoarder, Integer columnIndex) {
        Integer rowIndex = startSlot + ROW_DIFFERENCE;
        String stringToCheck = timeTable[rowIndex][columnIndex + 1];
        boolean isOccupied = stringToCheck.equals(UI.HORIZONTAL_BORDER);
        if (!isOccupied) {
            try {
                write(upBoarder.toString(), startSlot + 3, columnIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.printResponse("index out of bounds when writing top boarder");
            }
        }
    }


    /**
     * Creates list of clashed slots, write them as "XXX" into output timetable.
     *
     * @param day Day of the week.
     */
    private static void initializeClashSlotList(int day) {
        boolean dailyClashFlag = checkDailySlotClash(day);
        if (dailyClashFlag) {
            // if clash exists, pre-write timetable with "X"
            ArrayList<Integer[]> clashSlotList = getDailyClashSlot(day);

            for (Integer[] slot : clashSlotList) {
                Integer clashStartIndex = slot[0];
                Integer clashEndIndex = slot[1];
                try {
                    writeClashSlot(day, clashStartIndex, clashEndIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.printResponse("index out of bounds when writing clashed lessons ");
                }


            }
        }
    }

    private static void writeClashSlot(int day, Integer clashStartIndex, Integer clashEndIndex) {
        try {
            for (int l = clashStartIndex; l < clashEndIndex + 1; l++) {
                // write the end with X for now
                String stringToWrite = "X".repeat(COLUMN_WIDTH - 1);
                write(stringToWrite, l + ROW_DIFFERENCE, getDayColumnIndex(day));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            UI.printResponse("index out of bounds when writing clashed lessons");
        }
    }

    /**
     * Writes and returns output timetable.
     *
     * @param timeTable 2D array of timeTable string for output
     * @return String of timetable for print
     */
    private static String printTimetable(String[][] timeTable) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < TIMETABLE_HEIGHT; i++) {
            for (int j = 0; j < TIMETABLE_WIDTH; j++) {
                output.append(timeTable[i][j]);
            }
            output.append(System.lineSeparator());
        }
        addClashModList(output);
        addRemarks(output);
        return output.toString();
    }

    /**
     * Check whether there is clash lessons that day.
     *
     * @param day Day of the week.
     * @return True if there is clash that day, false otherwise.
     */
    private static boolean checkDailySlotClash(Integer day) {
        if (emptySlotList.size() > 0) {
            sortDailySlots(day, emptySlotList);
        }

        for (int i = 1; i < emptySlotList.get(day).size(); i++) {
            if ((emptySlotList.get(day).get(i - 1)[1]) > emptySlotList.get(day).get(i)[0]) {
                return true; 
            }
        }
        return false;

    }

    /**
     * Get an ArrayList of merged clash lesson's starting and ending slots for that day.
     *
     * @param day Day of the week.
     * @return Arraylist of clashing slots for each day.
     */
    private static ArrayList<Integer[]> getDailyClashSlot(Integer day) {
        if (emptySlotList.size() > 0) {
            sortDailySlots(day, emptySlotList);
        }

        ArrayList<ArrayList<Integer[]>> newEmptySlotList = new ArrayList<>(emptySlotList);

        Deque<Integer[]> deque = new LinkedList<>();
        deque.push(newEmptySlotList.get(day).get(0));

        Stack<Integer[]> refStack = new Stack<>();
        refStack.push(newEmptySlotList.get(day).get(0));

        sortSlotList(day, deque, refStack, newEmptySlotList);

        populateRawTimetable(listOfModules);
        sortDailySlots(day, emptySlotList);

        ArrayList<Integer[]> clashSlotList = createClashList(deque);

        removeUnclashSlot(day, clashSlotList, refStack);
        populateClashMod(day, clashSlotList);

        return clashSlotList;

    }


    private static void populateClashMod(Integer day, ArrayList<Integer[]> clashSlotList) {
        ArrayList<Object[]> todayRawTimetable = rawTimetable.get(day);
        // find the rawTimetable of that particular day
        for (Integer[] clashInterval : clashSlotList) {
            addClashLesson(todayRawTimetable, clashInterval);
        }

    }

    /**
     * Reads lesson data structure from the rawTimetable and popualtes ArrayList of clashed lesson slots.
     *
     * @param todayRawTimetable Raw timetable data structure for that day
     * @param clashInterval ArrayList for storing clashed interval of that day
     */
    private static void addClashLesson(ArrayList<Object[]> todayRawTimetable, Integer[] clashInterval) {
        for (Object[] lesson : todayRawTimetable) {
            // compare original lessons with clash interval one by one
            Integer startInterval = Integer.parseInt(lesson[0].toString());
            Integer endInterval = Integer.parseInt(lesson[1].toString());

            boolean isInStartRange = startInterval.equals(clashInterval[0]) || startInterval > clashInterval[0];
            boolean isInEndRange = endInterval.equals(clashInterval[1]) || endInterval < clashInterval[1];

            // if the lesson is within the clash interval, add to printing list
            if (isInStartRange && isInEndRange) {
                addCodeToList(lesson);
            }
        }
    }

    /**
     * Check if the module code is already in the clashed module code list.
     * Add them if not in such list.
     *
     * @param lesson User's attending lesson data structure.
     */
    private static void addCodeToList(Object[] lesson) {
        // make sure there are no duplicates in list before adding
        String modToAdd = lesson[2].toString();
        boolean isDuplicateMod = clashModCodeList.contains(modToAdd);
        if (!isDuplicateMod) {
            clashModCodeList.add(modToAdd);

        }

    }


    private static ArrayList<Integer[]> createClashList(Deque<Integer[]> deque) {
        ArrayList<Integer[]> clashSlotList = new ArrayList<>();
        while (!deque.isEmpty()) {
            clashSlotList.add(deque.pop());
        }
        return clashSlotList;
    }

    /**
     * Removes non-clashing lessons from the merged intervals of clashing lessons.
     *
     * @param day Day of the week
     * @param clashList List of all intervals, both clashed and un-clashed lessons
     * @param refStack Stack of un-clash lessons
     */
    private static void removeUnclashSlot(Integer day, ArrayList<Integer[]> clashList, Stack<Integer[]> refStack) {
        ArrayList<Integer> removeIndex = new ArrayList<>();
        for (Integer[] untouchedSlot : refStack) {
            for (Integer[] oldClashSlot : clashList) {
                boolean isFirstSame = untouchedSlot[0].equals(oldClashSlot[0]);
                boolean isSecondSame = untouchedSlot[1].equals(oldClashSlot[1]);
                if (isFirstSame && isSecondSame) {
                    removeIndex.add(clashList.indexOf(oldClashSlot));
                }
            }
        }

        Collections.sort(removeIndex, Collections.reverseOrder());
        for (int i : removeIndex) {
            try {
                clashList.remove(i);
            } catch (IndexOutOfBoundsException e) {
                UI.printResponse("index out of bounds removing non-clashed lessons");

            }
        }



    }


    /**
     * Merge the overlapping clashing lesson's intervals.
     * Intervals are pairs of integers marking the starting and ending slots of lessons.
     *
     * @param day Day of the week
     * @param deque Intervals of merged clash intervals and un-clash unintervals of lesson
     * @param stack Stack of un-clash lessons
     * @param newEsl List of lessons for that day
     */
    private static void sortSlotList(Integer day, Deque<Integer[]> deque,
                                     Stack<Integer[]> stack, ArrayList<ArrayList<Integer[]>> newEsl) {

        for (int i = 1; i < newEsl.get(day).size(); i++) {
            Integer[] top = deque.getLast();
            if (top[1] < newEsl.get(day).get(i)[0]) { //[1] is pair.second, [0] is pair.first
                deque.addLast(newEsl.get(day).get(i));
                stack.push(newEsl.get(day).get(i));
            } else if (top[1].equals(newEsl.get(day).get(i)[0])) {
                deque.addLast(newEsl.get(day).get(i));
                stack.push(newEsl.get(day).get(i));
            } else if (top[1] < newEsl.get(day).get(i)[1]) {
                top[1] = newEsl.get(day).get(i)[1];
                deque.pop();
                deque.addLast(top);
                popStack(stack);
            } else {
                popStack(stack);
            }
        }


    }

    private static void popStack(Stack<Integer[]> stack) {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }


    private static boolean checkSlotWritten(Integer row, Integer column) {
        return timeTable[row][column].equals("X");
    }

    /**
     * Add comments under the output timetable.
     *
     * @param timetable Stringbuilder for output timetable
     * @return String of output timetable
     */
    private static String addRemarks(StringBuilder timetable) {
        timetable.append(System.lineSeparator()
                + " * Note that timings indicated refers to the start of "
                + "the corresponding 30 minutes timeslot." + System.lineSeparator()
                + " * Slots with XXXXXX indicates that there is a clash between two or more lessons."
                + System.lineSeparator()
                + " * Modules, if any, that start before 8am or ends after 10pm timings are excluded."
                + System.lineSeparator()
                + " * Timings are approximated to 30 minutes block with valid assumption that "
                + "NUS mods are typically designed in such blocks." + System.lineSeparator());
        return timetable.toString();
    }

    /**
     * Add module codes that cause crash at the end ot the output timetable.
     *
     * @param timetable Stringbuilder of output timetable
     * @return String of output timetable
     */

    private static String addClashModList(StringBuilder timetable) {
        boolean isClashListEmpty = clashModCodeList.isEmpty();
        if (isClashListEmpty) {
            timetable.append(" ");
            return timetable.toString(); // nothing is to be printed
        } else {
            timetable.append(System.lineSeparator()
                    + "These are the clashed modules : " + System.lineSeparator());
            for (String modCode : clashModCodeList) {
                timetable.append(modCode + System.lineSeparator());
            }
            return timetable.toString();
        }
    }


}


