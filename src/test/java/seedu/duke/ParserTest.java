package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.ArrayList;

class ParserTest {
    ReviewList rv = new ReviewList();
    Parser ps = new Parser(rv);

    void addTestMovie() {
        String addString = "add /movieinception2/rating10/date 10-01-2020/genrethriller";
        ps.executeAdd(addString);
    }

    @Test
    void commandWordTest() {
        String testCommand = "Test command";
        String[] expected = new String[]{"Test", "command"};

        ps.getCommandWord(testCommand);

        assertArrayEquals(expected, ps.getCommandWord(testCommand));
    }

    @Test
    void executeFindTestPositive() {
        addTestMovie();
        String[] findArray = new String[]{"find","inc"};
        ArrayList<Media> expected = new ArrayList<>();
        ArrayList<Media> results = new ArrayList<>();

        Movie foundMovie = new Movie("inception2", 10, "thriller", "10-01-2020");
        expected.add(foundMovie);
        results = ps.executeFind(findArray);

        for (int i = 0; i < results.size(); i++) {
            assertEquals(expected.get(i).toString(), results.get(i).toString());
        }
    }

    @Test
    void executeAddTestWrongArguments() {
        String addString = "add /movieinception /rating 10 /date 10-01-2020";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ps.executeAdd(addString);
        
        assertEquals("Ensure that input format and number of arguments is correct.\n"
            .replaceAll("\n", System.getProperty("line.separator")), outContent.toString());
    }
}