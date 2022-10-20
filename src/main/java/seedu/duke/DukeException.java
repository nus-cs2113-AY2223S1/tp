package seedu.duke;

public class DukeException extends Exception {
    
    /*
     * Returns error message
     * 
     * @return string representing the error message
     */
    @Override
    public String getMessage() {
        return "Incorrect usage of Duke commands.";
    }
}
