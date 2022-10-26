package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {
    ReviewList reviews = new ReviewList();

    @Test
    void execute_addMovieTest() {
        Media harryPotter = new Movie("Harry Potter",5.0, "SciFi","10-01-2020");
        AddCommand addCommandTest = new AddCommand(reviews,harryPotter);
        String output = addCommandTest.execute();
        assertEquals("\tGot it. I've added the following item to the list:\n\t" + harryPotter.toString()
                + "\n\n\tNow you have " + reviews.inputs.size() + " reviews in the list.",output);
        assertEquals(addCommandTest.media, harryPotter);
        assertEquals(addCommandTest.reviewList, reviews);
    }

    @Test
    void execute_addTvShowTest() {
        Media friends = new TvShow("Friends",5.0, "Sitcom","10-01-2022", "Netflix");
        AddCommand addCommandTest = new AddCommand(reviews,friends);
        String output = addCommandTest.execute();
        assertEquals("\tGot it. I've added the following item to the list:\n\t" + friends.toString()
                + "\n\n\tNow you have " + reviews.inputs.size() + " reviews in the list.",output);
        assertEquals(addCommandTest.media, friends);
        assertEquals(addCommandTest.reviewList, reviews);
    }
}