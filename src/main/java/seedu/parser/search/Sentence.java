package seedu.parser.search;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of {@link Word} objects in order.
 */
public class Sentence {
    private final List<Word> words = new ArrayList<>();
    private final String textString;
    private List<Character> delimiters = new ArrayList<>();

    /**
     * Constructor for the {@link Sentence} object. Splits an input string
     * with various delimiters and populates a list of {@link Word} objects.
     *
     * @param input Text to be split and processed.
     */
    public Sentence(String input) {
        textString = input;
        String[] wordStrings = splitAndExtractDelimiters(input.trim());
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
            delimiters.add(' ');
            bufferText += word + " ";
            words.add(new Word(word));
        }
        textString = bufferText.trim();
    }

    /**
     * Constructor for an empty {@link Sentence} object, but for a list of words.
     */
    public Sentence() {
        textString = "";
    }

    /**
     * Returns a String of words in Sentence.
     *
     * @return String of words in Sentence.
     */
    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        int ind = 0;
        for (Word word : words) {
            outputString.append(word.getAnsiFormatString());
            outputString.append(delimiters.get(ind++));
        }
        return outputString.toString().trim();
    }

    /**
     * Getter method to retrieve words.
     *
     * @return Words.
     */
    public List<Word> getWords() {
        return words;
    }

    /**
     * Returns number of words.
     *
     * @return Number of words.
     */
    public int getWordCount() {
        return words.size();
    }

    /**
     * Keeps track of which delimiter is at which point for reconstruction of string later.
     *
     * @param input input string.
     * @return Split array of input.
     */
    private String[] splitAndExtractDelimiters(String input) {
        String[] wordStrings = input.split("[ /,-]");
        for (char character : textString.toCharArray()) {
            if (character == ' ' || character == '/' || character == ',' || character == '-') {
                delimiters.add(character);
            }
        }
        delimiters.add(' ');
        return wordStrings;
    }
}
