package seedu.duke.exceptions;

public class InvalidMcException extends Exception {
    /**
     * function to check if mc is valid or not
     * @param mcs mc entered by user. Format: int
     * @return returns true if mc is between 0 and 20 (both inclusive). Otherwise returns false. Format: boolean
     */
    public static boolean invalidMc(int mcs) {
        return mcs < 0 || mcs > 20;
    }

    public String getMessage() {
        return "* Invalid MC Input, must be 0 < mcs <= 20." + "\n";
    }
}
