package seedu.duke.exceptions;

public class InvalidInputContentException extends Exception {

    /**
     * Function to check if the input entered by the user is empty or not.
     * @param startIndex the beginning index of input
     * @param endIndex the ending index of input
     * @param input the input itself
     * @return true if the input content is empty. False if it is not.
     */
    public static boolean emptyContent(int startIndex, int endIndex, String input) {
        if (startIndex == endIndex) {
            return true;
        } else {
            return startIndex == input.length();
        }
    }

    public String getMessage() {
        return "INPUT CONTENT IS WRONG!! PLEASE KEY IN THE CORRECT INPUT!!"
                + "\n"  + "input 'help' if you are unsure about the requirements" + "\n";
    }

}
