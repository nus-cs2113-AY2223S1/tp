package seedu.duke.user;

import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.module.Module;
import seedu.duke.ui.Ui;
import seedu.duke.university.University;

public class UserModuleMapping {
    Module nusModule;
    Module puModule;
    private String comment;

    public UserModuleMapping(String puCode, String puTitle, String nusCode, String nusTitle,
            String nusCredit, String puCredit, String puName, String puCountry) {
        assert puCode.length() > 0 : "PU module code length cannot be null";
        assert puTitle.length() > 0 : "PU module title length cannot be null";
        assert puCredit.length() > 0 : "PU module credits length cannot be null";
        assert puName.length() > 0 : "PU name length cannot be null";
        assert nusCode.length() > 0 : "NUS module code length cannot be null";
        assert nusTitle.length() > 0 : "NUS module title length cannot be null";
        assert nusCredit.length() > 0 : "NUS module credits length cannot be null";

        try {
            University nus = new University("NUS", "Singapore");
            University pu = new University(puName, puCountry);
            nusModule = new Module(nusCode, nusTitle, nusCredit, nus);
            puModule = new Module(puCode, puTitle, puCredit, pu);
        } catch (InvalidUniversityException | InvalidModuleException e) {
            Ui.printExceptionMessage(e);
        }
    }

    public String getNusCode() {
        return nusModule.getCode();
    }

    public String getPuCode() {
        return puModule.getCode();
    }

    public String getNusTitle() {
        return nusModule.getTitle();
    }

    public String getNusCredit() {
        return nusModule.getCredit();
    }

    public String getPuTitle() {
        return puModule.getTitle();
    }

    public String getPuCredit() {
        return puModule.getCredit();
    }

    public String getPuName() {
        return puModule.getUniversity().getName();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setNusCode(String nusCode) {
        nusModule.setCode(nusCode);
    }

    public void setPuCode(String puCode) {
        puModule.setCode(puCode);
    }

    public void setNusTitle(String nusTitle) {
        nusModule.setTitle(nusTitle);
    }

    public void setNusCredit(String nusCredit) {
        nusModule.setCredit(nusCredit);
    }

    public void setPuTitle(String puTitle) {
        puModule.setTitle(puTitle);
    }

    @Override
    public String toString() {
        return getNusCode() + " " + getNusTitle() + " | " + getPuCode() + " " + getPuTitle()
                + " " + getNusCredit() + " MCs";
    }
}
