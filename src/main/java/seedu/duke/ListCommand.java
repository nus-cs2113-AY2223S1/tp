package seedu.duke;

public class ListCommand extends Commands {

    public ListCommand(ReviewList reviews) {
        super(reviews);
    }

    @Override
    public String execute() {
        String output = "Here are the reviews in your list:\n";
        for (int i = 0; i < reviewList.inputs.size(); i++) {
            output += (i + 1) + ". " + reviewList.inputs.get(i).toString() + "\n";
        }
        output.strip();
        return output;
    }

}
