package seedu.parser.search;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of {@link Word} objects in order.
 */
public class Sentence {
    private final List<Word> words = new ArrayList<>();
    private final String textString;

    /**
     * Constructor for the {@link Sentence} object. Splits an input string
     * with various delimiters and populates a list of {@link Word} objects.
     *
     * @param input Text to be split and processed.
     */
    public Sentence(String input) {
        String[] wordStrings = input.trim().split("[ /,-]");
        textString = input;
        for (String word : wordStrings) {
            words.add(new Word(word));
        }
    }

    @Override
    public String toString() {
        String outputString = textString;
        for (Word word : words) {
            if (word.isBold()) {
                outputString = outputString.replace(word.getText(), word.toString());
            }
        }
        return outputString.trim();
    }

    public List<Word> getWords() {
        return words;
    }

}
