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

    /**
     * Constructor for the {@link Sentence} object, but for a list of words.
     * with various delimiters and populates a list of {@link Word} objects.
     *
     * @param wordStrings A list of String objects to be converted into a Sentence.
     */
    public Sentence(List<String> wordStrings) {
        String bufferText = "";
        for (String word : wordStrings) {
            bufferText += word + " ";
            words.add(new Word(word));
        }
        textString = bufferText.trim();
    }

    /**
     * Constructor for an empty {@link Sentence} object. but for a list of words.
     */
    public Sentence() {
        textString = "";
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

    public int getWordCount() {
        return words.size();
    }

}
