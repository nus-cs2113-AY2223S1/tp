package seedu.duke.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Module data for a single module. A module is identified by its module code. A module contains one or more
 * semester data objects containing information about the particular offering in the semester, such as 
 * the examination details and class schedules. 
 * Module properties are all "public final" as they are not meant to be freely accessed but not modified.
 * Based off https://github.com/nusmodifications/nusmods/blob/master/scrapers/nus-v2/src/types/modules.ts
 */
public class Module {
    /**
     * Academic year e.g. "2022/2023".
     */
    public final String acadYear;
    /**
     * Module code.
     */
    public final String moduleCode;
    /**
     * Module title.
     */
    public final String title;
    /**
     * Module description.
     */
    public final String description;
    /**
     * Number of modular credits for this module.
     */
    public final int moduleCredit;
    /**
     * Department in charge of this module.
     */
    public final String department;
    /**
     * Faculty in charge of this module.
     */
    public final String faculty;
    /**
     * Workload represented as a list of 5 integers.
     */
    public final List<Integer> workload;
    /**
     * Data for each semester this module is offered in.
     */
    public final List<SemesterData> semesterData;
    /**
     * Prerequisite list for this module. May not be in a structured format.
     */
    public final String prerequisite;
    /**
     * Corequisites for this module.
     */
    public final String corequisite;
    /**
     * Preclusions for this module.
     */
    public final String preclusion;

    /**
     * All modules offered in the current academic year, to be loaded lazily once per run of the application.
     */
    private static List<Module> moduleList;
    /**
     * All modules offered in the current academic year, indexed by module code.
     */
    private static Map<String, Module> modulesByCode;

    /**
     * Retrieves all modules in the current academic year. Loads the list from the resource file lazily.
     * @return A list of all modules offered in the current academic year.
     */
    public static List<Module> getAll() {
        if (moduleList == null) {
            moduleList = ModuleLoader.loadModules();
            modulesByCode = new HashMap<>();
            for (Module m : moduleList) {
                modulesByCode.put(m.moduleCode, m);
            }
        }
        return moduleList;
    }

    /**
     * Retrieves data for a single module by module code.
     * @param moduleCode The module code of the desired module.
     * @return Module data for the module.
     */
    public static Module get(String moduleCode) {
        moduleCode = moduleCode.toUpperCase();
        if (modulesByCode == null) {
            getAll();
        }
        return modulesByCode.get(moduleCode);
    }

    /**
     * Constructor for the Module class. This should only be used by the ModuleLoader class.
     * @param acadYear Academic year
     * @param moduleCode Module code
     * @param title Module title
     * @param description Module description
     * @param moduleCredit Modular credits for this module
     * @param department Department offering the module
     * @param faculty Faculty in charge of the module
     * @param workload Workload for the module
     * @param semesterData Semester data for the module
     * @param prerequisite Prerequisite information
     * @param corequisite Corequisites for the module
     * @param preclusion Preclusions for the module
     */
    Module(String acadYear,
           String moduleCode,
           String title,
           String description,
           int moduleCredit,
           String department,
           String faculty,
           List<Integer> workload,
           List<SemesterData> semesterData,
           String prerequisite,
           String corequisite,
           String preclusion) {
        this.acadYear = acadYear;
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
        this.moduleCredit = moduleCredit;
        this.department = department;
        this.faculty = faculty;
        this.workload = workload;
        this.semesterData = semesterData;
        this.prerequisite = prerequisite;
        this.corequisite = corequisite;
        this.preclusion = preclusion;
    }

    /**
     * Gets the semester data for a particular semester.
     * @param semester An integer from 1 to 4 representing S1, S2, ST1 and ST2 respectively
     * @return The semester data object or null if the module is not offered in that semester
     */
    public SemesterData getSemesterData(int semester) {
        for (SemesterData s : semesterData) {
            if (s.semester == semester) {
                return s;
            }
        }
        return null;
    }

    public int getLevel() {
        int level = moduleCode.replaceAll("[^0-9]", "").charAt(0) - '0';
        return level;
    }

    // get the semesters that the module is offered in
    public List<Integer> getSemestersOffering() {
        List<Integer> semestersOffering = this.semesterData
                .stream()
                .map(semesterData -> semesterData.semester)
                .collect(Collectors.toList());
        return semestersOffering;
    }
}