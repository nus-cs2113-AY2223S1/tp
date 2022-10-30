package seedu.duke.exceptions;

public class InvalidMcException extends Exception {
    public static boolean invalidMc(int mcs) {
        if (mcs < 0 || mcs > 20) {
            return true;
        }
        return false;
    }
}
