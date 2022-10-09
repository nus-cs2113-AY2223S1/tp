package seedu.duke.command;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.module.Module;
import seedu.duke.university.University;

public class Database {
    private static Logger logger = Logger.getLogger("Database");

    private static ArrayList<University> universities = new ArrayList<>();
    private static ArrayList<Module> partnerUniversityModules = new ArrayList<>();
    private static ArrayList<Module> nusModules = new ArrayList<>();

    public static ArrayList<University> getUniversities() {
        return universities;
    }

    public static ArrayList<Module> getpartnerUniversityModules() {
        return partnerUniversityModules;
    }

    public static ArrayList<Module> getnusModules() {
        return nusModules;
    }

    public static void addUniversity(University newUniversity) {
        assert newUniversity.getName().length() > 0 : "New university name cannot be empty";
        assert newUniversity.getCountry().length() > 0 : "New university country cannot be empty";

        if (isNewUniversity(newUniversity)) {
            logger.log(Level.FINE, "New university found, adding to list");

            universities.add(newUniversity);
        }
    }

    public static void addPartnerUniversityModule(Module newModule) {
        assert newModule.getCode().length() > 0 : "New module code cannot be empty";
        assert newModule.getTitle().length() > 0 : "New module title cannot be empty";
        assert newModule.getCredit().length() > 0 : "New module credit cannot be empty";

        partnerUniversityModules.add(newModule);
    }

    public static void addNusModules(Module newModule) {
        nusModules.add(newModule);
    }

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
}
