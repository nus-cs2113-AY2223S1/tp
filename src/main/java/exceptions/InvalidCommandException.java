package exceptions;

public class InvalidCommandException extends Exception {
    /**.
     * Exception thrown if any command fields is left empty
     * @return String containing error message to be outputted to user
     */
    @Override
    public String getMessage() {
        return "Wrong formatting of command!";
    }
}
