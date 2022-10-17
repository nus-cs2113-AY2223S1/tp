package seedu.parser.search;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private final List<Word> words = new ArrayList<>();
    private final String textString;

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
