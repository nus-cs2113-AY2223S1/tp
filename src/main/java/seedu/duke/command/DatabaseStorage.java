package seedu.duke.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.exceptions.InvalidDatabaseLineException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleMapping;
import seedu.duke.university.University;
import seedu.duke.command.Database;
import seedu.duke.parser.DatabaseParser;

public class DatabaseStorage {
    private static Logger logger = Logger.getLogger("DatabaseStorage");
    private static final String DATABASE_FILE_PATH = "./data/data.csv";

    public static void loadDatabase() {
        logger.log(Level.INFO, "Start loading database");

        try {
            readFile(DATABASE_FILE_PATH);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Database file not found");
        }

        logger.log(Level.INFO, "Finish loading database");
    }

    private static void readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);

        Scanner scanner = new Scanner(file);

        // ignore the first line of csv
        String line = scanner.nextLine();

        int databaseLineNumber = 1;

        while (scanner.hasNext()) {
            logger.log(Level.FINE, "Start reading database line " + databaseLineNumber);

            line = scanner.nextLine();

            String[] lineData = readDatabaseLine(line);
            updateDatabase(lineData);

            databaseLineNumber += 1;
        }

        scanner.close();
    }

    private static String[] readDatabaseLine(String line) {
        String[] lineData = new String[7];

        try {
            lineData = DatabaseParser.parseDatabaseLine(line);
        } catch (InvalidDatabaseLineException e) {
            logger.log(Level.WARNING, "Invalid database line: " + line);
        }

        return lineData;
    }

    private static void updateDatabase(String[] lineData) {
        assert lineData.length == 7 : "Line data should have 7 elements";

        logger.log(Level.FINE, "Start updating database");

        String partnerUniversityName = lineData[0];
        assert partnerUniversityName.length() > 0 : "Partner University should not be empty";

        // TODO: v2.0 add country for partner university
        String partnerUniversityCountry = "null";

        String parterUniversityModuleCode = lineData[1];
        assert parterUniversityModuleCode.length() > 0
                : "Partner University Module Code should not be empty";

        String partnerUnviersityModuleTitle = lineData[2];
        assert partnerUnviersityModuleTitle.length() > 0
                : "Partner University Module Title should not be empty";

        String partnerUniversityModuleCredit = lineData[3];
        assert partnerUniversityModuleCredit.length() > 0
                : "Partner University Module Credit should not be empty";

        String nusModuleCode = lineData[4];
        assert nusModuleCode.length() > 0 : "NUS Module Code should not be empty";

        String nusModuleTitle = lineData[5];
        assert nusModuleTitle.length() > 0 : "NUS Module Title should not be empty";

        String nusModuleCredit = lineData[6];
        assert nusModuleCredit.length() > 0 : "NUS Module Credit should not be empty";

        updateUniversityDatabase(partnerUniversityName, partnerUniversityCountry);
        updateModuleMappingDatabase(partnerUniversityName, partnerUniversityCountry, parterUniversityModuleCode,
                partnerUnviersityModuleTitle,
                partnerUniversityModuleCredit, nusModuleCode, nusModuleTitle, nusModuleCredit);

        logger.log(Level.FINE, "Finish updating database");
    }

    private static void updateUniversityDatabase(String partnerUniversityName, String partnerUniversityCountry) {
        assert partnerUniversityName.length() > 0 : "Partner University should not be empty";

        logger.log(Level.FINER, "Start updating university database");

        University newUniversity = new University(partnerUniversityName, partnerUniversityCountry);
        Database.addUniversity(newUniversity);

        logger.log(Level.FINER, "Finish updating university database");
    }

    private static void updateModuleMappingDatabase(String partnerUniversityName, String partnerUniversityCountry,
            String parterUniversityModuleCode,
            String partnerUnviersityModuleTitle, String partnerUniversityModuleCredit,
            String nusModuleCode, String nusModuleTitle, String nusModuleCredit) {
        assert parterUniversityModuleCode.length() > 0
                : "Partner University Module Code should not be empty";
        assert partnerUnviersityModuleTitle.length() > 0
                : "Partner University Module Title should not be empty";
        assert partnerUniversityModuleCredit.length() > 0
                : "Partner University Module Credit should not be empty";
        assert nusModuleCode.length() > 0 : "NUS Module Code should not be empty";
        assert nusModuleTitle.length() > 0 : "NUS Module Title should not be empty";
        assert nusModuleCredit.length() > 0 : "NUS Module Credit should not be empty";

        logger.log(Level.FINER, "Start updating module mapping database");

        University partnerUniversity = new University(partnerUniversityName, partnerUniversityCountry);
        Module partnerUniversityModule = new Module(parterUniversityModuleCode,
                partnerUnviersityModuleTitle, partnerUniversityModuleCredit, partnerUniversity);
        University nusUniversity = new University("NUS", "Singapore");
        Module nusModule = new Module(nusModuleCode, nusModuleTitle, nusModuleCredit, nusUniversity);
        ModuleMapping newModuleMapping = new ModuleMapping(partnerUniversityModule, nusModule);

        Database.addModuleMapping(newModuleMapping);

        logger.log(Level.FINER, "Finish updating module mapping database");
    }
}
