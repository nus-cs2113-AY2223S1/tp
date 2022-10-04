package seedu.duke.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import seedu.duke.exceptions.InvalidDatabaseLineException;
import seedu.duke.module.Module;
import seedu.duke.university.University;
import seedu.duke.command.Database;

public class Storage {
    private static final String DATABASE_FILE_PATH = "./tp/data/data.csv";

    public static void loadDatabase() {
        try {
            readFile(DATABASE_FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found");
        }
    }

    private static void readFile(String filePath) throws FileNotFoundException {
        final String READING_FILE_START = "Reading file inputs...";
        final String READING_FILE_END = "Done reading file inputs!";

        File file = new File(filePath);

        Scanner scanner = new Scanner(file);

        System.out.println(READING_FILE_START);

        // ignore the first line of csv
        String line = scanner.nextLine();

        while (scanner.hasNext()) {
            line = scanner.nextLine();

            // can reuse this to read in other files
            switch (filePath) {
            case DATABASE_FILE_PATH:
                String[] lineData = readDatabaseLine(line);
                updateDatabase(lineData);

                break;

            default:
                break;
            }
        }

        System.out.println(READING_FILE_END);

        scanner.close();
    }

    private static String[] readDatabaseLine(String line) {
        String[] lineData = new String[7];

        try {
            lineData = Parser.parseDatabaseLine(line);
        } catch (InvalidDatabaseLineException e) {
            System.out.println("Invalid Database Line");
        }

        return lineData;
    }

    private static void updateDatabase(String[] lineData) {
        String partnerUniversity = lineData[0];
        String parterUniversityModuleCode = lineData[1];
        String partnerUnviersityModuleTitle = lineData[2];
        String partnerUniversityModuleCredit = lineData[3];
        String nusModuleCode = lineData[4];
        String nusModuleTitle = lineData[5];
        String nusModuleCredit = lineData[6];

        updateUniversityDatabase(partnerUniversity);
        updateNUSModuleDatabase(nusModuleCode, nusModuleTitle, nusModuleCredit);
        updatePartnerUniversityDatabase(parterUniversityModuleCode, partnerUnviersityModuleTitle,
                partnerUniversityModuleCredit);

    }

    private static void updateUniversityDatabase(String partnerUniversity) {
        // TODO: v2.0 add country for partner university
        University newUniversity = new University(partnerUniversity, "null");
        Database.addUniversity(newUniversity);
    }

    private static void updatePartnerUniversityDatabase(String parterUniversityModuleCode,
            String partnerUnviersityModuleTitle, String partnerUniversityModuleCredit) {
        Module newModule = new Module(parterUniversityModuleCode, partnerUnviersityModuleTitle,
                partnerUniversityModuleCredit);
        Database.addPartnerUniversityModule(newModule);
    }

    private static void updateNUSModuleDatabase(String nusModuleCode, String nusModuleTitle,
            String nusModuleCredit) {
        Module newModule = new Module(nusModuleCode, nusModuleTitle, nusModuleCredit);
        Database.addNUSModules(newModule);
    }
}
