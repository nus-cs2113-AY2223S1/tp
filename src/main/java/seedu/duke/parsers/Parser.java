package seedu.duke.parsers;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.ui.Ui;

public class Parser {
    protected static Ui ui = new Ui();
    protected static final String EXIT_ENTITY = "bye";
    protected static final String PASSENGER_ENTITY = "passenger";
    protected static final String FLIGHT_ENTITY = "flight";
    protected static final int ENTITY_INDEX = 0;
    protected static final int OPERATION_INDEX = 1;
    protected static Command command;
    protected static boolean isPassengerEntity = false;
    protected static boolean isFlightEntity = false;
    protected static boolean isAdd = false;
    protected static boolean isDelete = false;
    protected static boolean isList = false;

    protected static boolean isExit = false;
    protected static String[] inputWords;
    protected static String entity;
    protected static String operation;

    public static Command parse(String lineInput) throws SkyControlException {
        Command command;
        checkEntity(lineInput);
        if (isPassengerEntity) {
            command = PassengerParser.parse(inputWords);
        } else if (isFlightEntity) {
            command = FlightParser.parse(inputWords);
        } else if (isExit) {
            command = new ExitCommand();
        } else {
            throw new SkyControlException(ui.showErrorMessage());
        }
        return command;
    }

    public static void checkEntity(String lineInput) {
        isPassengerEntity = isPassengerEntity(lineInput);
        isFlightEntity = isFlightEntity(lineInput);
        isExit = isExitEntity(lineInput);
    }

    public static void checkOperation(String[] inputWords) {
        operation = inputWords[OPERATION_INDEX];
        isAdd = operation.equalsIgnoreCase("add");
        isDelete = operation.equalsIgnoreCase("delete");
        isList = operation.equalsIgnoreCase("list");
    }

    public static boolean isPassengerEntity(String lineInput) {
        getEntity(lineInput);
        isPassengerEntity = entity.equalsIgnoreCase(PASSENGER_ENTITY);
        return isPassengerEntity;
    }

    public static boolean isFlightEntity(String lineInput) {
        getEntity(lineInput);
        isFlightEntity = entity.equalsIgnoreCase(FLIGHT_ENTITY);
        return isFlightEntity;
    }

    public static boolean isExitEntity(String lineInput) {
        getEntity(lineInput);
        isExit = entity.equalsIgnoreCase(EXIT_ENTITY);
        return isExit;
    }

    public static void getEntity(String lineInput) {
        getInputWords(lineInput);
        entity = inputWords[ENTITY_INDEX];
    }

    public static void getInputWords(String lineInput) {
        inputWords = lineInput.split("\\s+");
    }
}
