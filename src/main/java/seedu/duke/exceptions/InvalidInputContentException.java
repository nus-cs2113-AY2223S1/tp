package seedu.duke.exceptions;

public class InvalidInputContentException extends Exception {

    /**
     * function to check if the input entered by the user is empty or not
     * @param startIndex the beginning index of input
     * @param endIndex the ending index of input
     * @param input the input itself
     * @return true if the input content is empty. False if it is not. Format: boolean
     */
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
