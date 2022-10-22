package seedu.duke.command;

public class FavouriteCommand extends Command {
    String favouriteOption;

    public FavouriteCommand(String[] parameters, CommandType commandType) {
        super(parameters, commandType);
        if (parameters[1].equals("view/")) {
            this.favouriteOption = "VIEW";
        } else if (parameters[1].startsWith("add/")) {
            this.universityName = parameters[1].substring(4).replace("_", " ");
            this.favouriteOption = "ADD";
        } else if (parameters[1].startsWith("del/")) {
            this.universityName = parameters[1].substring(4).replace("_", " ");
            this.favouriteOption = "DELETE";
        }

    }

    public String getFavouriteOption() {
        return favouriteOption;
    }
}
