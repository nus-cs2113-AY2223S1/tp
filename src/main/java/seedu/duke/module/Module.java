package seedu.duke.module;

import seedu.duke.module.lessons.Lesson;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private String moduleName;
    private String moduleCode;
    private String moduleDescription;
    private List<Lesson> lessons;

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public Module(String moduleCode, String moduleName, String moduleDescription, List<Lesson> lessons) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.lessons = lessons;
    }

    public String getModuleDetails() {
        return this.getModuleCode() + ": " + this.getModuleName();
    }

    @Override
    public String toString() {
        return moduleCode + " : " + moduleName;
    }
}
