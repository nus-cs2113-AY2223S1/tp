package seedu.duke.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Module {

    public final String acadYear;
    public final String moduleCode;
    public final String title;
    public final String description;
    public final int moduleCredit;
    public final String department;
    public final String faculty;
    public final List<Integer> workload;
    public final List<SemesterData> semesterData;
    public final String prerequisite;
    public final String corequisite;
    public final String preclusion;

    private static List<Module> moduleList;
    private static Map<String, Module> modulesByCode;
    private static Map<String, Module> modulesByTitle;

    public static List<Module> getAll() {
        if (moduleList == null) {
            moduleList = ModuleLoader.loadModules();
            modulesByCode = new HashMap<>();
            for (Module m : moduleList) {
                modulesByCode.put(m.moduleCode, m);
            }
            modulesByTitle = new HashMap<>();
            for (Module m : moduleList) {
                modulesByTitle.put(m.title, m);
            }
        }
        return moduleList;
    }

    public static Module get(String moduleCode) {
        if (modulesByCode == null) {
            getAll();
        }
        return modulesByCode.get(moduleCode);
    }

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

    public SemesterData getSemesterData(int semester) {
        for (SemesterData s : semesterData) {
            if (s.semester == semester) {
                return s;
            }
        }
        return null;
    }
}
