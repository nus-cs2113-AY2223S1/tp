package recipeditor.exception;

public class ExcessFlagsException extends Exception {

    public ExcessFlagsException(String flagType) {
        super("There are excess " + flagType + " flags.");
    }
}
