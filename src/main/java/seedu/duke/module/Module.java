package seedu.duke.module;

public class Module {
    private String moduleName;
    private String moduleCode;
    private String moduleDescription;

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public Module(String moduleCode, String moduleName, String moduleDescription) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
    }

    public String getModuleDetails() {
        return this.getModuleCode() + ": " + this.getModuleName();
    }

    @Override
    public String toString() {
        return moduleCode + " : " + moduleName;
    }
}
