package recipeditor.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ParserUtilsTest {

    @Test
    void checkForAlphanumeric_notAlphanumericInput_false() {
        String string = "asdfasd fasd fa df asdfasdsfa asdfasdf";
        Assertions.assertFalse(ParserUtils.isTitleNotAlphanumeric(string));
    }

    @Test
    void checkForAlphanumeric_alphanumericInput_true() {
        String string = "asdfasd fa@sd fa df asdfasdsfa asdfasdf";
        Assertions.assertTrue(ParserUtils.isTitleNotAlphanumeric(string));
    }

    @Test
    void checkTitleExceedLimit_exceedLimitTitle_true() {
        String string = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaa";
        Assertions.assertTrue(ParserUtils.doesTitleExceedLimit(string));
    }

    @Test
    void checkTitleExceedLimit_noExceedLimitTitle_false() {
        String string = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Assertions.assertFalse(ParserUtils.doesTitleExceedLimit(string));
    }
}
