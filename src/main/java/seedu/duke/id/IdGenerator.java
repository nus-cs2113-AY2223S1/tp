package seedu.duke.id;

import java.util.UUID;

/**
 * A class of IdGenerator to generate unique id.
 */
public class IdGenerator {
    /**
     * Generates unique id for transaction and item.
     *
     * @return A string of id
     */
    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        String[] uid = uuid.toString().split("-");
        return uid[0];
    }


}
