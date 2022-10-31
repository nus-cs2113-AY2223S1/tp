package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TvShowTest {
    ReviewList storedReviews = new ReviewList();

    //@@author redders7
    @Test
    void addTvShow_Friends() {
        TvShow friends = new TvShow("Friends",5.0, "Sitcom","10-01-2022", "Netflix");
        storedReviews.add(friends);
        TvShow assertTvShow = (TvShow) storedReviews.inputs.get(0);
        assertEquals(1,storedReviews.inputs.size());
        assertEquals("Friends", assertTvShow.title);
        assertEquals(5.0, assertTvShow.rating);
        assertEquals("Sitcom", assertTvShow.genre);
        assertEquals("Netflix", assertTvShow.site);
        assertEquals("10-01-2022", assertTvShow.dateString);
        assertEquals("[TV Show] Friends Rating:5.0 Genre:Sitcom Date watched:10-01-2022 Site: Netflix",
                assertTvShow.toString());
    }

    @Test
    void clearTvShowList() {
        TvShow friends = new TvShow("Friends",5.0, "Sitcom","10-01-2022", "Netflix");
        ReviewList assertList = storedReviews;
        storedReviews.add(friends);
        storedReviews.inputs.clear();
        assertEquals(0, assertList.inputs.size());
    }

    @Test
    void createTvShowFileString() {
        TvShow friends = new TvShow("Friends",5.0, "Sitcom","10-01-2022", "Netflix");
        assertEquals("T|N|Friends|5.0|Sitcom|10-01-2022|Netflix", friends.createFileString());
        friends.isFavourite = true;
        assertEquals("T|Y|Friends|5.0|Sitcom|10-01-2022|Netflix", friends.createFileString());
    }
}
