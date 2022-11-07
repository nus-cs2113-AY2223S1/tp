package seedu.duke.module;

import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.university.University;

public class Module {
    private String code;
    private String title;
    private String credit;
    private University university;

    /**
     * Constructor for new module.
     * 
     * @param code       Module code of new module
     * @param title      Module title of new module
     * @param credit     Module credit of new module
     * @param university University that the new module belongs to
     * @throws InvalidModuleException If any constructor parameters are empty
     */
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

    /**
     * Checks if module is valid.
     * 
     * @param code       Module code of new module
     * @param title      Module title of new module
     * @param credit     Module credit of new module
     * @param university University that the new module belongs to
     * @return True if no empty parameters, false otherwise
     */
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
