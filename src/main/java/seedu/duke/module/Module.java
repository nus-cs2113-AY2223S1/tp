package seedu.duke.module;

import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.university.University;

public class Module {
    private String code;
    private String title;
    private String credit;
    private University university;

    public Module(String code, String title, String credit, University university) throws InvalidModuleException {
        if (!isValidModule(code, title, credit, university)) {
            throw new InvalidModuleException(
                    "Invalid Module: " + code + " " + title + " " + credit + "MCs in " + university.toString());
        }

        setCode(code);
        setTitle(title);
        setCredit(credit);
        setUniversity(university);
    }

    @Override
    public String toString() {
        return getCode() + " " + getTitle() + " " + getCredit() + "MCs in " + getUniversity();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    private boolean isValidModule(String code, String title, String credit, University university) {
        if (code.length() == 0) {
            return false;
        }

        if (title.length() == 0) {
            return false;
        }

        if (credit.length() == 0) {
            return false;
        }

        if (university.getName().length() == 0) {
            return false;
        }

        if (university.getCountry().length() == 0) {
            return false;
        }

        return true;
    }
}
