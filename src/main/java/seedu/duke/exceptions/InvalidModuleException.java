package seedu.duke.exceptions;

public class InvalidModuleException extends Exception {
    private static final String ERROR_MESSAGE = "Module is invalid!"
            + System.lineSeparator()
            + "Please enter a valid module code." + System.lineSeparator()
            + "Each module of study has a unique module code consisting of a two- "
            + "or three-letter prefix that generally denotes the discipline,"
            + "and four digits, the first of which indicates the level of the module " + System.lineSeparator()
            + "(e.g., 1000 indicates a Level 1 module and 2000, a Level 2 module).";

    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }
}
