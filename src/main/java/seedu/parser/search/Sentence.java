package seedu.parser.search;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private List<Word> words = new ArrayList<>();

    public Sentence(String input) {
        String[] wordStrings = input.trim().split("[ /,-]");
        for (String word : wordStrings) {
            words.add(new Word(word));
        }
    }

    @Override
    public String toString() {
        StringBuilder bufferString = new StringBuilder();
        for (Word word : words) {
            bufferString.append(word).append(" ");
        }
        return bufferString.toString().trim();
    }

    public List<Word> getWords() {
        return words;
    }

}
