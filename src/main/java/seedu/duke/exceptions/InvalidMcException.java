package seedu.duke.exceptions;

public class InvalidMcException extends Exception {
    public static boolean invalidMc(int mcs) {
        if (mcs < 0 || mcs > 20) {
            return true;
        }
        return false;
    }

    public String getMessage() {
        String message = "* Invalid MC Input, must be 0 < mcs <= 20." + "\n"  ;
        return message;
    }
}
