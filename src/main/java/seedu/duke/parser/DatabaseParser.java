package seedu.duke.parser;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.exceptions.InvalidDatabaseLineException;

public class DatabaseParser {
    private static Logger logger = Logger.getLogger("DatabaseParser");

    public static String[] parseDatabaseLine(String line) throws InvalidDatabaseLineException {
        logger.log(Level.FINE, "Start parsing database line");

        String[] parts = line.split(",");

        if (parts.length != 15) {
            throw new InvalidDatabaseLineException("Invalid database line: " + line);
        }

        String partnerUniversity = parts[1];
        String partnerUniversityModuleCode = parts[2];
        String partnerUniversityModuleTitle = parts[3];
        String partnerUniversityModuleCredit = parts[4];
        String nusModuleCode = parts[8];
        String nusModuleTitle = parts[9];
        String nusModuleCredit = parts[10];

        String[] lineData = { partnerUniversity, partnerUniversityModuleCode,
                partnerUniversityModuleTitle, partnerUniversityModuleCredit, nusModuleCode,
                nusModuleTitle, nusModuleCredit };

        logger.log(Level.FINE, "End parsing database line");

        return lineData;
    }
}
