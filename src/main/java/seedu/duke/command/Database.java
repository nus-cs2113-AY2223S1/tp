package seedu.duke.command;

import java.util.ArrayList;
import seedu.duke.module.Module;
import seedu.duke.university.University;

public class Database {
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
        if (!universities.contains(newUniversity)) {
            universities.add(newUniversity);
        }
    }

    public static void addPUModule(Module newModule) {
        partnerUniversityModules.add(newModule);
    }

    public static void addNUSModule(Module newModule) {
        nusModules.add(newModule);
    }
}
