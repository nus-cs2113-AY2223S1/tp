package seedu.duke.command;

public abstract class Command implements CommandInterface {
    protected static final String DEFAULT_DELIMITER = " ";
    protected static final int DEFAULT_FIRST_INDEX = 0;
    protected static final int DEFAULT_INDEX_INCREMENT = 1;
    protected static final int EMPTY_ARRAY_SIZE = 0;
    protected static final int NOT_FOUND_INDEX = -1;
}
