package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.InvalidDatabaseLineException;

public class ParserTest {
    @Test
    void parseDatabaseLine_emptyLine_expectException() {
        String line = "";

        assertThrows(InvalidDatabaseLineException.class, () -> Parser.parseDatabaseLine(line));
    }

    @Test
    void parseDatabaseLine_14Arguments_expectException() {
        String line = "a,b,c,d,e,f,g,h,i,j,k,l,m,n";

        assertThrows(InvalidDatabaseLineException.class, () -> Parser.parseDatabaseLine(line));
    }

    @Test
    void parseDatabaseLine_16Arguments_expectException() {
        String line = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p";

        assertThrows(InvalidDatabaseLineException.class, () -> Parser.parseDatabaseLine(line));
    }
}
