import java.util.ArrayList;
import java.util.Random;

public class Utils {
    public static final int NULL_INT = -1;
    private static int idCounter = 0;

    //todo add: regex things
    public static int generateId() {
        return idCounter++;
    }

}
