package seedu.duke.command;

import seedu.duke.timetable.Lesson;

public class AddCommand extends Command {
    Lesson lesson;
    String comment;

    boolean hasComment;

    public AddCommand(String[] parameters, CommandType commandType, Lesson lesson, String comment) {
        super(parameters, commandType);
        this.lesson = lesson;
        this.universityName = parameters[1].substring(2);
        this.moduleCode = parameters[2].substring(2);

        if (parameters.length == 3) {
            this.hasComment = false;
            this.comment = "";
        }
        else {
            this.hasComment = true;
            this.comment = comment;
        }
    }

    @Override
    public Lesson getLesson() {
        return lesson;
    }

    public String getComment() {
        return comment;
    }

    public boolean hasComment() {
        return hasComment;
    }
}
