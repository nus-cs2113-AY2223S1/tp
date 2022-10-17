package seedu.parser.search;

/**
 * Represents a single word in a {@link SearchQuery}.
 */
public class Word {
    private final String text;
    /** Flag to determine if word should be bolded or not in outputted string. */
    private boolean isBold = false;

    /**
     * Constructor.
     */
    public Word(String word) {
        text = word.trim();
    }

    /**
     * Function to set {@link Word#isBold} flag.
     *
     * @param bool the value to set the isBold flag to.
     */
    public void makeBold(boolean bool) {
        isBold = bool;
    }

    @Override
    public String toString() {
        if (isBold) {
            return "`" + text + "`";
        } else {
            return text;
        }
    }
}
