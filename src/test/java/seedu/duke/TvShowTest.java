package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TvShowTest {
    ReviewList storedReviews = new ReviewList();

    @Test
    void addTvShow_Friends() {
        TvShow friends = new TvShow("Friends",5.0, "Sitcom","25 Aug 2022", "Netflix");
        storedReviews.add(friends);
        TvShow assertTvShow = (TvShow) storedReviews.inputs.get(0);
        assertEquals(1,storedReviews.inputs.size());
        assertEquals("Friends", assertTvShow.title);
        assertEquals(5.0, assertTvShow.rating);
        assertEquals("Sitcom", assertTvShow.genre);
        assertEquals("25 Aug 2022", assertTvShow.dateWatched);
        assertEquals("Netflix", assertTvShow.site);
    }
}
