package seedu.duke;

import exceptions.DukeException;
import exceptions.IllegalCharacterException;
import exceptions.InvalidCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    void illegalCharacterTest() {
        String addString = "|";
        assertThrows(IllegalCharacterException.class, () -> {
            ps.checkIllegalCharacter(addString);
        });
    }

    @Test
    void emptyCommandFieldsTest() {
        String empty = "";
        assertThrows(InvalidCommandException.class, () -> {
            ps.checkEmptyCommand(empty);
        });
    }

    @Test
    void clearCommandTest() {
        addTestMovie();
        ReviewList result = ps.executeClear();
        assertEquals(0, result.inputs.size());
    }

    @Test
    void parserValidDateTest() {
        String invalidDate = "10-20-30-20";
        String[] dateFields = invalidDate.split("-");
        assertFalse(ps.isValidDate(dateFields));
    }

    @Test
    void deleteCommandTest() {
        ps.executeClear();
        addTestMovie();
        ReviewList currentList = ps.getReviewList();
        assertEquals(1, currentList.inputs.size());

        String deleteString = "delete movie 1";
        String[] splitString = deleteString.split(" ");
        ps.executeDelete(splitString);
        currentList = ps.getReviewList();
        assertEquals(0, currentList.inputs.size());
    }
}