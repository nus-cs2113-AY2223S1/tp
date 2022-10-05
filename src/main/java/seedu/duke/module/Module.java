package seedu.duke.module;

public class Module {
    private String code;
    private String title;
    private String credit;

    public Module(String code, String title, String credit) {
        setCode(code);
        setTitle(title);
        setCredit(credit);
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
}
