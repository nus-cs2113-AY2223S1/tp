package seedu.duke.exceptions;


public class InvalidCommandWordException extends Exception {

    public String getMessage() {
        return "NO COMMAND WORD SPECIFIED!! PLEASE KEY IN THE CORRECT INPUT!!"
                + "\n" + "input 'help' if you are unsure about the requirements" + "\n";
    }
}
