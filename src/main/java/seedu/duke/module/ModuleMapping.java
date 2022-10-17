package seedu.duke.module;

public class ModuleMapping {
    private Module partnerUniversityModule;
    private Module nusModule;

    public ModuleMapping(Module partnerUniversityModule, Module nusModule) {
        this.partnerUniversityModule = partnerUniversityModule;
        this.nusModule = nusModule;
    }

    @Override
    public String toString() {
        return getPartnerUniversityModule() + " | " + getNusModule();
    }

    public Module getPartnerUniversityModule() {
        return partnerUniversityModule;
    }

    public void setPartnerUniversityModule(Module partnerUniversityModule) {
        this.partnerUniversityModule = partnerUniversityModule;
    }

    public Module getNusModule() {
        return nusModule;
    }

    public void setNusModule(Module nusModule) {
        this.nusModule = nusModule;
    }
}
