package seedu.duke.Module;

public class Module {
    private String moduleName;
    private String moduleCode;

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public Module(String moduleName, String moduleCode) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
    }

    public String getModuleDetails() {
        return this.getModuleCode() + ": " + this.getModuleName();
    }
}
