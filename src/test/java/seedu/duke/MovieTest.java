package seedu.duke;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieTest {
    ReviewList storedReviews = new ReviewList();

    @Test
    void addMovie_HarryPotter() {
        Movie harryPotter = new Movie("Harry Potter",5.0,"5 Sep 2022");
        storedReviews.add(harryPotter);
        Movie assertMovie = (Movie) storedReviews.inputs.get(0);
        assertEquals(1,storedReviews.inputs.size());
        assertEquals("Harry Potter",assertMovie.title);
        assertEquals("5.0",assertMovie.rating);
        assertEquals("5 Sep 2022",assertMovie.dateWatched);
    }
}
