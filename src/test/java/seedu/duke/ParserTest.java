package seedu.duke;

import exceptions.IllegalCharacterException;
import exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ParserTest {
    ReviewList rv = new ReviewList();
    Parser ps = new Parser(rv);

    static final String DELETE_ERROR = "Incomplete or wrongly formatted command, try again.\n"
            .replaceAll("\\n", System.getProperty("line.separator"));
    static final String DELETE_INDEX_ERROR = "Unable to find item for specified type and index\n"
            .replaceAll("\\n", System.getProperty("line.separator"));
    static final String ADD_INCOMPLETE_ARGUMENT_ERROR = "Ensure that input format and number of arguments is correct.\n"
            .replaceAll("\\n", System.getProperty("line.separator"));
    static final String ADD_EMPTY_ARGUMENT_ERROR = "Fields cannot be left empty!\n"
            .replaceAll("\\n", System.getProperty("line.separator"));
    static final String SORT_INCORRECT_ARGUMENT = ("Invalid sort field given. Choose any of the following sorting "
            + "fields: 'rating', 'title', 'genre' or 'date'.\n")
            .replaceAll("\\n", System.getProperty("line.separator"));
    static final String ADD_DUPLICATE = "List already contains item.\n"
            .replaceAll("\\n", System.getProperty("line.separator"));

    @Test
    void addTestMovie() {
        String addString = "add /movieinception2/rating10/date 10-01-2020/genrethriller";
        ps.executeAdd(addString);
    }

    @Test
    void newParserTest() {
        Parser testParser = new Parser(rv);
        assertEquals(rv, testParser.mediaList);
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
    void isFutureDateTest() {
        String date = "25-12-2024";
        assertTrue(ps.isFutureDate(date));
        date = "25-12-2021";
        assertFalse(ps.isFutureDate(date));
    }

    @Test
    void isFutureDateInvalidDateTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String futureDate = "10-10e-2060";
        //should just return true because any future or invalid date is an invalid format
        assertTrue(ps.isFutureDate(futureDate));
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

    @Test
    void getReviewListTest() {
        ReviewList testList = ps.getReviewList();
        assertEquals(rv,testList);
    }

    @Test
    void deleteIncorrectType() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String deleteString = "delete test 1";
        String[] splitString = deleteString.split(" ");
        ps.executeDelete(splitString);
        assertEquals(DELETE_ERROR, outContent.toString());
    }

    @Test
    void deleteNonNumberIndex() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String deleteString = "delete movie e";
        String[] splitString = deleteString.split(" ");
        ps.executeDelete(splitString);
        assertEquals(DELETE_ERROR, outContent.toString());
    }

    @Test
    void deleteOutOfRangeIndex() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String deleteString = "delete movie 0";
        String[] splitString = deleteString.split(" ");
        ps.executeDelete(splitString);
        assertEquals(DELETE_INDEX_ERROR, outContent.toString());
    }

    @Test
    void addTvShow() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String addString = "add /tv game of thrones /rating 5 /date 02-02-2022 /genre fantasy /site hbo";
        ps.executeAdd(addString);
        ReviewList currentList = ps.getReviewList();
        assertEquals(1, currentList.inputs.size());
    }

    @Test
    void addDuplicate() {
        String addString = "add /tv game of thrones /rating 10 /date 02-02-2022 /genre fantasy /site hbo";
        ps.executeAdd(addString);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ps.executeAdd(addString);
        ReviewList currentList = ps.getReviewList();
        assertEquals(1, currentList.inputs.size());
        assertEquals(ADD_DUPLICATE, outContent.toString());
    }

    @Test
    void addTvShowNotEnoughArguments() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String addString = "add /tvShow inception /rating 10 /date 10-01-2020 /genre thriller";
        ps.executeAdd(addString);
        assertEquals(ADD_INCOMPLETE_ARGUMENT_ERROR, outContent.toString());
    }

    @Test
    void addNonNumberForRating() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String addString = "add /tv game of thrones /rating e /date 02-02-2022 /genre fantasy /site hbo";
        ps.executeAdd(addString);
        assertEquals(ADD_INCOMPLETE_ARGUMENT_ERROR, outContent.toString());
    }

    @Test
    void addOverMaxForRating() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String addString = "add /tvShow inception /rating 200 /date 10-01-2020 /genre thriller";
        ps.executeAdd(addString);
        assertEquals(ADD_INCOMPLETE_ARGUMENT_ERROR, outContent.toString());
    }

    @Test
    void addTvShowSlashIncludedInUserInput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String addString = "add /tv game of thro/nes /rating 5 /date 02-02-2022 /genre fantasy /site hbo";
        ps.executeAdd(addString);
        assertEquals(ADD_INCOMPLETE_ARGUMENT_ERROR, outContent.toString());
    }

    @Test
    void addMovieSlashIncludedInUserInput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String addString = "add /movie incept/ion /rating 90 /date 10-01-2020 /genre thriller";
        ps.executeAdd(addString);
        assertEquals(ADD_INCOMPLETE_ARGUMENT_ERROR, outContent.toString());
    }

    @Test
    void addMovieEmptyArgument() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String addString = "add /movie /rating 90 /date 10-01-2020 /genre thriller";
        ps.executeAdd(addString);
        assertEquals(ADD_EMPTY_ARGUMENT_ERROR, outContent.toString());
    }

    @Test
    void executeListTest() {
        //need to ensure an error is not thrown unexpectedly, format correctness is checked in ListCommandTest/Ui Test
        addTestMovie();
        ps.executeList();
    }

    @Test
    void executeSortTest() {
        //just need to ensure an unexpected error is not thrown, correctness is checked in SortCommandTest/Ui Test
        addTestMovie();
        String sortString = "sort title";
        String[] splitString = sortString.split(" ");
        ps.executeSort(splitString);
    }

    @Test
    void executeSortTestIncorrectArgument() {
        addTestMovie();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String sortString = "sort test";
        String[] splitString = sortString.split(" ");
        ps.executeSort(splitString);
        assertEquals(SORT_INCORRECT_ARGUMENT, outContent.toString());
    }
}