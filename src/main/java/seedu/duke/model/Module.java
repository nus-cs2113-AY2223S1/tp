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
public class Module implements Comparable<Module> {
    public final String acadYear;
    public final String moduleCode;
    public final String title;
    public final String description;
    public final int moduleCredit;
    public final String department;
    public final String faculty;
    public final List<Integer> workload; // workload represented as a list of 5 integers.
    public final List<SemesterData> semesterData;
    public final String prerequisite;
    public final String corequisite;
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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Module) {
            Module module = (Module) other;
            return this.moduleCode.equals(module.moduleCode);
        }
        return false;
    }

    @Override
    public int compareTo(Module other) {
        return moduleCode.compareTo(other.moduleCode);
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

    public List<Integer> getSemestersOffering() {
        List<Integer> semestersOffering = this.semesterData
                .stream()
                .map(semesterData -> semesterData.semester)
                .collect(Collectors.toList());
        return semestersOffering;
    }

    public boolean isOfferedInSemester(int semester) {
        return getSemestersOffering().contains(semester);
    }
}