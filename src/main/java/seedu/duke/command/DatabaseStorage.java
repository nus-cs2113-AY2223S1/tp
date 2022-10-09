package seedu.duke.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.exceptions.InvalidDatabaseLineException;
import seedu.duke.module.Module;
import seedu.duke.university.University;
import seedu.duke.command.Database;

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

        lineData = Parser.parseDatabaseLine(line);

        return lineData;
    }

    private static void updateDatabase(String[] lineData) {
        assert lineData.length == 7 : "Line data should have 7 elements";

        logger.log(Level.FINE, "Start updating database");

        String partnerUniversity = lineData[0];
        assert partnerUniversity.length() > 0 : "Partner University should not be empty";

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
        assert nusModuleCode.length() > 0
                : "NUS Module Code should not be empty";

        String nusModuleTitle = lineData[5];
        assert nusModuleTitle.length() > 0
                : "NUS Module Title should not be empty";

        String nusModuleCredit = lineData[6];
        assert nusModuleCredit.length() > 0
                : "NUS Module Credit should not be empty";

        updateUniversityDatabase(partnerUniversity);
        updateNusModuleDatabase(nusModuleCode, nusModuleTitle, nusModuleCredit);
        updatePartnerUniversityDatabase(parterUniversityModuleCode, partnerUnviersityModuleTitle,
                partnerUniversityModuleCredit);

        logger.log(Level.FINE, "Finish updating database");
    }

    private static void updateUniversityDatabase(String partnerUniversity) {
        assert partnerUniversity.length() > 0 : "Partner University should not be empty";

        logger.log(Level.FINER, "Start updating university database");

        // TODO: v2.0 add country for partner university
        University newUniversity = new University(partnerUniversity, "null");
        Database.addUniversity(newUniversity);

        logger.log(Level.FINER, "Finish updating university database");
    }

    private static void updatePartnerUniversityDatabase(String parterUniversityModuleCode,
            String partnerUnviersityModuleTitle, String partnerUniversityModuleCredit) {
        assert parterUniversityModuleCode.length() > 0
                : "Partner University Module Code should not be empty";
        assert partnerUnviersityModuleTitle.length() > 0
                : "Partner University Module Title should not be empty";
        assert partnerUniversityModuleCredit.length() > 0
                : "Partner University Module Credit should not be empty";

        logger.log(Level.FINER, "Start updating partner university module database");

        Module newModule = new Module(parterUniversityModuleCode, partnerUnviersityModuleTitle,
                partnerUniversityModuleCredit);
        Database.addPartnerUniversityModule(newModule);

        logger.log(Level.FINER, "Finish updating partner university module database");
    }

    private static void updateNusModuleDatabase(String nusModuleCode, String nusModuleTitle,
            String nusModuleCredit) {
        assert nusModuleCode.length() > 0
                : "NUS Module Code should not be empty";
        assert nusModuleTitle.length() > 0
                : "NUS Module Title should not be empty";
        assert nusModuleCredit.length() > 0
                : "NUS Module Credit should not be empty";

        logger.log(Level.FINER, "Start updating nus module database");

        Module newModule = new Module(nusModuleCode, nusModuleTitle, nusModuleCredit);
        Database.addNusModules(newModule);

        logger.log(Level.FINER, "Finish updating nus module database");
    }
}
