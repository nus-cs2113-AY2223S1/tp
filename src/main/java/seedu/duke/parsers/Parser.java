package seedu.duke.parsers;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class Parser {
    protected static Command command;
    protected static Ui ui = new Ui();
    protected static final String EXIT_ENTITY = "bye";
    protected static final String PASSENGER_ENTITY = "passenger";
    protected static final String FLIGHT_ENTITY = "flight";
    protected static final int ENTITY_INDEX = 0;
    protected static final int OPERATION_INDEX = 1;
    protected static final int ONE_WORD = 1;
    protected static boolean isPassengerEntity = false;
    protected static boolean isFlightEntity = false;
    protected static boolean isAdd = false;
    protected static boolean isDelete = false;
    protected static boolean isList = false;
    protected static boolean isExit = false;
    protected static boolean isBlankOperation = false;
    protected static String[] inputWords;
    protected static String entity;
    protected static String operation;

    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        Parser.LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        Parser.LOGGER.addHandler(consoleHandler);
        createFileHandle();
        Parser.LOGGER.log(Level.INFO, ui.getLoggerStartUpMessage());
    }

    private static void createFileHandle() {
        try {
            FileHandler fileHandler = new FileHandler("ParserPassenger-logger.log");
            fileHandler.setLevel(Level.WARNING);
            Parser.LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            Parser.LOGGER.log(Level.SEVERE, ui.getLoggerError(), e);
        }
    }

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
            throw new SkyControlException(ui.getErrorMessage());
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
        try {
            getEntity(lineInput);
        } catch (SkyControlException e) {
            ui.showError(e.getMessage());
            return false;
        }
        isPassengerEntity = entity.equalsIgnoreCase(PASSENGER_ENTITY);
        return isPassengerEntity;
    }

    public static boolean isFlightEntity(String lineInput) {
        try {
            getEntity(lineInput);
        } catch (SkyControlException e) {
            return false;
        }
        isFlightEntity = entity.equalsIgnoreCase(FLIGHT_ENTITY);
        return isFlightEntity;
    }

    public static boolean isExitEntity(String lineInput) {
        try {
            getEntity(lineInput);
        } catch (SkyControlException e) {
            return isExit;
        }
        isExit = entity.equalsIgnoreCase(EXIT_ENTITY);
        return isExit;
    }

    public static void getEntity(String lineInput) throws SkyControlException {
        getInputWords(lineInput);
        checkBlankOperation(lineInput);
        if (isBlankOperation) {
            isBlankOperation = false;
            throw new SkyControlException(ui.getBlankOperationError());
        } else {
            entity = inputWords[ENTITY_INDEX];
        }
    }
    //@@author shengiv
    public static void getInputWords(String lineInput) {
        inputWords = lineInput.split("\\s+");
    }

    //@@author ivanthengwr
    private static void checkBlankOperation(String lineInput) {
        boolean isNotBye = !lineInput.contains(EXIT_ENTITY);
        if (inputWords.length == ONE_WORD && isNotBye) {
            isBlankOperation = true;
        }
    }
}
