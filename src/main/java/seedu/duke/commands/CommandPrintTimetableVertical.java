package seedu.duke.commands;

import seedu.duke.Timetable;
import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;


public class CommandPrintTimetableVertical {

    private static final String[] DAYS_IN_WEEK = {"MON", "TUE", "WED", "THU", "FRI"};
    private static final Integer FIRST_HOUR = 8; // timetable start at 0800
    private static final Integer COLUMN_WIDTH = 13;
    private static final Integer TIMETABLE_HEIGHT = 28;
    private static final Integer LEFT_PADDING = 3;
    private static final Integer TOP_PADDING = 1;
    private static final Integer DAY_PER_WEEK = 5; // only considering Mon to Fri

    private static final Integer timetableWidth = 78;
    private static String[][] timetableVertical;
    private static ArrayList<ArrayList<Object[]>> rawTimetable = new ArrayList<>(5);

    private static ArrayList<Integer[]>[] emptySlotRegister = new ArrayList[DAY_PER_WEEK];
    // array of 5 arraylist of integer pairs

    public static String viewTimetable() {
        populateRawTimetable(Timetable.getListOfModules());
        setTable();
        initializeTable();
        writeTableHeader();
        writeTable();

        return printTimetable(timetableVertical);
    }

    private static void populateRawTimetable(List<Module> listOfModules) {
        rawTimetable = new ArrayList<>(5);
        emptySlotRegister = new ArrayList[DAY_PER_WEEK];

        for (int i = 0; i < DAY_PER_WEEK; i++) {
            rawTimetable.add(new ArrayList<>());
            emptySlotRegister[i] = new ArrayList<Integer[]>();
        }

        for (Module module : listOfModules) {
            List<Lesson> lessons = module.getAttending();
            String name = module.getModuleCode();
            for (Lesson les : lessons) {
                int[] info = convertTimeToIndex(les.getDay(), les.getStartTime(), les.getEndTime());
                if (info[0] == -1) {
                    continue;
                }

                Object[] rawModule = new Object[4];
                rawModule[0] = info[1]; // starting slot
                rawModule[1] = info[2] + 1; // ending slot, difference between api data and data here
                rawModule[2] = name; // module name
                String type = les.getLessonType();
                rawModule[3] = type;// module type

                Integer[] rawModuleSlot = new Integer[2];
                rawModuleSlot[0] = (Integer)info[1];
                rawModuleSlot[1] = (Integer)info[2] + 1;


                rawTimetable.get(info[0]).add(rawModule); // add each rawModule into respective day
                emptySlotRegister[info[0]].add(rawModuleSlot);
            }

        }
        sortTimetable(rawTimetable);

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


    private static void sortTimetable(ArrayList<ArrayList<Object[]>> rawTimetable) {
        // sort modules to see conflict
        ArrayList<int[]> todayLessonSpan = new ArrayList<>();
        for (int i = 0; i < DAY_PER_WEEK; i++) {

            for (int j = 0; j < rawTimetable.get(i).size(); j++) { // loop through all lessons in that day
                if (!rawTimetable.get(i).isEmpty()) {

                    int startSlot = (Integer) rawTimetable.get(i).get(j)[0];
                    int endSlot = (Integer) rawTimetable.get(i).get(j)[1];

                    int[] pair = new int[2];
                    pair[0] = startSlot;
                    pair[1] = endSlot;
                    todayLessonSpan.add(pair);

                }
            }
        }
    }


    private static void sortDailySlots(Integer day, ArrayList<Integer[]>[] emptySlotRegister) {

        Collections.sort(emptySlotRegister[day],new Comparator<Integer[]>() {
            public int compare(Integer[] i1,Integer[] i2) {
                return i1[0] - i2[0];
            }
        });
    }

    private static void sortRawTimetable(Integer day) {
        Collections.sort(emptySlotRegister[day],new Comparator<Integer[]>() {
            public int compare(Integer[] i1,Integer[] i2) {
                return i1[0] - i2[0];
            }
        });
    }

    private static void setTable() {
        timetableVertical = new String[TIMETABLE_HEIGHT][timetableWidth];
    }

    private static void initializeTable() {
        for (int i = 0; i < TIMETABLE_HEIGHT; i++) {
            for (int j = 0; j < timetableWidth; j++) {
                timetableVertical[i][j] = " ";
            }
        }
    }


    private static void writeTableHeader() {
        // draw border below days
        for (int i = 0; i < timetableWidth; i++) {
            timetableVertical[2][i] = "="; // FIXED CHAR -- write this to UI
        }
        // left time column
        for (int i = 3; i < 28; i++) { // FIXED 3+24-1 for slots
            write(indexToTime(i), i, LEFT_PADDING);
        }
        // write day headers
        for (int i = 0; i < DAY_PER_WEEK; i++) {
            write(UI.DOTTED_CHAR + " " + DAYS_IN_WEEK[i], TOP_PADDING,
                    getDayColumnIndex(i) + 1);
        }
        // draw border between day columns
        for (int i = 0; i < TIMETABLE_HEIGHT; i++) {
            for (int j = 0; j < DAY_PER_WEEK; j++) { // FIXED 3+24-1 for slots
                write("" + UI.DOTTED_CHAR, i, getDayColumnIndex(j) + 1);
            }
        }
    }

    private static void write(String text, int row, int column) {
        timetableVertical[row][column] = "" + text.charAt(0);
        for (int i = 1; i < text.length(); i++) {

            timetableVertical[row][column + i] = "" + text.charAt(i);
        }
    }

    private static String indexToTime(int index) {
        boolean isHalf = index % 2 == 0; // even index in timetable is half hours
        int hours = (index - 3) / 2 + FIRST_HOUR; //index - 3 gives original slots, 0800 index is 3 but slot is 0
        return String.format("%02d", hours) + (isHalf ? "30" : "00");
    }

    private static int getDayColumnIndex(int day) { // day 0 is Monday
        int columnIndex = 0;
        for (int i = 0; i < day; i++) {
            columnIndex += COLUMN_WIDTH;
        }

        return columnIndex + 11; // 11 is time column length xxx1000xxx
    }

    private static void writeTable() {
        for (int i = 0; i < rawTimetable.size(); i++) {
            if (!rawTimetable.get(i).isEmpty()) {
                sortRawTimetable(i);
                ArrayList<Object[]> dayIterator = rawTimetable.get(i);
                // proceed with non-empty days to process

                boolean dailyClashFlag = checkDailySlotClash(i);
                if (dailyClashFlag) { // if clash exists, pre-write timetable with "X"
                    ArrayList<Integer[]> clashSlotList = getDailyClashSlot(i);

                    for (Integer[] slot : clashSlotList) {
                        Integer clashStartIndex = slot[0];
                        Integer clashEndIndex = slot[1];

                        for (int l = clashStartIndex; l < clashEndIndex + 1; l++) { // write the end with X for now
                            write("X".repeat(COLUMN_WIDTH - 1),l + 3,getDayColumnIndex(i) + 2);
                        }
                    }
                }

                for (int j = 0; j < dayIterator.size(); j++) {
                    Object[] rawModule = dayIterator.get(j);
                    int modStartSlot = (Integer) rawModule[0];
                    int modEndSlot = (Integer) rawModule[1];
                    String currentModCode = (String) rawModule[2];
                    String currentModType = (String) rawModule[3];
                    StringBuilder upperBoarder = new StringBuilder();
                    StringBuilder lowerBoarder = new StringBuilder();

                    if (modEndSlot - modStartSlot < 3) { // lower boarder joins lesson type
                        lowerBoarder.append(UI.HORIZONTAL_BORDER);
                        String lessonType = currentModType.substring(0,3).toUpperCase();
                        lowerBoarder.append(lessonType);
                        int currentLength = lowerBoarder.length();
                        lowerBoarder.append(UI.HORIZONTAL_BORDER.repeat(COLUMN_WIDTH - 1 - currentLength));


                    } else {
                        lowerBoarder.append(UI.HORIZONTAL_BORDER.repeat(Math.max(0, COLUMN_WIDTH) - 1));
                    }
                    upperBoarder.append(UI.HORIZONTAL_BORDER.repeat(Math.max(0, COLUMN_WIDTH) - 1));

                    Integer columnIndex = getDayColumnIndex(i) + 2;


                        if (!checkTimetableSlotWritten(modStartSlot + 3,columnIndex,"X")
                        && !checkTimetableSlotWritten(modStartSlot + 4,columnIndex,"X")) {
                            // if no clash is indicated in timetable, write the timetable

                            write(currentModCode, modStartSlot + 4, columnIndex);
                            String lessonType = currentModType.substring(0,3).toUpperCase();
                            write(lessonType, modStartSlot + 5, columnIndex + 1);
                            write(lowerBoarder.toString(), modEndSlot + 3, columnIndex);
                            if (!timetableVertical[modStartSlot + 3][columnIndex + 1].equals(UI.HORIZONTAL_BORDER)) {
                                write(upperBoarder.toString(), modStartSlot + 3, columnIndex);
                            }
                        }
                }
            }
        }

    }


    private static String printTimetable(String[][] timetableVertical) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < TIMETABLE_HEIGHT; i++) {
            for (int j = 0; j < timetableWidth; j++) {
                output.append(timetableVertical[i][j]);
            }
            output.append(System.lineSeparator());
        }
        return output.toString();
    }


    private static boolean checkDailySlotClash(Integer day) {
        if (emptySlotRegister.length > 0) {
            sortDailySlots(day, emptySlotRegister);
        }

        for (int i = 1; i < emptySlotRegister[day].size(); i++) { //[1] is pair.second, [0] is pair.first
            if ((emptySlotRegister[day].get(i - 1)[1]) > emptySlotRegister[day].get(i)[0]) {
                return true; // slot end + 1 is the real ending
            }
        }
        return false;

    }

    private static ArrayList<Integer[]> getDailyClashSlot(Integer day) {
        if (emptySlotRegister.length > 0) {
            sortDailySlots(day, emptySlotRegister);
        }
        Stack<Integer[]> stack = new Stack<>();
        stack.push(emptySlotRegister[day].get(0));


        for (int i = 1; i < emptySlotRegister[day].size(); i++) {
            Integer[] top = stack.peek();

            if (top[1] <= emptySlotRegister[day].get(i)[0]) { //[1] is pair.second, [0] is pair.first
                stack.pop();
                stack.push(emptySlotRegister[day].get(i));
            } else if (top[1] < emptySlotRegister[day].get(i)[1]) {
                top[1] = emptySlotRegister[day].get(i)[1];
                stack.pop();
                stack.push(top);
            }
        }

        ArrayList<Integer[]> clashSlotList = new ArrayList(stack);

        for (Integer[] slot : clashSlotList) {
            for (Integer a : slot) {
                System.out.println(a);
            }
        }

        for (Integer[] originalSlot : emptySlotRegister[day]) {
            if (clashSlotList.indexOf(originalSlot) == -1) {
                clashSlotList.remove(originalSlot);
            }
        }
        return clashSlotList;

    }

    private static boolean checkTimetableSlotWritten(Integer row, Integer column, String string) {
        return timetableVertical[row][column].equals(string);
    }

}



/* to fix:
1. import java util *
2. rename functions and variables
3. cutdown numbers of global variables...?
4. write javadoc and comments
5. generalization of code
6. "writetable()" function arrowhead
7. int - Integer unify the types
8. cutdown magic literals
9. apply more SLAP
10. assert: starting slot < ending slot
11. static class pair
{
    int first;
    char second;

    pair(int first, char second)
    {
        this.first = first;
        this.second = second;
    }
}
data.add(new pair(v[i][0], 'x'));


*/