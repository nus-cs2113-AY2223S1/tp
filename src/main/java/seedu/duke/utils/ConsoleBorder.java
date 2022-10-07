package seedu.duke.utils;

public class ConsoleBorder {
    public static final char TOP_LEFT = '\u250C';
    public static final char TOP_RIGHT = '\u2510';
    public static final char TOP_MID = '\u252C';
    public static final char SIDE = '\u2502';
    public static final char CROSS = '\u253C';
    public static final char MID_LEFT = '\u251C';
    public static final char MID_RIGHT = '\u2524';
    public static final char ACROSS = '\u2500';
    public static final char BOTTOM_LEFT = '\u2514';
    public static final char BOTTOM_RIGHT = '\u2518';
    public static final char BOTTOM_MID = '\u2534';

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
