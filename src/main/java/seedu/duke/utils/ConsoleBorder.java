package seedu.duke.utils;

import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.lang3.SystemUtils;

public class ConsoleBorder {
    private final char topLeft;
    private final char topMid;
    private final char topRight;
    private final char side;
    private final char cross;
    private final char midLeft;
    private final char midRight;
    private final char across;
    private final char bottomLeft;
    private final char bottomRight;
    private final char bottomMid;

    private static ConsoleBorder singleton;
    private static ConsoleBorder styleSimpleSingleton;

    public static final char DOTTED_CHAR = ':';

    public static ConsoleBorder getInstance() {
        return getInstance(false);
    }

    public static ConsoleBorder getInstance(boolean isSimple) {
        if (isSimple) {
            if (styleSimpleSingleton == null) {
                styleSimpleSingleton = new ConsoleBorder(true);
            }
            return styleSimpleSingleton;
        }
        if (singleton == null) {
            singleton = new ConsoleBorder(false);
        }
        return singleton;
    }

    public char getSide() {
        return side;
    }

    public char getAcross() {
        return across;
    }

    public char get(int top, int left) {
        if (top == 1) {
            if (left == 1) {
                return topLeft;
            }
            if (left == 0) {
                return topMid;
            }
            if (left == -1) {
                return topRight;
            }
        }
        if (top == -1) {
            if (left == 1) {
                return bottomLeft;
            }
            if (left == 0) {
                return bottomMid;
            }
            if (left == -1) {
                return bottomRight;
            }
        }
        if (top == 0) {
            if (left == 1) {
                return midLeft;
            }
            if (left == 0) {
                return cross;
            }
            if (left == -1) {
                return midRight;
            }
        }
        return side;
    }

    private ConsoleBorder(boolean isStyleSimple) {
        String borderFileName = (isStyleSimple || SystemUtils.IS_OS_WINDOWS) ? "borderWindows.txt" : "border.txt";
        InputStream stream = ConsoleBorder.class.getClassLoader()
                .getResourceAsStream(borderFileName);
        Scanner scanner = new Scanner(stream);
        String s = scanner.nextLine();
        int counter = 0;
        topLeft = s.charAt(counter++);
        topMid = s.charAt(counter++);
        topRight = s.charAt(counter++);
        side = s.charAt(counter++);
        midLeft = s.charAt(counter++);
        cross = s.charAt(counter++);
        midRight = s.charAt(counter++);
        across = s.charAt(counter++);
        bottomLeft = s.charAt(counter++);
        bottomMid = s.charAt(counter++);
        bottomRight = s.charAt(counter++);
        scanner.close();
    }

    public boolean isBorderLeft(char c) {
        return (c == topLeft || c == midLeft || c == bottomLeft);
    }

    public boolean isBorderRight(char c) {
        return (c == topRight || c == midRight || c == bottomRight);
    }

    public boolean isBorderTop(char c) {
        return (c == topLeft || c == topMid || c == topRight);
    }

    public boolean isBorderBottom(char c) {
        return (c == bottomLeft || c == bottomMid || c == bottomRight);
    }

    public char mergeBorder(char current, char next) {
        assert next == topLeft || next == topRight || next == bottomLeft 
                || next == bottomRight || next == side :
                "mergeBorder called with invalid arguments";
        // next is always TOP/BOTTOM_LEFT/RIGHT
        if (current == DOTTED_CHAR) {
            return next;
        }
        if (current == ' ') {
            return next;
        }
        if (current == cross || next == cross) {
            return cross;
        }
        if (next == side) {
            char tmp = next;
            next = current;
            current = tmp;
        }
        if (current == side) {
            if (next == topLeft || next == bottomLeft || next == midLeft) {
                return midLeft;
            }
            if (next == topRight || next == bottomRight || next == midRight) {
                return midRight;
            }
            return side;
        }
        // none are sides or crosses
        if (current == topMid) {
            return isBorderBottom(next) ? cross : current;
        }
        if (current == bottomMid) {
            return isBorderTop(next) ? cross : current;
        }
        if (current == midLeft) {
            return isBorderRight(next) ? cross : current;
        }
        if (current == midRight) {
            return isBorderLeft(next) ? cross : current;
        }
        if (isBorderLeft(current) != isBorderLeft(next)) {
            if (isBorderTop(current) != isBorderTop(next)) {
                return cross;
            }
            if (isBorderTop(current)) {
                return topMid;
            }
            return bottomMid;
        }
        if (isBorderLeft(current)) {
            return midLeft;
        }
        if (isBorderRight(current)) {
            return midRight;
        }
        return next;
    }
}
