package seedu.duke.module;

import seedu.duke.exceptions.InvalidModuleMappingException;

public class ModuleMapping {
    private Module partnerUniversityModule;
    private Module nusModule;

    public ModuleMapping(Module partnerUniversityModule, Module nusModule) throws InvalidModuleMappingException {
        if (!isValidModuleMapping(partnerUniversityModule, nusModule)) {
            throw new InvalidModuleMappingException(
                    "Invalid Module Mapping: " + partnerUniversityModule.toString() + " " + nusModule.toString());
        }

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

    private boolean isValidModuleMapping(Module partnerUniversityModule, Module nusModule) {
        if (partnerUniversityModule.getCode().length() == 0) {
            return false;
        }

        if (partnerUniversityModule.getTitle().length() == 0) {
            return false;
        }

        if (partnerUniversityModule.getCredit().length() == 0) {
            return false;
        }

        if (partnerUniversityModule.getUniversity().getName().length() == 0) {
            return false;
        }

        if (partnerUniversityModule.getUniversity().getCountry().length() == 0) {
            return false;
        }

        if (nusModule.getCode().length() == 0) {
            return false;
        }

        if (nusModule.getTitle().length() == 0) {
            return false;
        }

        if (nusModule.getCredit().length() == 0) {
            return false;
        }

        if (nusModule.getUniversity().getName().length() == 0) {
            return false;
        }

        if (nusModule.getUniversity().getCountry().length() == 0) {
            return false;
        }

        return true;
    }
}
