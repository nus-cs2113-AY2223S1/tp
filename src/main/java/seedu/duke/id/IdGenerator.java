package seedu.duke.id;

import java.util.UUID;


public class IdGenerator {
    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        String[] uid = uuid.toString().split("-");
        return uid[0];
    //        return Long.toString(System.currentTimeMillis()).substring(1, 7);
    }


}
