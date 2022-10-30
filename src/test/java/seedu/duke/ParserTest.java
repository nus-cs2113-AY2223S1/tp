package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ParserTest {
    ReviewList rv = new ReviewList();
    Parser ps = new Parser(rv);

    void addTestMovie() {
        String addString = "add /movie inception2 /rating 10 /date 10-01-2020 /genre thriller";
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
        
        // sets outContent to capture any would-be console output
        // Only AFTER test Movie has been added to test list.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        String[] findArray = new String[]{"find","inc"};
        ps.executeFind(findArray);
        
        String expected = "---Here are the reviews that match the keyword---\n" 
            + "\nMovies:"
            + "\n1. [Movie]  inception2  Rating:10.0 Genre: thriller Date watched:10-01-2020\n" 
            + "\nTV Shows:\n"
            .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        assertEquals(expected, outContent.toString());
    }

    @Test
    void executeFindTestNegative() {
        addTestMovie();
        
        // sets outContent to capture any would-be console output
        // Only AFTER test Movie has been added to test list.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        String[] findArray = new String[]{"find","in2"};
        ps.executeFind(findArray);
        
        String expected = "---Here are the reviews that match the keyword---" 
            + "\n\nMovies:"
            + "\n\nTV Shows:\n"
            .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        assertEquals(expected, outContent.toString());
    }
}