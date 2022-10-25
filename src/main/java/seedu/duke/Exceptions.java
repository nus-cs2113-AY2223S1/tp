package seedu.duke;

public class Exceptions {
    public Exceptions.InvalidSemException invalidSemException;

    public static class InvalidSemException extends Exception {
    }

    public static class InvalidTimeslotException extends Exception {
    }

    public static class InvalidModuleCodeException extends Exception {
    }

    public static class FileLoadException extends Exception {
    }
}
