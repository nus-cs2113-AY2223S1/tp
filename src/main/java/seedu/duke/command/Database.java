package seedu.duke.command;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.exceptions.UniversityNotFoundException;
import seedu.duke.module.ModuleMapping;
import seedu.duke.university.University;

public class Database {
    private static Logger logger = Logger.getLogger("Database");

    private static ArrayList<University> universities = new ArrayList<>();
    private static ArrayList<ModuleMapping> moduleMappings = new ArrayList<>();

    public static ArrayList<University> getUniversities() {
        return universities;
    }

    public static ArrayList<ModuleMapping> getModuleMappings() {
        return moduleMappings;
    }

    /**
     * Adds university to list of universities
     * 
     * @param newUniversity New university to be added
     */
    public static void addUniversity(University newUniversity) {
        if (isNewUniversity(newUniversity)) {
            logger.log(Level.FINE, "New university found, adding to list");

            universities.add(newUniversity);
        }
    }

    /**
     * Adds module mapping to list of module mappings
     * 
     * @param newModuleMapping New module mapping to be added
     */
    public static void addModuleMapping(ModuleMapping newModuleMapping) {
        moduleMappings.add(newModuleMapping);
    }

    /**
     * Checks if the new university has not been added to the current list of
     * universities before
     * 
     * @param newUniversity New university to be added
     * @return True if the university has not been added before, false otherwise
     */
    private static boolean isNewUniversity(University newUniversity) {
        assert newUniversity.getName().length() > 0 : "New university name cannot be empty";
        assert newUniversity.getCountry().length() > 0 : "New university country cannot be empty";

        for (University university : universities) {
            if (university.getName().equals(newUniversity.getName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Search for partner university module code in current list of module mappings
     * 
     * @param moduleCode Partner university module code to find
     * @return Module mapping which contains partner university module code
     * @throws ModuleNotFoundException If module code is not found inside of current
     *                                 list of module mappings
     */
    public static ModuleMapping findPuMapping(String moduleCode) throws ModuleNotFoundException {
        for (ModuleMapping moduleMapping : moduleMappings) {
            if (moduleMapping.getPartnerUniversityModule().getCode().equals(moduleCode)) {
                return moduleMapping;
            }
        }

        throw new ModuleNotFoundException("Error! " + moduleCode + " not found in database");
    }

    /**
     * Search for NUS module code in current list of module mappings
     * 
     * @param moduleCode NUS module code to find
     * @return List of module mappings which contains the NUS module code
     * @throws ModuleNotFoundException If there are no module mappings which
     *                                 contains the NUS module code
     */
    public static ArrayList<ModuleMapping> findNusMapping(String moduleCode) throws ModuleNotFoundException {
        ArrayList<ModuleMapping> nusMappings = new ArrayList<>();

        for (ModuleMapping moduleMapping : moduleMappings) {
            if (moduleMapping.getNusModule().getCode().equals(moduleCode)) {
                nusMappings.add(moduleMapping);
            }
        }

        if (nusMappings.size() == 0) {
            throw new ModuleNotFoundException("Error! " + moduleCode + " not found in database");
        }

        return nusMappings;
    }

    /**
     * Search for university name in current list of module mappings
     * 
     * @param universityName Name of university to find
     * @return List of module mappings which contains the university name
     * @throws UniversityNotFoundException If there are no module mappings which
     *                                     contains the university name
     */
    public static ArrayList<ModuleMapping> findUniversityMapping(String universityName)
            throws UniversityNotFoundException {
        ArrayList<ModuleMapping> universityMappings = new ArrayList<>();

        for (ModuleMapping moduleMapping : moduleMappings) {
            if (moduleMapping.getPartnerUniversityModule().getUniversity().getName().equals(universityName)) {
                universityMappings.add(moduleMapping);
            }
        }

        if (universityMappings.size() == 0) {
            throw new UniversityNotFoundException("Error! " + universityName + " not found in database");
        }

        return universityMappings;
    }

}
