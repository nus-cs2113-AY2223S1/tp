package seedu.duke.parser;

//@@author joshuan98

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.InvalidDatabaseLineException;

public class DatabaseParserTest {
    @Test
    void parseDatabaseLine_validLine_correctLineData() throws InvalidDatabaseLineException {
        String line = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o";

        String[] lineData = DatabaseParser.parseDatabaseLine(line);
        String[] expected = new String[] { "b", "c", "d", "e", "i", "j", "k" };

        assertArrayEquals(expected, lineData);
    }

    @Test
    void parseDatabaseLine_extraComma_correctLineData() throws InvalidDatabaseLineException {
        String line = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,";
        String[] lineData = DatabaseParser.parseDatabaseLine(line);

        String[] expected = new String[] { "b", "c", "d", "e", "i", "j", "k" };

        assertArrayEquals(expected, lineData);
    }

    @Test
    void parseDatabaseLine_missingComma_expectException() {
        String line = "a,b,c,d,e,f,g,h,i,j,k,l,m,n o";

        assertThrows(InvalidDatabaseLineException.class, () -> DatabaseParser.parseDatabaseLine(line));
    }

    @Test
    void parseDatabaseLine_emptyLine_expectException() {
        String line = "";

        assertThrows(InvalidDatabaseLineException.class, () -> DatabaseParser.parseDatabaseLine(line));
    }

    @Test
    void parseDatabaseLine_14Arguments_expectException() {
        String line = "a,b,c,d,e,f,g,h,i,j,k,l,m,n";

        assertThrows(InvalidDatabaseLineException.class, () -> DatabaseParser.parseDatabaseLine(line));
    }

    @Test
    void parseDatabaseLine_16Arguments_expectException() {
        String line = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p";

        assertThrows(InvalidDatabaseLineException.class, () -> DatabaseParser.parseDatabaseLine(line));
    }
}
