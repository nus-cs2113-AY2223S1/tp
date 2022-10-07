package seedu.duke.utils;

public class ConsoleBorder {
    // private static final char TOP_LEFT = '╓';
    // private static final char TOP_RIGHT = '╖';
    // private static final char TOP_MID = '╥';
    // private static final char SIDE = '║';
    // private static final char CROSS = '╫';
    // private static final char MID_LEFT = '╟';
    // private static final char MID_RIGHT = '╢';
    // private static final char ACROSS = '─';
    // private static final char BOTTOM_LEFT = '╙';
    // private static final char BOTTOM_RIGHT = '╜';
    // private static final char BOTTOM_MID = '╨';
    public static final char TOP_LEFT = '┌';
    public static final char TOP_RIGHT = '┐';
    public static final char TOP_MID = '┬';
    public static final char SIDE = '│';
    public static final char CROSS = '┼';
    public static final char MID_LEFT = '├';
    public static final char MID_RIGHT = '┤';
    public static final char ACROSS = '─';
    public static final char BOTTOM_LEFT = '└';
    public static final char BOTTOM_RIGHT = '┘';
    public static final char BOTTOM_MID = '┴';

    public static boolean isBorderLeft(char c) {
        return (c == TOP_LEFT || c == MID_LEFT || c == BOTTOM_LEFT);
    }

    public static boolean isBorderRight(char c) {
        return (c == TOP_RIGHT || c == MID_RIGHT || c == BOTTOM_RIGHT);
    }

    public static boolean isBorderTop(char c) {
        return (c == TOP_LEFT || c == TOP_MID || c == TOP_RIGHT);
    }

    public static boolean isBorderBottom(char c) {
        return (c == BOTTOM_LEFT || c == BOTTOM_MID || c == BOTTOM_RIGHT);
    }

    public static char mergeBorder(char current, char next) {
        // next is always TOP/BOTTOM_LEFT/RIGHT
        if (current == '·') {
            return next;
        }
        if (current == ' ') {
            return next;
        }
        if (current == CROSS || next == CROSS) {
            return CROSS;
        }
        if (next == SIDE) {
            char tmp = next;
            next = current;
            current = tmp;
        }
        if (current == SIDE) {
            if (next == TOP_LEFT || next == BOTTOM_LEFT || next == MID_LEFT) {
                return MID_LEFT;
            }
            if (next == TOP_RIGHT || next == BOTTOM_RIGHT || next == MID_RIGHT) {
                return MID_RIGHT;
            }
            return SIDE;
        }
        // none are sides or crosses
        if (current == TOP_MID) {
            return isBorderBottom(next) ? CROSS : current;
        }
        if (current == BOTTOM_MID) {
            return isBorderTop(next) ? CROSS : current;
        }
        if (current == MID_LEFT) {
            return isBorderRight(next) ? CROSS : current;
        }
        if (current == MID_RIGHT) {
            return isBorderLeft(next) ? CROSS : current;
        }
        if (isBorderLeft(current) != isBorderLeft(next)) {
            if (isBorderTop(current) != isBorderTop(next)) {
                return CROSS;
            }
            if (isBorderTop(current)) {
                return TOP_MID;
            }
            return BOTTOM_MID;
        }
        if (isBorderLeft(current)) {
            return MID_LEFT;
        }
        if (isBorderRight(current)) {
            return MID_RIGHT;
        }
        return next;
    }
}
