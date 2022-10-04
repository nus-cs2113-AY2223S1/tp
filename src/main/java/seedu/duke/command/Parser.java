package seedu.duke.command;

import seedu.duke.exceptions.InvalidDatabaseLineException;

public class Parser {
    public static String[] parseDatabaseLine(String line) throws InvalidDatabaseLineException {
        String[] parts = line.split(",");

        if (parts.length != 15) {
            throw new InvalidDatabaseLineException();
        }

        String partnerUniversity = parts[1];
        String parterUniversityModuleCode = parts[2];
        String partnerUnviersityModuleTitle = parts[3];
        String partnerUniversityModuleCredit = parts[4];
        String nusModuleCode = parts[8];
        String nusModuleTitle = parts[9];
        String nusModuleCredit = parts[10];

        String[] lineData = {partnerUniversity, parterUniversityModuleCode,
                partnerUnviersityModuleTitle, partnerUniversityModuleCredit, nusModuleCode,
                nusModuleTitle, nusModuleCredit};

        return lineData;
    }
}
