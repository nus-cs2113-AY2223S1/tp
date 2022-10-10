package seedu.duke;

public class Parser {
    private static final String EXIT_MESSAGE = "bye";
    private static final String PASSENGER_ENTITY = "passenger";
    private static final String FLIGHT_ENTITY = "flight";

    public static void parse(String lineInput) {
        String[] inputWords = lineInput.split("\\s+");
        String entity = inputWords[0];
        if (entity.equalsIgnoreCase(PASSENGER_ENTITY)) {
            PassengerParser.parse(inputWords);
        }

    }

    public static boolean isRunning(String lineInput) {
        return !lineInput.equalsIgnoreCase(EXIT_MESSAGE);
    }
}
