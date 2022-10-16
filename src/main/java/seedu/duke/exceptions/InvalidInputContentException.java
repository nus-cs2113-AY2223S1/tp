package seedu.duke.exceptions;

public class InvalidInputContentException extends Exception {

    public static boolean emptyContent(int startIndex, int endIndex, String input) {
        if (startIndex == endIndex) {
            return true;
        } else if (startIndex == input.length()) {
            return true;
        } else {
            return false;
        }
    }

    //more to add: if content is wrong/not as required ...
}
