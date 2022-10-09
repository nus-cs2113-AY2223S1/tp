package seedu.duke.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.exceptions.InvalidDatabaseLineException;

public class Parser {
    private static Logger logger = Logger.getLogger("Parser");

    public static String[] parseDatabaseLine(String line) throws InvalidDatabaseLineException {
        logger.log(Level.FINE, "Start parsing database line");

        String[] parts = line.split(",");

        if (parts.length != 15) {
            throw new InvalidDatabaseLineException();
        }

        String partnerUniversity = parts[1];
        assert partnerUniversity.length() > 0 : "Partner University should not be empty";

        String parterUniversityModuleCode = parts[2];
        assert parterUniversityModuleCode.length() > 0
                : "Partner University Module Code should not be empty";

        String partnerUnviersityModuleTitle = parts[3];
        assert partnerUnviersityModuleTitle.length() > 0
                : "Partner University Module Title should not be empty";

        String partnerUniversityModuleCredit = parts[4];
        assert partnerUniversityModuleCredit.length() > 0
                : "Partner University Module Credit should not be empty";

        String nusModuleCode = parts[8];
        assert nusModuleCode.length() > 0
                : "NUS Module Code should not be empty";

        String nusModuleTitle = parts[9];
        assert nusModuleTitle.length() > 0
                : "NUS Module Title should not be empty";

        String nusModuleCredit = parts[10];
        assert nusModuleCredit.length() > 0
                : "NUS Module Credit should not be empty";

        String[] lineData = { partnerUniversity, parterUniversityModuleCode,
                partnerUnviersityModuleTitle, partnerUniversityModuleCredit, nusModuleCode,
                nusModuleTitle, nusModuleCredit };

        logger.log(Level.FINE, "End parsing database line");

        return lineData;
    }
}
