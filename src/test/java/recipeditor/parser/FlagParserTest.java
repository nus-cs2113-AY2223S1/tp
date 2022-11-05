package recipeditor.parser;

import org.junit.jupiter.api.Test;
import recipeditor.edit.Change;
import recipeditor.edit.EditModeCommand;
import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlagParserTest {

    @Test
    void countFlags_correctArgs_correctCount() {
        String[] input = { "0", "0", "-i", "-add", "0", "0" };
        FlagType[] output = FlagParser.getFlags(input);
        assertEquals(output.length, 2);
    }

    @Test
    void countFlags_correctArgs_incorrectCount() {
        String[] input = { "0", "0", "-i", "-t", "-add", "0", "0" };
        FlagType[] output = FlagParser.getFlags(input);
        assertEquals(output, null);
    }

    @Test
    void countFlags_incorrectArgs_correctCount() {
        String[] input = { "0", "0", "-i", "-g", "-add", "0", "0" };
        FlagType[] output = FlagParser.getFlags(input);
        assertEquals(output.length, 2);
    }
}
