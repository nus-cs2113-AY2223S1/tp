package seedu.parser.search;

/**
 * Represents a single word in a {@link Sentence}.
 */
public class Word {
    private final String text;
    /**
     * Flag to determine if word should be bolded or not in outputted string.
     */
    private boolean isBold = false;

    /**
     * Constructor for the {@link Word} class.
     *
     * @param word word.
     */
    public Word(String word) {
        text = word.trim();
    }

    /**
     * Setter method to set {@link Word#isBold} flag.
     *
     * @param bool the value to set the isBold flag to.
     */
    public void makeBold(boolean bool) {
        isBold = bool;
    }

    /**
     * Returns a String of the contents of this object.
     *
     * @return String of the contents of this object.
     */
    @Override
    public String toString() {
        if (isBold) {
            return "`" + text + "`";
        } else {
            return text;
        }
    }

    /**
     * Returns a String of the contents of this object in Ansi format.
     *
     * @return String of the contents of this object in Ansi format.
     */
    public String getAnsiFormatString() {
        if (isBold) {
            return String.format("@|bold,cyan %s|@", text);
        } else {
            return text;
        }
    }

    /**
     * Getter method to retrieve text.
     *
     * @return Text.
     */
    public String getText() {
        return text;
    }

    /**
     * Getter method to retrieve bold status.
     *
     * @return True if bold, false otherwise.
     */
    public boolean isBold() {
        return isBold;
    }
}
