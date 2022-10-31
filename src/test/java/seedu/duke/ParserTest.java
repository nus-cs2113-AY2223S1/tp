package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
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
}