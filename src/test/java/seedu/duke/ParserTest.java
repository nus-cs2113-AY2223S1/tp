package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

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
        
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        printWriter.println("---Here are the reviews that match the keyword---");
        printWriter.println(System.getProperty("line.separator") + "Movies:");
        printWriter.println("1. [Movie]  inception2  Rating:10.0 Genre: thriller Date watched:10-01-2020");
        printWriter.println(System.getProperty("line.separator") + "TV Shows:");
        printWriter.close();

        String expected = expectedStringWriter.toString();
        assertEquals(expected, outContent.toString());
    }

    // @Test
    // void executeFindTestNegative() {
    //     addTestMovie();
        
    //     // sets outContent to capture any would-be console output
    //     // Only AFTER test Movie has been added to test list.
    //     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outContent));
        
    //     String[] findArray = new String[]{"find","in2"};
    //     ps.executeFind(findArray);

    //     StringWriter expectedStringWriter = new StringWriter();
    //     PrintWriter printWriter = new PrintWriter(expectedStringWriter);

    //     printWriter.println("---Here are the reviews that match the keyword---\n");
    //     printWriter.println("Movies:");
    //     printWriter.println("\nTV Shows:");
    //     printWriter.close();

    //     String expected = expectedStringWriter.toString();
    //     assertEquals(expected, outContent.toString());
    // }
}