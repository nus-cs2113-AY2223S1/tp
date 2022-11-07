package recipeditor.parser;

import org.junit.jupiter.api.Test;
import recipeditor.exception.ExcessFlagsException;
import recipeditor.exception.InvalidFlagException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlagParserTest {

    @Test
    public void existFlags_containRecipeAndCommandFlags_success() {
        String[] args = {"/edit", "1", "-swp", "-t"};
        try {
            FlagType[] flags = FlagParser.getFlags(args);
            assertEquals(flags[0], FlagType.SWAP);
            assertEquals(flags[1], FlagType.TITLE);
        } catch (ExcessFlagsException e) {
            assert false;
        } catch (InvalidFlagException e) {
            assertEquals(e.getMessage(),
                    "Invalid flag in input. Type '/help edit' to see the syntax.");
        }
    }

    @Test
    public void existFlags_containsRecipeFlag_success() {
        String[] args = {"/edit", "1", "", "-d"};
        try {
            FlagType[] flags = FlagParser.getFlags(args);
            assertEquals(flags[0], FlagType.NULL);
            assertEquals(flags[1], FlagType.DESCRIPTION);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void existFlags_containsRecipeFlag_excess() {
        String[] args = {"/edit", "1", "-d", "-s"};
        try {
            FlagParser.getFlags(args);
            assert false;
        } catch (Exception e) {
            assertEquals(new ExcessFlagsException("recipe").getMessage(), e.getMessage());
        }
    }

    @Test
    public void existFlags_containsCommandFlag_success() {
        String[] args = {"/edit", "1", "-del", ""};
        try {
            FlagType[] flags = FlagParser.getFlags(args);
            assertEquals(flags[0], FlagType.DELETE);
            assertEquals(flags[1], FlagType.NULL);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void existFlags_containsCommandFlag_excess() {
        String[] args = {"/edit", "-i", "-chg", "-swp"};
        try {
            FlagParser.getFlags(args);
            assert false;
        } catch (Exception e) {
            assertEquals(new ExcessFlagsException("command").getMessage(), e.getMessage());
        }
    }

    @Test
    public void existFlags_invalidFlags() {
        String[]  args = {"-chg", "-k", "-", "ok"};
        try {
            FlagParser.getFlags(args);
            assert false;
        } catch (Exception e) {
            assertEquals(new InvalidFlagException().getMessage(), e.getMessage());
        }
    }
}
