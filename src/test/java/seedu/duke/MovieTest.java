package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {
    ReviewList storedReviews = new ReviewList();

    @Test
    void addMovie_HarryPotter() {
        Movie harryPotter = new Movie("Harry Potter",5.0, "SciFi","10-01-2020");
        storedReviews.add(harryPotter);
        Movie assertMovie = (Movie) storedReviews.inputs.get(0);
        assertEquals(1,storedReviews.inputs.size());
        assertEquals("Harry Potter",assertMovie.title);
        assertEquals(5.0,assertMovie.rating);
        assertEquals("SciFi", assertMovie.genre);
    }

    @Test
    void clearMovieList() {
        Movie inception = new Movie("Inception",9.0, "SciFi","11-01-2020");
        ReviewList assertList = storedReviews;
        storedReviews.add(inception);
        storedReviews.inputs.clear();

        assertEquals(0, assertList.inputs.size());
    }
}
