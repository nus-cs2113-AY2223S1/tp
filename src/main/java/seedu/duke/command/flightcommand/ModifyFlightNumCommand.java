package seedu.duke.command.flightcommand;

import seedu.duke.command.Command;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.operationlist.OperationList;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

/**
 * Handles execution of methods when the user tries to modify the flight number of a flight.
 */
public class ModifyFlightNumCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ModifyFlightNumCommand.class.getName());

    /**
     * Setups up the logger config and file for this class's activity documentation.
     */
    public static void setupLogger() {
        LogManager.getLogManager().reset();
        ModifyFlightNumCommand.LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        ModifyFlightNumCommand.LOGGER.addHandler(consoleHandler);
        createFileHandle();
        ModifyFlightNumCommand.LOGGER.log(Level.INFO, ui.getLoggerStartUpMessage());
    }

    private static void createFileHandle() {
        try {
            FileHandler fileHandler = new FileHandler("ModifyFlightNum-logger.log");
            fileHandler.setLevel(Level.WARNING);
            ModifyFlightNumCommand.LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            ModifyFlightNumCommand.LOGGER.log(Level.SEVERE, ui.getLoggerError(), e);
        }
    }

    @Override
    public void execute(OperationList entityList, String lineInput) throws SkyControlException {
        String[] inputWords;
        inputWords = lineInput.split("\\s+");
        String flightNum = getFlightNumFromModifyCmd(inputWords);
        String newFlightNum = getModifiedDetail(inputWords);
        entityList.modifyFlightNum(flightNum, newFlightNum);
    }
}
