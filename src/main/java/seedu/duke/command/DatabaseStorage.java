package seedu.duke.command;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.exceptions.InvalidDatabaseLineException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidModuleMappingException;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleMapping;
import seedu.duke.university.University;
import seedu.duke.parser.DatabaseParser;
import seedu.duke.ui.Ui;

public class DatabaseStorage {
    private static Logger logger = Logger.getLogger("DatabaseStorage");
    
    private static final String DATABASE_FILE_NAME = "data.csv";
    private static final String FILE_DIRECTORY = "data";

    private static final String PARTNER_UNVIERSITY_COUNTRY = "nil";

    /**
     * Helps user set up the database.
     */
    public static void createDatabase() throws IOException {
        File fileDir = new File(FILE_DIRECTORY);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
    }

    /**
     * Loads data from data.csv into database and creates data folder to store save
     * files.
     */
    public static void loadDatabase() {
        logger.log(Level.INFO, "Start loading database");

        try {
            readFile(DATABASE_FILE_NAME);
            createDatabase();
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }

        logger.log(Level.INFO, "Finish loading database");
    }

    /**
     * Read entire data.csv file and populate database.
     * 
     * @param filePath File path to data.csv
     * @throws IOException If data.csv not found at file path
     */
    private static void readFile(String filePath) throws IOException {
        ClassLoader classLoader = DatabaseStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // ignore the first line of csv
        String line = reader.readLine();

        int databaseLineNumber = 1;

        while (reader.ready()) {
            logger.log(Level.FINE, "Start reading database line " + databaseLineNumber);

            line = reader.readLine();

            String[] lineData = readDatabaseLine(line);
            updateDatabase(lineData);

            databaseLineNumber += 1;
        }

        reader.close();
    }

    /**
     * Get useful data to get ready to populate database.
     * 
     * @param line Current database line
     * @return Array of strings which contains all useful data
     */
    private static String[] readDatabaseLine(String line) {
        // cannot use new String[7] because of bug if empty "Pre Approved?" field
        String[] lineData = { "", "", "", "", "", "", "" };

        try {
            lineData = DatabaseParser.parseDatabaseLine(line);
        } catch (InvalidDatabaseLineException e) {
            Ui.printExceptionMessage(e);
        }

        return lineData;
    }

    /**
     * Update database with useful data from each line.
     * 
     * @param lineData Current array of strings which contains the useful data from
     *                 the current line
     */
    private static void updateDatabase(String[] lineData) {
        assert lineData.length == 7 : "Line data should have 7 elements";

        logger.log(Level.FINE, "Start updating database");

        String partnerUniversityName = lineData[0];
        String parterUniversityModuleCode = lineData[1];
        String partnerUnviersityModuleTitle = lineData[2];
        String partnerUniversityModuleCredit = lineData[3];
        String nusModuleCode = lineData[4];
        String nusModuleTitle = lineData[5];
        String nusModuleCredit = lineData[6];

        updateUniversityDatabase(partnerUniversityName, PARTNER_UNVIERSITY_COUNTRY);
        updateModuleMappingDatabase(partnerUniversityName, PARTNER_UNVIERSITY_COUNTRY, parterUniversityModuleCode,
                partnerUnviersityModuleTitle, partnerUniversityModuleCredit, nusModuleCode, nusModuleTitle,
                nusModuleCredit);

        logger.log(Level.FINE, "Finish updating database");
    }

    /**
     * Updates the university database with a new university.
     * 
     * @param partnerUniversityName    Name of current partner university
     * @param partnerUniversityCountry Country of current partner university
     */
    private static void updateUniversityDatabase(String partnerUniversityName, String partnerUniversityCountry) {
        logger.log(Level.FINER, "Start updating university database");

        try {
            University newUniversity = new University(partnerUniversityName, partnerUniversityCountry);
            Database.addUniversity(newUniversity);
        } catch (InvalidUniversityException e) {
            Ui.printExceptionMessage(e);
        }

        logger.log(Level.FINER, "Finish updating university database");
    }

    /**
     * Updates the module mapping database with a new module mapping.
     * 
     * @param partnerUniversityName         Name of current partner university
     * @param partnerUniversityCountry      Country of current partner university
     * @param parterUniversityModuleCode    Module code of current partner
     *                                      university module
     * @param partnerUnviersityModuleTitle  Module title of current partner
     *                                      university module
     * @param partnerUniversityModuleCredit Module credit of current partner
     *                                      university module
     * @param nusModuleCode                 Module code of current NUS module
     * @param nusModuleTitle                Module title of current NUS module
     * @param nusModuleCredit               Module credit of current NUS module
     */
    private static void updateModuleMappingDatabase(String partnerUniversityName, String partnerUniversityCountry,
            String parterUniversityModuleCode,
            String partnerUnviersityModuleTitle, String partnerUniversityModuleCredit,
            String nusModuleCode, String nusModuleTitle, String nusModuleCredit) {

        logger.log(Level.FINER, "Start updating module mapping database");

        try {
            University partnerUniversity = new University(partnerUniversityName, partnerUniversityCountry);
            Module partnerUniversityModule = new Module(parterUniversityModuleCode,
                    partnerUnviersityModuleTitle, partnerUniversityModuleCredit, partnerUniversity);
            University nusUniversity = new University("NUS", "Singapore");
            Module nusModule = new Module(nusModuleCode, nusModuleTitle, nusModuleCredit, nusUniversity);
            ModuleMapping newModuleMapping = new ModuleMapping(partnerUniversityModule, nusModule);

            Database.addModuleMapping(newModuleMapping);
        } catch (InvalidUniversityException | InvalidModuleException | InvalidModuleMappingException e) {
            Ui.printExceptionMessage(e);
        }

        logger.log(Level.FINER, "Finish updating module mapping database");
    }
}
