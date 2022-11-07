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
    protected static final String MODIFY_COMMAND = "modify";
    protected static final String DELAY_COMMAND = "delay";
    protected static final String PASSENGER_ENTITY = "passenger";
    protected static final String FLIGHT_ENTITY = "flight";
    protected static final int ENTITY_INDEX = 0;
    protected static final int OPERATION_INDEX = 1;
    public static final int MIN_LENGTH = 2;
    protected static final int ONE_WORD = 1;
    protected static final int DETAIL_INDEX = 1;
    protected static boolean isPassengerEntity = false;
    protected static boolean isFlightEntity = false;
    protected static boolean isAdd = false;
    protected static boolean isDelete = false;
    protected static boolean isList = false;
    protected static boolean isDelay = false;
    protected static boolean isExit = false;
    protected static boolean isModify = false;
    protected static boolean isBlankOperation = false;
    protected static String[] inputWords;
    protected static String entity;
    protected static String operation;
    protected static String[] passengerDetailArray;

    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    /**
     * Setups up the logger config and file for this class's activity documentation.
     */
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

    /**
     * Parse and make sense of the command that the user inputs.
     *
     * @param lineInput input of the user.
     * @return the type of command to be executed.
     * @throws SkyControlException an error if the input does not have a command or its blank
     */
    public static Command parse(String lineInput) throws SkyControlException {
        Command command;
        checkEntity(lineInput);
        if (isPassengerEntity) {
            command = PassengerParser.parse(inputWords);
        } else if (isFlightEntity) {
            command = FlightParser.parse(inputWords);
        } else if (isModify) {
            command = ModificationParser.parse(inputWords);
        } else if (isDelay) {
            command = ModificationParser.parse(inputWords);
        } else if (isExit) {
            command = new ExitCommand();
        } else {
            throw new SkyControlException(ui.getErrorMessage());
        }
        return command;
    }

    public static void checkEntity(String lineInput) throws SkyControlException {
        isPassengerEntity = isPassengerEntity(lineInput);
        isFlightEntity = isFlightEntity(lineInput);
        isExit = isExitCommand(lineInput);
        isModify = isModifyCommand(lineInput);
        isDelay = isDelayCommand(lineInput);
    }

    /**
     * Checks the operation of the user's Input.
     *
     * @param inputWords input of the user that have been split into two words, one for command,
     *                  and the other for type of operation.
     */
    public static void checkOperation(String[] inputWords) {
        operation = inputWords[OPERATION_INDEX];
        isAdd = operation.equalsIgnoreCase("add");
        isDelete = operation.equalsIgnoreCase("delete");
        isList = operation.equalsIgnoreCase("list");
    }

    //@@author shengiv
    public static boolean isPassengerEntity(String lineInput) throws SkyControlException {
        getEntity(lineInput);
        isPassengerEntity = entity.equalsIgnoreCase(PASSENGER_ENTITY);
        return isPassengerEntity;
    }

    public static boolean isFlightEntity(String lineInput) throws SkyControlException {
        getEntity(lineInput);
        isFlightEntity = entity.equalsIgnoreCase(FLIGHT_ENTITY);
        return isFlightEntity;
    }

    public static boolean isModifyCommand(String lineInput) throws SkyControlException {
        getEntity(lineInput);
        isModify = entity.equalsIgnoreCase(MODIFY_COMMAND);
        return isModify;
    }

    //@@author Franky4566
    public static boolean isDelayCommand(String lineInput) throws SkyControlException {
        getEntity(lineInput);
        isDelay = entity.equalsIgnoreCase(DELAY_COMMAND);
        return isDelay;
    }

    //@@author ivanthengwr
    public static boolean isExitCommand(String lineInput) {
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

    public static boolean getAdd(String lineInput) {
        getInputWords(lineInput);
        operation = inputWords[OPERATION_INDEX];
        isAdd = operation.equalsIgnoreCase("add");
        return isAdd;
    }

    //@@author shengiv
    public static void getInputWords(String lineInput) {
        inputWords = lineInput.split("\\s+");
    }

    public static boolean getDelete(String lineInput) {
        getInputWords(lineInput);
        operation = inputWords[OPERATION_INDEX];
        isDelete = operation.equalsIgnoreCase("delete");
        return isDelete;
    }

    /**
     * Checks what the operation is and retrieves only the passenger details from the String lineInput.
     *
     * @param lineInput user input from the CLI.
     * @return passengerDetail User input where the command is removed and only passenger details are present.
     * @throws SkyControlException If the operation name is not specified in the user input.
     */
    public static String getPassengerDetail(String lineInput) throws SkyControlException {
        Parser.getInputWords(lineInput);
        Parser.checkOperation(inputWords);
        String passengerDetail;
        if (isAdd) {
            checkAddOperationDetail(lineInput);
            passengerDetail = passengerDetailArray[DETAIL_INDEX].stripTrailing();
        } else if (isDelete) {
            checkDeleteOperationDetail(lineInput);
            passengerDetail = passengerDetailArray[DETAIL_INDEX].stripTrailing();
        } else {
            throw new SkyControlException(ui.getOperationError());
        }
        return passengerDetail;
    }

    //@@author ivanthengwr
    private static void checkBlankOperation(String lineInput) {
        boolean isNotBye = !lineInput.contains(EXIT_ENTITY);
        if (inputWords.length == ONE_WORD && isNotBye) {
            isBlankOperation = true;
        }
    }

    private static void checkDeleteOperationDetail(String lineInput) throws SkyControlException {
        passengerDetailArray = lineInput.split("delete");
        checkBlankDetailInput();
    }

    public static void checkAddOperationDetail(String lineInput) throws SkyControlException {
        passengerDetailArray = lineInput.split("add");
        checkBlankDetailInput();
    }

    public static void checkBlankDetailInput() throws SkyControlException {
        if (passengerDetailArray.length < MIN_LENGTH) {
            throw new SkyControlException(ui.getBlankOpsError());
        }
    }
}
