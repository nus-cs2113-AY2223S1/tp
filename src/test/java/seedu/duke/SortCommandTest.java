package seedu.duke;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SortCommandTest {
    @Test
    void execute_sortByRating_lowestRatingFirst() {
        ReviewList storedReviews = new ReviewList();
        Movie movie1 = new Movie("Harry Potter",99, "SciFi","10-01-2020");
        Movie movie2 = new Movie("Harry Potter",50, "SciFi","10-01-2020");
        Movie movie3 = new Movie("Harry Potter",1, "SciFi","10-01-2020");

        storedReviews.add(movie1);
        storedReviews.add(movie2);
        storedReviews.add(movie3);

        String[] userInput = {"sort", "rating"};
        SortCommand sort = new SortCommand(storedReviews, userInput);
        sort.execute();

        assertEquals(1.0, storedReviews.inputs.get(0).getRating());
    }

    @Test
    void execute_sortByTitle_earlierLetterFirst() {
        ReviewList storedReviews = new ReviewList();
        Movie movie1 = new Movie("Iron Man",99, "SciFi","10-01-2020");
        Movie movie2 = new Movie("Avengers",99, "SciFi","10-01-2020");
        Movie movie3 = new Movie("Black Panther",99, "SciFi","10-01-2020");

        storedReviews.add(movie1);
        storedReviews.add(movie2);
        storedReviews.add(movie3);

        String[] userInput = {"sort", "title"};
        SortCommand sort = new SortCommand(storedReviews, userInput);
        sort.execute();

        assertEquals("Avengers", storedReviews.inputs.get(0).getTitle());
    }

    @Test
    void execute_sortByGenre_earlierLetterFirst() {
        ReviewList storedReviews = new ReviewList();
        Movie movie1 = new Movie("Avengers",99, "SciFi","10-01-2020");
        Movie movie2 = new Movie("Avengers",99, "Action","10-01-2020");
        Movie movie3 = new Movie("Avengers",99, "Romance","10-01-2020");

        storedReviews.add(movie1);
        storedReviews.add(movie2);
        storedReviews.add(movie3);

        String[] userInput = {"sort", "genre"};
        SortCommand sort = new SortCommand(storedReviews, userInput);
        sort.execute();

        assertEquals("Action", storedReviews.inputs.get(0).getGenre());
    }

    @Test
    void execute_sortByDateWatched_earlierDateFirst() {
        ReviewList storedReviews = new ReviewList();
        Movie movie1 = new Movie("Avengers",99, "Action","01-01-2021");
        Movie movie2 = new Movie("Avengers",99, "Action","15-02-2020");
        Movie movie3 = new Movie("Avengers",99, "Action","31-03-2022");

        storedReviews.add(movie1);
        storedReviews.add(movie2);
        storedReviews.add(movie3);

        String[] userInput = {"sort", "date"};
        SortCommand sort = new SortCommand(storedReviews, userInput);
        sort.execute();

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            Date date = dateFormat.parse("15-02-2020");
            assertEquals(date, storedReviews.inputs.get(0).getDateWatched());
        } catch (ParseException e) {
            fail("Encountered ParseException.");
        }
    }
}