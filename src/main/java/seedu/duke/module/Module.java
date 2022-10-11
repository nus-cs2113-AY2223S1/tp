package seedu.duke.module;

import seedu.duke.university.University;

public class Module {
    private String code;
    private String title;
    private String credit;
    private University university;

    public Module(String code, String title, String credit, University university) {
        assert code.length() > 0 : "Code cannot be empty";
        assert title.length() > 0 : "Title cannot be empty";
        assert credit.length() > 0 : "Credit cannot be empty";
        assert university.getName().length() > 0 : "University name cannot be empty";
        assert university.getCountry().length() > 0 : "University country cannot be empty";

        setCode(code);
        setTitle(title);
        setCredit(credit);
        setUniversity(university);
    }

    @Override
    public String toString() {
        return getCode() + " " + getTitle() + " " + getCredit() + "MCs";
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
}
