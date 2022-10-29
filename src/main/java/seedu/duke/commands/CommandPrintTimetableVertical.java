package seedu.duke.commands;

import seedu.duke.Timetable;
import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.UI;

import java.util.*;


public class CommandPrintTimetableVertical {

    private static final String[] DAYS_IN_WEEK = {"MON", "TUE", "WED", "THU", "FRI"};
    private static final Integer FIRST_HOUR = 8; // timetable start at 0800
    private static final Integer COLUMN_WIDTH = 11;
    private static final Integer TIMETABLE_HEIGHT = 28;
    private static final Integer LEFT_PADDING = 3;
    private static final Integer TOP_PADDING = 1;
    private static final Integer DAY_PER_WEEK = 5; // only considering Mon to Fri

    private static Integer timetableWidth = 0;
    private static int[] dailyWidthArray = new int[DAY_PER_WEEK];
    private static String[][] timetableVertical;
    private static ArrayList<ArrayList<Object[]>> rawTimetable = new ArrayList<>(5);

    public static String viewTimetable() {
        populateRawTimetable(Timetable.getListOfModules());
        setTableWidth();
        System.out.println(timetableVertical[0].length + "TIMETABLE WIDTH");


        initializeTable();
        writeTableHeader();
        writeTable();
        return printTimetable(timetableVertical);
    }

    private static void populateRawTimetable(List<Module> listOfModules) {
        //Object[] rawModule = new Object[3];



        for (int i = 0; i < DAY_PER_WEEK; i++) {
            rawTimetable.add(new ArrayList<>());
        } 

        for (Module module : listOfModules) { // need close attention
            List<Lesson> lessons = module.getAttending();
            String name = module.getModuleCode();
            for (Lesson les : lessons) {
                int[] info = convertTimeToIndex(les.getDay(), les.getStartTime(), les.getEndTime());
                if (info[0] == -1) {
                    continue;
                }

               Object[] rawModule = new Object[3];
                rawModule[0] = info[1]; // starting slot
                rawModule[1] = info[2]; // ending slot
                rawModule[2] = name; // module name


                rawTimetable.get(info[0]).add(rawModule); // add each rawModule into respective day
            }

        }
        sortTimetable(rawTimetable);


        //.get(0) gives all lesson in that day; .get(0).get(0) gives you the 1st lesson in Monday
        //.get(0).get(0)[0] gives you the starting slot
        //RawtimeTable.get(0).isEmpty()  see if Monday is empty

    }

    /*private static void addToTimeline(String[][] timeline, String name, int[] info) {
        //info [0] is the day
        //info [1] is starting slot
        //info [2] is ending slot
        //daily slots are 24, 0800-0830-2000
        //name is correct here
        int cnt = 0;
        for (int i = info[1]; i <= info[2]; i++) {
            if (!Objects.equals(timeline[info[0]][i], blankTimeSlot)) {
                timeline[info[0]][i] = clashedTimeSlot;
                continue;
            }
            timeline[info[0]][i] = name.substring(0, Math.min(name.length(), 8)) + "|";

        }


    }*/

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

    /* private static String convertToString(String[][] timeline) {
        StringBuilder timetable = new StringBuilder();
        timetable.append("    |");

            // PRINTS THE TIME HEADINGS
        /*boolean isHalfHour = true;
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

        return addModuleInfo(timetable, timeline).toString();
    } */

   /*  private static String addRemarks(StringBuilder timetable) {
        timetable.append("\n * Note that timings indicated refers to the start of "
                + "the corresponding 30 minutes timeslot.\n"
                + " * Slots with XXXXXX indicates that there is a clash between two or more lessons.\n"
                + " * Modules, if any, that start or end beyond the 8am to 8pm timings are excluded.\n"
                + " * Timings are approximated to 30 minutes block with valid assumption that "
                + "NUS mods are typically designed in such blocks.\n");
        return timetable.toString();
    } */

   /*  private static StringBuilder addModuleInfo(StringBuilder timetable, String[][] timeline) {
        for (int i = 0; i < 5; i++) {
            //timetable.append(daysInWeek[i]);
            for (int j = 0; j < 24; j++) {
                timetable.append(timeline[i][j]);
            }
            timetable.append('\n');
        }
        return timetable;
    } */

    /* private static String getStringFromTime(int time) {
        if (time < 1000) {
            return " 0" + time + " |";
        } else {
            return " " + time + " |";
        }
    } */

    /* private static String[][] createTimeline() {
        String[][] timeline = new String[5][24];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 24; j++) {
                timeline[i][j] = blankTimeSlot;
            }
        }
        return timeline;
    } */

    private static ArrayList<ArrayList<Object[]>> sortTimetable(ArrayList<ArrayList<Object[]>> rawTimetable) {
        // sort modules to see conflict
        ArrayList<int[]> todayLessonSpan = new ArrayList<>();
        for (int i = 0; i < DAY_PER_WEEK; i++){  ////// change to iteration

            for (int j = 0; j < rawTimetable.get(i).size(); j++){ // loop through all lessons in that day
                if (!rawTimetable.get(i).isEmpty()) {

                    int startSlot = (Integer) rawTimetable.get(i).get(j)[0];
                    int endSlot = (Integer) rawTimetable.get(i).get(j)[1];

                    int[] pair = new int[2];
                    pair[0] = startSlot;
                    pair[1] = endSlot;
                    todayLessonSpan.add(pair);

                    System.out.println(i);
                    System.out.println(j);
                    System.out.println("THESE ARE I AND J");

                    System.out.println(todayLessonSpan.size() + "SIZEE");

                    /*for (int[] a: todayLessonSpan) {
                        for (int b: a) {
                            System.out.println(b + "AFT FOR LOOP");
                        }
                    }*/

                }
            }
            if (!todayLessonSpan.isEmpty()) {
                setDailyWidth(i,todayLessonSpan);
            }
        }

        return rawTimetable;

    }

    private static void setDailyWidth(Integer i, ArrayList<int[]> todayLessonSpan ) {
        Collections.sort(todayLessonSpan, new Comparator<>() {
            private static final int INDEX = 0;
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[INDEX], o2[INDEX]);
            }
        });
        int[][] transposedSortedTodayLessonSpan = transposeArray(todayLessonSpan);



        int dailyWidth = calculateDailyWidth(transposedSortedTodayLessonSpan);

        dailyWidthArray[i] = dailyWidth;

    }

    //UNTESTED:
    private static int[][] transposeArray(ArrayList<int[]> LessonSpan){
        int m = LessonSpan.size();
        int n = LessonSpan.get(0).length;

        int[][] transposedArray = new int[n][m];

        for(int x = 0; x < n; x++) {
            for(int y = 0; y < m; y++) {
                transposedArray[x][y] = LessonSpan.get(y)[x];
            }
        }

        return transposedArray;
    }

    private static int calculateDailyWidth(int[][] LessonSpan) {
        int i = 0;
        int j = 0;

        int currentWidth = 0;
        int maximumWidth = 0;
        int spanLength = LessonSpan[0].length;
        while (i < spanLength && j < spanLength) {
            int startSlot = LessonSpan[0][i];
            int endSlot = LessonSpan[1][j];
            if (startSlot < endSlot) {
                i++;
                currentWidth++;

            }
            else if (startSlot > endSlot){
                j++;
                currentWidth--;

            }
            maximumWidth = Math.max(currentWidth,maximumWidth);
        }

        return maximumWidth;
    }

    private static void setTableWidth() {
        timetableWidth = COLUMN_WIDTH;
        for (int i: dailyWidthArray) {
            if (i == 0 || i == 1) {
                timetableWidth += COLUMN_WIDTH;
            }
            else {
                timetableWidth += i * COLUMN_WIDTH;
            }
        }
        timetableVertical = new String[TIMETABLE_HEIGHT][timetableWidth];
    }

    private static void initializeTable() {
        for (int i = 0; i < TIMETABLE_HEIGHT; i++) {
            for (int j = 0; j < timetableWidth; j++) {
                timetableVertical[i][j] = " ";
            }
        }
    }



    //COPIED
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
                    getDayColumnIndex(i, dailyWidthArray[i]));
        }
        // draw border between day columns
        for (int i = 0; i < TIMETABLE_HEIGHT; i++) {
            for (int j = 0; j < DAY_PER_WEEK; j++) { // FIXED 3+24-1 for slots
                write("" + UI.DOTTED_CHAR, i, getDayColumnIndex(j, dailyWidthArray[j]));
            }
        }
    }

    private static void write(String text, int row, int column) {
        timetableVertical[row][column] = "" + text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            timetableVertical[row][column + i] = "" + text.charAt(i);
        }
    }
    private static boolean checkIsWritten(int row, int column) {
        return !Objects.equals(timetableVertical[row][column], " ");
    }

    private static String indexToTime(int index) {
        boolean isHalf = index % 2 == 0; // even index in timetable is half hours
        int hours = (index - 3) / 2 + FIRST_HOUR ; //index - 3 gives original slots, 0800 index is 3 but slot is 0
        return String.format("%02d", hours) + (isHalf ? "30" : "00");
    }

    private static int getDayColumnIndex(int day, int numberOfModules) { // day 0 is Monday
        int columnIndex = 0;
        for (int i = 0; i < day; i++) {
            columnIndex += Math.max(1,numberOfModules) * COLUMN_WIDTH;
        }
        return columnIndex + 11; // 11 is time column length xxx1000xxx
    }

    private static void writeTable() {
        //int horizontalSectionLength = 0;
        for (int i = 0; i < rawTimetable.size(); i++) {
            if (!rawTimetable.get(i).isEmpty()) {
                ArrayList<Object[]> dayIterator = rawTimetable.get(i);
                for (int j = 0; j < dayIterator.size(); j++) {
                   // horizontalSectionLength++;
                    Object[] rawModule = dayIterator.get(j);
                    int currentModstartSlot = (Integer) rawModule[0];
                    int currentModendSlot = (Integer) rawModule[1];
                    String currentModCode = (String) rawModule[2];
                    StringBuilder upperBoarder = new StringBuilder();
                    StringBuilder lowerBoarder = new StringBuilder();

                    lowerBoarder.append(UI.HORIZONTAL_BORDER.repeat(Math.max(0, COLUMN_WIDTH) - 1));

                    upperBoarder.append(UI.HORIZONTAL_BORDER.repeat(Math.max(0, COLUMN_WIDTH) - 1));

                    write(upperBoarder.toString(), currentModstartSlot + 3, calculateColumn(i,j,currentModstartSlot));

                    write(currentModCode, currentModstartSlot + 4, calculateColumn(i, j, currentModstartSlot) + 1);

                    write(lowerBoarder.toString(), currentModendSlot + 4, calculateColumn(i,j,currentModstartSlot));

                }
            }
        }
    }

    private static String printTimetable(String[][] timetableVertical) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < TIMETABLE_HEIGHT; i++) {
            for (int j = 0; j < timetableWidth ; j++) {
                output.append(timetableVertical[i][j]);
            }
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

    private static Integer calculateColumn (int dayOfWeek, int lessonOfDay, int currentModstartSlot) {
        int baselineColumnIndex = getDayColumnIndex(dayOfWeek,lessonOfDay) + 1;
        if (lessonOfDay == 0 || lessonOfDay == 1) {
            return baselineColumnIndex;
        }
        while (checkIsWritten(currentModstartSlot,baselineColumnIndex + 1)) {

           baselineColumnIndex += COLUMN_WIDTH;
        }
        return baselineColumnIndex;
    }



}



