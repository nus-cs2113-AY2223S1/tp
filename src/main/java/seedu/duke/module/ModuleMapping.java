package seedu.duke.module;

import seedu.duke.exceptions.InvalidModuleMappingException;

public class ModuleMapping {
    private Module partnerUniversityModule;
    private Module nusModule;

    /**
     * Constructor for new module mapping.
     * 
     * @param partnerUniversityModule Partner university module part of the module
     *                                mapping
     * @param nusModule               NUS module part of the module mapping
     * @throws InvalidModuleMappingException If any constructor parameters are empty
     */
    public ModuleMapping(Module partnerUniversityModule, Module nusModule) throws InvalidModuleMappingException {
        if (!isValidModuleMapping(partnerUniversityModule, nusModule)) {
            throw new InvalidModuleMappingException(
                    "Invalid Module Mapping: " + partnerUniversityModule.toString() + " " + nusModule.toString());
        }

        setPartnerUniversityModule(partnerUniversityModule);
        setNusModule(nusModule);
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

    /**
     * Checks if module mapping is valid.
     * 
     * @param partnerUniversityModule Partner university module part of the module
     *                                mapping
     * @param nusModule               NUS module part of the module mapping
     * @return True if no empty parameters, false otherwise
     */
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
