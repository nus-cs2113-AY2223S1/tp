package seedu.duke.id;

public class IdGenerator {
    public static String generateId() {
        return Long.toString(System.currentTimeMillis()).substring(1, 11);
    }
}
