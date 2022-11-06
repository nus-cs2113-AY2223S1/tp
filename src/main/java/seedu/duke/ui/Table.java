package seedu.duke.ui;

public abstract class Table {
    String caption;

    public Table(String caption) {
        this.caption = caption;
    }

    protected String addRightPadding(String string, int numberOfSpace) {
        return String.format("%-" + numberOfSpace + "s", string);
    }

}
