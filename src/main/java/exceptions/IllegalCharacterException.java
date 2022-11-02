package exceptions;

public class IllegalCharacterException extends Exception {
    /**.
     * Exception thrown if illegal characters are added within the command.
     * @return String containing error message to be outputted to user
     */
    @Override
    public String getMessage() {
        return "Illegal character used";
    }
}
