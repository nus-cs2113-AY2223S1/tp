package seedu.duke;

import java.util.Comparator;

public class SortCommand extends Commands {
    String[] userInput;

    public SortCommand(ReviewList reviews, String[] userInput) {
        super(reviews);
        this.userInput = userInput;
    }

    @Override
    public String execute() {
        String output = "";

        switch (userInput[1]) {
        case "rating":
            this.reviewList.inputs.sort(Comparator.comparing(Media::getRating));
            break;

        case "title":
            this.reviewList.inputs.sort(Comparator.comparing(Media::getTitle));
            break;

        case "genre":
            this.reviewList.inputs.sort(Comparator.comparing(Media::getGenre));
            break;

        case "date":
            this.reviewList.inputs.sort(Comparator.comparing(Media::getDateWatched));
            break;

        default:
            int throwDummyException = Integer.parseInt("dummy");
            break;
        }

        output += "Your list has been sorted by " + userInput[1] + '\n';
        Commands listExecutor = new ListCommand(this.reviewList);
        output += listExecutor.execute();

        return output;
    }
}
