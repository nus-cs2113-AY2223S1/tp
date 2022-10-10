package seedu.duke.user;

import seedu.duke.module.Module;

public class UserModule extends Module {
    private String nusCode;
    private String puCode;
    private String nusTitle;
    private String nusCredit;
    private String puTitle;

    private String comment;

    public UserModule(String puCode, String puTitle, String nusCode, String nusTitle, String nusCredit) {
        super(nusCode, nusTitle, nusCredit);
        assert puCode.length() > 0 : "PU module code length cannot be null";
        assert puTitle.length() > 0 : "PU module title length cannot be null";
        assert nusCode.length() > 0 : "NUS module code length cannot be null";
        assert nusTitle.length() > 0 : "NUS module title length cannot be null";
        assert nusCredit.length() > 0 : "NUS module credits length cannot be null";
        this.nusCode = nusCode;
        this.puCode = puCode;
        this.nusTitle = nusTitle;
        this.nusCredit = nusCredit;
        this.puTitle = puTitle;

    }

    public String getNusCode() {
        return nusCode;
    }

    public String getPuCode() {
        return puCode;
    }

    public String getNusTitle() {
        return nusTitle;
    }

    public String getNusCredit() {
        return nusCredit;
    }

    public String getPuTitle() {
        return puTitle;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setNusCode(String nusCode) {
        this.nusCode = nusCode;
    }

    public void setPuCode(String puCode) {
        this.puCode = puCode;
    }

    public void setNusTitle(String nusTitle) {
        this.nusTitle = nusTitle;
    }

    public void setNusCredit(String nusCredit) {
        this.nusCredit = nusCredit;
    }

    public void setPuTitle(String puTitle) {
        this.puTitle = puTitle;
    }

    public String toString() {
        return getNusCode() + " " + getNusTitle() + " "
                + getPuCode() + " " + getPuTitle() + " " + getNusCredit() + " MCs";
    }
}
