package seedu.duke.module;

public class Module {
    private String code;
    private String title;
    private String credit;

    public Module(String code, String title, String credit) {
        assert code.length() > 0 : "Code cannot be empty";
        assert title.length() > 0 : "Title cannot be empty";
        assert credit.length() > 0 : "Credit cannot be empty";

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
